package org.example.ds;

import java.util.Stack;

class MinStack {

  private final Stack<Integer> stack = new Stack<>();
  private final Stack<Integer> minStack = new Stack<>();

  public void push(int val) {
    stack.push(val);
    if (minStack.isEmpty() || val <= minStack.peek()) {
      minStack.push(val);
    }
  }

  public void pop() {
    if (stack.isEmpty()) return;
    int top = stack.pop();
    if (top == minStack.peek()) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(1);
    minStack.push(2);
    minStack.push(0);
    System.out.println(minStack.getMin()); // return 0
    minStack.pop();
    System.out.println(minStack.top()); // return 2
    System.out.println(minStack.getMin()); // return 1
  }
}
