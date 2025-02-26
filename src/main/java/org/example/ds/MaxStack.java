package org.example.ds;

import java.util.Comparator;
import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;

/**
 * Asked in LinkedIn. https://www.youtube.com/watch?v=SyDmmNQFW_I
 * https://leetcode.com/problems/max-stack/
 */
public interface MaxStack<T extends Comparable<T>> {

  void push(T elem);

  T pop();

  T peek();

  T peekMax();

  T popMax();
}

class MaxStackImpl<T extends Comparable<T>> implements MaxStack<T> {

  private class Node implements Comparable<Node> {
    final T value;
    Node next;
    Node prev;

    private Node(T value) {
      this.value = value;
    }

    @Override
    public int compareTo(@NotNull Node o) {
      return o.value.compareTo(value);
    }
  }

  private Node head, tail;
  private final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.reverseOrder());

  @Override
  public void push(T elem) {
    var node = new Node(elem);
    if (head == null && tail == null) {
      head = tail = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    queue.offer(node);
  }

  @Override
  public T pop() {
    var curr = tail;
    tail = tail.prev;
    if (tail != null) {
      tail.next = null;
    } else {
      head = null;
    }
    queue.remove(curr);
    return curr.value;
  }

  @Override
  public T peek() {
    if (tail != null) {
      return tail.value;
    }
    return null;
  }

  @Override
  public T peekMax() {
    var peek = queue.peek();
    return peek != null ? peek.value : null;
  }

  @Override
  public T popMax() {
    var peek = queue.poll();
    if (peek != null) {
      var prev = peek.prev;
      var next = peek.next;
      if (prev != null) {
        prev.next = next;
      } else {
        head = next;
      }
      if (next != null) {
        next.prev = prev; // Ensure the new head has no previous reference
      } else {
        tail = null; // If the list is now empty, set tail to null
      }
      peek.next = null; // Clear the reference in the removed node
    }
    return peek == null ? null : peek.value;
  }

  public static void main(String[] args) {
    MaxStack<Integer> stack = new MaxStackImpl<>();
    stack.push(1);
    stack.push(3);
    stack.push(2);
    System.out.println(stack.peek());
    System.out.println(stack.peekMax());
    System.out.println(stack.popMax());
    System.out.println("=============");
    stack.push(5);
    System.out.println(stack.peek());
    System.out.println(stack.peekMax());
    stack.push(7);
    System.out.println("=============");
    System.out.println(stack.pop());
    System.out.println(stack.peekMax());
  }
}
