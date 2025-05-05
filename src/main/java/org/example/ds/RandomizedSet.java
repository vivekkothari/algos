package org.example.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * https://www.youtube.com/watch?v=46dZH7LDbf8
 * https://leetcode.com/problems/insert-delete-getrandom-o1/submissions/1528528444/
 */
class RandomizedSet {

  // stores number to its last index.
  private final Map<Integer, Integer> map = new HashMap<>();
  private final List<Integer> list = new ArrayList<>();

  public boolean insert(int n) {
    if (map.containsKey(n)) {
      return false;
    }
    list.add(n);
    map.put(n, list.size() - 1);
    return true;
  }

  public boolean remove(int n) {
    if (!map.containsKey(n)) {
      return false;
    }
    // get pos of to be removed element from the map.
    var i = map.get(n);
    // get the actual last from the list.
    var temp = list.getLast();
    // move the to be removed element at the last.
    list.set(i, temp);
    // update previous last element index in map
    map.put(temp, i);
    // now remove the element from list and map.
    list.removeLast();
    map.remove(n);
    return true;
  }

  public int getRandom() {
    return list.get(new Random().nextInt(list.size()));
  }

  public static void main(String[] args) {
    var store = new RandomizedSet();
    store.insert(0);
    store.insert(10);
    store.insert(10);
    store.insert(10);
    store.insert(15);
    System.out.println(store.getRandom());
    store.remove(10);
    store.remove(15);
    System.out.println(store.getRandom());
  }
}
