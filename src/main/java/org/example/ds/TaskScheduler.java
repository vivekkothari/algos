package org.example.ds;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/** Asked in UBER DSA round 2. */
@SuppressWarnings("unchecked")
public class TaskScheduler implements AutoCloseable {

  @Override
  public void close() {
    closed = true;
    poller.interrupt();
    try {
      poller.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void awaitTermination() {
    try {
      poller.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }

  record Task<T>(Callable<T> task, CompletableFuture<Object> promise, long scheduledTime)
      implements Comparable<Task<T>> {
    @Override
    public int compareTo(Task<T> other) {
      return Long.compare(this.scheduledTime, other.scheduledTime);
    }
  }

  private final PriorityQueue<Task<Object>> tasks = new PriorityQueue<>();
  private final Thread poller = new Thread(this::run);
  private final Object mutex = new Object();
  private volatile boolean closed;

  private void run() {
    while (!closed) {
      try {
        Task<Object> task = null;
        synchronized (mutex) {
          while (tasks.isEmpty()
              || (task = tasks.peek()).scheduledTime > System.currentTimeMillis()) {
            if (tasks.isEmpty()) {
              mutex.wait();
            } else {
              long delay = task.scheduledTime - System.currentTimeMillis();
              if (delay > 0) {
                mutex.wait(delay);
              }
            }
          }
          task = tasks.poll(); // Remove the task to execute
        }
        try {
          task.promise.complete(task.task.call());
        } catch (Exception e) {
          task.promise.completeExceptionally(e);
        }
      } catch (InterruptedException e) {
        if (closed) {
          Thread.currentThread().interrupt();
          break;
        }
      }
    }
  }

  <T> CompletableFuture<T> scheduleTask(Callable<T> task, Duration delay) {
    CompletableFuture<Object> result = new CompletableFuture<>();
    synchronized (mutex) {
      log("Scheduling task: " + task);
      tasks.offer(
          new Task<>((Callable<Object>) task, result, Instant.now().plus(delay).toEpochMilli()));
      mutex.notifyAll(); // Notify waiting threads about the new task
    }
    return (CompletableFuture<T>) result;
  }

  public static void main(String[] args) {
    TaskScheduler scheduler = new TaskScheduler();
    scheduler.poller.start();
    var future10 = scheduler.scheduleTask(() -> "Hello world 10", Duration.ofSeconds(10));
    future10.thenAccept(TaskScheduler::log);
    var future5 = scheduler.scheduleTask(() -> "Hello world 5", Duration.ofSeconds(5));
    future5.thenAccept(TaskScheduler::log);
    var future15 = scheduler.scheduleTask(() -> "Hello world 15", Duration.ofSeconds(15));
    future15.thenAccept(TaskScheduler::log);
    var futureErr =
        scheduler.scheduleTask(
            () -> {
              throw new RuntimeException("failing");
            },
            Duration.ofSeconds(15));
    futureErr.thenAccept(_ -> log("This will not be printed"));
    futureErr.exceptionally(
        ex -> {
          log(ex.toString());
          return "";
        });
    scheduler.awaitTermination();
  }

  private static void log(String message) {
    System.out.printf(
        "%s: %s%n",
        Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toString().substring(11, 19),
        message.replaceAll("\n", " "));
  }
}
