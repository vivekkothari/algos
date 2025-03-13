package org.example;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Arrays {

  public static void main(String[] args) {
    minZeroArray(new int[] {2, 0, 2}, new int[][] {{0, 2, 1}, {0, 2, 1}, {1, 1, 3}});
    System.out.println(
        java.util.Arrays.deepToString(
            highFive(
                new int[][] {
                  {1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100},
                  {2, 100}, {2, 76}
                })));
    // [1,2],[2,3],[3,4],[1,3]
    //    eraseOverlapIntervals(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}});
    //    candy(new int[] {1, 0, 2, 3, 6});
    //    System.out.println(maximumUniqueSubarray(new int[] {5, 2, 1, 2, 5, 2, 1, 2, 5}));
    //    System.out.println(longestOnes(new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 3));
    //    System.out.println(summaryRanges(new int[] {0, 1, 2, 4, 5, 7, 8}));
    //    System.out.println(findClosestNumber(new int[] {-4, -2, 1, 4, 8}));
    //    System.out.println(searchSortedRotated(new int[] {1}, 1));
    //    trapOptimised(new int[] {0, 2, 0, 3, 1, 0, 1, 3, 2, 1});
    //    dominantIndex(new int[] {3, 6, 1, 0});
    //    System.out.println(findPivotIndex(new int[] {1, 7, 3, 6, 5, 6}));
    //    maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3);
    //    System.out.println(
    //        java.util.Arrays.toString(dailyTemperatures(new int[] {30, 38, 30, 36, 35, 40, 28})));
    //    nextGreaterElements(new int[] {1, 4, 2, 5, 4, 6, 3, 8});
    //    System.out.println(
    //        java.util.Arrays.toString(nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4,
    // 2})));
    //    nextPermutation(new int[] {3, 1, 2});
    //    System.out.println(Arrays.toString(twoSum(new int[] {3, 2, 4}, 6)));
    //    System.out.println(maxStockProfit(new int[] {7, 1, 5, 3, 6, 4}));
    //    var nums = new int[] {1, 2, 3, 0, 4, 0, 5};
    //    moveZeroes(nums);
    //    System.out.println(Arrays.toString(nums));
    //
    //    System.out.println(maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    //    System.out.println(
    //        Arrays.toString(productExceptSelfUsingDivision(new int[] {-1, 1, 0, -3, 3})));
    System.out.println(
        java.util.Arrays.toString(productExceptSelfWithoutDivision(new int[] {1, 2, 3, 4})));
    //    System.out.println(
    //        java.util.Arrays.deepToString(merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15,
    // 18}})));
    //
    //    System.out.println(binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 1));
    //
    //    System.out.println(java.util.Arrays.toString(getConcatenation(new int[] {1, 2, 3}, 1)));
    //    System.out.println(Strings.isAnagram("anagram", "nagaram"));
    //
    //    System.out.println(
    //        Strings.groupAnagrams(new String[] {"act", "pots", "tops", "cat", "stop", "hat"}));
    //
    //    System.out.println(java.util.Arrays.toString(topKFrequent(new int[] {1, 2, 3, 4, 5, 6},
    // 2)));
    //
    //    var encoded = Strings.encode(List.of(""));
    //    System.out.println(encoded);
    //    System.out.println(Strings.decode(encoded));

    //    System.out.println(removeElement(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2));
    //    System.out.println(java.util.Arrays.toString(sortArray(new int[] {0, 1, 2, 2, 3, 0, 4,
    // 2})));
    //    System.out.println(java.util.Arrays.toString(sortColors(new int[] {2, 0, 2, 1, 1, 0})));
    //    var nums1 = new int[] {1, 2, 3, 0, 0, 0};
    //    merge(nums1, 3, new int[] {2, 5, 6}, 3);
    //    System.out.println(java.util.Arrays.toString(nums1));

    //    var nums2 = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    //    System.out.println(removeDuplicates(nums2));
    //    System.out.println(java.util.Arrays.toString(nums2));

    //    System.out.println(java.util.Arrays.toString(twoSumSortedArray(new int[] {2, 7, 11, 15},
    // 9)));
    //    System.out.println(threeSumDifferent(new int[] {-1, 0, 1, 2, -1, -4}));
    //    System.out.println(threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    //    System.out.println(threeSumClosest(new int[] {1, 1, 1, 0}, -100));
    //    System.out.println(kSum(new int[] {-1, 0, 1, 2, -1, -4}, 3, 0));
    //    var nums = new int[] {1, 2, 3, 4, 5, 6, 7};
    //    rotateNoExtraSpace(nums, 3);
    //    System.out.println(java.util.Arrays.toString(nums));

    //    System.out.println(minJumps(new int[] {2, 3, 1, 1, 4}));
    //    System.out.println(canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1));
    //    System.out.println(canPlaceFlowers(new int[] {1, 0}, 1));
    //    System.out.println(containsNearbyDuplicate(new int[] {99, 99}, 2));
    //    System.out.println(maxArea(new int[] {1, 7, 2, 5, 4, 7, 3, 6}));
    //    System.out.println(searchInsert(new int[] {1, 3, 5, 6}, 7));

    System.out.println(climbStairsDp(3));
    //    System.out.println(fib(50));
    //    System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    //    System.out.println(java.util.Arrays.toString(plusOne(new int[] {9})));
    //    System.out.println(java.util.Arrays.toString(applyOperations(new int[] {1, 2, 2, 1, 1,
    // 0})));
    //    System.out.println(countQuadruplets(new int[] {1, 1, 1, 3, 5}));
  }

  public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> numMap = new HashMap<>();
    for (var i = 0; i < nums.length; i++) {
      var complement = target - nums[i];
      if (numMap.containsKey(complement)) {
        return new int[] {i, numMap.get(complement)};
      }
      numMap.put(nums[i], i);
    }
    return new int[] {};
  }

  /**
   * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
   *
   * <pre>
   *   Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
   *
   * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
   *
   * The tests are generated such that there is exactly one solution. You may not use the same element twice.
   *
   * Your solution must use only constant extra space.
   *
   *
   *
   * Example 1:
   *
   * Input: numbers = [2,7,11,15], target = 9
   * Output: [1,2]
   * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
   * Example 2:
   *
   * Input: numbers = [2,3,4], target = 6
   * Output: [1,3]
   * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
   * Example 3:
   *
   * Input: numbers = [-1,0], target = -1
   * Output: [1,2]
   * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
   * </pre>
   */
  public static int[] twoSumSortedArray(int[] numbers, int target) {
    int l = 0, r = numbers.length - 1;
    while (l < r) {
      var sum = numbers[l] + numbers[r];
      if (sum == target) {
        break;
      } else if (sum > target) {
        r--;
      } else {
        l++;
      }
    }
    // Since output is 1-indexed
    return new int[] {l + 1, r + 1};
  }

  /**
   *
   *
   * <pre>
   *   Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
   *
   * Notice that the solution set must not contain duplicate triplets.
   *
   *
   *
   * Example 1:
   *
   * Input: nums = [-1,0,1,2,-1,-4]
   * Output: [[-1,-1,2],[-1,0,1]]
   * Explanation:
   * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
   * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
   * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
   * The distinct triplets are [-1,0,1] and [-1,-1,2].
   * Notice that the order of the output and the order of the triplets does not matter.
   * Example 2:
   *
   * Input: nums = [0,1,1]
   * Output: []
   * Explanation: The only possible triplet does not sum up to 0.
   * Example 3:
   *
   * Input: nums = [0,0,0]
   * Output: [[0,0,0]]
   * Explanation: The only possible triplet sums up to 0.
   * </pre>
   */
  public static List<List<Integer>> threeSum(int[] nums) {
    java.util.Arrays.sort(nums);
    List<List<Integer>> triplets = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        break;
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int l = i + 1, r = nums.length - 1;
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (sum > 0) {
          r--;
        } else if (sum < 0) {
          l++;
        } else {
          triplets.add(List.of(nums[i], nums[l], nums[r]));
          l++;
          r--;
          while (l < r && nums[l] == nums[l - 1]) {
            l++;
          }
        }
      }
    }
    return triplets;
  }

  public static List<List<Integer>> threeSumDifferent(int[] nums) {
    java.util.Arrays.sort(nums);
    var res = new HashSet<List<Integer>>();
    for (int i = 0; i < nums.length; i++) {
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        var sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          res.add(List.of(nums[i], nums[j], nums[k]));
          j++;
          k--;
        } else if (sum < 0) {
          j++;
        } else {
          k--;
        }
      }
    }
    return res.stream().toList();
  }

  /**
   * https://leetcode.com/problems/3sum-closest/
   *
   * <pre>
   *   Given an integer array nums of length n and an integer target,
   *
   *   find three integers in nums such that the sum is closest to target.
   *
   * Return the sum of the three integers.
   *
   * You may assume that each input would have exactly one solution.
   *
   *
   *
   * Example 1:
   *
   * Input: nums = [-1,2,1,-4], target = 1
   * Output: 2
   * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
   * Example 2:
   *
   * Input: nums = [0,0,0], target = 1
   * Output: 0
   * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
   * </pre>
   */
  public static int threeSumClosest(int[] nums, int target) {
    java.util.Arrays.sort(nums);
    var minDiff = Integer.MAX_VALUE;
    var ans = 0;
    for (int i = 0; i < nums.length; i++) {
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        var sum = nums[i] + nums[j] + nums[k];
        if (sum == target) {
          return sum;
        }
        var diff = Math.abs(target - sum);
        if (diff < minDiff) {
          minDiff = diff;
          ans = sum;
        }
        if (sum < target) {
          j++;
        } else {
          k--;
        }
      }
    }
    return ans;
  }

  public static int maxStockProfit(int[] stockPrices) {
    var minPrice = Integer.MAX_VALUE;
    var maxProfit = 0;
    for (var todaysPrice : stockPrices) {
      minPrice = Math.min(minPrice, todaysPrice);
      var currentProfit = todaysPrice - minPrice;
      maxProfit = Math.max(maxProfit, currentProfit);
    }
    return maxProfit;
  }

  /**
   *
   *
   * <pre>
   *   You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
   *
   * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
   *
   * Find and return the maximum profit you can achieve.
   *
   *
   *
   * Example 1:
   *
   * Input: prices = [7,1,5,3,6,4]
   * Output: 7
   * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
   * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
   * Total profit is 4 + 3 = 7.
   * Example 2:
   *
   * Input: prices = [1,2,3,4,5]
   * Output: 4
   * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
   * Total profit is 4.
   * Example 3:
   *
   * Input: prices = [7,6,4,3,1]
   * Output: 0
   * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
   * </pre>
   */
  public static int maxStockProfitHoldOneDay(int[] prices) {
    var maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i - 1] < prices[i]) {
        maxProfit += prices[i] - prices[i - 1];
      }
    }
    return maxProfit;
  }

  /**
   *
   *
   * <pre>
   *   You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
   *
   * Return true if you can reach the last index, or false otherwise.
   *
   *
   *
   * Example 1:
   *
   * Input: nums = [2,3,1,1,4]
   * Output: true
   * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
   * Example 2:
   *
   * Input: nums = [3,2,1,0,4]
   * Output: false
   * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
   *
   * The question states "each element in the array represents your maximum jump length at that position."
   * It means if we are at a position k and nums[k] = 4, then it means we can jump forward a maximum of 4 steps from this position. It's our choice to jump 1,2,3 or 4 positions, but not more than 4.
   *
   * For example
   * In this test case [2,3,1,4]
   * nums[0] = 2
   * It means we can jump either 1 or 2 steps
   *
   * At the end, if we reached the last index or greater than that we win!
   * </pre>
   */
  public static boolean canJump(int[] nums) {
    var goalPost = nums.length - 1;
    for (int i = nums.length - 1; i > 0; i--) {
      if (i + nums[i] >= goalPost) {
        goalPost = i;
      }
    }
    return goalPost == 0;
  }

  /**
   * https://leetcode.com/problems/partition-array-according-to-given-pivot/?envType=daily-question&envId=2025-03-03
   *
   * <pre>
   * Input: nums = [9,12,5,10,14,3,10], pivot = 10
   * Output: [9,5,3,10,10,12,14]
   * Explanation:
   * The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
   * The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
   * The relative ordering of the elements less than and greater than pivot is also maintained.
   * [9, 5, 3] and [12, 14] are the respective orderings.
   * </pre>
   */
  public static int[] pivotArray(int[] nums, int pivot) {
    int[] res = new int[nums.length];
    int k = 0;
    for (var num : nums) {
      if (num < pivot) {
        res[k++] = num;
      }
    }
    for (var num : nums) {
      if (num == pivot) {
        res[k++] = num;
      }
    }
    for (var num : nums) {
      if (num > pivot) {
        res[k++] = num;
      }
    }
    return res;
  }

  static int minJumpsRecur(int i, int[] arr, Map<Integer, Integer> memo) {

    // Return 0 when last element is reached.
    if (i >= arr.length - 1) {
      return 0;
    }

    // Traverse through all the points
    // reachable from arr[i].
    // Recursively, get the minimum number
    // of jumps needed to reach array end from
    // these points.
    int ans = Integer.MAX_VALUE;
    for (int j = i + 1; j <= i + arr[i]; j++) {
      int val;
      if (memo.get(j) == null) {
        val = minJumpsRecur(j, arr, memo);
        memo.put(j, val);
      } else {
        val = memo.get(j);
      }
      if (val != Integer.MAX_VALUE) {
        ans = Math.min(ans, 1 + val);
      }
    }

    return ans;
  }

  static int minJumps(int[] arr) {
    int ans = minJumpsRecur(0, arr, new HashMap<>());
    // If end cannot be reached.
    if (ans == Integer.MAX_VALUE) {
      return -1;
    }
    return ans;
  }

  public static void moveZeroes(int[] nums) {
    var startOfZeroSeq = 0;
    for (var i = 0; i < nums.length; i++) {
      var num = nums[i];
      if (num == 0) {
        continue;
      }
      swap(nums, startOfZeroSeq, i);
      startOfZeroSeq++;
    }
  }

  public static void swap(int[] nums, int j, int i) {
    var temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }

  public static int maxSubArray(int[] nums) {
    var maxSum = 0;
    var currentSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      currentSum = Math.max(nums[i], currentSum + nums[i]);
      maxSum = Math.max(maxSum, currentSum);
    }
    return maxSum;
  }

  public static int[] productExceptSelfUsingDivision(int[] nums) {
    var totalProduct = 1;
    var zeroCount = 0;
    for (var num : nums) {
      if (num == 0) {
        zeroCount++;
      } else {
        totalProduct *= num;
      }
    }
    var ret = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      if (zeroCount > 1) {
        ret[i] = 0;
      } else if (zeroCount == 1) {
        ret[i] = nums[i] == 0 ? totalProduct : 0;
      } else {
        ret[i] = totalProduct / nums[i];
      }
    }
    return ret;
  }

  public static int[] productExceptSelfWithoutDivision(int[] nums) {
    var ret = new int[nums.length];
    // No elements to the left of the first element
    ret[0] = 1;
    // Prefix product
    for (int i = 1; i < nums.length; i++) {
      ret[i] = ret[i - 1] * nums[i - 1];
    }
    // suffix product
    var suffixProduct = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      ret[i] *= suffixProduct;
      suffixProduct *= nums[i];
    }
    return ret;
  }

  public static int[] minOperationsOptimal(String boxes) {
    var res = new int[boxes.length()];
    int moves = 0;
    int numBalls = 0;
    for (int i = 0; i < boxes.length(); i++) {
      res[i] += moves + numBalls;
      moves = res[i];
      numBalls += boxes.charAt(i) - '0';
    }
    moves = 0;
    numBalls = 0;
    for (int i = boxes.length() - 1; i >= 0; i--) {
      var temp = res[i];
      res[i] += moves + numBalls;
      moves = res[i] - temp;
      numBalls += boxes.charAt(i) - '0';
    }
    return res;
  }

  public static int missingNumber(int[] nums) {
    var n = nums.length;
    var expectedSum = n * (n + 1) / 2;
    var actualSum = 0;
    for (var num : nums) {
      actualSum += num;
    }
    return expectedSum - actualSum;
  }

  /**
   * https://leetcode.com/problems/merge-intervals/ Given an array of intervals where intervals[i] =
   * [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping
   * intervals that cover all the intervals in the input.
   *
   * <p>Example 1:
   *
   * <p>Input: intervals = [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]] Explanation:
   * Since intervals [1,3] and [2,6] overlap, merge them into [1,6]. Example 2:
   *
   * <p>Input: intervals = [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5] are
   * considered overlapping.
   *
   * <p>Constraints:
   *
   * <p>1 <= intervals.length <= 104 intervals[i].length == 2 0 <= starti <= endi <= 104
   */
  public static int[][] merge(int[][] intervals) {
    java.util.Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    // Step 2: Initialize the list to store the merged intervals
    List<int[]> merged = new ArrayList<>(intervals.length);
    // Step 3: Iterate through the intervals and merge when necessary
    for (int[] interval : intervals) {
      // If merged list is empty or no overlap, add the interval
      if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
        merged.add(interval);
      } else {
        // If overlap, merge by updating the end of the last interval
        merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
      }
    }
    // Step 4: Convert the list to a 2D array and return it
    return merged.toArray(new int[merged.size()][]);
  }

  public static int binarySearch(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
      var mid = low + (high - low) / 2;
      if (target == nums[mid]) {
        return mid;
      } else if (target < nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

  /**
   *
   *
   * <pre>
   *   Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i] and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
   *
   * Specifically, ans is the concatenation of two nums arrays.
   *
   * Return the array ans.
   *
   *
   *
   * Example 1:
   *
   * Input: nums = [1,2,1]
   * Output: [1,2,1,1,2,1]
   * Explanation: The array ans is formed as follows:
   * - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
   * - ans = [1,2,1,1,2,1]
   * Example 2:
   *
   * Input: nums = [1,3,2,1]
   * Output: [1,3,2,1,1,3,2,1]
   * Explanation: The array ans is formed as follows:
   * - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
   * - ans = [1,3,2,1,1,3,2,1]
   * </pre>
   */
  public static int[] getConcatenation(int[] nums, int duplicateTimes) {
    var ans = new int[duplicateTimes * nums.length];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = nums[i % nums.length];
    }
    return ans;
  }

  /**
   *
   *
   * <pre>
   *   Contains Duplicate
   * Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
   *
   * Example 1:
   *
   * Input: nums = [1, 2, 3, 3]
   *
   * Output: true
   *
   * Example 2:
   *
   * Input: nums = [1, 2, 3, 4]
   *
   * Output: false
   *
   *
   * Recommended Time & Space Complexity
   * You should aim for a solution with O(n) time and O(n) space, where n is the size of the input array.
   * </pre>
   */
  public static boolean hasDuplicate(int[] nums) {
    var set = new HashSet<Integer>();
    for (var num : nums) {
      if (!set.add(num)) {
        return true;
      }
    }
    return false;
  }

  public static int[] topKFrequent(int[] nums, int k) {
    // freq map
    Map<Integer, Integer> freq = new HashMap<>();
    for (int n : nums) {
      freq.put(n, freq.getOrDefault(n, 0) + 1);
    }
    // bucket sort on freq
    List<Integer>[] bucket = new List[nums.length + 1];
    for (int i = 0; i < bucket.length; i++) {
      bucket[i] = new ArrayList<>();
    }
    for (int key : freq.keySet()) {
      bucket[freq.get(key)].add(key);
    }
    // gather result
    List<Integer> res = new ArrayList<>();
    for (int i = bucket.length - 1; i >= 0; i--) {
      res.addAll(bucket[i]);
      if (res.size() >= k) {
        break;
      }
    }
    return res.stream().limit(k).mapToInt(Integer::intValue).toArray();
  }

  /**
   * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
   * The order of the elements may be changed. Then return the number of elements in nums which are
   * not equal to val.
   *
   * <p>Consider the number of elements in nums which are not equal to val be k, to get accepted,
   * you need to do the following things:
   *
   * <p>Change the array nums such that the first k elements of nums contain the elements which are
   * not equal to val. The remaining elements of nums are not important as well as the size of nums.
   * Return k. Custom Judge:
   *
   * <p>The judge will test your solution with the following code:
   *
   * <p>int[] nums = [...]; // Input array int val = ...; // Value to remove int[] expectedNums =
   * [...]; // The expected answer with correct length. // It is sorted with no values equaling val.
   *
   * <p>int k = removeElement(nums, val); // Calls your implementation
   *
   * <p>assert k == expectedNums.length; sort(nums, 0, k); // Sort the first k elements of nums for
   * (int i = 0; i < actualLength; i++) { assert nums[i] == expectedNums[i]; } If all assertions
   * pass, then your solution will be accepted.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [3,2,2,3], val = 3 Output: 2, nums = [2,2,_,_] Explanation: Your function
   * should return k = 2, with the first two elements of nums being 2. It does not matter what you
   * leave beyond the returned k (hence they are underscores). Example 2:
   *
   * <p>Input: nums = [0,1,2,2,3,0,4,2], val = 2 Output: 5, nums = [0,1,4,0,3,_,_,_] Explanation:
   * Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3,
   * and 4. Note that the five elements can be returned in any order. It does not matter what you
   * leave beyond the returned k (hence they are underscores).
   */
  public static int removeElement(int[] nums, int val) {
    var k = 0;
    for (var i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[k++] = nums[i];
      }
    }
    return k;
  }

  /**
   * Given an array nums of size n, return the majority element.
   *
   * <p>The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume
   * that the majority element always exists in the array.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [3,2,3] Output: 3 Example 2:
   *
   * <p>Input: nums = [2,2,1,1,1,2,2] Output: 2
   */
  public static int majorityElement(int[] nums) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    int res = 0, maxCount = 0;
    for (int num : nums) {
      var currNumCount = freqMap.getOrDefault(num, 0) + 1;
      freqMap.put(num, currNumCount);
      if (currNumCount > maxCount) {
        res = num;
        maxCount = currNumCount;
      }
    }
    return res;
  }

  public static int majorityElementNoMap(int[] nums) {
    int count = 0, res = 0;
    for (var num : nums) {
      if (count == 0) {
        res = num;
      }
      count += (res == num ? 1 : -1);
    }
    return res;
  }

  /**
   *
   *
   * <pre>
   *   Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
   *
   * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
   *
   * You must solve this problem without using the library's sort function.
   *
   *
   *
   * Example 1:
   *
   * Input: nums = [2,0,2,1,1,0]
   * Output: [0,0,1,1,2,2]
   * Example 2:
   *
   * Input: nums = [2,0,1]
   * Output: [0,1,2]
   *
   *
   * Constraints:
   *
   * n == nums.length
   * 1 <= n <= 300
   * nums[i] is either 0, 1, or 2.
   *
   *
   * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
   * </pre>
   */
  public static int[] sortColors(int[] nums) {
    var cardinality = 3;
    var colourCount = new int[cardinality];
    for (var num : nums) {
      colourCount[num]++;
    }
    for (int j = 0, i = 0; j < cardinality; j++) {
      while (colourCount[j]-- > 0) {
        nums[i++] = j;
      }
    }
    return nums;
  }

  public static int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
  }

  private static void mergeSort(int[] nums, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    merge(nums, left, mid, right);
  }

  private static void merge(int[] nums, int left, int mid, int right) {
    var temp = new ArrayList<Integer>();
    // left array start index
    int i = left;
    // right array start index
    int j = mid + 1;
    while (i <= mid && j <= right) {
      // correctly sorted
      if (nums[i] <= nums[j]) {
        temp.add(nums[i++]);
      } else {
        temp.add(nums[j++]);
      }
    }
    while (i <= mid) {
      temp.add(nums[i++]);
    }

    while (j <= right) {
      temp.add(nums[j++]);
    }

    for (i = left; i <= right; i++) {
      nums[i] = temp.get(i - left);
    }
  }

  public static int[] reverse(int[] nums) {
    //    var mid = nums.length / 2;
    //    for (int i = 0, j = nums.length - 1; i < mid; i++, j--) {
    //      swap(nums, i, j);
    //    }
    //    return nums;
    return reverse(nums, 0, nums.length - 1);
  }

  public static int[] reverse(int[] nums, int l, int r) {
    while (l < r) {
      swap(nums, l, r);
      l++;
      r--;
    }
    return nums;
  }

  /**
   *
   *
   * <pre>
   *   You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
   *
   * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
   *
   * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
   *
   *
   *
   * Example 1:
   *
   * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
   * Output: [1,2,2,3,5,6]
   * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
   * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
   * Example 2:
   *
   * Input: nums1 = [1], m = 1, nums2 = [], n = 0
   * Output: [1]
   * Explanation: The arrays we are merging are [1] and [].
   * The result of the merge is [1].
   * Example 3:
   *
   * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
   * Output: [1]
   * Explanation: The arrays we are merging are [] and [1].
   * The result of the merge is [1].
   * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
   *
   * </pre>
   */
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    var t = m + n - 1;
    // Merge in reverse order
    while (m > 0 && n > 0) {
      if (nums1[m - 1] > nums2[n - 1]) {
        nums1[t] = nums1[m - 1];
        m--;
      } else {
        nums1[t] = nums2[n - 1];
        n--;
      }
      t--;
    }
    while (n > 0) {
      nums1[t] = nums2[n - 1];
      n--;
      t--;
    }
  }

  /**
   *
   *
   * <pre>
   *   Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
   *
   * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
   *
   * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
   * Return k.
   * Custom Judge:
   *
   * The judge will test your solution with the following code:
   *
   * int[] nums = [...]; // Input array
   * int[] expectedNums = [...]; // The expected answer with correct length
   *
   * int k = removeDuplicates(nums); // Calls your implementation
   *
   * assert k == expectedNums.length;
   * for (int i = 0; i < k; i++) {
   *     assert nums[i] == expectedNums[i];
   * }
   * If all assertions pass, then your solution will be accepted.
   *
   *
   *
   * Example 1:
   *
   * Input: nums = [1,1,2]
   * Output: 2, nums = [1,2,_]
   * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
   * It does not matter what you leave beyond the returned k (hence they are underscores).
   * Example 2:
   *
   * Input: nums = [0,0,1,1,1,2,2,3,3,4]
   * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
   * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
   * It does not matter what you leave beyond the returned k (hence they are underscores).
   * </pre>
   */
  public static int removeDuplicates(int[] nums) {
    var k = 0;
    for (int i = 0; i < nums.length; i++) {
      while ((i + 1) < nums.length && nums[i] == nums[i + 1]) {
        i++;
      }
      nums[k++] = nums[i];
    }
    return k;
  }

  /**
   *
   *
   * <pre>
   *   Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
   *
   * Example 1:
   *
   * Input: nums = [1,2,3,4,5,6,7], k = 3
   * Output: [5,6,7,1,2,3,4]
   * Explanation:
   * rotate 1 steps to the right: [7,1,2,3,4,5,6]
   * rotate 2 steps to the right: [6,7,1,2,3,4,5]
   * rotate 3 steps to the right: [5,6,7,1,2,3,4]
   * Example 2:
   *
   * Input: nums = [-1,-100,3,99], k = 2
   * Output: [3,99,-1,-100]
   * Explanation:
   * rotate 1 steps to the right: [99,-1,-100,3]
   * rotate 2 steps to the right: [3,99,-1,-100]
   * </pre>
   */
  public static void rotate(int[] nums, int k) {
    var res = new int[nums.length];
    k = k % nums.length;
    var j = 0;
    for (int i = nums.length - k; i < nums.length; i++, j++) {
      res[j] = nums[i];
    }
    for (int i = 0; i < nums.length - k; i++, j++) {
      res[j] = nums[i];
    }
    System.arraycopy(res, 0, nums, 0, nums.length);
  }

  public static void rotateNoExtraSpace(int[] nums, int k) {
    reverse(nums);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  /**
   *
   *
   * <pre>
   *   There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
   *
   * Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
   *
   * Note that multiple kids can have the greatest number of candies.
   *
   *
   *
   * Example 1:
   *
   * Input: candies = [2,3,5,1,3], extraCandies = 3
   * Output: [true,true,true,false,true]
   * Explanation: If you give all extraCandies to:
   * - Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
   * - Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
   * - Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
   * - Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
   * - Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
   * Example 2:
   *
   * Input: candies = [4,2,1,1,2], extraCandies = 1
   * Output: [true,false,false,false,false]
   * Explanation: There is only 1 extra candy.
   * Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
   * Example 3:
   *
   * Input: candies = [12,1,12], extraCandies = 10
   * Output: [true,false,true]
   *
   * This question is poorly explained! It took me a long time to understand the question. here's a detailed explanation.
   *
   * Input: candies = [2,3,5,1,3], extraCandies = 3
   * Output: [true,true,true,false,true]
   * Explanation: The current max value is 5 which is held by Kid 3
   *
   * Kid 1, they will have 2 + 3 = 5 candies, which is greater or equal to the current max of 5.
   * Kid 2, they will have 3 + 3 = 6 candies, which is greater or equal to the current max of 5.
   * Kid 3, they will have 5 + 3 = 8 candies, which is greater or equal to the current max of 5.
   * Kid 4, they will have 1 + 3 = 4 candies, which is less than the current max of 5.
   * Kid 5, they will have 3 + 3 = 6 candies, which is greater or equal to the current max of 5.
   *
   * I hope this explains it better
   * </pre>
   */
  public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
    int max = -1;
    for (var candy : candies) {
      if (max < candy) {
        max = candy;
      }
    }
    var res = new ArrayList<Boolean>(candies.length);
    for (int candy : candies) {
      res.add(candy + extraCandies >= max);
    }
    return res;
  }

  /**
   *
   *
   * <pre>
   *   You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
   *
   * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
   *
   *
   *
   * Example 1:
   *
   * Input: flowerbed = [1,0,0,0,1], n = 1
   * Output: true
   * Example 2:
   *
   * Input: flowerbed = [1,0,0,0,1], n = 2
   * Output: false
   *
   * </pre>
   */
  public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    for (var i = 0; i < flowerbed.length && n > 0; i++) {
      if (canPlaceInPosition(flowerbed, i)) {
        n--;
      }
    }
    return n == 0;
  }

  private static boolean canPlaceInPosition(int[] flowerbed, int pos) {
    if (flowerbed[pos] == 1) {
      return false;
    }
    var leftEmpty = pos == 0 || flowerbed[pos - 1] == 0;
    var rightEmpty = pos == flowerbed.length - 1 || flowerbed[pos + 1] == 0;
    if (leftEmpty && rightEmpty) {
      flowerbed[pos] = 1;
      return true;
    }
    return false;
  }

  // https://leetcode.com/problems/insert-interval/
  public static int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> res = new ArrayList<>();
    int i = 0;
    var toBeInserted = newInterval.clone();
    while (i < intervals.length) {
      var curr = intervals[i];
      if (curr[1] < newInterval[0]) {
        res.add(curr);
        i++;
        continue;
      }
      if (curr[0] < newInterval[0]) {
        toBeInserted[0] = curr[0];
      }
      if (newInterval[1] < curr[0]) break;
      toBeInserted[1] = Math.max(curr[1], newInterval[1]);
      i++;
    }
    res.add(toBeInserted);
    while (i < intervals.length) {
      res.add(intervals[i++]);
    }

    return res.stream().toArray(int[][]::new);
  }

  /**
   *
   *
   * <pre>
   *   Given an integer array nums and an integer k, return true if there are two distinct
   *   indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
   *
   * Example 1:
   *
   * Input: nums = [1,2,3,1], k = 3
   * Output: true
   * Example 2:
   *
   * Input: nums = [1,0,1,1], k = 1
   * Output: true
   * Example 3:
   *
   * Input: nums = [1,2,3,1,2,3], k = 2
   * Output: false
   *
   * </pre>
   */
  public static boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> window = new HashSet<>();
    int L = 0;

    for (int R = 0; R < nums.length; R++) {
      if (R - L > k) {
        window.remove(nums[L]);
        L++;
      }
      if (window.contains(nums[R])) {
        return true;
      }
      window.add(nums[R]);
    }
    return false;
  }

  public static int maxArea(int[] heights) {
    var maxArea = 0;
    int l = 0, r = heights.length - 1;
    while (l < r) {
      var minHeight = Math.min(heights[l], heights[r]);
      maxArea = Math.max(maxArea, minHeight * (r - l));
      if (heights[l] > heights[r]) {
        r--;
      } else {
        l++;
      }
    }
    return maxArea;
  }

  public static int candy(int[] ratings) {
    var candies = new int[ratings.length];
    // [1,0,2,3,6]
    // [1,1,1,1,1]
    // [1,1,2,3,4]
    // [2,1,2,3,4]
    java.util.Arrays.fill(candies, 1);
    // Left to right, if current child is ranked higher, then increment by 1.
    for (int i = 1; i < candies.length; i++) {
      if (ratings[i] > ratings[i - 1]) {
        candies[i] = candies[i - 1] + 1;
      }
    }
    for (int i = candies.length - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1]) {
        candies[i] = Math.max(candies[i], candies[i + 1] + 1);
      }
    }
    return java.util.Arrays.stream(candies).sum();
  }

  /**
   * https://leetcode.com/problems/non-overlapping-intervals/ Input: intervals =
   * [[1,2],[2,3],[3,4],[1,3]] Output: 1 Explanation: [1,3] can be removed and the rest of the
   * intervals are non-overlapping.
   */
  public static int eraseOverlapIntervals(int[][] intervals) {
    java.util.Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
    int end = intervals[0][1];
    int count = 1;

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= end) {
        end = intervals[i][1];
        count++;
      }
    }
    return intervals.length - count;
  }

  /**
   *
   *
   * <pre>
   *   Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.
   *
   * A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.
   *
   * You must write an algorithm that runs in O(n) time.
   *
   * Example 1:
   *
   * Input: nums = [2,20,4,10,3,4,5]
   *
   * Output: 4
   * Explanation: The longest consecutive sequence is [2, 3, 4, 5].
   *
   * Example 2:
   *
   * Input: nums = [0,3,2,5,4,6,1,1]
   *
   * Output: 7
   * </pre>
   */
  public static int longestConsecutive(int[] nums) {
    var items = new HashSet<Integer>();
    for (var num : nums) {
      items.add(num);
    }
    // Seq start -> size
    var longest = 0;
    for (var num : nums) {
      // no left neighbour
      if (!items.contains(num - 1)) {
        var length = 1;
        while (items.contains(num + length)) {
          length++;
        }
        longest = Math.max(longest, length);
      }
    }
    return longest;
  }

  public static boolean isValidSudoku(char[][] board) {
    Map<Integer, Set<Character>> cols = new HashMap<>();
    Map<Integer, Set<Character>> rows = new HashMap<>();
    Map<String, Set<Character>> squares = new HashMap<>();
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        var element = board[r][c];
        if (element == '.') {
          continue;
        }
        String squareKey = (r / 3) + "," + (c / 3);
        if (rows.computeIfAbsent(r, k -> new HashSet<>()).contains(element)
            || cols.computeIfAbsent(c, k -> new HashSet<>()).contains(element)
            || squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(element)) {
          return false;
        }
        rows.get(r).add(element);
        cols.get(c).add(element);
        squares.get(squareKey).add(element);
      }
    }
    return true;
  }

  /**
   *
   *
   * <pre>
   *   Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
   *
   * You must write an algorithm with O(log n) runtime complexity.
   *
   * Example 1:
   *
   * Input: nums = [1,3,5,6], target = 5
   * Output: 2
   * Example 2:
   *
   * Input: nums = [1,3,5,6], target = 2
   * Output: 1
   * Example 3:
   *
   * Input: nums = [1,3,5,6], target = 7
   * Output: 4
   * </pre>
   */
  public static int searchInsert(int[] nums, int target) {
    int l = 0, r = nums.length - 1;
    while (l <= r) {
      var m = l + (r - l) / 2;
      if (nums[m] == target) {
        return m;
      }
      if (nums[m] > target) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return l;
  }

  /**
   *
   *
   * <pre>
   *   Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
   *
   * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
   *
   * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
   *
   * Return the minimum integer k such that she can eat all the bananas within h hours.
   *
   * Example 1:
   *
   * Input: piles = [3,6,7,11], h = 8
   * Output: 4
   * Example 2:
   *
   * Input: piles = [30,11,23,4,20], h = 5
   * Output: 30
   * Example 3:
   *
   * Input: piles = [30,11,23,4,20], h = 6
   * Output: 23
   *
   *
   * Constraints:
   *
   * 1 <= piles.length <= 104
   * piles.length <= h <= 109
   * 1 <= piles[i] <= 109
   * </pre>
   */
  public static int minEatingSpeed(int[] piles, int h) {
    var l = 1;
    var r = java.util.Arrays.stream(piles).max().getAsInt();
    var res = r;
    while (l <= r) {
      var k = l + (r - l) / 2;
      int totalTime = 0;
      for (int p : piles) {
        totalTime += (int) Math.ceil((double) p / k);
      }
      if (totalTime <= h) {
        res = k;
        r = k - 1;
      } else {
        l = k + 1;
      }
    }
    return res;
  }

  public static int climbStairsDp(int n) {
    if (n <= 2) return n;
    int last1 = 2, last2 = 1;
    for (int i = 3; i <= n; i++) {
      var temp = last1;
      last1 = last1 + last2;
      last2 = temp;
    }
    return last1;
  }

  public static int climbStairs(int n) {
    return climbStairs(n, new HashMap<>());
  }

  public static int climbStairs(int n, Map<Integer, Integer> memo) {
    // Base cases: If there are 0 or 1 stairs,
    // there is only one way to reach the top.
    // If n < 0, it means this is not a valid way so return 0
    if (n < 0) {
      return 0;
    }
    // If n = 0, it means we have reached the bottom, so return 1
    if (n == 0) {
      return 1;
    }
    if (memo.containsKey(n)) {
      return memo.get(n);
    }
    memo.put(n, climbStairs(n - 1, memo) + climbStairs(n - 2, memo));
    return memo.get(n);
  }

  public static long fib(long n) {
    return fib(n, new HashMap<>());
  }

  public static long fib(long n, Map<Long, Long> memo) {
    if (n < 2) {
      return n;
    }
    if (memo.containsKey(n)) {
      return memo.get(n);
    }
    memo.put(n, fib(n - 1, memo) + fib(n - 2, memo));
    return memo.get(n);
  }

  /**
   *
   *
   * <pre>
   *   You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
   *
   * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
   *
   * Example 1:
   *
   * Input: nums = [1,2,3,1]
   * Output: 4
   * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
   * Total amount you can rob = 1 + 3 = 4.
   * Example 2:
   *
   * Input: nums = [2,7,9,3,1]
   * Output: 12
   * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
   * Total amount you can rob = 2 + 9 + 1 = 12.
   * </pre>
   */
  public static int rob(int[] nums) {
    return rob(nums, 0, new HashMap<>());
  }

  static int rob(int[] nums, int i, Map<Integer, Integer> memo) {
    if (memo.containsKey(i)) {
      return memo.get(i);
    }
    if (i >= nums.length) {
      return 0;
    }
    var max = Math.max(nums[i] + rob(nums, i + 2, memo), rob(nums, i + 1, memo));
    memo.put(i, max);
    return max;
  }

  /** Similar to {@link #rob(int[])} but houses are in circle. */
  public int robCircular(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    return Math.max(
        robCircular(nums, 0, nums.length - 2, new HashMap<>()),
        robCircular(nums, 1, nums.length - 1, new HashMap<>()));
  }

  static int robCircular(int[] nums, int i, int n, Map<String, Integer> memo) {
    var key = String.join(",", String.valueOf(i), String.valueOf(n));
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    if (i > n || n < 0) {
      return 0;
    }
    memo.put(
        key,
        Math.max(robCircular(nums, i, n - 2, memo) + nums[n], robCircular(nums, i, n - 1, memo)));
    return memo.get(key);
  }

  /**
   * Asked in MSFT interview 2.
   *
   * <pre>
   *   Given an array of integers,
   *   generate an output array where element at index i is smallest element
   *   (to the right of i) greater that INPUT[i] in the input array.
   *   INPUT - [30, 130, 46, 140, 66, 110]
   *   OUTPUT- [46, 140, 66, -1, 110, -1]
   * </pre>
   */
  static int[] sortedMin(int[] num) {
    int[] res = new int[num.length];
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = num.length - 1; i >= 0; i--) {
      var n = set.ceiling(num[i]);
      res[i] = Objects.requireNonNullElse(n, -1);
      set.add(num[i]);
    }
    return res;
  }

  /**
   *
   *
   * <pre>
   *   You are given two arrays of equal length, nums1 and nums2.
   *
   * Each element in nums1 has been increased (or decreased in the case of negative) by an integer, represented by the variable x.
   *
   * As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers with the same frequencies.
   *
   * Return the integer x.
   *
   * Example 1:
   *
   * Input: nums1 = [2,6,4], nums2 = [9,7,5]
   *
   * Output: 3
   *
   * Explanation:
   *
   * The integer added to each element of nums1 is 3.
   *
   * Example 2:
   *
   * Input: nums1 = [10], nums2 = [5]
   *
   * Output: -5
   *
   * Explanation:
   *
   * The integer added to each element of nums1 is -5.
   *
   * Example 3:
   *
   * Input: nums1 = [1,1,1,1], nums2 = [1,1,1,1]
   *
   * Output: 0
   *
   * Explanation:
   *
   * The integer added to each element of nums1 is 0.
   * </pre>
   */
  public static int addedInteger(int[] nums1, int[] nums2) {
    java.util.Arrays.sort(nums1);
    java.util.Arrays.sort(nums2);
    return nums2[0] - nums1[0];
  }

  public static int addedIntegerOptimal(int[] nums1, int[] nums2) {
    return java.util.Arrays.stream(nums2).min().orElseThrow()
        - java.util.Arrays.stream(nums1).min().orElseThrow();
  }

  /**
   * https://leetcode.com/problems/plus-one/
   *
   * <pre>
   *   You are given a large integer represented as an integer array digits,
   *   where each digits[i] is the ith digit of the integer. The digits are ordered from
   *   most significant to least significant in left-to-right order.
   *   The large integer does not contain any leading 0's.
   *
   * Increment the large integer by one and return the resulting array of digits.
   *
   * Example 1:
   *
   * Input: digits = [1,2,3]
   * Output: [1,2,4]
   * Explanation: The array represents the integer 123.
   * Incrementing by one gives 123 + 1 = 124.
   * Thus, the result should be [1,2,4].
   * Example 2:
   *
   * Input: digits = [4,3,2,1]
   * Output: [4,3,2,2]
   * Explanation: The array represents the integer 4321.
   * Incrementing by one gives 4321 + 1 = 4322.
   * Thus, the result should be [4,3,2,2].
   * Example 3:
   *
   * Input: digits = [9]
   * Output: [1,0]
   * Explanation: The array represents the integer 9.
   * Incrementing by one gives 9 + 1 = 10.
   * Thus, the result should be [1,0].
   * </pre>
   */
  public static int[] plusOne(int[] digits) {
    LinkedList<Integer> res = new LinkedList<>();
    var carry = 0;
    for (var i = digits.length - 1; i >= 0; i--) {
      var val = digits[i] + (i == (digits.length - 1) ? 1 : 0) + carry;
      if (val > 9) {
        res.addFirst(val % 10);
        carry = val / 10;
      } else {
        res.addFirst(val);
        carry = 0;
      }
    }
    if (carry > 0) {
      res.addFirst(carry);
    }
    return res.stream().mapToInt(Integer::intValue).toArray();
  }

  public static int[] plusOneOptimised(int[] digits) {
    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      }
      digits[i] = 0;
    }

    digits = new int[digits.length + 1];
    digits[0] = 1;
    return digits;
  }

  /**
   * https://leetcode.com/problems/apply-operations-to-an-array/description/
   *
   * <pre>
   *   You are given a 0-indexed array nums of size n consisting of non-negative integers.
   *
   * You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), you will apply the following on the ith element of nums:
   *
   * If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0. Otherwise, you skip this operation.
   * After performing all the operations, shift all the 0's to the end of the array.
   *
   * For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
   * Return the resulting array.
   *
   * Note that the operations are applied sequentially, not all at once.
   *
   * Example 1:
   *
   * Input: nums = [1,2,2,1,1,0]
   * Output: [1,4,2,0,0,0]
   * Explanation: We do the following operations:
   * - i = 0: nums[0] and nums[1] are not equal, so we skip this operation.
   * - i = 1: nums[1] and nums[2] are equal, we multiply nums[1] by 2 and change nums[2] to 0. The array becomes [1,4,0,1,1,0].
   * - i = 2: nums[2] and nums[3] are not equal, so we skip this operation.
   * - i = 3: nums[3] and nums[4] are equal, we multiply nums[3] by 2 and change nums[4] to 0. The array becomes [1,4,0,2,0,0].
   * - i = 4: nums[4] and nums[5] are equal, we multiply nums[4] by 2 and change nums[5] to 0. The array becomes [1,4,0,2,0,0].
   * After that, we shift the 0's to the end, which gives the array [1,4,2,0,0,0].
   * Example 2:
   *
   * Input: nums = [0,1]
   * Output: [1,0]
   * Explanation: No operation can be applied, we just shift the 0 to the end.
   * </pre>
   */
  public static int[] applyOperations(int[] nums) {
    for (int i = 0, j = 1; j < nums.length; i++, j++) {
      if (nums[i] == nums[j]) {
        nums[i] *= 2;
        nums[j] = 0;
      }
    }
    moveZeroes(nums);
    return nums;
  }

  /**
   * https://leetcode.com/problems/count-special-quadruplets/description/
   *
   * <pre>
   *   Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
   *
   * nums[a] + nums[b] + nums[c] == nums[d], and
   * a < b < c < d
   *
   *
   * Example 1:
   *
   * Input: nums = [1,2,3,6]
   * Output: 1
   * Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.
   * Example 2:
   *
   * Input: nums = [3,3,6,4,5]
   * Output: 0
   * Explanation: There are no such quadruplets in [3,3,6,4,5].
   * Example 3:
   *
   * Input: nums = [1,1,1,3,5]
   * Output: 4
   * Explanation: The 4 quadruplets that satisfy the requirement are:
   * - (0, 1, 2, 3): 1 + 1 + 1 == 3
   * - (0, 1, 3, 4): 1 + 1 + 3 == 5
   * - (0, 2, 3, 4): 1 + 1 + 3 == 5
   * - (1, 2, 3, 4): 1 + 1 + 3 == 5
   * </pre>
   */
  public static int countQuadruplets(int[] nums) {
    int res = 0;
    int len = nums.length;
    Map<Integer, Integer> count = new HashMap<>();
    count.put(nums[len - 1] - nums[len - 2], 1);
    for (int b = len - 3; b >= 1; b--) {
      for (int a = b - 1; a >= 0; a--) {
        res += count.getOrDefault(nums[a] + nums[b], 0);
      }

      for (int x = len - 1; x > b; x--) {
        count.put(nums[x] - nums[b], count.getOrDefault(nums[x] - nums[b], 0) + 1);
      }
    }

    return res;
  }

  /**
   * https://leetcode.com/problems/contains-duplicate/description/
   *
   * <pre>
   *   Given an integer array nums, return true if any value appears at least twice in the array,
   *   and return false if every element is distinct.
   * Example 1:
   *
   * Input: nums = [1,2,3,1]
   *
   * Output: true
   *
   * Explanation:
   *
   * The element 1 occurs at the indices 0 and 3.
   *
   * Example 2:
   *
   * Input: nums = [1,2,3,4]
   *
   * Output: false
   *
   * Explanation:
   *
   * All elements are distinct.
   *
   * Example 3:
   *
   * Input: nums = [1,1,1,3,3,4,3,2,4,2]
   *
   * Output: true
   * </pre>
   */
  public boolean containsDuplicate(int[] nums) {
    java.util.Arrays.sort(nums);
    var res = false;
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == nums[i + 1]) {
        return true;
      }
    }
    return res;
  }

  public boolean containsDuplicateSet(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (var num : nums) {
      if (!seen.add(num)) {
        return true;
      }
    }
    return false;
  }

  public static List<List<Integer>> kSum(int[] nums, int k, int target) {
    java.util.Arrays.sort(nums);
    return kSumHelper(nums, k, target, 0);
  }

  private static List<List<Integer>> kSumHelper(int[] nums, int k, int target, int start) {
    List<List<Integer>> res = new LinkedList<>();
    int n = nums.length;
    if (k == 2) {
      int left = start, right = n - 1;
      while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) {
          res.add(List.of(nums[left], nums[right]));
          // Skip duplicates
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }
          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }
          left++;
          right--;
        } else if (sum < target) {
          left++;
        } else {
          right--;
        }
      }
      return res;
    }
    for (int i = start; i <= n - k; i++) {
      // Skip duplicate elements
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }

      // Recursive call for (k-1)-Sum
      List<List<Integer>> subResults = kSumHelper(nums, k - 1, target - nums[i], i + 1);

      // Add the current element to each subset found
      for (List<Integer> subList : subResults) {
        List<Integer> newList = new LinkedList<>(subList);
        newList.addFirst(nums[i]);
        res.add(newList);
      }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/sort-array-by-increasing-frequency/
   *
   * <pre>
   *   Given an array of integers nums, sort the array in increasing order based on the
   *   frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
   *
   * Return the sorted array.
   * </pre>
   */
  public static int[] frequencySort(int[] nums) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (var num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }
    return java.util.Arrays.stream(nums)
        .boxed()
        .sorted(
            (o1, o2) -> {
              var r = Integer.compare(freqMap.get(o1), freqMap.get(o2));
              if (r != 0) {
                return r;
              }
              return Integer.compare(o2, o1);
            })
        .mapToInt(i -> i)
        .toArray();
  }

  /**
   *
   *
   * <pre>
   *   Given an array of integers arr, return true if the number of occurrences of each
   *   value in the array is unique or false otherwise.
   *
   * Example 1:
   *
   * Input: arr = [1,2,2,1,1,3]
   * Output: true
   * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1.
   * No two values have the same number of occurrences.
   * </pre>
   */
  public static boolean uniqueOccurrences(int[] arr) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (var i : arr) {
      freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
    }
    Set<Integer> seenFreq = new HashSet<>();
    for (var value : freqMap.values()) {
      if (!seenFreq.add(value)) {
        return false;
      }
    }
    return true;
  }

  /**
   * A permutation of an array of integers is an arrangement of its members into a sequence or
   * linear order.
   *
   * <p>For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3],
   * [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]. The next permutation of an array of integers
   * is the next lexicographically greater permutation of its integer. More formally, if all the
   * permutations of the array are sorted in one container according to their lexicographical order,
   * then the next permutation of that array is the permutation that follows it in the sorted
   * container. If such arrangement is not possible, the array must be rearranged as the lowest
   * possible order (i.e., sorted in ascending order).
   *
   * <p>For example, the next permutation of arr = [1,2,3] is [1,3,2]. Similarly, the next
   * permutation of arr = [2,3,1] is [3,1,2]. While the next permutation of arr = [3,2,1] is [1,2,3]
   * because [3,2,1] does not have a lexicographical larger rearrangement. Given an array of
   * integers nums, find the next permutation of nums.
   *
   * <p>The replacement must be in place and use only constant extra memory.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [1,2,3] Output: [1,3,2] Example 2:
   *
   * <p>Input: nums = [3,2,1] Output: [1,2,3] Example 3:
   *
   * <p>Input: nums = [1,1,5] Output: [1,5,1]
   *
   * <p>Constraints:
   *
   * <p>1 <= nums.length <= 100 0 <= nums[i] <= 100
   */
  public static void nextPermutation(int[] nums) {
    int n = nums.length;
    int i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
    if (i >= 0) {
      int j = n - 1;
      while (j >= 0 && nums[i] >= nums[j]) {
        j--;
      }
      swap(nums, i, j);
    }
    reverse(nums, i + 1, n - 1);
  }

  /**
   * Given a sorted array of distinct integers and a target value, return the index if the target is
   * found. If not, return the index where it would be if it were inserted in order.
   *
   * <p>You must write an algorithm with O(log n) runtime complexity.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [1,3,5,6], target = 5 Output: 2 Example 2:
   *
   * <p>Input: nums = [1,3,5,6], target = 2 Output: 1 Example 3:
   *
   * <p>Input: nums = [1,3,5,6], target = 7 Output: 4
   */
  public static int searchInsertAssessment(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
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
    return low;
  }

  /**
   * https://leetcode.com/problems/largest-time-for-given-digits/description/
   *
   * <p>Given an array arr of 4 digits, find the latest 24-hour time that can be made using each
   * digit exactly once.
   *
   * <p>24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00
   * and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.
   *
   * <p>Return the latest 24-hour time in "HH:MM" format. If no valid time can be made, return an
   * empty string.
   *
   * <p>Example 1:
   *
   * <p>Input: arr = [1,2,3,4] Output: "23:41" Explanation: The valid 24-hour times are "12:34",
   * "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these
   * times, "23:41" is the latest. Example 2:
   *
   * <p>Input: arr = [5,5,5,5] Output: "" Explanation: There are no valid 24-hour times as "55:55"
   * is not valid.
   */
  public static String largestTimeFromDigits(int[] arr) {
    String ans = "";
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        for (int k = 0; k < 4; ++k) {
          if (i == j || i == k || j == k) {
            continue; // avoid duplicate among i, j & k.
          }
          String h = "" + arr[i] + arr[j],
              m = "" + arr[k] + arr[6 - i - j - k],
              t = h + ":" + m; // hour, minutes, & time.
          if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && ans.compareTo(t) < 0) {
            ans = t; // hour < 24; minute < 60; update result.
          }
        }
      }
    }
    return ans;
  }

  /**
   * https://leetcode.com/problems/next-greater-element-i/?envType=problem-list-v2&envId=monotonic-stack
   *
   * <pre>
   *   The next greater element of some element x in an array is the first greater element
   *   that is to the right of x in the same array.
   * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
   *
   * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and
   * determine the next greater element of nums2[j] in nums2. If there is no next greater element,
   * then the answer for this query is -1.
   *
   * Return an array ans of length nums1.length such that ans[i] is the next greater element as
   * described above.
   *
   * Example 1:
   *
   * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
   * Output: [-1,3,-1]
   * Explanation: The next greater element for each value of nums1 is as follows:
   * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
   * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
   * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
   * Example 2:
   *
   * Input: nums1 = [2,4], nums2 = [1,2,3,4]
   * Output: [3,-1]
   * Explanation: The next greater element for each value of nums1 is as follows:
   * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
   * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
   *
   * </pre>
   */
  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> mp = new HashMap<>();
    Stack<Integer> s = new Stack<>();
    // Find and store the Next greater element in array
    for (int num : nums2) {
      while (!s.isEmpty() && s.peek() < num) {
        mp.put(s.pop(), num);
      }
      s.push(num);
    }
    // Reusing the nums1 array to store the Next greater element
    for (int i = 0; i < nums1.length; i++) {
      nums1[i] = mp.getOrDefault(nums1[i], -1);
    }
    return nums1;
  }

  /**
   *
   *
   * <pre>
   *   Next Greater Element (NGE)
   * 📌 Problem: Given an array, find the next greater element for each element.
   * 🔹 Example:
   *
   * text
   * Copy
   * Edit
   * Input:  [2, 1, 5, 3]
   * Output: [5, 5, -1, -1]  // -1 means no greater element exists
   * </pre>
   */
  public static int[] nextGreaterElements(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    var res = new int[nums.length];
    java.util.Arrays.fill(res, -1);
    for (int i = nums.length - 1; i >= 0; i--) {
      var num = nums[i];
      while (!stack.isEmpty() && stack.peek() <= num) {
        stack.pop();
      }
      if (!stack.isEmpty()) {
        res[i] = stack.peek();
      }
      stack.push(num);
    }
    return res;
  }

  /**
   * https://neetcode.io/problems/daily-temperatures Daily Temperatures You are given an array of
   * integers temperatures where temperatures[i] represents the daily temperatures on the ith day.
   *
   * <p>Return an array result where result[i] is the number of days after the ith day before a
   * warmer temperature appears on a future day. If there is no day in the future where a warmer
   * temperature will appear for the ith day, set result[i] to 0 instead.
   *
   * <p>Example 1:
   *
   * <p>Input: temperatures = [30,38,30,36,35,40,28]
   *
   * <p>Output: [1,4,1,2,1,0,0] Example 2:
   *
   * <p>Input: temperatures = [22,21,20]
   *
   * <p>Output: [0,0,0]
   */
  public static int[] dailyTemperatures(int[] temperatures) {
    var res = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>(); // [index]
    for (int i = 0; i < temperatures.length; i++) {
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        var idx = stack.pop();
        res[idx] = i - idx;
      }
      stack.push(i);
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/sliding-window-maximum/
   *
   * <pre>
   *   You are given an array of integers nums, there is a sliding window of size k
   *   which is moving from the very left of the array to the very right. You can only see
   *   the k numbers in the window. Each time the sliding window moves right by one position.
   *
   * Return the max sliding window.
   *
   * Example 1:
   *
   * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
   * Output: [3,3,5,5,6,7]
   * Explanation:
   * Window position                Max
   * ---------------               -----
   * [1  3  -1] -3  5  3  6  7       3
   *  1 [3  -1  -3] 5  3  6  7       3
   *  1  3 [-1  -3  5] 3  6  7       5
   *  1  3  -1 [-3  5  3] 6  7       5
   *  1  3  -1  -3 [5  3  6] 7       6
   *  1  3  -1  -3  5 [3  6  7]      7
   * Example 2:
   *
   * Input: nums = [1], k = 1
   * Output: [1]
   *
   * Constraints:
   *
   * 1 <= nums.length <= 105
   * -104 <= nums[i] <= 104
   * 1 <= k <= nums.length
   * </pre>
   */
  public static int[] maxSlidingWindow(int[] nums, int k) {
    var res = new ArrayList<Integer>();
    var deque = new LinkedList<Integer>();
    for (int i = 0; i < nums.length; i++) {
      var num = nums[i];
      while (!deque.isEmpty() && deque.getLast() < num) {
        deque.pollLast();
      }
      deque.addLast(num);
      // We got past the first window, its time to start draining..
      if (i >= k && nums[i - k] == deque.getFirst()) {
        deque.pollFirst();
      }

      if (i >= k - 1) {
        res.add(deque.getFirst());
      }
    }
    return res.stream().mapToInt(i -> i).toArray();
  }

  /**
   *
   *
   * <pre>
   *   An axis-aligned rectangle is represented as a list [x1, y1, x2, y2],
   *   where (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2)
   *   is the coordinate of its top-right corner. Its top and bottom edges are
   *   parallel to the X-axis, and its left and right edges are parallel to the Y-axis.
   *
   * Two rectangles overlap if the area of their intersection is positive.
   * To be clear, two rectangles that only touch at the corner or edges do not overlap.
   *
   * Given two axis-aligned rectangles rec1 and rec2, return true if they
   * overlap, otherwise return false.
   *
   *
   * Example 1:
   *
   * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
   * Output: true
   * Example 2:
   *
   * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
   * Output: false
   * Example 3:
   *
   * Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
   * Output: false
   * </pre>
   */
  public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
    // Check if one rectangle is to the left of the other
    boolean isLeft = rec1[2] <= rec2[0] || rec2[2] <= rec1[0];

    // Check if one rectangle is above the other
    boolean isAbove = rec1[3] <= rec2[1] || rec2[3] <= rec1[1];

    // If neither is true, the rectangles overlap
    return !(isLeft || isAbove);
  }

  /**
   * https://leetcode.com/problems/find-pivot-index/description/
   *
   * <p>https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
   * Given an array of integers nums, calculate the pivot index of this array.
   *
   * <p>The pivot index is the index where the sum of all the numbers strictly to the left of the
   * index is equal to the sum of all the numbers strictly to the index's right.
   *
   * <p>If the index is on the left edge of the array, then the left sum is 0 because there are no
   * elements to the left. This also applies to the right edge of the array.
   *
   * <p>Return the leftmost pivot index. If no such index exists, return -1.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [1,7,3,6,5,6] Output: 3 Explanation: The pivot index is 3. Left sum = nums[0]
   * + nums[1] + nums[2] = 1 + 7 + 3 = 11 Right sum = nums[4] + nums[5] = 5 + 6 = 11
   */
  public static int findPivotIndex(int[] nums) {
    var n = nums.length - 1;
    int[] sumLeft = new int[n + 1];
    int[] sumRight = new int[n + 1];
    for (int i = 0, j = n; i <= n; i++, j--) {
      sumLeft[i] = sumLeft[i == 0 ? 0 : i - 1] + nums[i];
      sumRight[j] = sumRight[j == n ? n : j + 1] + nums[j];
    }
    for (int i = 0; i <= n; i++) {
      if (sumLeft[i] == sumRight[i]) {
        return i;
      }
    }
    return -1;
  }

  /** Largest Number At Least Twice of Others */
  public static int dominantIndex(int[] nums) {
    int m = -1, mIndex = -1;
    for (int i = 0; i < nums.length; i++) {
      if (m < nums[i]) {
        m = nums[i];
        mIndex = i;
      }
    }
    // 3,6,1,0
    for (int num : nums) {
      if (num * 2 > m && m != num) {
        return -1;
      }
    }
    return mIndex;
  }

  /**
   * https://neetcode.io/problems/trapping-rain-water
   *
   * <pre>
   *   You are given an array non-negative integers height which represent
   *   an elevation map. Each value height[i] represents the height of a bar, which has a width of 1.
   *
   * Return the maximum area of water that can be trapped between the bars.
   * </pre>
   *
   * <p><img
   * src="https://imagedelivery.net/CLfkmk9Wzy8_9HRyug4EVA/0c25cb81-1095-4382-fff2-6ef77c1fd100/public"></img>
   * Input: height = [0,2,0,3,1,0,1,3,2,1]
   *
   * <p>Output: 9
   */
  public static int trap(int[] height) {
    // [0,2,2,5,6,6,7,10,12,13]
    // [13,13,11,11,8,7,7,6,3,1]
    int n = height.length;
    if (n == 0) {
      return 0;
    }
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];

    leftMax[0] = height[0];
    for (int i = 1; i < n; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], height[i]);
    }

    rightMax[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], height[i]);
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
      res += Math.min(leftMax[i], rightMax[i]) - height[i];
    }
    return res;
  }

  public static int trapOptimised(int[] height) {
    int leftMax = height[0];
    int rightMax = height[height.length - 1];
    int l = 0, r = height.length - 1;
    int water = 0;
    while (l < r) {
      if (leftMax < rightMax) {
        l++;
        leftMax = Math.max(leftMax, height[l]);
        water += leftMax - height[l];
      } else {
        r--;
        rightMax = Math.max(rightMax, height[r]);
        water += rightMax - height[r];
      }
    }
    return water;
  }

  /**
   * https://neetcode.io/problems/find-minimum-in-rotated-sorted-array
   *
   * <pre>
   *   Find min in a rotated sorted array.
   * </pre>
   */
  public static int findMin(int[] nums) {
    int l = 0, r = nums.length - 1;
    int min = nums[0];
    while (l <= r) {
      if (nums[l] < nums[r]) {
        min = Math.min(min, nums[l]);
        break;
      }
      var m = l + (r - l) / 2;
      min = Math.min(min, nums[m]);
      if (nums[m] >= nums[l]) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return min;
  }

  public static int searchSortedRotated(int[] nums, int target) {
    int l = 0, r = nums.length - 1;
    while (l < r) {
      int m = l + (r - l) / 2;
      if (nums[m] > nums[r]) {
        l = m + 1;
      } else {
        r = m;
      }
    }
    var i = java.util.Arrays.binarySearch(nums, 0, l, target);
    if (i >= 0) {
      return i;
    }
    i = java.util.Arrays.binarySearch(nums, l, nums.length, target);
    return i < 0 ? -1 : i;
  }

  /** https://neetcode.io/problems/find-target-in-rotated-sorted-array */
  public static int searchSortedRotatedOptimised(int[] nums, int target) {
    // 3, 4, 5, 6, 1, 2 : target: 1
    int l = 0, r = nums.length - 1;
    while (l <= r) {
      var m = l + (r - l) / 2;
      if (nums[m] == target) {
        return m;
      }
      if (nums[l] <= nums[m]) {
        if (target > nums[m] || target < nums[l]) {
          l = m + 1;
        } else {
          r = m - 1;
        }
      } else {
        if (target < nums[m] || target > nums[r]) {
          r = m - 1;
        } else {
          l = m + 1;
        }
      }
    }
    return -1;
  }

  /** https://leetcode.com/problems/find-closest-number-to-zero/ */
  public static int findClosestNumber(int[] nums) {
    int res = Integer.MIN_VALUE;
    int minDistanceToZero = Integer.MAX_VALUE;
    // [2,-1,1]
    for (var num : nums) {
      var currMin = Math.min(minDistanceToZero, Math.abs(num));
      if (currMin < minDistanceToZero) {
        // Min has changed
        minDistanceToZero = Math.abs(currMin);
        res = num;
      } else if (currMin == minDistanceToZero && res + num == 0) {
        res = Math.max(res, num);
      }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/summary-ranges/
   *
   * <pre>
   *   Example 1:
   *
   * Input: nums = [0,1,2,4,5,7]
   * Output: ["0->2","4->5","7"]
   * Explanation: The ranges are:
   * [0,2] --> "0->2"
   * [4,5] --> "4->5"
   * [7,7] --> "7"
   * Example 2:
   *
   * Input: nums = [0,2,3,4,6,8,9]
   * Output: ["0","2->4","6","8->9"]
   * Explanation: The ranges are:
   * [0,0] --> "0"
   * [2,4] --> "2->4"
   * [6,6] --> "6"
   * [8,9] --> "8->9"
   * </pre>
   */
  public static List<String> summaryRanges(int[] nums) {
    List<String> ans = new ArrayList<>();
    int n = nums.length;
    int i = 0;
    while (i < n) {
      int start = nums[i];
      while (i < n - 1 && nums[i] + 1 == nums[i + 1]) {
        i++;
      }
      if (start != nums[i]) {
        ans.add(start + "->" + nums[i]);
      } else {
        ans.add("" + nums[i]);
      }
      i++;
    }
    return ans;
  }

  /** https://leetcode.com/problems/squares-of-a-sorted-array/ */
  public int[] sortedSquares(int[] nums) {
    int[] sqrs = new int[nums.length];
    int l = 0, r = nums.length - 1, k = sqrs.length - 1;
    while (l <= r) {
      var sqrL = nums[l] * nums[l];
      var sqrR = nums[r] * nums[r];
      if (sqrL > sqrR) {
        sqrs[k--] = sqrL;
        l++;
      } else {
        sqrs[k--] = sqrR;
        r--;
      }
    }
    return sqrs;
  }

  public double findMaxAverage(int[] nums, int k) {
    var maxSum = 0;
    for (int i = 0; i < k; i++) {
      maxSum += nums[i];
    }
    int currentSum = maxSum;
    for (int i = k; i < nums.length; i++) {
      currentSum += nums[i] - nums[i - k];
      maxSum = Math.max(maxSum, currentSum);
    }
    return (double) maxSum / k;
  }

  /**
   * https://leetcode.com/problems/max-consecutive-ones-iii/
   *
   * <pre>
   *   Given a binary array nums and an integer k, return the maximum number
   *   of consecutive 1's in the array if you can flip at most k 0's.
   *
   * Example 1:
   *
   * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
   * Output: 6
   * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
   * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
   * Example 2:
   *
   * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
   * Output: 10
   * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
   * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
   * </pre>
   */
  public static int longestOnes(int[] nums, int k) {
    int left = 0, maxLength = 0, zeroCount = 0;
    for (int right = 0; right < nums.length; ++right) {
      if (nums[right] == 0) {
        zeroCount++;
      }
      while (zeroCount > k) {
        if (nums[left] == 0) {
          zeroCount--;
        }
        left++;
      }
      maxLength = Math.max(maxLength, right - left + 1);
    }
    return maxLength;
  }

  /** https://leetcode.com/problems/minimum-size-subarray-sum/ */
  public int minSubArrayLen(int target, int[] nums) {
    int l = 0, minLen = Integer.MAX_VALUE, currSum = 0;
    for (int r = 0; r < nums.length; r++) {
      currSum += nums[r];
      while (currSum >= target) {
        minLen = Math.min(minLen, r - l + 1);
        currSum -= nums[l++];
      }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
  }

  public static int maximumUniqueSubarray(int[] nums) {
    int l = 0, r = 0, maxSum = 0, currSum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    while (r < nums.length) {
      var num = nums[r];
      currSum += num;
      // count letters
      map.put(num, map.getOrDefault(num, 0) + 1);
      while (map.getOrDefault(num, 0) > 1) {
        currSum -= nums[l];
        map.put(nums[l], map.get(nums[l]) - 1);
        l++;
      }
      maxSum = Math.max(maxSum, currSum);
      r++;
    }
    return maxSum;
  }

  /**
   * https://neetcode.io/problems/median-of-two-sorted-arrays
   *
   * <p>https://leetcode.com/problems/median-of-two-sorted-arrays/description/
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n1 = nums1.length, n2 = nums2.length;
    // Ensure nums1 is the smaller array for simplicity
    if (n1 > n2) return findMedianSortedArrays(nums2, nums1);
    int total = nums1.length + nums2.length;
    int half = (total + 1) / 2;

    int l = 0;
    int r = nums1.length;
    while (l <= r) {
      int m1 = l + (r - l) / 2;
      int m2 = half - m1;

      int left1 = m1 > 0 ? nums1[m1 - 1] : Integer.MIN_VALUE;
      int right1 = m1 < nums1.length ? nums1[m1] : Integer.MAX_VALUE;
      int left2 = m2 > 0 ? nums2[m2 - 1] : Integer.MIN_VALUE;
      int right2 = m2 < nums2.length ? nums2[m2] : Integer.MAX_VALUE;

      if (left1 <= right2 && left2 <= right1) {
        if (total % 2 != 0) {
          return Math.max(left1, left2);
        }
        return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
      } else if (left1 > right2) {
        r = m1 - 1;
      } else {
        l = m1 + 1;
      }
    }
    return -1;
  }

  /** https://leetcode.ca/all/1086.html */
  public static int[][] highFive(int[][] nums) {
    return java.util.Arrays.stream(nums)
        .collect(
            Collectors.groupingBy(
                i -> i[0],
                TreeMap::new,
                Collectors.collectingAndThen(
                    Collectors.mapping(i -> i[1], Collectors.toCollection(PriorityQueue::new)),
                    scores -> {
                      // Min Heap for top 5 scores
                      var minHeap = new PriorityQueue<Integer>();
                      for (int score : scores) {
                        minHeap.offer(score);
                        if (minHeap.size() > 5) {
                          minHeap.poll(); // Remove smallest element if more than 5 exist
                        }
                      }
                      return minHeap.stream().mapToInt(Integer::intValue).sum()
                          / 5; // Integer division
                    })))
        .entrySet()
        .stream()
        .map(e -> new int[] {e.getKey(), e.getValue()})
        .toArray(int[][]::new);
  }

  public static int minZeroArray(int[] nums, int[][] queries) {
    for (int i = 0; i < queries.length; i++) {
      var query = queries[i];
      for (int j = query[0]; j <= query[1]; j++) {
        nums[j] = Math.max(0, nums[j] - query[2]);
      }
      boolean allZeros = true;
      for (int num : nums) {
        if (num == 0) continue;
        allZeros = false;
        break;
      }
      if (allZeros) return i + 1;
    }
    return -1;
  }
}
