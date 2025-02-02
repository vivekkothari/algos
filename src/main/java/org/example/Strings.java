package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Strings {

  /**
   * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise
   * return false.
   *
   * <p>An anagram is a string that contains the exact same characters as another string, but the
   * order of the characters can be different.
   *
   * <p>Example 1:
   *
   * <p>Input: s = "racecar", t = "carrace"
   *
   * <p>Output: true Example 2:
   *
   * <p>Input: s = "jar", t = "jam"
   *
   * <p>Output: false Constraints:
   *
   * <p>s and t consist of lowercase English letters.
   */
  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] charcount = new int[26];
    for (int i = 0; i < s.length(); i++) {
      charcount[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < t.length(); i++) {
      charcount[t.charAt(i) - 'a']--;
      if (charcount[t.charAt(i) - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }

  public static List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groupedAnagrams = new HashMap<>();
    for (var str : strs) {
      char[] charcount = new char[26];
      for (var c : str.toCharArray()) {
        charcount[c - 'a']++;
      }
      var freqList = String.valueOf(charcount);
      var strings = groupedAnagrams.getOrDefault(freqList, new ArrayList<>());
      strings.add(str);
      groupedAnagrams.put(freqList, strings);
    }
    return groupedAnagrams.values().stream().toList();
  }

  public static String encode(List<String> strs) {
    var sb = new StringBuilder();
    strs.forEach(s -> sb.append(s.length()).append("#").append(s));
    return sb.toString();
  }

  public static List<String> decode(String str) {
    List<String> ret = new ArrayList<>();
    // Hold the string
    var curr = new StringBuilder();
    // Hold the number
    var numStr = new StringBuilder();
    var num = 0;
    for (var i = 0; i < str.length(); i++) {
      var c = str.charAt(i);
      if (num == 0) {
        if (c != '#') {
          numStr.append(c);
        } else {
          num = Integer.parseInt(numStr.toString());
          if (num == 0) {
            ret.add("");
          }
          numStr = new StringBuilder();
        }
      } else {
        curr.append(c);
        num--;
        if (num == 0) {
          ret.add(curr.toString());
          curr = new StringBuilder();
        }
      }
    }
    return ret;
  }

  public static String longestCommonPrefix(String[] strs) {
    var commonPrefix = new StringBuilder();
    for (int i = 0; i < strs[0].length(); i++) {
      char currentChar = strs[0].charAt(i);
      for (var str : strs) {
        if (str.length() <= i || str.charAt(i) != currentChar) {
          return commonPrefix.toString();
        }
      }
      commonPrefix.append(currentChar);
    }
    return commonPrefix.toString();
  }

  public static void reverseString(char[] s) {
    var mid = s.length / 2;
    for (int i = 0, j = s.length - 1; i < mid; i++, j--) {
      swap(s, i, j);
    }
  }

  private static void swap(char[] s, int i, int j) {
    var temp = s[i];
    s[i] = s[j];
    s[j] = temp;
  }

  /**
   *
   *
   * <pre>
   *   Given a string s, return true if it is a palindrome, otherwise return false.
   *
   * A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.
   *
   * Example 1:
   *
   * Input: s = "Was it a car or a cat I saw?"
   *
   * Output: true
   * Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.
   *
   * Example 2:
   *
   * Input: s = "tab a cat"
   *
   * Output: false
   * Explanation: "tabacat" is not a palindrome.
   * </pre>
   */
  public static boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
      while (l < r && !alphaNum(s.charAt(l))) {
        l++;
      }
      while (l < r && !alphaNum(s.charAt(r))) {
        r--;
      }
      if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
        return false;
      }
      l++;
      r--;
    }
    return true;
  }

  public static boolean isPalindromeByDeletingOneChar(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
      if (s.charAt(l) != s.charAt(r)) {
        return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
      }
      l++;
      r--;
    }
    return true;
  }

  private static boolean isPalindrome(String s, int l, int r) {
    while (l < r) {
      if (s.charAt(l) != s.charAt(r)) {
        return false;
      }
      l++;
      r--;
    }
    return true;
  }

  public static boolean alphaNum(char c) {
    return (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9');
  }

  public static String mergeAlternately(String word1, String word2) {
    var res = new StringBuilder();
    var forLoopLimit = Math.min(word1.length(), word2.length());
    var i = 0;
    for (; i < forLoopLimit; i++) {
      res.append(word1.charAt(i)).append(word2.charAt(i));
    }
    if (i < word1.length()) {
      res.append(word1.substring(i));
    } else if (i < word2.length()) {
      res.append(word2.substring(i));
    }
    return res.toString();
  }

  public static void main(String[] args) {
    //    System.out.println(longestCommonPrefix(new String[] {"flower", "flow"}));
    //    System.out.println(isPalindrome("Was it a car or a cat I saw?"));
    //    System.out.println(isPalindrome("0P"));
    //
    //    System.out.println(isPalindromeByDeletingOneChar("aba"));
    //    System.out.println(isPalindromeByDeletingOneChar("aaaz"));
    //    System.out.println(isPalindromeByDeletingOneChar("eceec"));

    //    System.out.println(mergeAlternately("ab", "pqrs"));
    //    System.out.println(reverseVowels("leetcode"));
    System.out.println(reverseWords("the sky is blue"));
  }

  // I c e C r e A m
  // A c e C r e I m
  public static String reverseVowels(String s) {
    int l = 0, r = s.length() - 1;
    var res = s.toCharArray();
    while (l < r) {
      while (l < r && isNotVowel(res[l])) {
        l++;
      }
      while (l < r && isNotVowel(res[r])) {
        r--;
      }
      char temp = res[l];
      res[l] = res[r];
      res[r] = temp;
      l++;
      r--;
    }
    return String.valueOf(res);
  }

  static boolean isNotVowel(char c) {
    return !switch (c) {
      case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
      default -> false;
    };
  }

  /**
   *
   *
   * <pre>
   *   Given an input string s, reverse the order of the words.
   *
   * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
   *
   * Return a string of the words in reverse order concatenated by a single space.
   *
   * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
   *
   *
   *
   * Example 1:
   *
   * Input: s = "the sky is blue"
   * Output: "blue is sky the"
   * Example 2:
   *
   * Input: s = "  hello world  "
   * Output: "world hello"
   * Explanation: Your reversed string should not contain leading or trailing spaces.
   * Example 3:
   *
   * Input: s = "a good   example"
   * Output: "example good a"
   * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
   * </pre>
   */
  public static String reverseWords(String s) {
    List<String> words = new LinkedList<>();
    int wStart = 0, wEnd = 0;
    for (int i = 0; i < s.length(); ) {
      // if space, skip forward
      if (s.charAt(i) == ' ') {
        wStart++;
        wEnd++;
        i++;
      } else {
        while (wEnd < s.length() && s.charAt(wEnd) != ' ') {
          wEnd++;
        }
        words.addFirst(s.substring(wStart, wEnd));
        i = wStart = wEnd;
      }
    }
    return String.join(" ", words);
  }
}
