package org.example.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

class MyHashSet {

  private static final int INITIAL_CAPACITY = 16;
  private static final float LOAD_FACTOR = 0.75f;

  private List<Node> table;
  private int size;

  public MyHashSet() {
    table = new ArrayList<>(INITIAL_CAPACITY);
    IntStream.range(0, INITIAL_CAPACITY).forEach(i -> table.add(null));
    size = 0;
  }

  public void add(int k) {
    var index = hash(k);
    var head = getNode(index);

    while (head != null) {
      if (head.key == k) {
        head.value = true; // Update value if key exists
        return;
      }
      head = head.next;
    }

    // Insert at the head of the linked list
    var newNode = new Node(k, true);
    newNode.next = getNode(index);
    table.set(index, newNode);
    size++;

    // Resize if load factor exceeds threshold
    if ((1.0 * size) / table.size() > LOAD_FACTOR) {
      resize();
    }
  }

  public void remove(int key) {
    int index = hash(key);
    var head = getNode(index);
    Node prev = null;

    while (head != null) {
      if (head.key == key) {
        if (prev != null) {
          prev.next = head.next;
        } else {
          table.set(index, head.next);
        }
        size--;
      }
      prev = head;
      head = head.next;
    }
  }

  public boolean contains(int key) {
    var index = hash(key);
    var head = getNode(index);

    while (head != null) {
      if (head.key == key) {
        return true;
      }
      head = head.next;
    }
    return false; // Key not found
  }

  private void resize() {
    var oldBuckets = table;
    table = new ArrayList<>(oldBuckets.size() * 2);
    size = 0;

    for (var headNode : oldBuckets) {
      while (headNode != null) {
        add(headNode.key);
        headNode = headNode.next;
      }
    }
  }

  private class Node {
    int key;
    boolean value;
    Node next; // For chaining

    Node(int key, boolean value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
  }

  private int hash(int key) {
    return Math.abs(Objects.hashCode(key)) % (table.isEmpty() ? 1 : table.size());
  }

  private Node getNode(int index) {
    return table.size() <= index ? null : table.get(index);
  }
}
