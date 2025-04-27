package org.example.ds;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/** Asked in UBER DSA round 2. */
public class TaskScheduler implements AutoCloseable {

  @Override
  public void close() throws Exception {
    closed = true;
    poller.interrupt();
    poller.join();
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
    //noinspection unchecked
    return (CompletableFuture<T>) result;
  }

  public static void main(String[] args) {
    TaskScheduler scheduler = new TaskScheduler();
    scheduler.poller.start();
    CompletableFuture<String> future =
        scheduler.scheduleTask(() -> "Hello world", Duration.ofSeconds(10));
    future.thenAccept(TaskScheduler::log);
    CompletableFuture<String> future1 =
        scheduler.scheduleTask(() -> "Hello world earlier", Duration.ofSeconds(5));
    future1.thenAccept(TaskScheduler::log);
    scheduler.awaitTermination();
  }

  private static void log(String message) {
    System.out.printf(
        "%s: %s%n",
        Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toString().substring(11, 19),
        message.replaceAll("\n", " "));
  }
}
