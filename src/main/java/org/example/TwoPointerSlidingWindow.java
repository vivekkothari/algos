package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoPointerSlidingWindow {

  public static void main(String[] args) {
    maxScore(new int[] {1, 79, 80, 1, 1, 1, 200, 1}, 3);
    numberOfSubstrings("abcabc");
    numSubarraysWithSum(new int[] {0, 0, 0, 0, 0}, 0);
  }

  /// ///////////////////////////
  /// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
  /// ///////////////////////////
  public static int maxScore(int[] cardPoints, int k) {
    int maxSum = 0;
    // Get right sum.
    for (int i = cardPoints.length - k; i < cardPoints.length; i++) {
      maxSum += cardPoints[i];
    }
    int l = cardPoints.length - k;
    int r = 0;
    int curSum = maxSum;
    // [1,2,3,4,5]
    while (k > 0) {
      curSum -= cardPoints[l];
      curSum += cardPoints[r];
      maxSum = Math.max(maxSum, curSum);
      l = (l + 1) % cardPoints.length;
      k--;
      r++;
    }
    return maxSum;
  }

  /// /////////////////////
  /// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
  /// ////////////////////
  public static int lengthOfLongestSubstring(String s) {
    int l = 0, r = 0, res = 0;
    // using bool array is most optimised
    boolean[] charSet = new boolean[256];
    while (r < s.length()) {
      while (charSet[s.charAt(r)]) {
        charSet[s.charAt(l)] = false;
        l++;
      }
      charSet[s.charAt(r)] = true;
      res = Math.max(res, r - l + 1);
      r++;
    }
    return res;
  }

  /// //////////////////////////////////
  /// https://leetcode.com/problems/max-consecutive-ones-iii/
  /// Given a binary array nums and an integer k, return the maximum number of
  /// consecutive 1's in the array if you can flip at most k 0's.
  /// ///////////////////////////////////////////////////////
  public static int longestOnes(int[] nums, int k) {
    int l = 0, r = 0, zeroCount = 0, maxLen = 0;
    while (r < nums.length) {
      if (nums[r] == 0) {
        zeroCount++;
      }
      while (zeroCount > k) {
        if (nums[l] == 0) zeroCount--;
        l++;
      }
      maxLen = Math.max(maxLen, r - l + 1);
      r++;
    }
    return maxLen;
  }

  /// ////////////////////////////////////
  /// https://leetcode.com/problems/fruit-into-baskets/
  /// ///////////////////////////////////
  public static int totalFruit(int[] fruits) {
    int l = 0, r = 0, total = 0, numBaskets = 2;
    // total type of fruits is atmax of len fruits.length
    Map<Integer, Integer> hash = new HashMap<>();
    while (r < fruits.length) {
      int currentFruit = fruits[r];
      hash.put(currentFruit, hash.getOrDefault(currentFruit, 0) + 1);

      while (hash.size() > numBaskets) {
        int prevFruit = fruits[l];
        hash.compute(
            prevFruit,
            (key, oldValue) -> {
              var newValue = oldValue == null ? 0 : oldValue - 1;
              return newValue <= 0 ? null : newValue;
            });
        l++;
      }

      total = Math.max(total, r - l + 1);

      r++;
    }
    return total;
  }

  /// ////////////////////////////////////
  /// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description
  /// ///////////////////////////////////
  public static int lengthOfLongestSubstringKDistinct(String s, int k) {
    int l = 0, r = 0, total = 0;
    Map<Integer, Integer> hash = new HashMap<>();
    while (r < s.length()) {
      int currentFruit = s.charAt(r);
      hash.put(currentFruit, hash.getOrDefault(currentFruit, 0) + 1);

      while (hash.size() > k) {
        int prevFruit = s.charAt(l);
        hash.compute(
            prevFruit,
            (key, oldValue) -> {
              var newValue = oldValue == null ? 0 : oldValue - 1;
              return newValue <= 0 ? null : newValue;
            });
        l++;
      }

      total = Math.max(total, r - l + 1);

      r++;
    }
    return total;
  }

  /// ////////////////////////////////////
  /// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
  /// s contains only a,b,c. count num of substring which contain all three.
  ///
  /// ///////////////////////////////////
  public static int numberOfSubstrings(String s) {
    int r = 0, maxCount = 0;
    int[] hash = new int[3]; // as only abc. last seen hash
    Arrays.fill(hash, -1);
    while (r < s.length()) {
      char rc = s.charAt(r);
      hash[rc - 'a'] = r;

      int startOfWindow = Math.min(hash[0], Math.min(hash[1], hash[2]));
      if (startOfWindow >= 0) {
        maxCount += startOfWindow + 1;
      }
      r++;
    }
    return maxCount;
  }

  /// ////////////////////////////////////
  /// https://leetcode.com/problems/binary-subarrays-with-sum/
  /// Input: nums = [1,0,1,0,1], goal = 2
  /// Output: 4
  /// Explanation: The 4 subarrays are bolded and underlined below:
  /// [1,0,1,X,X]
  /// [1,0,1,0,X]
  /// [X,0,1,0,1]
  /// [X,X,1,0,1]
  ///
  /// Example 2:
  ///
  /// Input: nums = [0,0,0,0,0], goal = 0
  /// Output: 15
  /// ///////////////////////////////////
  public static int numSubarraysWithSum(int[] nums, int goal) {
    return atMost(nums, goal) - atMost(nums, goal - 1);
  }

  private static int atMost(int[] nums, int goal) {
    if (goal < 0) return 0;
    int l = 0, r = 0, sum = 0, count = 0;
    while (r < nums.length) {
      sum += nums[r];
      while (sum > goal) {
        sum -= nums[l++];
      }
      count += r - l + 1; // all subarrays ending at right
      r++;
    }
    return count;
  }

  /// ////////////////////////////////////``
  /// https://leetcode.com/problems/count-number-of-nice-subarrays/
  /// ///////////////////////////////////
  public static int numberOfSubarrays(int[] nums, int k) {
    return atMostModulo(nums, k) - atMostModulo(nums, k - 1);
  }

  private static int atMostModulo(int[] nums, int goal) {
    if (goal < 0) return 0;
    int l = 0, r = 0, sum = 0, count = 0;
    while (r < nums.length) {
      sum += nums[r] % 2;
      while (sum > goal) {
        sum -= nums[l++] % 2;
      }
      count += r - l + 1; // all subarrays ending at right
      r++;
    }
    return count;
  }
}
