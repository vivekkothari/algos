package org.example.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/all-oone-data-structure/description/
 *
 * <pre>
 *   Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 *
 * Constraints:
 *
 * 1 <= key.length <= 10
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 * </pre>
 */
class AllOne {

  public static class Node {
    String key = "";
    int count = 0;
    int pos = 0;

    Node(String key, int count, int pos) {
      this.key = key;
      this.count = count;
      this.pos = pos;
    }
  }

  private final List<Node> nodes = new ArrayList<>();
  private final Map<String, Node> map = new HashMap<>();

  public AllOne() {}

  public void inc(String key) {
    if (map.containsKey(key)) {
      Node temp = map.get(key);
      temp.count++;
      int pos = temp.pos;
      while (--pos >= 0 && nodes.get(pos).count < temp.count) {
        nodes.get(pos).pos = pos + 1;
        Collections.swap(nodes, pos + 1, pos);
        temp.pos = pos;
      }
    } else {
      Node temp = new Node(key, 1, nodes.size());
      nodes.add(temp);
      map.put(key, temp);
    }
  }

  public void dec(String key) {
    Node temp = map.get(key);
    temp.count--;
    int pos = temp.pos;

    while (++pos < nodes.size() && nodes.get(pos).count > temp.count) {
      nodes.get(pos).pos = pos - 1;
      Collections.swap(nodes, pos, pos - 1);
      nodes.get(pos).pos = pos;
    }

    if (temp.count == 0) {
      nodes.remove(pos - 1);
      map.remove(key);
    }
  }

  public String getMaxKey() {
    return nodes.isEmpty() ? "" : nodes.getFirst().key;
  }

  public String getMinKey() {
    return nodes.isEmpty() ? "" : nodes.getLast().key;
  }

  public static void main(String[] args) {
    AllOne allOne = new AllOne();

    allOne.inc("a");
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
