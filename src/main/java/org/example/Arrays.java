package org.example;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Arrays {

  public static void main(String[] args) {
    //    System.out.println(Arrays.toString(twoSum(new int[] {3, 2, 4}, 6)));
    //    System.out.println(maxStockProfit(new int[] {7, 1, 5, 3, 6, 4}));
    //    var nums = new int[] {1, 2, 3, 0, 4, 0, 5};
    //    moveZeroes(nums);
    //    System.out.println(Arrays.toString(nums));
    //
    //    System.out.println(maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    //    System.out.println(
    //        Arrays.toString(productExceptSelfUsingDivision(new int[] {-1, 1, 0, -3, 3})));
    //    System.out.println(
    //        Arrays.toString(productExceptSelfWithoutDivision(new int[] {-1, 1, 0, -3, 3})));
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
    //    System.out.println(java.util.Arrays.toString(sortColours(new int[] {2, 0, 2, 1, 1, 0})));
    //    var nums1 = new int[] {1, 2, 3, 0, 0, 0};
    //    merge(nums1, 3, new int[] {2, 5, 6}, 3);
    //    System.out.println(java.util.Arrays.toString(nums1));

    //    var nums2 = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    //    System.out.println(removeDuplicates(nums2));
    //    System.out.println(java.util.Arrays.toString(nums2));

    //    System.out.println(java.util.Arrays.toString(twoSumSortedArray(new int[] {2, 7, 11, 15},
    // 9)));
    //    System.out.println(threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    //    var nums = new int[] {1, 2, 3, 4, 5, 6, 7};
    //    rotateNoExtraSpace(nums, 3);
    //    System.out.println(java.util.Arrays.toString(nums));

    //    System.out.println(minJumps(new int[] {2, 3, 1, 1, 4}));
    //    System.out.println(canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1));
    //    System.out.println(canPlaceFlowers(new int[] {1, 0}, 1));
    //    System.out.println(containsNearbyDuplicate(new int[] {99, 99}, 2));
    //    System.out.println(maxArea(new int[] {1, 7, 2, 5, 4, 7, 3, 6}));
    //    System.out.println(searchInsert(new int[] {1, 3, 5, 6}, 7));

    //    System.out.println(climbStairs(5));
    //    System.out.println(fib(50));
    //    System.out.println(rob(new int[] {2, 7, 9, 3, 1}));
    System.out.println(java.util.Arrays.toString(plusOne(new int[] {9})));
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
   *
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
      if (numbers[l] + numbers[r] == target) {
        break;
      } else if (numbers[l] + numbers[r] > target) {
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
      if (nums[i] > 0) break;
      if (i > 0 && nums[i] == nums[i - 1]) continue;

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

  public static int maxStockProfit(int[] stockPrices) {
    var minPrice = Integer.MAX_VALUE;
    var maxProfit = 0;
    for (var todaysPrice : stockPrices) {
      minPrice = Math.min(minPrice, todaysPrice);
      var currentProfit = todaysPrice - minPrice;
      maxProfit = max(maxProfit, currentProfit);
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

  private static void swap(int[] nums, int j, int i) {
    var temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }

  public static int maxSubArray(int[] nums) {
    var maxSum = 0;
    var currentSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      currentSum = max(nums[i], currentSum + nums[i]);
      maxSum = max(maxSum, currentSum);
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

  public static int missingNumber(int[] nums) {
    var n = nums.length;
    var expectedSum = n * (n + 1) / 2;
    var actualSum = 0;
    for (var num : nums) {
      actualSum += num;
    }
    return expectedSum - actualSum;
  }

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
        merged.getLast()[1] = max(merged.getLast()[1], interval[1]);
      }
    }
    // Step 4: Convert the list to a 2D array and return it
    return merged.toArray(new int[merged.size()][]);
  }

  public static int binarySearch(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
      var mid = (low + high) / 2;
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
    for (int i = 0; i < bucket.length; i++) bucket[i] = new ArrayList<>();
    for (int key : freq.keySet()) {
      bucket[freq.get(key)].add(key);
    }
    // gather result
    List<Integer> res = new ArrayList<>();
    for (int i = bucket.length - 1; i >= 0; i--) {
      res.addAll(bucket[i]);
      if (res.size() >= k) break;
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
  public static int[] sortColours(int[] nums) {
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
    if (left >= right) return;
    int mid = (left + right) / 2;
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
      int temp = nums[l];
      nums[l] = nums[r];
      nums[r] = temp;
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
      if (max < candy) max = candy;
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
      maxArea = max(maxArea, Math.min(heights[l], heights[r]) * (r - l));
      if (heights[l] > heights[r]) {
        r--;
      } else {
        l++;
      }
    }
    return maxArea;
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
        longest = max(longest, length);
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
        if (element == '.') continue;
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
      if (nums[m] == target) return m;
      if (nums[m] > target) r = m - 1;
      else l = m + 1;
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

  public static int climbStairs(int n) {
    return climbStairs(n, new HashMap<>());
  }

  public static int climbStairs(int n, Map<Integer, Integer> memo) {
    // Base cases: If there are 0 or 1 stairs,
    // there is only one way to reach the top.
    // If n < 0, it means this is not a valid way so return 0
    if (n < 0) return 0;
    // If n = 0, it means we have reached the bottom, so return 1
    if (n == 0) return 1;
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
    var max = max(nums[i] + rob(nums, i + 2, memo), rob(nums, i + 1, memo));
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
}
