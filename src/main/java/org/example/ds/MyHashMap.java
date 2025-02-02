package org.example.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

class MyHashMap<K, V> {

  private static final int INITIAL_CAPACITY = 16;
  private static final float LOAD_FACTOR = 0.75f;

  private List<Node> table;
  private int size;

  public MyHashMap() {
    table = new ArrayList<>(INITIAL_CAPACITY);
    IntStream.range(0, INITIAL_CAPACITY).forEach(i -> table.add(null));
    size = 0;
  }

  public void put(K k, V v) {
    var index = hash(k);
    var head = getNode(index);

    while (head != null) {
      if (head.key.equals(k)) {
        head.value = v; // Update value if key exists
        return;
      }
      head = head.next;
    }

    // Insert at the head of the linked list
    var newNode = new Node(k, v);
    newNode.next = getNode(index);
    table.set(index, newNode);
    size++;

    // Resize if load factor exceeds threshold
    if ((1.0 * size) / table.size() > LOAD_FACTOR) {
      resize();
    }
  }

  private Node getNode(int index) {
    return table.size() <= index ? null : table.get(index);
  }

  public void remove(K key) {
    int index = hash(key);
    var head = getNode(index);
    Node prev = null;

    while (head != null) {
      if (head.key.equals(key)) {
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

  public V get(K key) {
    var index = hash(key);
    var head = getNode(index);

    while (head != null) {
      if (head.key.equals(key)) {
        return head.value;
      }
      head = head.next;
    }
    return null; // Key not found
  }

  private class Node {
    K key;
    V value;
    Node next; // For chaining

    Node(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
  }

  private int hash(K key) {
    return Math.abs(Objects.hashCode(key)) % (table.isEmpty() ? 1 : table.size());
  }

  private void resize() {
    var oldBuckets = table;
    table = new ArrayList<>(oldBuckets.size() * 2);
    IntStream.range(0, oldBuckets.size() * 2).forEach(i -> table.add(null));
    size = 0;

    for (var headNode : oldBuckets) {
      while (headNode != null) {
        put(headNode.key, headNode.value);
        headNode = headNode.next;
      }
    }
  }

  public int size() {
    return size;
  }

  public static void main(String[] args) {
    var map = new MyHashMap<String, Integer>();
    map.put("a", 2);
    System.out.println(map.get("a"));
    map.put("b", 3);
    System.out.println(map.get("b"));
    map.put("a", 5);
    System.out.println(map.get("a"));
  }
}
