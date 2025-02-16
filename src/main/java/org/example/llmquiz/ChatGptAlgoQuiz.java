package org.example.llmquiz;

import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

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
    //    System.out.println(findSecondLargest(List.of(3, 4, 6, 8, 8)));
    //    System.out.println(Arrays.toString(findNextGreaterElement(new int[] {4, 1, 2, 7, 5, 3,
    // 8})));
    //    System.out.println(
    //        Arrays.toString(findPreviousSmallerElement(new int[] {4, 1, 2, 7, 5, 3, 8})));
    //    System.out.println(Arrays.toString(findNextSmallerElement(new int[] {4, 1, 2, 7, 5, 3,
    // 8})));

    //    System.out.println(longestSubstringWithoutRepeating("pwwkew"));
    System.out.println(longestConsecutiveSequence(new int[] {1, 2, 3, 4, 5}));
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
        longest = max(longest, length);
      }
    }
    return longest;
  }
}
