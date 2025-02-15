package org.example.llmquiz;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Q8: Implement a Fixed-Size Thread Pool from scratch (like Executors.newFixedThreadPool(n)) Your
 * task is to implement a fixed-size thread pool that:
 *
 * <p>Has a fixed number of worker threads (n) that process tasks. Accepts new tasks via
 * execute(Runnable task). Worker threads should run tasks in a queue (BlockingQueue<Runnable>).
 * Gracefully shuts down when requested.
 *
 * <pre>
 *   class FixedThreadPool {
 *     public FixedThreadPool(int numThreads) {
 *         // Implement this
 *     }
 *
 *     public void execute(Runnable task) {
 *         // Implement this
 *     }
 *
 *     public void shutdown() {
 *         // Implement this
 *     }
 * }
 * </pre>
 */
class FixedThreadPool {

  private final WorkerThread[] workers;
  private final BlockingQueue<Runnable> taskQueue;
  private volatile boolean isShutdown = false;

  public FixedThreadPool(int numThreads) {
    this.taskQueue = new LinkedBlockingQueue<>();
    this.workers = new WorkerThread[numThreads];

    // Create and start worker threads
    for (var i = 0; i < numThreads; i++) {
      workers[i] = new WorkerThread();
      workers[i].start();
    }
  }

  public void execute(Runnable task) {
    if (!isShutdown) {
      try {
        taskQueue.put(task); // Add task to queue (blocking if full)
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  public void shutdown() {
    isShutdown = true;
    for (var worker : workers) {
      worker.interrupt(); // Interrupt workers to stop waiting for tasks
    }
  }

  private class WorkerThread extends Thread {
    public void run() {
      while (!isShutdown) {
        try {
          var task = taskQueue.take(); // Retrieve task from queue
          task.run(); // Execute task
        } catch (InterruptedException e) {
          if (isShutdown) break; // Exit loop if shutdown
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  public static void main(String[] args) {
    var pool = new FixedThreadPool(3);
    pool.execute(
        () -> {
          System.out.println("Hello world1");
          sleep(1);
        });
    pool.execute(
        () -> {
          System.out.println("Hello world2");
          sleep(2);
        });
    pool.execute(
        () -> {
          System.out.println("Hello world3");
          sleep(3);
        });
    pool.execute(
        () -> {
          System.out.println("Hello world4");
          sleep(4);
        });
    pool.execute(
        () -> {
          System.out.println("Hello world5");
          sleep(5);
        });
    pool.execute(
        () -> {
          System.out.println("Hello world6");
          sleep(6);
        });
    pool.shutdown();
  }

  static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
