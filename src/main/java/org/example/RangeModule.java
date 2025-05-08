package org.example;

import java.util.*;

public class RangeModule {

  record Interval(int start, int end) {}

  private final NavigableSet<Interval> intervals;

  public RangeModule() {
    intervals = new TreeSet<>((a, b) -> a.end - b.end);
  }

  public void addRange(int start, int end) {
    var tailSet = intervals.tailSet(new Interval(0, start));
    var iterator = tailSet.iterator();
    while (iterator.hasNext()) {
      var current = iterator.next();
      if (current.start > end) {
        break;
      }
      start = Math.min(start, current.start);
      end = Math.max(end, current.end);
      iterator.remove();
    }
    intervals.add(new Interval(start, end));
  }

  public boolean queryRange(int start, int end) {
    var higher = intervals.higher(new Interval(0, start));
    return higher != null && higher.start <= start && end <= higher.end;
  }

  public void removeRange(int start, int end) {
    var tailSet = intervals.tailSet(new Interval(0, start));
    var iterator = tailSet.iterator();
    List<Interval> toAdd = new ArrayList<>();
    while (iterator.hasNext()) {
      var current = iterator.next();
      if (current.start > end) {
        break;
      }
      if (current.start < start) {
        toAdd.add(new Interval(current.start, start));
      }
      if (end < current.end) {
        toAdd.add(new Interval(end, current.end));
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
