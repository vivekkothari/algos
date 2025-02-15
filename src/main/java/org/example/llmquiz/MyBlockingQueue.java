package org.example.llmquiz;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a BlockingQueue<T> with enqueue() and dequeue() methods using wait() and notifyAll().
 *
 * <pre>
 *   class BlockingQueue<T> {
 *     private final Queue<T> queue = new LinkedList<>();
 *     private final int capacity;
 *
 *     public BlockingQueue(int capacity) {
 *         this.capacity = capacity;
 *     }
 *
 *     public synchronized void enqueue(T item) throws InterruptedException {
 *         // Implement this
 *     }
 *
 *     public synchronized T dequeue() throws InterruptedException {
 *         // Implement this
 *     }
 * }
 * </pre>
 */
class MyBlockingQueue<T> {
  private final Queue<T> queue = new LinkedList<>();
  private final int capacity;

  public MyBlockingQueue(int capacity) {
    this.capacity = capacity;
  }

  public synchronized void enqueue(T item) throws InterruptedException {
    while (queue.size() >= capacity) {
      wait();
    }
    queue.offer(item);
    notifyAll();
  }

  public synchronized T dequeue() throws InterruptedException {
    while (queue.isEmpty()) {
      wait();
    }
    var poll = queue.poll();
    notifyAll();
    return poll;
  }

  public static void main(String[] args) throws InterruptedException {
    var queue = new MyBlockingQueue<Integer>(1);
    var t1 =
        new Thread(
            () -> {
              try {
                queue.enqueue(1);
                System.out.println("Added 1");
                queue.enqueue(2);
                System.out.println("Added 2");
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            });
    t1.start();
    var t2 =
        new Thread(
            () -> {
              try {
                System.out.println(queue.dequeue());
                System.out.println(queue.dequeue());
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            });
    //    t2.start();
    t1.join();
    t2.join();
  }
}
