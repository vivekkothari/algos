package org.example.llmquiz;

import static java.lang.Integer.MAX_VALUE;
import static org.example.Arrays.swap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChatGptAlgoQuiz {

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
    findWords(
        new char[][] {
          {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
        },
        new String[] {"oath", "pea", "eat", "rain"});
    //    System.out.println(cutOffTree(List.of(List.of(1, 2, 3), List.of(0, 0, 4), List.of(7, 6,
    // 5))));
    //    System.out.println(openLock(new String[] {"0201", "0101", "0102", "1212", "2002"},
    // "0202"));
    //    var board =
    //        new char[][] {
    //          {'X', 'X', 'X', 'X'},
    //          {'X', 'O', 'O', 'X'},
    //          {'X', 'X', 'O', 'X'},
    //          {'X', 'O', 'X', 'X'},
    //        };
    //    surroundedRegion(board);
    //    System.out.println(Arrays.deepToString(board));
    //    wallsAndGate(
    //        new int[][] {
    //          {MAX_VALUE, -1, 0, MAX_VALUE},
    //          {MAX_VALUE, MAX_VALUE, MAX_VALUE, -1},
    //          {MAX_VALUE, -1, MAX_VALUE, -1},
    //          {0, -1, MAX_VALUE, MAX_VALUE}
    //        });
    //    System.out.println(subarraysDivByK(new int[] {4, 5, 0, -2, -3, 1}, 5));
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
    int l = 0, minLen = MAX_VALUE, currSum = 0;
    for (int r = 0; r < nums.length; r++) {
      currSum += nums[r];
      while (currSum >= k) {
        minLen = Math.min(minLen, r - l + 1);
        currSum -= nums[l];
        l++;
      }
    }
    return minLen == MAX_VALUE ? 0 : minLen;
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
   *
   * Also see {@link org.example.Arrays#findPivotIndex(int[])}.
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

  /** https://leetcode.com/problems/number-of-islands/ */
  public static int numIslands(char[][] grid) {
    var m = grid.length;
    var n = grid[0].length;
    int count = 0;
    // Explore all 4 directions
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          // Found a new island
          count++;
          numIslandsDfsRecur(grid, i, j, directions, m, n);
        }
      }
    }
    return count;
  }

  private static void numIslandsBfs(char[][] grid, int i, int j, int[][] directions, int m, int n) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {i, j});
    grid[i][j] = '0';
    while (!queue.isEmpty()) {
      var poll = queue.poll();
      int r = poll[0];
      int c = poll[1];
      for (int[] dir : directions) {
        int nr = r + dir[0];
        int nc = c + dir[1];

        // Check if within bounds, is land ('1'), and not visited
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '1') {
          queue.offer(new int[] {nr, nc});
          grid[nr][nc] = '0';
        }
      }
    }
  }

  private static void numIslandsDfsIter(
      char[][] grid, int i, int j, int[][] directions, int m, int n) {
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[] {i, j});
    grid[i][j] = '0';
    while (!stack.isEmpty()) {
      var poll = stack.pop();
      int r = poll[0];
      int c = poll[1];
      for (int[] dir : directions) {
        int nr = r + dir[0];
        int nc = c + dir[1];

        // Check if within bounds, is land ('1'), and not visited
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == '1') {
          stack.push(new int[] {nr, nc});
          grid[nr][nc] = '0';
        }
      }
    }
  }

  private static void numIslandsDfsRecur(
      char[][] grid, int i, int j, int[][] directions, int m, int n) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') return; // Base case
    grid[i][j] = '0'; // Mark as visited
    for (int[] dir : directions) {
      numIslandsDfsRecur(grid, i + dir[0], j + dir[1], directions, m, n);
    }
  }

  private static void surroundedRegionDfsIter(
      char[][] grid, int i, int j, int[][] directions, int m, int n) {
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[] {i, j});
    grid[i][j] = 'S';
    while (!stack.isEmpty()) {
      int[] cur = stack.pop();
      for (int[] direction : directions) {
        int nextRow = direction[0] + cur[0];
        int nextCol = direction[1] + cur[1];
        if (nextRow >= 0
            && nextRow < m
            && nextCol >= 0
            && nextCol < n
            && grid[nextRow][nextCol] == 'O') {
          grid[nextRow][nextCol] = 'S';
          stack.push(new int[] {nextRow, nextCol});
        }
      }
    }
  }

  /** https://leetcode.com/problems/number-of-closed-islands/ */
  public static int closedIsland(int[][] grid) {
    var m = grid.length;
    var n = grid[0].length;
    // Explore all 4 directions, bottom, top, right, left
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // Top row
    for (int j = 0; j < n; j++) {
      if (grid[0][j] == 0) {
        closedIslandBfs(grid, 0, j, directions, m, n);
      }
    }
    // bottom row
    for (int j = 0; j < n; j++) {
      if (grid[m - 1][j] == 0) {
        closedIslandBfs(grid, m - 1, j, directions, m, n);
      }
    }
    // left column
    for (int i = 0; i < m; i++) {
      if (grid[i][0] == 0) {
        closedIslandBfs(grid, i, 0, directions, m, n);
      }
    }
    // right column
    for (int i = 0; i < m; i++) {
      if (grid[i][n - 1] == 0) {
        closedIslandBfs(grid, i, n - 1, directions, m, n);
      }
    }
    int closedIslands = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) { // Found a new closed island
          closedIslandBfs(grid, i, j, directions, m, n); // Mark the island
          closedIslands++; // Increment count
        }
      }
    }
    return closedIslands;
  }

  private static void closedIslandBfs(
      int[][] grid, int i, int j, int[][] directions, int m, int n) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {i, j});
    grid[i][j] = 1;
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      for (int[] direction : directions) {
        int nextRow = direction[0] + cur[0];
        int nextCol = direction[1] + cur[1];
        if (nextRow >= 0
            && nextRow < m
            && nextCol >= 0
            && nextCol < n
            && grid[nextRow][nextCol] == 0) {
          grid[nextRow][nextCol] = 1;
          queue.offer(new int[] {nextRow, nextCol});
        }
      }
    }
  }

  public static int numDistinctIslands(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left
    Set<Set<String>> uniqueIslands = new HashSet<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          Set<String> shape = new HashSet<>();
          numDistinctIslands(grid, i, j, shape, directions, m, n);
          uniqueIslands.add(shape);
        }
      }
    }
    return uniqueIslands.size();
  }

  private static void numDistinctIslands(
      int[][] grid, int i, int j, Set<String> shape, int[][] directions, int m, int n) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {i, j});
    grid[i][j] = 0; // Mark as visited
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int r = cur[0], c = cur[1];

      // Store relative position (r - i, c - j)
      shape.add((r - i) + "," + (c - j));
      for (int[] dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
          grid[nr][nc] = 0; // Mark visited
          queue.offer(new int[] {nr, nc});
        }
      }
    }
  }

  /** https://leetcode.com/problems/shortest-bridge/ */
  public static int shortestBridge(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // Step 1: Find the first island and mark it
    boolean found = false;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          markIsland(grid, i, j, queue, directions, m, n);
          found = true;
          break;
        }
      }
      if (found) break;
    }

    // Step 2: BFS expansion to find the shortest bridge
    int distance = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] cur = queue.poll();
        int r = cur[0], c = cur[1];
        for (int[] dir : directions) {
          int nr = r + dir[0], nc = c + dir[1];
          if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
            if (grid[nr][nc] == 1) {
              return distance;
            } else if (grid[nr][nc] == 0) {
              queue.offer(new int[] {nr, nc});
              grid[nr][nc] = -1; // Mark as visited water
            }
          }
        }
      }
      distance++; // Move this here for clarity
    }
    return -1; // Should never happen
  }

  // Helper function to mark the first island
  private static void markIsland(
      int[][] grid, int i, int j, Queue<int[]> queue, int[][] directions, int m, int n) {
    Queue<int[]> bfsQueue = new LinkedList<>();
    bfsQueue.offer(new int[] {i, j});
    grid[i][j] = 2; // Mark as visited island

    while (!bfsQueue.isEmpty()) {
      int[] cur = bfsQueue.poll();
      int r = cur[0], c = cur[1];
      queue.offer(new int[] {r, c}); // Add border cells to BFS queue

      for (int[] dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
          grid[nr][nc] = 2; // Mark as visited island
          bfsQueue.offer(new int[] {nr, nc});
        }
      }
    }
  }

  /**
   * https://leetcode.com/problems/flood-fill/
   *
   * <pre>
   * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
   * Output: [[2,2,2],[2,2,0],[2,0,1]]
   * </pre>
   */
  public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int m = image.length, n = image[0].length;
    Queue<int[]> queue = new LinkedList<>();
    var origColor = image[sr][sc];
    if (origColor == color) {
      return image;
    }
    queue.offer(new int[] {sr, sc});
    image[sr][sc] = color;
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while (!queue.isEmpty()) {
      var cur = queue.poll();
      int r = cur[0], c = cur[1];
      for (var dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == origColor) {
          image[nr][nc] = color;
          queue.offer(new int[] {nr, nc});
        }
      }
    }
    return image;
  }

  /**
   * https://leetcode.com/problems/rotting-oranges/description/
   *
   * <pre>
   * You are given an m x n grid where each cell can have one of three values:
   * 0 representing an empty cell,
   * 1 representing a fresh orange, or
   * 2 representing a rotten orange.
   * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
   *
   * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
   * If this is impossible, return -1.
   * </pre>
   */
  public static int orangesRotting(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int minutes = -1;
    Queue<int[]> queue = new LinkedList<>();
    int countFreshOrange = 0;
    // Store all rotting oranges here.
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) queue.offer(new int[] {i, j});
        if (grid[i][j] == 1) countFreshOrange++;
      }
    }
    if (countFreshOrange == 0) return 0;
    if (queue.isEmpty()) return -1;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        var cur = queue.poll();
        int r = cur[0], c = cur[1];
        for (var dir : directions) {
          int nr = r + dir[0], nc = c + dir[1];
          if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
            grid[nr][nc] = 2;
            countFreshOrange--;
            queue.offer(new int[] {nr, nc});
          }
        }
      }
      minutes++;
    }
    return countFreshOrange == 0 ? minutes : -1;
  }

  /** https://leetcode.com/problems/word-search/ */
  public boolean exist(char[][] board, String word) {
    // Try to start DFS from every cell
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (existDfs(board, word, i, j, 0)) {
          return true; // If word is found, return true
        }
      }
    }
    return false;
  }

  private boolean existDfs(char[][] board, String word, int r, int c, int index) {
    // Base Case: If we matched all characters in word
    if (index == word.length()) {
      return true;
    }

    // Boundary Check & Character Match Check
    if (r < 0
        || c < 0
        || r >= board.length
        || c >= board[0].length
        || board[r][c] != word.charAt(index)) {
      return false;
    }

    // Mark as visited by modifying the board temporarily
    char temp = board[r][c];
    board[r][c] = '#';

    // Explore all 4 directions
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int[] dir : directions) {
      if (existDfs(board, word, r + dir[0], c + dir[1], index + 1)) {
        return true;
      }
    }

    // Backtrack (restore the original character)
    board[r][c] = temp;
    return false;
  }

  /**
   * https://leetcode.ca/all/286.html
   *
   * <pre>
   *   You are given a m x n 2D grid initialized with these three possible values.
   *
   * -1 - A wall or an obstacle.
   * 0 - A gate.
   * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to
   * represent INF as you may assume that the distance to a gate is less than 2147483647.
   * Fill each empty room with the distance to its nearest gate. If it is
   * impossible to reach a gate, it should be filled with INF.
   *
   * Example:
   *
   * Given the 2D grid:
   *
   * INF  -1  0  INF
   * INF INF INF  -1
   * INF  -1 INF  -1
   *   0  -1 INF INF
   * After running your function, the 2D grid should be:
   *
   *   3  -1   0   1
   *   2   2   1  -1
   *   1  -1   2  -1
   *   0  -1   3   4
   * </pre>
   */
  public static void wallsAndGate(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // We start from gate
        if (grid[i][j] == 0) {
          queue.offer(new int[] {i, j});
        }
      }
    }
    while (!queue.isEmpty()) {
      var curr = queue.poll();
      int r = curr[0], c = curr[1];
      for (var dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == MAX_VALUE) {
          grid[nr][nc] = grid[r][c] + 1; // Update distance
          queue.offer(new int[] {nr, nc});
        }
      }
    }
  }

  /** https://leetcode.com/problems/minimum-genetic-mutation/ */
  public static int minMutation(String startGene, String endGene, String[] bank) {
    var words = Arrays.stream(bank).collect(Collectors.toSet());
    if (!words.contains(endGene)) {
      return -1;
    }
    var allowedChars = List.of('A', 'C', 'G', 'T');
    Queue<String> queue = new LinkedList<>();
    queue.offer(startGene);
    int level = 1; // Level starts from 1 because we count the `beginWord`
    while (!queue.isEmpty()) {
      int size = queue.size(); // Process all words at the current level
      for (int i = 0; i < size; i++) {
        var word = queue.poll();
        for (var j = 0; j < word.length(); j++) {
          var sb = new StringBuilder(word);
          char originalChar = word.charAt(j);
          for (var ch : allowedChars) {
            if (ch == originalChar) continue; // Skip the original character
            sb.setCharAt(j, ch);
            var newWord = sb.toString();
            if (newWord.equals(endGene)) {
              return level + 1; // Return steps including this last transformation
            }
            if (words.contains(newWord)) {
              queue.offer(newWord);
              words.remove(newWord); // Remove to avoid re-visiting
            }
          }
        }
      }
      level++; // Move to the next BFS level
    }
    return -1;
  }

  public static int openLock(String[] deadends, String target) {
    var visited = Arrays.stream(deadends).collect(Collectors.toSet());
    var start = "0000";
    if (visited.contains(start)) return -1;
    if (start.equals(target)) return 0;
    Queue<String> queue = new LinkedList<>();
    queue.offer(start);
    visited.add(start);
    int level = 1; // Level starts from 1 because we count the `beginWord`
    while (!queue.isEmpty()) {
      var size = queue.size();

      for (int i = 0; i < size; i++) {
        var word = queue.poll();

        for (int j = 0; j < word.length(); j++) {
          var sb = new StringBuilder(word);
          int originalChar = word.charAt(j) - '0';

          for (int k = 0; k < 2; k++) {
            var combo = (10 + originalChar + (k == 0 ? 1 : -1)) % 10;
            sb.setCharAt(j, (char) (combo + '0'));
            var newWord = sb.toString();
            if (newWord.equals(target)) {
              return level;
            }

            if (!visited.contains(newWord)) {
              visited.add(newWord);
              queue.offer(newWord);
            }
          }
        }
      }
      level++; // Move to the next BFS level
    }
    return -1;
  }

  /**
   * https://leetcode.com/problems/cut-off-trees-for-golf-event/ Input: forest =
   * [[1,2,3],[0,0,4],[7,6,5]] Output: 6
   */
  public static int cutOffTree(List<List<Integer>> forest) {
    int m = forest.size(), n = forest.get(0).size();
    List<int[]> trees = new ArrayList<>();

    // Step 1: Extract trees and sort by height
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (forest.get(i).get(j) >= 1) {
          trees.add(new int[] {i, j, forest.get(i).get(j)});
        }
      }
    }
    trees.sort(Comparator.comparingInt(a -> a[2])); // Sort by height

    int totalSteps = 0;
    int startX = 0, startY = 0;

    // Step 2: BFS to find the shortest path between trees
    for (int[] tree : trees) {
      int steps = cutOffTreeBfs(forest, startX, startY, tree[0], tree[1]);
      if (steps == -1) return -1; // If a tree is unreachable, return -1
      totalSteps += steps;
      startX = tree[0]; // Move to the next starting position
      startY = tree[1];
    }
    return totalSteps;
  }

  // BFS function to find the shortest path from (sr, sc) to (tr, tc)
  private static int cutOffTreeBfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
    if (sr == tr && sc == tc) return 0; // Already at the target

    int m = forest.size(), n = forest.getFirst().size();
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[m][n];
    queue.offer(new int[] {sr, sc, 0}); // {row, col, steps}
    visited[sr][sc] = true;

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int r = curr[0], c = curr[1], steps = curr[2];

      for (int[] dir : directions) {
        int nr = r + dir[0], nc = c + dir[1];
        if (nr >= 0
            && nr < m
            && nc >= 0
            && nc < n
            && !visited[nr][nc]
            && forest.get(nr).get(nc) != 0) {

          if (nr == tr && nc == tc) return steps + 1; // Reached target

          queue.offer(new int[] {nr, nc, steps + 1});
          visited[nr][nc] = true;
        }
      }
    }
    return -1; // Target tree is unreachable
  }

  public static List<String> findWords(char[][] board, String[] words) {
    Trie trie = buildTrie(words);
    Set<String> res = new HashSet<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(board, trie, res, i, j);
      }
    }
    return new ArrayList<>(res);
  }

  private static void dfs(char[][] board, Trie node, Set<String> res, int i, int j) {
    if (i < 0
        || i >= board.length
        || j < 0
        || j >= board[0].length
        || board[i][j] == '#'
        || node.next[board[i][j] - 'a'] == null) {
      return;
    }
    if (node.next[board[i][j] - 'a'].word != null) {
      res.add(node.next[board[i][j] - 'a'].word);
    }

    // Go to next char
    node = node.next[board[i][j] - 'a'];
    char c = board[i][j];
    board[i][j] = '#';
    dfs(board, node, res, i - 1, j);
    dfs(board, node, res, i + 1, j);
    dfs(board, node, res, i, j - 1);
    dfs(board, node, res, i, j + 1);
    board[i][j] = c;
  }

  private static Trie buildTrie(String[] words) {
    Trie root = new Trie();
    for (String w : words) {
      Trie p = root;
      for (char c : w.toCharArray()) {
        if (p.next[c - 'a'] == null) {
          p.next[c - 'a'] = new Trie();
        }
        p = p.next[c - 'a']; // will point to curr char
      }
      p.word = w;
    }
    return root;
  }

  private static class Trie {
    Trie[] next = new Trie[26];
    String word = null;
  }
}
