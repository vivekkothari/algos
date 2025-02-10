package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    //    System.out.println(reverseWords("the sky is blue"));
    //    System.out.println(generateParenthesis(3));
    //    System.out.println(lengthOfLongestSubstring("abcabcbb"));
    //    System.out.println(rotateString("abcabcbb", 2));
    //    System.out.println(detectCapitalUse("Google"));
    //    System.out.println(numberOfSpecialChars("abBCab"));
    System.out.println(numberOfSpecialCharsII("cCceDC"));
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
      swap(res, l, r);
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

  /**
   *
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
    List<String> res = new ArrayList<>();
    StringBuilder stack = new StringBuilder();
    backtrack(0, 0, n, res, stack);
    return res;
  }

  private static void backtrack(
      int openN, int closedN, int n, List<String> res, StringBuilder stack) {
    if (openN == closedN && openN == n) {
      res.add(stack.toString());
      return;
    }

    if (openN < n) {
      stack.append('(');
      backtrack(openN + 1, closedN, n, res, stack);
      stack.deleteCharAt(stack.length() - 1);
    }
    if (closedN < openN) {
      stack.append(')');
      backtrack(openN, closedN + 1, n, res, stack);
      stack.deleteCharAt(stack.length() - 1);
    }
  }

  /**
   *
   *
   * <pre>
   *   Given a string s, find the length of the longest
   * substring without repeating characters.
   *
   * Example 1:
   *
   * Input: s = "abcabcbb"
   * Output: 3
   * Explanation: The answer is "abc", with the length of 3.
   * Example 2:
   *
   * Input: s = "bbbbb"
   * Output: 1
   * Explanation: The answer is "b", with the length of 1.
   * Example 3:
   *
   * Input: s = "pwwkew"
   * Output: 3
   * Explanation: The answer is "wke", with the length of 3.
   * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
   *
   *
   * Constraints:
   *
   * 0 <= s.length <= 5 * 104
   * s consists of English letters, digits, symbols and spaces.
   * </pre>
   */
  public static int lengthOfLongestSubstring(String s) {
    int l = 0, r = 0, max = 0;
    Map<Character, Integer> charIndex = new HashMap<>();
    while (r < s.length()) {
      char c = s.charAt(r);
      if (charIndex.containsKey(c)) {
        l = Math.max(l, charIndex.get(c) + 1);
      }
      charIndex.put(c, r);
      max = Math.max(max, r - l + 1);
      r++;
    }
    return max;
  }

  public static int lengthOfLongestSubstringV2(String s) {
    Set<Character> charSet = new HashSet<>();
    int l = 0;
    int res = 0;

    for (int r = 0; r < s.length(); r++) {
      while (charSet.contains(s.charAt(r))) {
        charSet.remove(s.charAt(l));
        l++;
      }
      charSet.add(s.charAt(r));
      res = Math.max(res, r - l + 1);
    }
    return res;
  }

  /**
   *
   *
   * <pre>
   *   You are given a string s. Simulate events at each second i:
   *
   * If s[i] == 'E', a person enters the waiting room and takes one of the chairs in it.
   * If s[i] == 'L', a person leaves the waiting room, freeing up a chair.
   * Return the minimum number of chairs needed so that a chair is available for every person who enters the waiting room given that it is initially empty.
   *
   * Example 1:
   *
   * Input: s = "EEEEEEE"
   *
   * Output: 7
   *
   * Explanation:
   *
   * After each second, a person enters the waiting room and no person leaves it. Therefore, a minimum of 7 chairs is needed.
   *
   * Example 2:
   *
   * Input: s = "ELELEEL"
   *
   * Output: 2
   * </pre>
   */
  public static int minimumChairs(String s) {
    List<Character> stack = new LinkedList<>();
    var max = 0;
    for (int i = 0; i < s.length(); i++) {
      var c = s.charAt(i);
      if (c == 'E') {
        stack.addFirst(c);
      } else {
        stack.removeFirst();
      }
      max = Math.max(max, stack.size());
    }
    return max;
  }

  public static int minimumChairsOptimal(String s) {
    var res = 0;
    var max = 0;
    for (int i = 0; i < s.length(); i++) {
      var c = s.charAt(i);
      if (c == 'E') {
        res++;
      } else {
        res--;
      }
      max = Math.max(max, res);
    }
    return max;
  }

  /**
   *
   *
   * <pre>
   *   Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
   *
   * A shift on s consists of moving the leftmost character of s to the rightmost position.
   *
   * For example, if s = "abcde", then it will be "bcdea" after one shift.
   *
   * Example 1:
   * Input: s = "abcde", goal = "cdeab"
   * Output: true
   *
   * Example 2:
   * Input: s = "abcde", goal = "abced"
   * Output: false
   * </pre>
   */
  public static boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }
    return (s + s).contains(goal);
  }

  // Rotate string to the left by k places
  // input - abcde, 2 => cdeab
  public static String rotateString(String s, int k) {
    var res = new char[s.length()];
    for (int i = 0; i < res.length; i++) {
      res[i] = s.charAt((i + k) % res.length);
    }
    return String.valueOf(res);
  }

  /**
   *
   *
   * <pre>
   *   Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
   *
   * Example 1:
   *
   * Input: s = "Hello"
   * Output: "hello"
   * Example 2:
   *
   * Input: s = "here"
   * Output: "here"
   * Example 3:
   *
   * Input: s = "LOVELY"
   * Output: "lovely"
   * </pre>
   */
  public static String toLowerCase(String s) {
    var res = new char[s.length()];
    for (int i = 0; i < res.length; i++) {
      var c = s.charAt(i);
      if ('A' <= c && c <= 'Z') {
        res[i] = (char) (c + 32);
      } else {
        res[i] = c;
      }
    }
    return String.valueOf(res);
  }

  /**
   * https://leetcode.com/problems/capitalize-the-title/description/
   *
   * <pre>
   *   You are given a string title consisting of one or more words separated by a single space, where each word consists of English letters. Capitalize the string by changing the capitalization of each word such that:
   *
   * If the length of the word is 1 or 2 letters, change all letters to lowercase.
   * Otherwise, change the first letter to uppercase and the remaining letters to lowercase.
   * Return the capitalized title.
   *
   * Example 1:
   *
   * Input: title = "capiTalIze tHe titLe"
   * Output: "Capitalize The Title"
   * Explanation:
   * Since all the words have a length of at least 3, the first letter of each word is uppercase, and the remaining letters are lowercase.
   * Example 2:
   *
   * Input: title = "First leTTeR of EACH Word"
   * Output: "First Letter of Each Word"
   * Explanation:
   * The word "of" has length 2, so it is all lowercase.
   * The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
   * Example 3:
   *
   * Input: title = "i lOve leetcode"
   * Output: "i Love Leetcode"
   * Explanation:
   * The word "i" has length 1, so it is lowercase.
   * The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
   * </pre>
   */
  public static String capitalizeTitle(String title) {
    var words = title.toLowerCase().split(" ");
    StringBuilder sb = new StringBuilder();
    for (var word : words) {
      if (word.length() < 3) {
        sb.append(word);
      } else {
        sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
      }
      sb.append(" ");
    }
    return sb.toString().trim();
  }

  /**
   * https://leetcode.com/problems/detect-capital/description/
   *
   * <pre>
   *   We define the usage of capitals in a word to be right when one of the following cases holds:
   *
   * All letters in this word are capitals, like "USA".
   * All letters in this word are not capitals, like "leetcode".
   * Only the first letter in this word is capital, like "Google".
   * Given a string word, return true if the usage of capitals in it is right.
   * [t,t,t]
   * [f,f,f]
   * [t,f,f,f]
   * Example 1:
   *
   * Input: word = "USA"
   * Output: true
   * Example 2:
   *
   * Input: word = "FlaG"
   * Output: false
   * </pre>
   */
  public static boolean detectCapitalUse(String word) {
    if (word.isEmpty() || word.length() == 1) return true;
    if (Character.isUpperCase(word.charAt(0))) {
      boolean isFirstCharacter = Character.isUpperCase(word.charAt(1));
      for (int i = 2; i < word.length(); i++) {
        boolean currentCharState = Character.isUpperCase(word.charAt(i));
        if (currentCharState != isFirstCharacter) return false;
      }
    } else {
      for (int i = 1; i < word.length(); i++) {
        if (Character.isUpperCase(word.charAt(i))) return false;
      }
    }
    return true;
  }

  /**
   * https://leetcode.com/problems/count-the-number-of-special-characters-i/description/
   *
   * <pre>
   *   You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
   *
   * Return the number of special letters in word.
   *
   * Example 1:
   *
   * Input: word = "aaAbcBC"
   *
   * Output: 3
   *
   * Explanation:
   *
   * The special characters in word are 'a', 'b', and 'c'.
   *
   * Example 2:
   *
   * Input: word = "abc"
   *
   * Output: 0
   *
   * Explanation:
   *
   * No character in word appears in uppercase.
   *
   * Example 3:
   *
   * Input: word = "abBCab"
   *
   * Output: 1
   *
   * Explanation:
   *
   * The only special character in word is 'b'.
   * </pre>
   */
  public static int numberOfSpecialChars(String word) {
    Set<Character> lowerChars = new HashSet<>();
    Set<Character> upperChars = new HashSet<>();
    int res = 0;
    for (int i = 0; i < word.length(); i++) {
      var c = word.charAt(i);
      var added = false;
      var isLower = Character.isLowerCase(c);
      if (isLower) {
        added = lowerChars.add(c);
      } else {
        added = upperChars.add(c);
      }
      if (added
          && ((isLower && upperChars.contains(Character.toUpperCase(c)))
              || (!isLower && lowerChars.contains(Character.toLowerCase(c))))) {
        res++;
      }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/description/
   *
   * <pre>
   *   Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s. The returned letter should be in uppercase. If no such letter exists, return an empty string.
   *
   * An English letter b is greater than another letter a if b appears after a in the English alphabet.
   *
   * Example 1:
   *
   * Input: s = "lEeTcOdE"
   * Output: "E"
   * Explanation:
   * The letter 'E' is the only letter to appear in both lower and upper case.
   * Example 2:
   *
   * Input: s = "arRAzFif"
   * Output: "R"
   * Explanation:
   * The letter 'R' is the greatest letter to appear in both lower and upper case.
   * Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.
   * Example 3:
   *
   * Input: s = "AbCdEfGhIjK"
   * Output: ""
   * Explanation:
   * There is no letter that appears in both lower and upper case.
   * </pre>
   */
  public static String greatestLetter(String s) {
    int[] lower = new int[26];
    int[] upper = new int[26];
    for (int i = 0; i < s.length(); i++) {
      var c = s.charAt(i);
      if (Character.isUpperCase(c)) {
        upper[c - 'A']++;
      } else {
        lower[c - 'a']++;
      }
    }
    for (int i = 25; i >= 0; i--) {
      if (lower[i] != 0 && upper[i] != 0) {
        return String.valueOf((char) Character.toUpperCase(i + 'a'));
      }
    }
    return "";
  }

  /**
   * https://leetcode.com/problems/count-the-number-of-special-characters-ii/description/
   *
   * <pre>
   *   You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.
   *
   * Return the number of special letters in word.
   *
   * Example 1:
   *
   * Input: word = "aaAbcBC"
   *
   * Output: 3
   *
   * Explanation:
   *
   * The special characters are 'a', 'b', and 'c'.
   *
   * Example 2:
   *
   * Input: word = "abc"
   *
   * Output: 0
   *
   * Explanation:
   *
   * There are no special characters in word.
   *
   * Example 3:
   *
   * Input: word = "AbBCab"
   *
   * Output: 0
   *
   * Explanation:
   *
   * There are no special characters in word.
   * </pre>
   */
  public static int numberOfSpecialCharsII(String word) {
    Map<Character, Integer> firstUpper = new HashMap<>();
    Map<Character, Integer> lastLower = new HashMap<>();
    for (int i = 0; i < word.length(); i++) {
      var c = word.charAt(i);
      if (Character.isUpperCase(c) && !firstUpper.containsKey(c)) {
        firstUpper.put(Character.toLowerCase(c), i);
      }
      if (Character.isLowerCase(c)) {
        lastLower.put(c, i);
      }
    }
    var res = 0;
    for (char c = 'a'; c <= 'z'; c++) {
      var ll = lastLower.getOrDefault(c, word.length());
      var fu = firstUpper.getOrDefault(c, -1);
      if (ll < fu) res++;
    }
    return res;

    // var arr = w.toCharArray();
    //        var lastLower  = new HashMap<Character, Integer>();
    //        var firstUpper = new HashMap<Character, Integer>();
    //        for (int i=0;i<arr.length;i++){
    //            var c = arr[i];
    //            var lc = Character.toLowerCase(c);
    //            if (Character.isUpperCase(c)){
    //                if (!firstUpper.containsKey(lc)) firstUpper.put(lc,i);
    //            } else {
    //                lastLower.put(lc,i);
    //            }
    //        }
    //        var res = 0;
    //        for (char c='a';c<='z';c++){
    //            var ll = lastLower.getOrDefault(c,arr.length);
    //            var fu = firstUpper.getOrDefault(c,-1);
    //            if (ll<fu) res++;
    //        }
    //        return res;
  }
}
