package org.example.introtoalgos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Every operation is O(1). */
class DequeUsingTwoLists {

  private final List<Integer> frontList;
  private final List<Integer> backList;

  DequeUsingTwoLists() {
    frontList = new ArrayList<>();
    backList = new ArrayList<>();
  }

  void addFirst(int i) {
    // Push to frontList (reverse order)
    frontList.addLast(i);
  }

  void addLast(int i) {
    // Push to backList (normal order)
    backList.addLast(i);
  }

  int size() {
    return frontList.size() + backList.size();
  }

  boolean isEmpty() {
    return frontList.isEmpty() && backList.isEmpty();
  }

  void removeFront() {
    if (frontList.isEmpty()) {
      if (backList.isEmpty()) {
        throw new IllegalStateException("empty");
      }
      moveHalf(backList, frontList);
    }
    frontList.removeLast();
  }

  void removeLast() {
    if (backList.isEmpty()) {
      if (frontList.isEmpty()) {
        throw new IllegalStateException("empty");
      }
      moveHalf(frontList, backList);
    }
    backList.removeLast();
  }

  private void moveHalf(List<Integer> from, List<Integer> to) {
    for (var i = from.size() / 2; i > 0; i--) {
      to.addLast(from.removeLast());
    }
  }

  int[] toArray() {
    var ints = new int[size()];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = get(i);
    }
    return ints;
  }

  int get(int i) {
    if (i < frontList.size()) {
      return frontList.get(frontList.size() - 1 - i);
    }
    return backList.get(i - frontList.size());
  }

  public static void main(String[] args) {
    var list = new DequeUsingTwoLists();
    list.addFirst(3);
    list.addFirst(2);
    list.addFirst(1);
    list.addLast(4);
    list.addLast(5);
    list.addLast(6);

    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }

    System.out.println(Arrays.toString(list.toArray()));
    list.removeFront();
    System.out.println(Arrays.toString(list.toArray()));
    list.removeFront();
    System.out.println(Arrays.toString(list.toArray()));
    list.removeLast();
    System.out.println(Arrays.toString(list.toArray()));
    list.removeLast();
    System.out.println(Arrays.toString(list.toArray()));
    list.removeLast();
    System.out.println(Arrays.toString(list.toArray()));
    list.removeFront();
    System.out.println(Arrays.toString(list.toArray()));
  }
}
