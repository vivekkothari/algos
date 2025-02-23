package org.example;

import java.util.ArrayList;
import java.util.List;

class Bitwise {

  public static void main(String[] args) {
    //    System.out.println(countSetBits(3));
    generateSubsets(new int[] {1, 2, 3});
  }

  static int countSetBits(int n) {
    int count = 0;
    while (n > 0) {
      n &= (n - 1);
      count++;
    }
    return count;
  }

  static boolean isPowerOfTwo(int n) {
    return n != 0 && (n & (n - 1)) == 0;
  }

  static int findOnlyNonRepeatingNumber(int[] n) {
    int res = 0;
    for (var i : n) {
      res ^= i;
    }
    return res;
  }

  public static List<List<Integer>> generateSubsets(int[] arr) {
    int n = arr.length;
    List<List<Integer>> res = new ArrayList<>();
    int totalSubsets = 1 << n;
    for (int mask = 0; mask < totalSubsets; mask++) {
      List<Integer> subset = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
          subset.add(arr[i]);
        }
      }
      res.add(subset);
    }
    return res;
  }
}
