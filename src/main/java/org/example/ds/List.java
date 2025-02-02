package org.example.ds;

interface List<T> {

  boolean addLast(T item);

  boolean addFirst(T item);

  T removeLast();

  T removeFirst();

  boolean isEmpty();

  void reverse();

  int size();

  T findMid();
}

class LinkedList<T> implements List<T> {

  static final class Node<T> {
    private final T data;
    private Node<T> next;
    private Node<T> prev;

    Node(T data, Node<T> next, Node<T> prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }

    Node(T data, Node<T> next) {
      this(data, next, null);
    }

    private Node(T data) {
      this(data, null);
    }

    @Override
    public String toString() {
      return (prev == null ? null : prev.data)
          + " <-> "
          + data.toString()
          + " <-> "
          + (next == null ? null : next.data);
    }
  }

  private Node<T> head, tail;
  private int size;

  @Override
  public boolean addLast(T item) {
    var node = new Node<>(item);
    if (head == null && tail == null) {
      head = node;
    } else {
      // current tail becomes prev
      node.prev = tail;
      tail.next = node;
    }
    tail = node;
    size++;
    return true;
  }

  @Override
  public boolean addFirst(T item) {
    var node = new Node<>(item);
    if (head == null && tail == null) {
      head = node;
      tail = node;
    } else {
      node.next = head;
      head.prev = node;
      head = node;
    }
    size++;
    return true;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }
    size--;
    var pop = tail;
    tail = tail.prev;
    if (tail != null) {
      tail.next = null;
    } else {
      head = null;
    }
    pop.prev = null;
    return pop.data;
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }
    size--;
    var pop = head;
    head = head.next;
    if (head != null) {
      head.prev = null; // Ensure the new head has no previous reference
    } else {
      tail = null; // If the list is now empty, set tail to null
    }
    pop.next = null; // Clear the reference in the removed node
    return pop.data;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void reverse() {
    if (head == null || head.next == null) {
      return; // No need to reverse if the list is empty or has only one element.
    }

    var current = head;
    Node<T> temp;

    // Traverse the list and swap next and prev for each node
    while (current != null) {
      temp = current.prev;
      current.prev = current.next;
      current.next = temp;
      current = current.prev; // Move to the next node, which is now in the previous position
    }

    // Swap head and tail pointers
    temp = head;
    head = tail;
    tail = temp;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T findMid() {
    if (isEmpty()) {
      return null;
    }
    var mid = size / 2;
    var curr = head;
    for (int i = 0; i < mid; i++) {
      curr = curr.next;
    }
    return curr.data;
  }

  @Override
  public String toString() {
    var current = head;
    var sb = new StringBuilder();
    while (current != null) {
      sb.append(current.data).append(" -> ");
      current = current.next;
    }
    sb.append("null");
    return sb.toString();
  }

  private String printReverse() {
    var current = tail;
    var sb = new StringBuilder();
    while (current != null) {
      sb.append(current.data).append(" <- ");
      current = current.prev;
    }
    sb.append("null");
    return sb.toString();
  }

  public static void main(String[] args) {
    var list = new LinkedList<Integer>();
    list.addLast(5);
    list.addLast(6);
    list.addLast(7);
    list.addLast(8);
    list.addLast(9);
    list.addFirst(4);
    list.addFirst(3);
    list.addFirst(2);
    list.addFirst(1);
    list.addFirst(0);
    System.out.println("Size: " + list.size());
    System.out.println(list);
    System.out.println(list.printReverse());
    System.out.println("=================");
    System.out.println("Mid: " + list.findMid());
    list.reverse();
    System.out.println(list);
    System.out.println("Mid: " + list.findMid());
    System.out.println(list.printReverse());
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    list.removeLast();
    System.out.println("After all removal");
    System.out.println(list);
    System.out.println(list.printReverse());
  }
}
