package org.example.llmquiz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
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
    System.out.println(longestSubarrayWithSumExactlyK(new int[] {3, 1, 1, 1, 5, 2, 3}, 5));
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
}
