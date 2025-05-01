package org.example;

import java.util.*;

class Recursion {

  public static void main(String[] args) {
    //    System.out.println(
    //        wordBreak(
    //
    // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
    //            List.of(
    //                "a",
    //                "aa",
    //                "aaa",
    //                "aaaa",
    //                "aaaaa",
    //                "aaaaaa",
    //                "aaaaaaa",
    //                "aaaaaaaa",
    //                "aaaaaaaaa",
    //                "aaaaaaaaaa")));
    //    System.out.println(partition("madammadam"));
    System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7));
    //    System.out.println(permute(new int[] {1, 2, 3}));
    //    System.out.println(getSubSequences(new int[] {3, 1, 2}));
    //    System.out.println(getSubSequencesWithSum(new int[] {1, 1, 2}, 2));
    //    System.out.println(countAndSay(30));
    //    removeInvalidParentheses(")()()").forEach(System.out::println);
    //    printName("vivek", 5);
    //    printNumsBackTrack(10);
    //    System.out.println(factorial(3));
    //    int[] arr = new int[] {1, 2, 3, 4, 5, 6};
    //    reverseArray(arr, 0);
    //    System.out.println(Arrays.toString(arr));
    //    System.out.println(checkIsPalindrome("madam", 0));
    //    printReverse(10);
  }

  public static void printName(String name, int n) {
    if (n == 0) return;
    System.out.println(name);
    printName(name, n - 1);
  }

  public static void printNums(int k, int n) {
    if (k == n + 1) return;
    System.out.println(k);
    printNums(k + 1, n);
  }

  public static void printNumsBackTrack(int n) {
    if (n == 0) return;
    printNumsBackTrack(n - 1);
    System.out.println(n);
  }

  public static int sum(int n) {
    if (n == 0) return 0;
    return n + sum(n - 1);
  }

  public static int factorial(int n) {
    if (n == 1) return 1;
    return n * factorial(n - 1);
  }

  public static void printReverse(int n) {
    if (n == 0) return;
    System.out.println(n);
    printReverse(n - 1);
  }

  public static void reverseArray(int[] arr, int l) {
    if (l > arr.length / 2) return;
    reverseArray(arr, l + 1);
    var tmp = arr[l];
    arr[l] = arr[arr.length - l - 1];
    arr[arr.length - l - 1] = tmp;
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

  public static boolean checkIsPalindrome(String s, int l) {
    if (l > s.length() / 2) return true;
    return s.charAt(l) == s.charAt(s.length() - l - 1) && checkIsPalindrome(s, l + 1);
  }

  public static List<List<Integer>> getSubSequences(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsequences(nums, 0, res, new ArrayList<>());
    return res;
  }

  public static void subsequences(int[] nums, int i, List<List<Integer>> res, List<Integer> temp) {
    if (i >= nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    temp.addLast(nums[i]);
    subsequences(nums, i + 1, res, temp);
    temp.removeLast();
    subsequences(nums, i + 1, res, temp);
  }

  public static List<List<Integer>> getSubSequencesWithSum(int[] nums, int k) {
    List<List<Integer>> res = new ArrayList<>();
    subsequencesWithSum(nums, 0, res, new ArrayList<>(), 0, k);
    return res;
  }

  public static void subsequencesWithSum(
      int[] nums, int i, List<List<Integer>> res, List<Integer> temp, int currSum, int k) {
    if (i >= nums.length) {
      if (currSum == k) {
        res.add(List.copyOf(temp));
      }
      return;
    }
    temp.addLast(nums[i]);
    subsequencesWithSum(nums, i + 1, res, temp, currSum + nums[i], k);
    temp.removeLast();
    subsequencesWithSum(nums, i + 1, res, temp, currSum, k);
  }

  public static String countAndSay(int n) {
    return recur(n);
  }

  static String recur(int n) {
    if (n == 1) return "1";

    String s = recur(n - 1);
    return countFreq(s);
  }

  // //////////////////////////////////////////////////

  /** Is subset adds upto target. */
  public static boolean isSubsetSum(int[] nums, int target) {
    return isSubsetSum(nums, target, 0, 0);
  }

  private static boolean isSubsetSum(int[] nums, int target, int i, int currentSum) {
    if (i >= nums.length) {
      return currentSum == target;
    }
    // take
    return isSubsetSum(nums, target, i + 1, currentSum + nums[i])
        // don't take
        || isSubsetSum(nums, target, i + 1, currentSum);
  }

  /// //////////////////////////////////////////////////
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    subsets(nums, res, new ArrayList<>(), 0);
    return res;
  }

  private void subsets(int[] nums, List<List<Integer>> res, List<Integer> temp, int i) {
    if (i >= nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    // take
    temp.addLast(nums[i]);
    subsets(nums, res, temp, i + 1);
    // don't take
    temp.removeLast();
    subsets(nums, res, temp, i + 1);
  }

  //////////////////////////////////////////////////
  /**
   * Permutations of Distinct Integers Problem: Given an array of distinct integers nums, return all
   * possible permutations of the array.
   */
  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    permute(nums, res, new ArrayList<>(), used);
    return res;
  }

  private static void permute(
      int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
    if (temp.size() == nums.length) {
      res.add(List.copyOf(temp));
      return;
    }
    for (int j = 0; j < nums.length; j++) {
      if (used[j]) continue;
      temp.addLast(nums[j]);
      used[j] = true;
      permute(nums, res, temp, used);
      used[j] = false;
      temp.removeLast();
    }
  }

  /// /////////////////////////

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    combinationSum(candidates, target, res, new ArrayList<>(), 0);
    return res;
  }

  private static void combinationSum(
      int[] candidates, int target, List<List<Integer>> res, List<Integer> temp, int i) {
    if (0 == target) {
      res.add(List.copyOf(temp));
      return;
    }
    if (i >= candidates.length) {
      return;
    }
    if (target >= candidates[i]) {
      temp.addLast(candidates[i]);
      combinationSum(candidates, target - candidates[i], res, temp, i);
      temp.removeLast();
    }
    combinationSum(candidates, target, res, temp, i + 1);
  }

  /// ////////////////////////////////////
  public static List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    partition(res, s, new ArrayList<>(), 0);
    return res;
  }

  private static void partition(List<List<String>> res, String s, List<String> list, int start) {
    // base condition
    if (start >= s.length()) {
      res.add(List.copyOf(list));
      return;
    }
    for (int end = start; end < s.length(); end++) {
      if (isPalindrome(s, start, end)) {
        list.add(s.substring(start, end + 1));
        partition(res, s, list, end + 1);
        list.removeLast();
      }
    }
  }

  private static boolean isPalindrome(CharSequence sb, int l, int r) {
    while (l <= r) {
      if (sb.charAt(l++) != sb.charAt(r--)) {
        return false;
      }
    }
    return true;
  }

  /// ////////////////////////////////////////////////////

  public static boolean wordBreak(String s, List<String> words) {
    Map<Integer, Boolean> memo = new HashMap<>();
    return wordBreak(s, new HashSet<>(words), 0, memo);
  }

  private static boolean wordBreak(
      String s, Set<String> words, int start, Map<Integer, Boolean> memo) {
    if (memo.containsKey(start)) {
      return memo.get(start);
    }
    if (start == s.length()) {
      return true;
    }
    for (int end = start + 1; end <= s.length(); end++) {
      if (words.contains(s.substring(start, end)) && wordBreak(s, words, end, memo)) {
        memo.put(start, true);
        return true;
      }
    }
    memo.put(start, false); // ✅ This is critical
    return false;
  }

  // Returns the required string, by appending frequency
  static String countFreq(String s) {
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == s.charAt(i - 1)) {
        count++;
      } else {
        sb.append(count);
        sb.append(s.charAt(i - 1));
        count = 1;
      }
    }
    sb.append(count);
    sb.append(s.charAt(s.length() - 1));
    return sb.toString();
  }

  public static List<String> removeInvalidParentheses(String s) {
    List<String> res = new ArrayList<>();
    char[] check = new char[] {'(', ')'};
    removeInvalidParentheses(s, res, check, 0, 0);
    return res;
  }

  public static void removeInvalidParentheses(
      String s, List<String> res, char[] check, int last_i, int last_j) {
    int count = 0;
    int i = last_i;
    while (i < s.length() && count >= 0) {
      if (s.charAt(i) == check[0]) count++;
      if (s.charAt(i) == check[1]) count--;
      i++;
    }
    if (count >= 0) {
      // no extra ')' is detected. We now have to detect extra '(' by reversing the string.
      String reversed = new StringBuffer(s).reverse().toString();
      if (check[0] == '(') {
        removeInvalidParentheses(reversed, res, new char[] {')', '('}, 0, 0);
      } else {
        res.add(reversed);
      }
    } else { // extra ')' is detected and we have to do something
      i -= 1; // 'i-1' is the index of abnormal ')' which makes count<0
      for (int j = last_j; j <= i; j++) {
        if (s.charAt(j) == check[1] && (j == last_j || s.charAt(j - 1) != check[1])) {
          removeInvalidParentheses(s.substring(0, j) + s.substring(j + 1), res, check, i, j);
        }
      }
    }
  }
}
