package org.example.llmquiz;

import static org.example.Arrays.swap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class ChatGptAlgoQuiz {

  /** Write a Java function to find the second largest element in an ArrayList<Integer>. */
  public static Integer findSecondLargest(List<Integer> list) {
    var set = new PriorityQueue<Integer>(Comparator.reverseOrder());
    set.addAll(list);
    set.poll();
    return set.poll();
  }

  /**
   * Implement a method that returns the closest lower and higher numbers in a TreeSet<Integer>
   * given an input value.
   */
  public static Entry<Integer, Integer> findClosestNumbers(TreeSet<Integer> set, int target) {
    return Map.entry(set.lower(target), set.higher(target));
  }

  public static void main(String[] args) {
    System.out.println(subarraysDivByK(new int[] {4, 5, 0, -2, -3, 1}, 5));
    //    System.out.println(pivotIndex(new int[] {1, 7, 3, 6, 5, 6}));
    //    System.out.println(validPalindrome("abcdeca", 2));
    //    sortColors(new int[] {2, 0, 2, 1, 1, 0});
    //    removeDuplicates(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    //    System.out.println(longestSubarrayWithSumExactlyK(new int[] {3, 1, 1, 1, 5, 2, 3}, 5));
    //    System.out.println(
    //        secondLongestConsecutiveSequence(new int[] {100, 4, 200, 1, 3, 2, 8, 9, 10}));
    //    System.out.println(countConsecutiveSequences(new int[] {100, 4, 200, 1, 3, 2}));
    //    System.out.println(longestConsecutiveSequenceList(new int[] {100, 4, 200, 1, 3, 2}));
    //    System.out.println(findSecondLargest(List.of(3, 4, 6, 8, 8)));
    //    System.out.println(Arrays.toString(findNextGreaterElement(new int[] {4, 1, 2, 7, 5, 3,
    // 8})));
    //    System.out.println(
    //        Arrays.toString(findPreviousSmallerElement(new int[] {4, 1, 2, 7, 5, 3, 8})));
    //    System.out.println(Arrays.toString(findNextSmallerElement(new int[] {4, 1, 2, 7, 5, 3,
    // 8})));

    //    System.out.println(longestSubstringWithoutRepeating("pwwkew"));
    //    System.out.println(longestConsecutiveSequence(new int[] {1, 2, 3, 4, 5}));
  }

  /**
   *
   *
   * <pre>
   *   Problem 5: "Next Greater Element"
   * Given an array nums of size n, find the next greater element for each element.
   * The next greater element of nums[i] is the first greater element to the right of nums[i].
   * If there is no greater element, return -1 for that index.
   *
   * input: int[] nums = {4, 1, 2, 7, 5, 3, 8};
   * output: [7, 2, 7, 8, 8, 8, -1]
   * </pre>
   */
  public static int[] findNextGreaterElement(int[] nums) {
    Deque<Integer> stack = new ArrayDeque<>();
    int[] res = new int[nums.length];
    for (int i = nums.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= nums[i]) {
        stack.pop();
      }
      res[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(nums[i]);
    }
    return res;
  }

  public static int[] findPreviousSmallerElement(int[] nums) {
    Deque<Integer> stack = new ArrayDeque<>();
    int[] res = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && stack.peek() >= nums[i]) {
        stack.pop();
      }
      res[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(nums[i]);
    }
    return res;
  }

  public static int[] findNextSmallerElement(int[] nums) {
    Deque<Integer> stack = new ArrayDeque<>();
    int[] res = new int[nums.length];
    for (int i = nums.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() >= nums[i]) {
        stack.pop();
      }
      res[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(nums[i]);
    }
    return res;
  }

  /**
   *
   *
   * <pre>
   *   LeetCode-Style Problem: "Find the Longest Substring Without Repeating Characters"
   * Given a string s, find the length of the longest substring that contains no repeating
   * characters.
   * Input: String s = "abcabcbb";
   * output: 3
   *
   * Input: String s = "bbbbb";
   * output: 1
   *
   * Input: String s = "pwwkew";
   * output: 3
   * </pre>
   */
  public static int longestSubstringWithoutRepeating(String s) {
    int l = 0, r = 0;
    int max = 0;
    Set<Character> set = new HashSet<>();
    while (l < s.length() && r < s.length()) {
      var c = s.charAt(r);
      max = Math.max(max, r - l);
      if (!set.contains(c)) {
        r++;
        set.add(c);
      } else {
        set.clear();
        l = r;
      }
    }
    return max;
  }

  /**
   *
   *
   * <pre>
   *   LeetCode-Style Problem: "Longest Consecutive Sequence"
   * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
   *
   * 🔹 Constraints:
   * 0 ≤ nums.length ≤ 10^5
   * nums[i] can be positive, negative, or zero.
   * ⚡ You must implement an O(n) solution.
   *
   * For int[] nums = {100, 4, 200, 1, 3, 2};, return 4
   * For int[] nums = {9, 1, 4, 7, 3, -1, 0, 5, 8, -2, 6};, return 7.
   * </pre>
   */
  public static int longestConsecutiveSequence(int[] nums) {
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

  // Actually return the seq as well.
  public static List<Integer> longestConsecutiveSequenceList(int[] nums) {
    var items = new HashSet<Integer>();
    for (var num : nums) {
      items.add(num);
    }
    var ret = new ArrayList<Integer>();
    var longest = 0;
    for (var num : nums) {
      // no left neighbour
      if (!items.contains(num - 1)) {
        var temp = new ArrayList<Integer>();
        var length = 1;
        temp.add(num);
        while (items.contains(num + length)) {
          temp.add(num);
          length++;
        }
        if (longest < length) {
          longest = length;
          ret = temp;
        }
      }
    }
    return ret;
  }

  // 100, 4, 200, 1, 3, 2
  public static int countConsecutiveSequences(int[] nums) {
    Set<Integer> items = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    int count = 0;
    for (var num : nums) {
      if (!items.contains(num - 1)) {
        count++;
      }
    }
    return count;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [100, 4, 200, 1, 3, 2, 8, 9, 10]
   * Output: 3
   * Explanation:
   * - Longest sequence is `[1, 2, 3, 4]` (length = 4).
   * - Second longest sequence is `[8, 9, 10]` (length = 3).
   *
   * Input: nums = [1, 2, 3, 4, 5]
   * Output: -1
   * Explanation: Only one sequence exists.
   * </pre>
   */
  public static int secondLongestConsecutiveSequence(int[] nums) {
    Set<Integer> items = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    NavigableSet<Integer> lenSet = new TreeSet<>(Comparator.reverseOrder());
    for (var num : nums) {
      // no left neighbour
      if (!items.contains(num - 1)) {
        var length = 1;
        while (items.contains(num + length)) {
          length++;
        }
        lenSet.add(length);
      }
    }
    var first = lenSet.first();
    var second = lenSet.higher(first);
    return second == null ? -1 : second;
  }

  /**
   * Input: nums = [2, 3, 5, 7, 1], k = 8 Output: 2 Explanation: The longest valid subarray is `[2,
   * 3]` (sum = 5) or `[7, 1]` (sum = 8).
   */
  public static int longestSubarrayWithSumAtMostK(int[] nums, int k) {
    int l = 0, maxLen = 0, currSum = 0;
    for (int r = 0; r < nums.length; r++) {
      currSum += nums[r];
      while (currSum > k) {
        currSum -= nums[l];
        l++;
      }
      maxLen = Math.max(maxLen, r - l + 1);
    }
    return maxLen;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [1, 2, 3, 4, 5], k = 9
   * Output: 3
   * Explanation: The longest subarray is `[2, 3, 4]`, which sums to `9`.
   *
   * Input: nums = [3, 1, 1, 1, 5, 2, 3], k = 5
   * Output: 4
   * Explanation: `[1, 1, 1, 2]` is the longest subarray with sum exactly `5`.
   * </pre>
   */
  public static int longestSubarrayWithSumExactlyK(int[] nums, int k) {
    int l = 0, maxLen = 0, currSum = 0;
    for (int r = 0; r < nums.length; r++) {
      currSum += nums[r];
      while (currSum >= k) {
        if (currSum == k) {
          maxLen = Math.max(maxLen, r - l + 1);
        }
        currSum -= nums[l];
        l++;
      }
    }
    return maxLen;
  }

  public static int minSubarrayWithSumAtLeastK(int[] nums, int k) {
    int l = 0, minLen = Integer.MAX_VALUE, currSum = 0;
    for (int r = 0; r < nums.length; r++) {
      currSum += nums[r];
      while (currSum >= k) {
        minLen = Math.min(minLen, r - l + 1);
        currSum -= nums[l];
        l++;
      }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [2, 1, 5, 1, 3, 2], k = 3
   * Output: 9
   * Explanation: The subarray `[5, 1, 3]` has the maximum sum of `9`.
   *
   * Input: nums = [4, 2, 1, 7, 8, 1, 2, 8, 1, 0], k = 3
   * Output: 16
   * Explanation: The subarray `[7, 8, 1]` or `[8, 1, 7]` has the maximum sum of `16`.
   * </pre>
   */
  public static int maxSumSubarrayOfSizeK(int[] nums, int k) {
    if (nums.length < k) {
      return 0;
    }
    int l = 0, maxSum = 0, currSum = 0;
    for (int r = 0; r < nums.length; r++) {
      currSum += nums[r];
      if (r - l + 1 == k) {
        maxSum = Math.max(maxSum, currSum);
        currSum -= nums[l];
        l++;
      }
    }
    return maxSum;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [2, 7, 11, 15], k = 9
   * Output: [1, 2]
   * Explanation: nums[0] + nums[1] = 2 + 7 = 9.
   *
   * Input: nums = [1, 3, 4, 5, 7, 10], k = 8
   * Output: [2, 4]
   * Explanation: nums[1] + nums[3] = 3 + 5 = 8.
   * </pre>
   */
  public static int[] twoSumSorted(int[] nums, int k) {
    int l = 0, r = nums.length - 1;
    while (l < r) {
      var sum = nums[l] + nums[r];
      if (sum == k) {
        return new int[] {l, r};
      } else if (sum < k) {
        r--;
      } else {
        l++;
      }
    }
    return new int[] {};
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [1, 1, 2]
   * Output: 2
   * Explanation: The array is modified in-place to `[1, 2, _]`.
   * The first two elements remain, and we ignore the rest.
   *
   * Input: nums = [0,0,1,1,1,2,2,3,3,4]
   * Output: 5
   * Explanation: The array is modified in-place to `[0, 1, 2, 3, 4, _, _, _, _, _]`.
   * </pre>
   */
  public static int removeDuplicates(int[] nums) {
    int l = 1;
    for (int r = 1; r < nums.length; r++) {
      if (nums[r] != nums[r - 1]) {
        nums[l] = nums[r]; // Place the unique element at index l
        l++;
      }
    }
    return l;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [3, 2, 2, 3], val = 3
   * Output: 2
   * Explanation: The modified array is [2, 2, _], and the new length is 2.
   *
   * Input: nums = [0, 1, 2, 2, 3, 0, 4, 2], val = 2
   * Output: 5
   * Explanation: The modified array is [0, 1, 3, 0, 4, _, _, _], and the new length is 5.
   * </pre>
   */
  public static int removeElement(int[] nums, int val) {
    int l = 0;
    for (int r = 0; r < nums.length; r++) {
      if (nums[r] != val) {
        nums[l++] = nums[r];
      }
    }
    return l;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [0, 1, 0, 3, 12]
   * Output: [1, 3, 12, 0, 0]
   * Explanation: All non-zero elements retain their original order.
   *
   * Input: nums = [0, 0, 1]
   * Output: [1, 0, 0]
   * </pre>
   */
  public static void moveZeroes(int[] nums) {
    removeElement(nums, 0);
    //    int l = 0;
    //    for (int r = 0; r < nums.length; r++) {
    //      if (nums[r] != 0) {
    //        nums[l++] = nums[r]; // Place non-zero element at index l
    //      }
    //    }
    //    while (l < nums.length) {
    //      nums[l++] = 0;
    //    }
  }

  /** {@link org.example.Arrays#sortColors(int[])} alternative. */
  public static void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;
    while (mid <= high) {
      if (nums[mid] == 0) {
        swap(nums, low++, mid++); // Move 0 to the left
      } else if (nums[mid] == 1) {
        mid++; // Keep 1 in the middle
      } else {
        swap(nums, mid, high--); // Move 2 to the right
      }
    }
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> triplets = new ArrayList<>();
    Arrays.sort(nums); // Step 1: Sort the array

    for (int i = 0; i < nums.length - 2; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicate values for `i`

      int l = i + 1, r = nums.length - 1;
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];

        if (sum == 0) {
          triplets.add(List.of(nums[i], nums[l], nums[r]));

          // Move past duplicates
          while (l < r && nums[l] == nums[l + 1]) l++;
          while (l < r && nums[r] == nums[r - 1]) r--;

          l++;
          r--;
        } else if (sum < 0) {
          l++; // Increase sum
        } else {
          r--; // Decrease sum
        }
      }
    }
    return triplets;
  }

  public static int maxArea(int[] height) {
    int l = 0, r = height.length - 1;
    int maxArea = 0;
    while (l < r) {
      var minHeight = Math.min(height[l], height[r]);
      maxArea = Math.max(maxArea, minHeight * (r - l));
      if (height[l] > height[r]) {
        r--;
      } else {
        l++;
      }
    }
    return maxArea;
  }

  public static boolean isPalindromeByDeletingOneChar(String s) {
    int l = 0, r = s.length() - 1;
    int diffCount = 0;
    while (l < r) {
      if (s.charAt(l) == s.charAt(r)) {
        l++;
        r--;
        continue;
      } else {
        diffCount++;
      }
      if (diffCount > 1) {
        return false;
      }
    }
    return true;
  }

  // Valid palindrome with at max k deletions allowed.
  public static boolean validPalindrome(String s, int k) {
    return isValid(s, 0, s.length() - 1, k, new HashMap<>());
  }

  private static boolean isValid(String s, int l, int r, int k, Map<String, Boolean> memo) {
    if (l >= r) return true; // Base case: valid palindrome
    if (k < 0) return false; // No deletions left

    String key = l + "," + r + "," + k; // Memoization key
    if (memo.containsKey(key)) return memo.get(key);

    if (s.charAt(l) == s.charAt(r)) {
      memo.put(key, isValid(s, l + 1, r - 1, k, memo));
    } else {
      memo.put(key, isValid(s, l + 1, r, k - 1, memo) || isValid(s, l, r - 1, k - 1, memo));
    }
    return memo.get(key);
  }

  /**
   *
   *
   * <pre>
   * Input: nums = [1, 7, 3, 6, 5, 6]
   * Output: 3
   * Explanation:
   * Left sum `[1, 7, 3]` = `11`, Right sum `[5, 6]` = `11`.
   *
   * Input: nums = [2, 1, -1]
   * Output: 0
   * Explanation:
   * Left sum `[] = 0`, Right sum `[1, -1] = 0`.
   *
   * Input: nums = [1, 2, 3]
   * Output: -1
   * Explanation: No valid pivot index.
   * </pre>
   */
  public static int pivotIndex(int[] nums) {
    var totalSum = Arrays.stream(nums).sum();
    int leftSum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (leftSum == totalSum - leftSum - nums[i]) return i;
      leftSum += nums[i];
    }
    return -1;
  }

  /**
   *
   *
   * <pre>
   *   Input: nums = [4, 5, 0, -2, -3, 1], k = 5
   * Output: 7
   * Explanation: The subarrays that sum to a multiple of `5` are:
   * [4, 5, 0]
   * [5, 0]
   * [0]
   * [0, -2, -3, 1]
   * [-2, -3, 1]
   * [5, 0, -2, -3]
   * [4, 5, 0, -2, -3, 1]
   *
   *
   * prefixsum = [4,9,9,7,4,5]
   * </pre>
   */
  public static int subarraysDivByK(int[] nums, int k) {
    int leftSum = 0;
    Map<Integer, Integer> remainderCount = new HashMap<>();
    remainderCount.put(0, 1);
    int count = 0;
    for (int num : nums) {
      leftSum = leftSum + num;
      var remainder = (leftSum % k + k) % k;
      count += remainderCount.getOrDefault(remainder, 0);
      remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
    }
    return count;
  }
}
