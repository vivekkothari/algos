package org.example;

import java.util.NavigableMap;
import java.util.TreeMap;

public class RangeModule {

  private final NavigableMap<Integer, Integer> ranges = new TreeMap<>();

  public RangeModule() {}

  public void addRange(int left, int right) {
    if (left >= right) return;
    Integer start = ranges.floorKey(left);
    if (start != null && ranges.get(start) >= left) {
      left = start;
      right = Math.max(right, ranges.get(start));
      ranges.remove(start);
    }
    Integer end = ranges.floorKey(right);
    while (end != null && ranges.get(end) >= left) {
      right = Math.max(right, ranges.get(end));
      ranges.remove(end);
      end = ranges.floorKey(right);
    }
    ranges.put(left, right);
  }

  public boolean queryRange(int left, int right) {
    Integer start = ranges.floorKey(left);
    return start != null && ranges.get(start) >= right;
  }

  public void removeRange(int left, int right) {
    if (left >= right) return;
    Integer start = ranges.floorKey(left);
    Integer end = ranges.floorKey(right);
    if (start != null && ranges.get(start) > left) {
      int rangeEnd = ranges.get(start);
      ranges.remove(start);
      if (start < left) ranges.put(start, left);
      if (rangeEnd > right) ranges.put(right, rangeEnd);
    }
    while (end != null && ranges.get(end) > left) {
      int rangeEnd = ranges.get(end);
      ranges.remove(end);
      if (rangeEnd > right) ranges.put(right, rangeEnd);
      end = ranges.floorKey(right);
    }
  }

  public static void main(String[] args) {
    RangeModule rangeModule = new RangeModule();
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
