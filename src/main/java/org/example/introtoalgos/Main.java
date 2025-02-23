package org.example.introtoalgos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {

  public static void main(String[] args) {
    //    System.out.println(exponent(2, 4));
    //    var nums = new int[] {6, 5, 4, 3, 2, 1};
    //    insertionSortRecur(nums, nums.length - 1);
    //    mergeSort(nums, 0, nums.length - 1);
    //    System.out.println(Arrays.toString(nums));
    //    selectionSort(nums);
    //    insertionSort(nums);
    //    insertionSortDecreasing(nums);

    var nums = new int[] {6, 5, 4, 3, 2, 1, 10};
    System.out.println(findMin(nums, 0, Integer.MAX_VALUE));

    var collect = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
    shiftLeft(collect, 3);
    System.out.println(collect);
  }

  public static void selectionSort(int[] nums) {
    var n = nums.length;
    for (int i = 0; i < n; i++) {
      var minIndex = i;
      // Find the minimum index in remaining array (i+1 : n)
      // So we are "selecting" the index of the lowest element.
      for (int j = i + 1; j < n; j++) {
        if (nums[j] < nums[i]) {
          minIndex = j;
        }
      }
      // swap the min index position with i's position
      int temp = nums[i];
      nums[i] = nums[minIndex];
      nums[minIndex] = temp;
    }
  }

  public static void mergeSort(int[] nums, int l, int r) {
    if (l >= r) return;
    int mid = l + (r - l) / 2;
    mergeSort(nums, l, mid);
    mergeSort(nums, mid + 1, r);
    mergeSubArrays(nums, l, mid, r);
  }

  private static void mergeSubArrays(int[] nums, int l, int mid, int r) {
    var leftArr = Arrays.copyOfRange(nums, l, mid + 1);
    var rightArr = Arrays.copyOfRange(nums, mid, r);
    int i = 0, j = 0, k = l;
    while (i < leftArr.length && j < rightArr.length) {
      if (leftArr[i] <= rightArr[j]) {
        nums[k++] = leftArr[i++];
      } else {
        nums[k++] = rightArr[j++];
      }
    }
    while (i < leftArr.length) nums[k++] = leftArr[i++];
    while (j < rightArr.length) nums[k++] = rightArr[j++];
  }

  public static void insertionSortRecur(int[] nums, int n) {
    if (n == 0) return;
    insertionSortRecur(nums, n - 1);
    var key = nums[n];
    var j = n - 1;
    while (j >= 0 && nums[j] > key) {
      nums[j + 1] = nums[j];
      j--;
    }
    nums[j + 1] = key;
  }

  public static void insertionSort(int[] nums) {
    var n = nums.length;
    // Visualise it as a deck of cards held in 2 hands
    // all values < i on left side, and we pick number from right side and insert at right position
    for (int i = 1; i < n; i++) {
      // Current card which we want to insert at right position.
      var key = nums[i];
      // Look on the left side
      var j = i - 1;
      // Keep shifting elements until we find the right index for key
      while (j >= 0 && nums[j] > key) {
        nums[j + 1] = nums[j];
        j--;
      }
      // Insert key at the current best position
      // For the current deck on the left hand should be sorted.
      nums[j + 1] = key;
    }
  }

  // Exercise: 2.1-3
  public static void insertionSortDecreasing(int[] nums) {
    var n = nums.length;
    // Visualise it as a deck of cards held in 2 hands
    // all values < i on left side, and we pick number from right side and insert at right position
    for (int i = 1; i < n; i++) {
      // Current card which we want to insert at right position.
      var key = nums[i];
      // Look on the left side
      var j = i - 1;
      // Keep shifting elements until we find the right index for key
      while (j >= 0 && nums[j] < key) {
        nums[j + 1] = nums[j];
        j--;
      }
      // Insert key at the current best position
      // For the current deck on the left hand should be sorted.
      nums[j + 1] = key;
    }
  }

  public static int search(int[] n, int target) {
    for (int i = 0; i < n.length; i++) {
      if (target == n[i]) {
        return i;
      }
    }
    return -1;
  }

  /** 2.1-5 <img src="2.1-5.png"></img> */
  public static int[] addNBitNumbers(int[] num1, int[] num2) {
    assert num1.length == num2.length;
    int[] res = new int[num1.length];
    for (int i = 0; i < num1.length; i++) {
      res[i] = num1[i] + num2[i];
    }
    return res;
  }

  public static int exponent(int a, int b) {
    if (b == 0) return 1;
    if ((b & 1) == 0) {
      // is even
      var d = exponent(a, b / 2);
      return d * d;
    } else {
      // is odd
      var d = exponent(a, b - 1);
      return a * d;
    }
  }

  public static void shiftLeft(List<Integer> list, int k) {
    if (k < 1 || k > list.size()) return;
    list.addLast(list.removeFirst());
    shiftLeft(list, k - 1);
  }

  public static int findMin(int[] nums, int k, int currentMin) {
    if (k == nums.length) {
      return currentMin;
    }
    currentMin = Math.min(currentMin, nums[k]);
    return findMin(nums, k + 1, currentMin);
  }
}
