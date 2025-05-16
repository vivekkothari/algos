package org.example;

import java.util.Arrays;

class Algos {

  public static void main(String[] args) {
    findPages(new int[] {12, 34, 67, 90}, 2);
    //    System.out.println(aggressiveCows(new int[] {1, 5, 17}, 2));
    //    smallestDivisor(new int[] {1, 2, 5, 9}, 6);
    //    var largeArray = IntStream.range(0, Integer.MAX_VALUE / 2).toArray();
    //    System.out.println(binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 7));
    //    System.out.println(binarySearch(largeArray, Integer.MAX_VALUE / 2 - 2));
    //    System.out.println(binarySearchRecur(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 10));
    //    System.out.println(binarySearchRecur(largeArray, 10));
    //    System.out.println(powmod(999999999, 999999999, 6));
    //    System.out.println(pow(2.1, 3));
    //    System.out.println(binarySearch(new int[] {-1, 0, 2, 4, 6, 8}, 4));
    //    var nums = new int[] {-1, 0, 2, 4, 6, 8};
    //    System.out.println(nums[floor(nums, 7)]);
    //    System.out.println(nums[ceil(nums, 7)]);
  }

  private static int binarySearch(int[] nums, int target) {
    var high = nums.length - 1;
    var low = 0;
    while (low <= high) {
      var mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      if (nums[mid] > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

  /**
   * The ceiling of x is the smallest element in the array greater than or equal to x( i.e. smallest
   * element in the array >= x).
   */
  private static int ceil(int[] nums, int target) {
    var high = nums.length - 1;
    var low = 0;
    int res = -1;
    while (low <= high) {
      var mid = low + (high - low) / 2;
      if (target <= nums[mid]) {
        high = mid - 1;
        res = mid;
      } else {
        low = mid + 1;
      }
    }
    return res;
  }

  /**
   * The floor of x is the largest element in the array which is smaller than or equal to x( i.e.
   * largest element in the array <= x).
   */
  private static int floor(int[] nums, int target) {
    var high = nums.length - 1;
    var low = 0;
    int res = -1;
    while (low <= high) {
      var mid = low + (high - low) / 2;
      if (target >= nums[mid]) {
        low = mid + 1;
        res = mid;
      } else {
        high = mid - 1;
      }
    }
    return res;
  }

  static int powmod(int x, int n, int d) {
    long pow = 1;
    for (int i = 0; i < n; i++) {
      pow *= x;
    }
    return Math.toIntExact(pow % d);
  }

  static double myPow(double a, int b) {
    var pow = pow(a, Math.abs((long) b));
    return b < 0 ? 1 / pow : pow;
  }

  static double pow(double a, long b) {
    if (a == 0) return 0;
    if (b == 0) return 1;
    if (b == 1) return a;

    if (b % 2 == 0) return pow(a * a, b / 2);
    return a * pow(a * a, (b - 1) / 2);
  }

  private static int binarySearchRecur(int[] nums, int target) {
    return binarySearchRecur(nums, target, 0, nums.length - 1);
  }

  private static int binarySearchRecur(int[] nums, int target, int low, int high) {
    if (low > high) {
      return -1;
    }
    var mid = low + (high - low) / 2;
    if (nums[mid] == target) {
      return mid;
    }
    if (nums[mid] > target) {
      return binarySearchRecur(nums, target, low, mid - 1);
    } else {
      return binarySearchRecur(nums, target, mid + 1, high);
    }
  }

  /** https://www.geeksforgeeks.org/problems/aggressive-cows/1 */
  public static int aggressiveCows(int[] stalls, int numCows) {
    Arrays.sort(stalls);
    int maxDistance = stalls[stalls.length - 1] - stalls[0];
    int bestDistance = -1;
    int low = 1, high = maxDistance;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      boolean canPlaceCow = canPlaceCow(stalls, numCows, mid);
      if (canPlaceCow) {
        bestDistance = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return bestDistance;
  }

  private static boolean canPlaceCow(int[] stalls, int numCows, int minDistance) {
    int countCows = 1, lastCow = stalls[0];
    for (int i = 1; i < stalls.length; i++) {
      if (stalls[i] - lastCow >= minDistance) {
        countCows++;
        lastCow = stalls[i];
        if (countCows == numCows) return true;
      }
    }
    return false;
  }

  /** https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/ */
  public static int smallestDivisor(int[] nums, int threshold) {
    int l = 1, r = Arrays.stream(nums).max().getAsInt();
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (possible(nums, threshold, m)) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return l;
  }

  private static boolean possible(int[] nums, int threshold, int m) {
    int sum = 0;
    for (int num : nums) {
      sum += Math.ceilDiv(num, m);
    }
    return sum < threshold;
  }

  /** https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/ */
  public static int minDays(int[] bloomDay, int bouquets, int k) {
    if (bouquets * k > bloomDay.length) return -1;
    int l = 0, r = 0, minDays = 1;
    for (int day : bloomDay) {
      l = Math.min(l, day);
      r = Math.max(r, day);
    }
    while (l <= r) {
      int mid = l + (r - l) / 2;
      boolean canMake = canMake(bloomDay, mid, k, bouquets);
      if (canMake) {
        minDays = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return minDays;
  }

  private static boolean canMake(int[] bloomDay, int days, int k, int bouquets) {
    int times = 0;
    for (int day : bloomDay) {
      if (day <= days) times++;
      else times = 0;
      if (times == k) {
        bouquets--;
        times = 0;
      }
    }
    return bouquets <= 0;
  }

  /** https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/ */
  public int shipWithinDays(int[] weights, int days) {
    int l = 0, r = 0;
    for (int w : weights) {
      l = Math.max(l, w);
      r += w;
    }
    while (l <= r) {
      int m = l + (r - l) / 2;
      boolean canShip = canShip(weights, days, m);
      if (canShip) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return l;
  }

  private boolean canShip(int[] weights, int threshold, int capacity) {
    int daysNeeded = 1, load = 0;
    for (int weight : weights) {
      load += weight;
      if (load > capacity) {
        daysNeeded++;
        load = weight;
      }
    }
    return daysNeeded <= threshold;
  }

  /** https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1 */
  public static int findPages(int[] books, int numStudents) {
    if (numStudents > books.length) return -1;
    int minPages = -1, maxPages = 0, res = -1;
    for (int pages : books) {
      minPages = Math.max(minPages, pages);
      maxPages += pages;
    }
    while (minPages <= maxPages) {
      int mid = minPages + (maxPages - minPages) / 2;
      boolean canAllocate = canAllocate(books, numStudents, mid);
      if (canAllocate) {
        res = mid;
        maxPages = mid - 1;
      } else {
        minPages = mid + 1;
      }
    }
    return res;
  }

  private static boolean canAllocate(int[] arr, int k, int maxPages) {
    int students = 1, pagesSum = 0;
    for (int pages : arr) {
      if (pagesSum + pages <= maxPages) {
        pagesSum += pages;
      } else {
        students++;
        pagesSum = pages;
      }
    }
    return students <= k;
  }

  /** https://leetcode.com/problems/split-array-largest-sum/ */
  public int splitArray(int[] nums, int k) {
    if (k > nums.length) return -1;
    int low = -1, high = 0;
    for (int num : nums) {
      low = Math.max(low, num);
      high += num;
    }
    int res = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      boolean canSplit = canSplit(nums, k, mid);
      if (canSplit) {
        res = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return res;
  }

  private boolean canSplit(int[] nums, int k, int mid) {
    int splits = 1, runningSum = 0;
    for (int num : nums) {
      if (runningSum + num <= mid) {
        runningSum += num;
      } else {
        splits++;
        runningSum = num;
      }
    }
    return splits <= k;
  }

  /// ////////////////////
  /// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
  /// /////////////////
  public int[] searchRange(int[] nums, int target) {
    return new int[] {findFirst(nums, target), findLast(nums, target)};
  }

  private int findFirst(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    int res = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        res = mid;
        high = mid - 1; // Keep searching left
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return res;
  }

  private int findLast(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    int res = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        res = mid;
        low = mid + 1; // Keep searching right
      } else if (nums[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return res;
  }
  /// /////////////////
}
