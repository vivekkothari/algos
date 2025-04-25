package org.example;

import java.util.*;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/finding-mk-average/description/?envType=company&envId=google&favoriteSlug=google-thirty-days
 */
class MKAverage {

  private final TreeMap<Integer, Integer> counts;
  private final Deque<Integer> stream;
  private final int m;
  private final int k;
  private int sumOfLastMNums = 0;

  public MKAverage(int m, int k) {
    counts = new TreeMap<>();
    stream = new LinkedList<>();
    this.m = m;
    this.k = k;
  }

  public void addElement(int num) {
    sumOfLastMNums += num;
    stream.add(num);
    if (stream.size() > m) {
      var key = stream.removeFirst();
      counts.put(key, counts.get(key) - 1);
      sumOfLastMNums -= key;
      if (counts.get(key) == 0) counts.remove(key);
    }
    counts.put(num, counts.getOrDefault(num, 0) + 1);
  }

  public int calculateMKAverage() {
    if (stream.size() < m) return -1;

    int key = counts.firstKey();
    int lastKey = counts.lastKey();
    int kSmall = k, kLarge = k;

    var extremeSum = 0;

    while (kSmall > 0) {
      // if there are 1 smallest element, and we want to remove 3, that's why the loop.
      // we subtract the that one element and then find the next higher key.
      var toSubtractSmall = Math.min(kSmall, counts.get(key));
      kSmall -= toSubtractSmall;
      extremeSum += key * toSubtractSmall;
      if (toSubtractSmall == counts.get(key)) key = counts.higherKey(key);
    }

    while (kLarge > 0) {
      var toSubtractLarge = Math.min(kLarge, counts.get(lastKey));
      kLarge -= toSubtractLarge;
      extremeSum += lastKey * toSubtractLarge;
      if (toSubtractLarge == counts.get(lastKey)) lastKey = counts.lowerKey(lastKey);
    }
    return (sumOfLastMNums - extremeSum) / (m - 2 * k);
  }
}
