package org.example;

import java.util.Arrays;

public class FindMissingElement {

  static int findMissingElement(int[] arr, int n) {
    Arrays.sort(arr);
    for (int i = 0; i < n; i++) {
      if (arr[i] == i + 1) {
        continue;
      }
      return i + 1;
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(findMissingElement(new int[] {1, 2, 4, 6, 3, 7, 8}, 8));
  }
}
