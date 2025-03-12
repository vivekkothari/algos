package org.example.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOneLinkedList {
  private static class Node {
    int freq;
    Node next, prev;
    Set<String> keys = new HashSet<>();

    Node(int freq) {
      this.freq = freq;
    }

    @Override
    public String toString() {
      return "{"
          + "freq="
          + freq
          + ", next="
          + (next == null ? "null" : next.freq)
          + ", prev="
          + (prev == null ? "null" : prev.freq)
          + ", keys="
          + keys
          + '}';
    }
  }

  private final Node head, tail;
  private final Map<String, Node> keyToNodeMap; // Maps key -> node
  private final Map<Integer, Node> countToNodeMap; // Maps frequency -> node

  public AllOneLinkedList() {
    head = new Node(0);
    tail = new Node(0);
    head.next = tail;
    tail.prev = head;
    keyToNodeMap = new HashMap<>();
    countToNodeMap = new HashMap<>();
  }

  public void inc(String key) {
    Node curNode = keyToNodeMap.get(key);
    int newFreq = curNode == null ? 1 : curNode.freq + 1;

    Node newNode = countToNodeMap.get(newFreq);
    if (newNode == null) {
      newNode = insertAfter(curNode == null ? head : curNode, newFreq);
    }
    newNode.keys.add(key);
    keyToNodeMap.put(key, newNode);
    if (curNode != null) {
      removeKeyFromBucket(curNode, key);
    }
  }

  public void dec(String key) {
    Node currNode = keyToNodeMap.get(key);
    if (currNode == null) {
      return;
    }
    int newFreq = currNode.freq - 1;
    keyToNodeMap.remove(key);
    if (newFreq > 0) {
      Node newNode = countToNodeMap.get(newFreq);
      if (newNode == null) {
        newNode = insertAfter(currNode.prev, newFreq);
      }
      newNode.keys.add(key);
      keyToNodeMap.put(key, newNode);
    }
    removeKeyFromBucket(currNode, key);
  }

  public String getMaxKey() {
    return tail.prev == head ? "" : tail.prev.keys.iterator().next();
  }

  public String getMinKey() {
    return head.next == tail ? "" : head.next.keys.iterator().next();
  }

  private void removeKeyFromBucket(Node curNode, String key) {
    curNode.keys.remove(key);
    if (curNode.keys.isEmpty()) {
      countToNodeMap.remove(curNode.freq);
      curNode.prev.next = curNode.next;
      curNode.next.prev = curNode.prev;
    }
  }

  private Node insertAfter(Node prevNode, int newFreq) {
    Node newNode = new Node(newFreq);
    countToNodeMap.put(newFreq, newNode);
    newNode.prev = prevNode;
    newNode.next = prevNode.next;
    prevNode.next.prev = newNode;
    prevNode.next = newNode;
    return newNode;
  }

  public static void main(String[] args) {
    AllOneLinkedList allOne = new AllOneLinkedList();

    allOne.inc("a");
    allOne.inc("c");
    allOne.inc("b");
    allOne.inc("c");

    System.out.println("Max key: " + allOne.getMaxKey()); // Should be "banana"
    System.out.println("Min key: " + allOne.getMinKey()); // Should be "apple"

    allOne.dec("banana");
    allOne.dec("banana");

    System.out.println("Max key: " + allOne.getMaxKey()); // Should be "apple"
    System.out.println("Min key: " + allOne.getMinKey()); // Should be "banana"
  }
}
