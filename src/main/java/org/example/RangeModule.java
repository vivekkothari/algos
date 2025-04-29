package org.example;

import java.util.*;

public class RangeModule {

  record Interval(int left, int right) {}

  private final NavigableSet<Interval> intervals;

  public RangeModule() {
    intervals = new TreeSet<>((a, b) -> a.right - b.right);
  }

  public void addRange(int left, int right) {
    var tailSet = intervals.tailSet(new Interval(0, left));
    var iterator = tailSet.iterator();
    while (iterator.hasNext()) {
      var current = iterator.next();
      if (current.left > right) {
        break;
      }
      left = Math.min(left, current.left);
      right = Math.max(right, current.right);
      iterator.remove();
    }
    intervals.add(new Interval(left, right));
  }

  public boolean queryRange(int left, int right) {
    var higher = intervals.higher(new Interval(0, left));
    return higher != null && higher.left <= left && right <= higher.right;
  }

  public void removeRange(int left, int right) {
    var tailSet = intervals.tailSet(new Interval(0, left));
    var iterator = tailSet.iterator();
    List<Interval> toAdd = new ArrayList<>();
    while (iterator.hasNext()) {
      var current = iterator.next();
      if (current.left > right) {
        break;
      }
      if (current.left < left) {
        toAdd.add(new Interval(current.left, left));
      }
      if (right < current.right) {
        toAdd.add(new Interval(right, current.right));
      }
      iterator.remove();
    }
    intervals.addAll(toAdd);
  }

  public static void main(String[] args) {
    var rangeModule = new RangeModule();
    rangeModule.addRange(10, 12);
    rangeModule.addRange(14, 16);
    rangeModule.addRange(18, 24);
    rangeModule.addRange(6, 24);
    rangeModule.removeRange(14, 16);
    System.out.println(rangeModule.queryRange(10, 14)); // true
    System.out.println(rangeModule.queryRange(13, 15)); // false
    System.out.println(rangeModule.queryRange(16, 17)); // true
  }
}
