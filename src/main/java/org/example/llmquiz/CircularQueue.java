package org.example.llmquiz;

/**
 *
 *
 * <pre>
 * Problem 1: Implement a Circular Queue
 * A circular queue is a queue that uses a fixed-size array in a circular manner to efficiently utilize space.
 *
 * Your Task:
 * Implement a class CircularQueue with the following methods:
 *
 * CircularQueue(int k): Initialize the queue with size k.
 * boolean enqueue(int value): Add an element at the rear. Return true if successful.
 * boolean dequeue(): Remove an element from the front. Return true if successful.
 * int front(): Get the front element. Return -1 if empty.
 * int rear(): Get the rear element. Return -1 if empty.
 * boolean isEmpty(): Return true if the queue is empty.
 * boolean isFull(): Return true if the queue is full.
 *
 * Constraints:
 *
 * Implement it using an array (not LinkedList).
 * Achieve O(1) time complexity for all operations.
 * Let me know once you've implemented it! 🚀
 * </pre>
 */
class CircularQueue<T> {
  private final boolean blockOnFull;
  private final Object[] data;
  private final int capacity;
  private int size;
  private int startIndex;
  private int endIndex;

  CircularQueue(int capacity) {
    this(capacity, false);
  }

  CircularQueue(int capacity, boolean blockOnFull) {
    this.blockOnFull = blockOnFull;
    this.data = new Object[capacity];
    this.capacity = capacity;
    size = 0;
    startIndex = 0;
    endIndex = 0;
  }

  boolean enqueue(T value) {
    if (blockOnFull && size >= capacity) return false;
    data[endIndex] = value;
    endIndex = (endIndex + 1) % capacity;
    size++;
    return true;
  }

  boolean dequeue() {
    if (size == 0) return false;
    data[startIndex] = null;
    startIndex = (startIndex + 1) % capacity;
    size--;
    return true;
  }

  T front() {
    if (size == 0) return null;
    //noinspection unchecked
    return (T) data[startIndex];
  }

  T rear() {
    if (size == 0) return null;
    //noinspection unchecked
    return (T) data[(endIndex - 1 + capacity) % capacity];
  }

  boolean isEmpty() {
    return size == 0;
  }

  boolean isFull() {
    return size == capacity;
  }

  public static void main(String[] args) {
    var queue = new CircularQueue<Integer>(3, false);
    System.out.println(queue.enqueue(1)); // true
    System.out.println(queue.enqueue(2)); // true
    System.out.println(queue.enqueue(3)); // true
    System.out.println(queue.enqueue(4)); // false (queue is full)
    System.out.println(queue.rear()); // 3
    System.out.println(queue.isFull()); // 3
    System.out.println(queue.dequeue()); // true
    System.out.println(queue.enqueue(4)); // false (queue is full)
    System.out.println(queue.front()); // 2
  }
}
