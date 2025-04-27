package org.example.ds;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  private final Map<Integer, Node> cache = new HashMap<>();
  private final Node head = new Node(-1, -1);
  private final Node tail = new Node(-1, -1);
  private final int capacity;
  private int size;

  public LRUCache(int capacity) {
    head.next = tail;
    tail.prev = head;
    this.capacity = capacity;
  }

  private void insertHead(Node node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
    size++;
  }

  private void removeTail() {
    Node tailNode = tail.prev;
    tailNode.prev.next = tail;
    tail.prev = tailNode.prev;
    cache.remove(tailNode.key);
    size--;
  }

  private void remove(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
    size--;
  }

  public int get(int key) {
    if (cache.containsKey(key)) {
      Node node = cache.get(key);
      remove(node);
      insertHead(node);
      return node.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (cache.containsKey(key)) {
      Node node = cache.get(key);
      remove(node);
      node.value = value;
      insertHead(node);
    } else {
      if (size == capacity) {
        removeTail();
      }
      Node newNode = new Node(key, value);
      cache.put(key, newNode);
      insertHead(newNode);
    }
  }

  private static class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    LRUCache lruCache = new LRUCache(2);
    lruCache.put(2, 1);
    lruCache.put(2, 2);
    System.out.println(lruCache.get(2));
    lruCache.put(1, 1);
    lruCache.put(4, 1);
    System.out.println(lruCache.get(2));
  }
}
