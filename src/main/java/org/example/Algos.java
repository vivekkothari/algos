package org.example;

class Algos {

  public static void main(String[] args) {
    //    var largeArray = IntStream.range(0, Integer.MAX_VALUE / 2).toArray();
    //    System.out.println(binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 7));
    //    System.out.println(binarySearch(largeArray, Integer.MAX_VALUE / 2 - 2));
    //    System.out.println(binarySearchRecur(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 10));
    //    System.out.println(binarySearchRecur(largeArray, 10));
    //    System.out.println(powmod(999999999, 999999999, 6));
    //    System.out.println(pow(2.1, 3));
    System.out.println(binarySearch(new int[] {-1, 0, 2, 4, 6, 8}, 4));
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

  static int powmod(int x, int n, int d) {
    long pow = 1;
    for (int i = 0; i < n; i++) {
      pow *= x;
    }
    return Math.toIntExact(pow % d);
  }

  static double myPow(double a, int b) {
    var pow = pow(a, Math.abs(b));
    return b < 0 ? 1 / pow : pow;
  }

  static double pow(double a, int b) {
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
}
