package org.example.llmquiz;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 *
 * <pre>
 * Problem 4: Implement a Queue Using Two Stacks
 * A queue follows FIFO (First-In-First-Out), while a stack follows LIFO (Last-In-First-Out).
 * Your task is to implement a Queue using only two stacks.
 *
 * ✅ Implement a Queue with the following methods:
 * void enqueue(int x) → Inserts an element into the queue.
 * int dequeue() → Removes and returns the front element.
 * int front() → Returns the front element without removing it.
 * boolean isEmpty() → Returns true if the queue is empty.
 * </pre>
 */
class MyQueueUsingTwoStacks {

  private final Deque<Integer> inStack = new ArrayDeque<>();
  private final Deque<Integer> outStack = new ArrayDeque<>();

  void enqueue(int x) {
    inStack.push(x);
  }

  int dequeue() {
    if (!outStack.isEmpty()) {
      return outStack.pop();
    }
    while (!inStack.isEmpty()) {
      outStack.push(inStack.pop());
    }
    return outStack.pop();
  }

  int front() {
    if (outStack.isEmpty()) {
      while (!inStack.isEmpty()) {
        outStack.push(inStack.pop()); // Transfer elements if needed
      }
    }
    return outStack.isEmpty() ? -1 : outStack.peek();
  }

  boolean isEmpty() {
    return outStack.isEmpty() && inStack.isEmpty();
  }

  public static void main(String[] args) {
    var queue = new MyQueueUsingTwoStacks();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.isEmpty());
  }
}
