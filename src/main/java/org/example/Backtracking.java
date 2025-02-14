package org.example;

import java.util.ArrayList;
import java.util.List;

class Backtracking {

  public static void main(String[] args) {
    System.out.println(subsets(new int[] {}));
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
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
  }

  private static void backtrack(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
    list.add(new ArrayList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      backtrack(list, tempList, nums, i + 1);
      tempList.removeLast();
    }
  }
}
