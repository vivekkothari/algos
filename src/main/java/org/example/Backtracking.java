package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class Backtracking {

  public static void main(String[] args) {
    System.out.println(removeDuplicateLetters("cbacdcbc"));
    //    largestNumber(new int[] {0, 0, 0, 0});
    //    canCompleteCircuit(new int[] {1, 2, 3, 4, 5}, new int[] {3, 4, 5, 1, 2});
    //    jump(new int[] {2, 3, 1, 1, 4});
    //    System.out.println(isMatch("aabcc", "a*c"));
    //    System.out.println(restoreIpAddresses("101023"));
    //    System.out.println(subsets(new int[] {}));
    //    System.out.println(permute(new int[] {1, 2, 3}));
    //    System.out.println(findAllBinaryStrings(2));
    //    System.out.println(permute("ABC", true));
    //    System.out.println(permute("ABC", false));
    //    System.out.println(generateParenthesis(5));
    //    System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7));
    //    System.out.println(combinationSumNoReuse(new int[] {2, 3, 6, 7}, 7));
    //    findSolution(Integer::sum, 5);
  }

  /**
   * https://leetcode.com/problems/generate-parentheses/?envType=daily-question&envId=2025-02-19
   *
   * <pre>
   *   Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
   * Example 1:
   *
   * Input: n = 3
   * Output: ["((()))","(()())","(())()","()(())","()()()"]
   * Example 2:
   *
   * Input: n = 1
   * Output: ["()"]
   * </pre>
   */
  public static List<String> generateParenthesis(int n) {
    var res = new ArrayList<String>();
    generateParenthesis(res, 0, 0, new StringBuilder(), n);
    return res;
  }

  static void generateParenthesis(List<String> res, int open, int closed, StringBuilder sb, int n) {
    if (open == closed && open == n) {
      res.add(sb.toString());
      return;
    }
    if (open < n) {
      sb.append('(');
      generateParenthesis(res, open + 1, closed, sb, n);
      sb.deleteCharAt(sb.length() - 1);
    }
    if (closed < open) {
      sb.append(')');
      generateParenthesis(res, open, closed + 1, sb, n);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/subsets/description/
   *
   * <pre>
   *   Given an integer array nums of unique elements, return all possible
   * subsets
   *  (the power set).
   *
   * The solution set must not contain duplicate subsets. Return the solution in any order.
   *
   * Example 1:
   *
   * Input: nums = [1,2,3]
   * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
   * Example 2:
   *
   * Input: nums = [0]
   * Output: [[],[0]]
   *
   * </pre>
   */
  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    java.util.Arrays.sort(nums);
    subsets(list, new ArrayList<>(), nums, 0);
    return list;
  }

  private static void subsets(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      subsets(list, tempList, nums, i + 1);
      tempList.removeLast();
    }
  }

  /**
   * https://leetcode.com/problems/combination-sum/solutions/6146998/simple-solution/
   *
   * <pre>
   *   Given an array of distinct integers candidates and a target integer target, return a list
   *   of all unique combinations of candidates where the chosen numbers sum to target.
   *   You may return the combinations in any order.
   *
   * The same number may be chosen from candidates an unlimited number of times.
   * Two combinations are unique if the
   * frequency
   *  of at least one of the chosen numbers is different.
   *
   * The test cases are generated such that the number of unique combinations that sum up
   * to target is less than 150 combinations for the given input.
   *
   * Example 1:
   *
   * Input: candidates = [2,3,6,7], target = 7
   * Output: [[2,2,3],[7]]
   * Explanation:
   * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
   * 7 is a candidate, and 7 = 7.
   * These are the only two combinations.
   * Example 2:
   *
   * Input: candidates = [2,3,5], target = 8
   * Output: [[2,2,2,2],[2,3,3],[3,5]]
   * Example 3:
   *
   * Input: candidates = [2], target = 1
   * Output: []
   * </pre>
   */
  public static List<List<Integer>> combinationSum(int[] nums, int target) {
    var res = new ArrayList<List<Integer>>();
    combinationSum(nums, target, 0, new ArrayList<>(), 0, res);
    return res;
  }

  private static void combinationSum(
      int[] nums, int target, int k, List<Integer> temp, int total, List<List<Integer>> res) {
    if (total == target) {
      res.add(List.copyOf(temp));
      return;
    }
    if (total > target || k >= nums.length) {
      return;
    }
    temp.add(nums[k]);
    combinationSum(nums, target, k, temp, total + nums[k], res);
    temp.removeLast();
    combinationSum(nums, target, k + 1, temp, total, res);
  }

  public static List<List<Integer>> combinationSumNoReuse(int[] nums, int target) {
    var res = new ArrayList<List<Integer>>();
    var used = new boolean[nums.length];
    combinationSumNoReuse(res, new ArrayList<>(), 0, nums, target, 0, used);
    return res;
  }

  private static void combinationSumNoReuse(
      List<List<Integer>> res,
      List<Integer> temp,
      int total,
      int[] nums,
      int target,
      int k,
      boolean[] used) {
    if (total == target) {
      res.add(List.copyOf(temp));
      return;
    }
    if (total > target || k >= nums.length) {
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) continue;
      used[i] = true;
      temp.add(nums[i]); // choose an element
      combinationSumNoReuse(res, temp, total + nums[i], nums, target, k, used);
      used[i] = false;
      temp.removeLast(); // undo the last choice
    }
  }

  /**
   * https://leetcode.com/problems/permutations/?envType=daily-question&envId=2025-02-19 Given an
   * array nums of distinct integers, return all the possible permutations . You can return the
   * answer in any order.
   *
   * <p>Example 1:
   *
   * <p>Input: nums = [1,2,3] Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]] Example 2:
   *
   * <p>Input: nums = [0,1] Output: [[0,1],[1,0]] Example 3:
   *
   * <p>Input: nums = [1] Output: [[1]]
   */
  public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    permute(list, new ArrayList<>(), nums, new boolean[nums.length]);
    return list;
  }

  public static void permute(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
    if (nums.length == tempList.size()) {
      list.add(new ArrayList<>(tempList));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) continue;
      used[i] = true;
      tempList.add(nums[i]); // choose an element
      permute(list, tempList, nums, used); // explore more options
      used[i] = false;
      tempList.removeLast(); // undo the last choice
    }
  }

  public static List<String> permute(String string, boolean reuseAllowed) {
    List<String> res = new ArrayList<>();
    var used = new boolean[string.length()];
    permute(res, new StringBuilder(), string, used, reuseAllowed);
    return res;
  }

  private static void permute(
      List<String> res, StringBuilder sb, String s, boolean[] used, boolean reuseAllowed) {
    if (sb.length() == s.length()) {
      res.add(sb.toString());
      return;
    }
    char[] charArray = s.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      if (used[i] && !reuseAllowed) continue;
      used[i] = true;
      var c = charArray[i];
      sb.append(c);
      permute(res, sb, s, used, reuseAllowed);
      used[i] = false;
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * Print All Binary Strings of Length N Problem: Given an integer N, print all binary strings of
   * length N. Example: N = 2 → { "00", "01", "10", "11" }
   *
   * <p>✅ Key Concept: Either choose 0 or 1 at each step.
   */
  public static List<String> findAllBinaryStrings(int n) {
    List<String> res = new ArrayList<>();
    findAllBinaryStrings(res, new StringBuilder(), n);
    return res;
  }

  private static void findAllBinaryStrings(List<String> list, StringBuilder sb, int n) {
    if (sb.length() == n) {
      list.add(sb.toString());
      return;
    }
    for (var c : "01".toCharArray()) {
      sb.append(c);
      findAllBinaryStrings(list, sb, n);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/find-the-punishment-number-of-an-integer/description/?envType=daily-question&envId=2025-02-15
   *
   * <pre>
   *   Given a positive integer n, return the punishment number of n.
   *
   * The punishment number of n is defined as the sum of the squares of all integers i such that:
   *
   * 1 <= i <= n
   * The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals i.
   *
   *
   * Example 1:
   *
   * Input: n = 10
   * Output: 182
   * Explanation: There are exactly 3 integers i in the range [1, 10] that satisfy the conditions in the statement:
   * - 1 since 1 * 1 = 1
   * - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 and 1 with a sum equal to 8 + 1 == 9.
   * - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 and 0 with a sum equal to 10 + 0 == 10.
   * Hence, the punishment number of 10 is 1 + 81 + 100 = 182
   * Example 2:
   *
   * Input: n = 37
   * Output: 1478
   * Explanation: There are exactly 4 integers i in the range [1, 37] that satisfy the conditions in the statement:
   * - 1 since 1 * 1 = 1.
   * - 9 since 9 * 9 = 81 and 81 can be partitioned into 8 + 1.
   * - 10 since 10 * 10 = 100 and 100 can be partitioned into 10 + 0.
   * - 36 since 36 * 36 = 1296 and 1296 can be partitioned into 1 + 29 + 6.
   * Hence, the punishment number of 37 is 1 + 81 + 100 + 1296 = 1478
   * </pre>
   */
  public static int punishmentNumber(int n) {
    int punishmentNum = 0;

    // Iterate through numbers in range [1, n]
    for (int currentNum = 1; currentNum <= n; currentNum++) {
      int squareNum = currentNum * currentNum;

      // Check if valid partition can be found and add squared number if so
      if (canPartition(Integer.toString(squareNum), currentNum)) {
        punishmentNum += squareNum;
      }
    }
    return punishmentNum;
  }

  public static boolean canPartition(String stringNum, int target) {
    // Valid Partition Found
    if (stringNum.isEmpty() && target == 0) {
      return true;
    }

    // Invalid Partition Found
    if (target < 0) {
      return false;
    }

    // Recursively check all partitions for a valid partition
    for (int index = 0; index < stringNum.length(); index++) {
      String left = stringNum.substring(0, index + 1);
      String right = stringNum.substring(index + 1);
      int leftNum = Integer.parseInt(left);

      if (canPartition(right, target - leftNum)) {
        return true;
      }
    }
    return false;
  }

  /**
   * https://leetcode.com/problems/gray-code/description/
   *
   * <pre>An n-bit gray code sequence is a sequence of 2n integers where:
   *
   * Every integer is in the inclusive range [0, 2^(n - 1)],
   * The first integer is 0,
   * An integer appears no more than once in the sequence,
   * The binary representation of every pair of adjacent integers differs by exactly one bit, and
   * The binary representation of the first and last integers differs by exactly one bit.
   * Given an integer n, return any valid n-bit gray code sequence.
   *
   * Example 1:
   *
   * Input: n = 2
   * Output: [0,1,3,2]
   * Explanation:
   * The binary representation of [0,1,3,2] is [00,01,11,10].
   * - 00 and 01 differ by one bit
   * - 01 and 11 differ by one bit
   * - 11 and 10 differ by one bit
   * - 10 and 00 differ by one bit
   * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
   * - 00 and 10 differ by one bit
   * - 10 and 11 differ by one bit
   * - 11 and 01 differ by one bit
   * - 01 and 00 differ by one bit
   * Example 2:
   *
   * Input: n = 1
   * Output: [0,1]</pre>
   */
  public static List<Integer> grayCode(int n) {
    List<Integer> v = new ArrayList<>();
    for (int i = 0; i < Math.pow(2, n); i++) {
      v.add(i ^ (i >> 1));
    }
    return v;
  }

  interface CustomFunction {
    // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
    int f(int x, int y);
  }

  /**
   *
   *
   * <pre>
   *   Given a callable function f(x, y) with a hidden formula and a value z, reverse engineer the formula and return all positive integer pairs x and y where f(x,y) == z. You may return the pairs in any order.
   *
   * While the exact formula is hidden, the function is monotonically increasing, i.e.:
   *
   * f(x, y) < f(x + 1, y)
   * f(x, y) < f(x, y + 1)
   * The function interface is defined like this:
   *
   * interface CustomFunction {
   * public:
   *   // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
   *   int f(int x, int y);
   * };
   * We will judge your solution as follows:
   *
   * The judge has a list of 9 hidden implementations of CustomFunction, along with a way to generate an answer key of all valid pairs for a specific z.
   * The judge will receive two inputs: a function_id (to determine which implementation to test your code with), and the target z.
   * The judge will call your findSolution and compare your results with the answer key.
   * If your results match the answer key, your solution will be Accepted.
   *
   *
   * Example 1:
   *
   * Input: function_id = 1, z = 5
   * Output: [[1,4],[2,3],[3,2],[4,1]]
   * Explanation: The hidden formula for function_id = 1 is f(x, y) = x + y.
   * The following positive integer values of x and y make f(x, y) equal to 5:
   * x=1, y=4 -> f(1, 4) = 1 + 4 = 5.
   * x=2, y=3 -> f(2, 3) = 2 + 3 = 5.
   * x=3, y=2 -> f(3, 2) = 3 + 2 = 5.
   * x=4, y=1 -> f(4, 1) = 4 + 1 = 5.
   * Example 2:
   *
   * Input: function_id = 2, z = 5
   * Output: [[1,5],[5,1]]
   * Explanation: The hidden formula for function_id = 2 is f(x, y) = x * y.
   * The following positive integer values of x and y make f(x, y) equal to 5:
   * x=1, y=5 -> f(1, 5) = 1 * 5 = 5.
   * x=5, y=1 -> f(5, 1) = 5 * 1 = 5.
   * </pre>
   */
  public static List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
    List<List<Integer>> res = new ArrayList<>();
    for (int x = 1, y = 100; x <= 100 && y >= 1; )
      if (customfunction.f(x, y) > z) y--;
      else if (customfunction.f(x, y) < z) x++;
      else {
        res.add(List.of(x, y));
        y--;
      }
    return res;
  }

  public static List<String> restoreIpAddresses(String s) {
    var ipes = new ArrayList<String>();
    f(s, 0, "", 0, ipes);
    return ipes;
  }

  private static boolean isIp(String ip) {
    if (ip.length() > 3 || ip.isEmpty()) return false;
    if (ip.length() > 1 && ip.charAt(0) == '0') return false;
    return Integer.parseInt(ip) <= 255;
  }

  private static void f(String s, int index, String ip, int dot, List<String> ipes) {
    // base case
    if (dot == 3) {
      var lastPart = s.substring(index);
      if (isIp(lastPart)) {
        ip += lastPart;
        ipes.add(ip);
      }
      return;
    }

    // do all the stuff
    for (int i = index; i < s.length(); i++) {
      var part = s.substring(index, i + 1);
      if (isIp(part)) {
        int k = part.length();
        ip += part + ".";
        f(s, i + 1, ip, dot + 1, ipes);
        ip = ip.substring(0, ip.length() - k - 1);
      }
    }
  }

  public static boolean isMatch(String s, String p) {
    int sLen = s.length(), pLen = p.length();
    int sIdx = 0, pIdx = 0;
    int starIdx = -1, sTmpIdx = -1;

    while (sIdx < sLen) {
      // Case 1: Characters match or '?' matches any single character
      if (pIdx < pLen && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?')) {
        sIdx++;
        pIdx++;
      }
      // Case 2: '*' matches zero or more characters
      else if (pIdx < pLen && p.charAt(pIdx) == '*') {
        // Remember the index of the star
        starIdx = pIdx;
        // Remember the index of the last string character
        sTmpIdx = sIdx;
        pIdx++;
      }
      // Case 3: Mismatch and backtrack to last '*'
      else if (starIdx != -1) {
        // We then need to check if next pattern char is something else.
        pIdx = starIdx + 1;
        // Move sIdx to the next character after the last matched character
        sIdx = ++sTmpIdx;
      }
      // Case 4: No match possible
      else {
        return false;
      }
    }

    // Check remaining characters in pattern are '*'
    while (pIdx < pLen && p.charAt(pIdx) == '*') {
      pIdx++;
    }

    return pIdx == pLen;
  }

  /** https://leetcode.com/problems/jump-game-ii */
  public static int jump(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length && j <= i + nums[i]; j++) {
        dp[j] = Math.min(dp[j], dp[i] + 1);
      }
    }
    return dp[nums.length - 1];
  }

  public static int canCompleteCircuit(int[] gas, int[] cost) {
    int tc = Arrays.stream(cost).sum();
    int tg = Arrays.stream(gas).sum();

    if (tc > tg) return -1;

    int n = gas.length;
    int start = 0;
    int tank = 0;

    for (int i = 0; i < n; i++) {
      tank += gas[i];
      if (tank >= cost[i]) {
        tank -= cost[i];
      } else {
        // We can't start from i, so we set start as i+1 and reset the tank.
        start = i + 1;
        tank = 0;
      }
    }

    return start;
  }

  /** https://leetcode.com/problems/largest-number/solutions/5783508/largest-number/ */
  public static String largestNumber(int[] nums) {
    var arr =
        Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (b + a).compareTo(a + b))
            .collect(Collectors.joining());
    return arr.charAt(0) == '0' ? "0" : arr;
  }

  /** https://leetcode.com/problems/remove-duplicate-letters */
  public static String removeDuplicateLetters(String s) {
    int[] lastIndex = new int[26];
    for (int i = 0; i < s.length(); i++) {
      lastIndex[s.charAt(i) - 'a'] = i; // track the lastIndex of character presence
    }
    boolean[] seen = new boolean[26]; // keep track seen
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      int curr = s.charAt(i) - 'a';
      if (seen[curr]) {
        // if seen continue as we need to pick one char only
        continue;
      }
      while (!st.isEmpty() && st.peek() > curr && i < lastIndex[st.peek()]) {
        seen[st.pop()] = false; // pop out and mark unseen
      }
      st.push(curr); // add into stack
      seen[curr] = true; // mark seen
    }

    StringBuilder sb = new StringBuilder();
    while (!st.isEmpty()) {
      sb.append((char) (st.pop() + 'a'));
    }
    return sb.reverse().toString();
  }
}
