package org.example.llmquiz;

import java.util.ArrayDeque;
import java.util.Deque;

class MinStackUsingDeque<T extends Comparable<T>> {
  private final Deque<T> stack;
  private final Deque<T> minStack;

  MinStackUsingDeque() {
    this.stack = new ArrayDeque<>();
    this.minStack = new ArrayDeque<>();
  }

  void push(T value) {
    stack.push(value);
    // Always push onto minStack if it's empty or the value is <= current min
    if (minStack.isEmpty() || value.compareTo(minStack.peek()) <= 0) {
      minStack.push(value);
    }
  }

  T getMin() {
    return minStack.peek();
  }

  T pop() {
    if (stack.isEmpty()) return null;
    var pop = stack.pop();
    if (pop.equals(minStack.peek())) {
      minStack.pop();
    }
    return pop;
  }

  T top() {
    return stack.peek();
  }

  public static void main(String[] args) {
    MinStackUsingDeque<Integer> minStack = new MinStackUsingDeque<>();

    minStack.push(5);
    minStack.push(2);
    minStack.push(3);
    minStack.push(1);

    System.out.println(minStack.getMin()); // Output: 1
    minStack.pop();
    System.out.println(minStack.getMin()); // Output: 2
    minStack.pop();
    System.out.println(minStack.getMin()); // Output: 2
    minStack.pop();
    System.out.println(minStack.getMin()); // Output: 5
  }
}
