package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    var charcount = new int[26];
    for (var i = 0; i < s.length(); i++) {
      charcount[s.charAt(i) - 'a']++;
    }
    for (var i = 0; i < t.length(); i++) {
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
      var charcount = new char[26];
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
    for (var i = 0; i < strs[0].length(); i++) {
      var currentChar = strs[0].charAt(i);
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
    //    System.out.println(numberOfSpecialCharsII("cCceDC"));
    //    System.out.println(lengthOfLastWord("Hello World"));
    //    System.out.println(reversePrefix("abcdefd", 'd'));
    //    System.out.println(maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));
    //    System.out.println(convert("AB", 1));
    System.out.println(myAtoi("-91283472332"));
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
    for (var i = 0; i < s.length(); ) {
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
    var stack = new StringBuilder();
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
      var c = s.charAt(r);
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
    var l = 0;
    var res = 0;

    for (var r = 0; r < s.length(); r++) {
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
    for (var i = 0; i < s.length(); i++) {
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
    for (var i = 0; i < s.length(); i++) {
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
    for (var i = 0; i < res.length; i++) {
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
    for (var i = 0; i < res.length; i++) {
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
    var sb = new StringBuilder();
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
      var isFirstCharacter = Character.isUpperCase(word.charAt(1));
      for (var i = 2; i < word.length(); i++) {
        var currentCharState = Character.isUpperCase(word.charAt(i));
        if (currentCharState != isFirstCharacter) return false;
      }
    } else {
      for (var i = 1; i < word.length(); i++) {
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
    var res = 0;
    for (var i = 0; i < word.length(); i++) {
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
    var lower = new int[26];
    var upper = new int[26];
    for (var i = 0; i < s.length(); i++) {
      var c = s.charAt(i);
      if (Character.isUpperCase(c)) {
        upper[c - 'A']++;
      } else {
        lower[c - 'a']++;
      }
    }
    for (var i = 25; i >= 0; i--) {
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
    for (var i = 0; i < word.length(); i++) {
      var c = word.charAt(i);
      if (Character.isUpperCase(c) && !firstUpper.containsKey(c)) {
        firstUpper.put(Character.toLowerCase(c), i);
      }
      if (Character.isLowerCase(c)) {
        lastLower.put(c, i);
      }
    }
    var res = 0;
    for (var c = 'a'; c <= 'z'; c++) {
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

  /**
   * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
   *
   * <pre>
   *   Given two strings needle and haystack, return the index of the first occurrence
   *   of needle in haystack, or -1 if needle is not part of haystack.
   *
   * Example 1:
   *
   * Input: haystack = "sadbutsad", needle = "sad"
   * Output: 0
   * Explanation: "sad" occurs at index 0 and 6.
   * The first occurrence is at index 0, so we return 0.
   * Example 2:
   *
   * Input: haystack = "leetcode", needle = "leeto"
   * Output: -1
   * Explanation: "leeto" did not occur in "leetcode", so we return -1.
   *
   *
   * Constraints:
   *
   * 1 <= haystack.length, needle.length <= 104
   * haystack and needle consist of only lowercase English characters.
   * </pre>
   */
  public static int strStr(String haystack, String needle) {
    return haystack.indexOf(needle);
  }

  /**
   * https://leetcode.com/problems/length-of-last-word/
   *
   * <pre>
   *   Given a string s consisting of words and spaces, return the length of the last word in the string.
   *
   * A word is a maximal
   * substring
   *  consisting of non-space characters only.
   *
   * Example 1:
   *
   * Input: s = "Hello World"
   * Output: 5
   * Explanation: The last word is "World" with length 5.
   * Example 2:
   *
   * Input: s = "   fly me   to   the moon  "
   * Output: 4
   * Explanation: The last word is "moon" with length 4.
   * Example 3:
   *
   * Input: s = "luffy is still joyboy"
   * Output: 6
   * Explanation: The last word is "joyboy" with length 6.
   *
   *
   * Constraints:
   *
   * 1 <= s.length <= 104
   * s consists of only English letters and spaces ' '.
   * There will be at least one word in s.
   * </pre>
   */
  public static int lengthOfLastWord(String s) {
    s = s.trim();
    var lastSpace = s.lastIndexOf(" ");
    return s.substring(lastSpace + 1).length();
  }

  /**
   * https://leetcode.com/problems/add-binary/
   *
   * <pre>
   *   Given two binary strings a and b, return their sum as a binary string.
   *
   * Example 1:
   *
   * Input: a = "11", b = "1"
   * Output: "100"
   * Example 2:
   *
   * Input: a = "1010", b = "1011"
   * Output: "10101"
   * </pre>
   */
  public static String addBinary(String a, String b) {
    var res = new StringBuilder();
    var i = a.length() - 1;
    var j = b.length() - 1;

    var carry = 0;

    while (i >= 0 || j >= 0) {

      var sum = carry;
      if (i >= 0) {
        sum += a.charAt(i) - '0';
        i--;
      }
      if (j >= 0) {
        sum += b.charAt(j) - '0';
        j--;
      }
      carry = sum > 1 ? 1 : 0;
      res.append(sum % 2);
    }
    if (carry != 0) {
      res.append(carry);
    }
    return res.reverse().toString();
  }

  /**
   * https://leetcode.com/problems/reverse-prefix-of-word/
   *
   * <pre>
   *   Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
   *
   * For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
   * Return the resulting string.
   *
   *
   *
   * Example 1:
   *
   * Input: word = "abcdefd", ch = "d"
   * Output: "dcbaefd"
   * Explanation: The first occurrence of "d" is at index 3.
   * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
   * Example 2:
   *
   * Input: word = "xyxzxe", ch = "z"
   * Output: "zxyxxe"
   * Explanation: The first and only occurrence of "z" is at index 3.
   * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
   * Example 3:
   *
   * Input: word = "abcd", ch = "z"
   * Output: "abcd"
   * Explanation: "z" does not exist in word.
   * You should not do any reverse operation, the resulting string is "abcd".
   * </pre>
   */
  public static String reversePrefix(String word, char ch) {
    var i = word.indexOf(ch);
    if (i < 1) {
      return word;
    }
    var s = word.toCharArray();
    var mid = i / 2;
    for (int k = 0, j = i; k <= mid; k++, j--) {
      var temp = s[k];
      s[k] = s[j];
      s[j] = temp;
    }
    return String.valueOf(s);
  }

  /**
   * https://leetcode.com/problems/minimum-index-sum-of-two-lists/
   *
   * <pre>
   *   Given two arrays of strings list1 and list2, find the common strings with the least index sum.
   *
   * A common string is a string that appeared in both list1 and list2.
   *
   * A common string with the least index sum is a common string such that if it appeared at list1[i] and list2[j] then i + j should be the minimum value among all the other common strings.
   *
   * Return all the common strings with the least index sum. Return the answer in any order.
   *
   * Example 1:
   *
   * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
   * Output: ["Shogun"]
   * Explanation: The only common string is "Shogun".
   * Example 2:
   *
   * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
   * Output: ["Shogun"]
   * Explanation: The common string with the least index sum is "Shogun" with index sum = (0 + 1) = 1.
   * Example 3:
   *
   * Input: list1 = ["happy","sad","good"], list2 = ["sad","happy","good"]
   * Output: ["sad","happy"]
   * Explanation: There are three common strings:
   * "happy" with index sum = (0 + 1) = 1.
   * "sad" with index sum = (1 + 0) = 1.
   * "good" with index sum = (2 + 2) = 4.
   * The strings with the least index sum are "sad" and "happy".
   * </pre>
   */
  public static String[] findRestaurant(String[] list1, String[] list2) {
    var map1 =
        IntStream.range(0, list1.length)
            .mapToObj(i -> Map.entry(list1[i], i))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    Set<String> res = new HashSet<>();
    var minIndex = Integer.MAX_VALUE;
    for (var i = 0; i < list2.length; i++) {
      if (!map1.containsKey(list2[i])) {
        continue;
      }
      var indexInFirst = map1.get(list2[i]);
      var sumOfIndices = indexInFirst + i;
      if (minIndex > sumOfIndices) {
        minIndex = sumOfIndices;
        // We found a new min, so clear everything we saw so far.
        res.clear();
        res.add(list2[i]);
      } else if (minIndex == sumOfIndices) {
        res.add(list2[i]);
      }
    }
    return res.toArray(String[]::new);
  }

  /**
   * https://leetcode.com/problems/maximum-repeating-substring/
   *
   * <pre>
   *   For a string sequence, a string word is k-repeating if word concatenated k
   *   times is a substring of sequence. The word's maximum k-repeating value is the
   *   highest value k where word is k-repeating in sequence. If word is not a substring
   *   of sequence, word's maximum k-repeating value is 0.
   *
   * Given strings sequence and word, return the maximum k-repeating value of word in sequence.
   *
   * Example 1:
   *
   * Input: sequence = "ababc", word = "ab"
   * Output: 2
   * Explanation: "abab" is a substring in "ababc".
   * Example 2:
   *
   * Input: sequence = "ababc", word = "ba"
   * Output: 1
   * Explanation: "ba" is a substring in "ababc". "baba" is not a substring in "ababc".
   * Example 3:
   *
   * Input: sequence = "ababc", word = "ac"
   * Output: 0
   * Explanation: "ac" is not a substring in "ababc".
   * </pre>
   */
  public static int maxRepeating(String sequence, String word) {
    int k = 0;
    var curr = new StringBuilder(word);
    while (sequence.contains(curr)) {
      curr.append(word);
      k++;
    }
    return k;
  }

  /**
   * https://leetcode.com/problems/longest-palindromic-substring/solutions/3598120/longest-palindromic-substring/
   *
   * <pre>
   *   Given a string s, return the longest palindromic substring in s.
   *
   * Example 1:
   *
   * Input: s = "babad"
   * Output: "bab"
   * Explanation: "aba" is also a valid answer.
   * Example 2:
   *
   * Input: s = "cbbd"
   * Output: "bb"
   *
   *
   * Constraints:
   *
   * 1 <= s.length <= 1000
   * s consist of only digits and English letters.
   * </pre>
   */
  public static String longestPalindrome(String s) {
    int resLen = 0, resIdx = 0;

    for (int i = 0; i < s.length(); i++) {
      // odd length
      int l = i, r = i;
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        if (r - l + 1 > resLen) {
          resIdx = l;
          resLen = r - l + 1;
        }
        l--;
        r++;
      }

      // even length
      l = i;
      r = i + 1;
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        if (r - l + 1 > resLen) {
          resIdx = l;
          resLen = r - l + 1;
        }
        l--;
        r++;
      }
    }

    return s.substring(resIdx, resIdx + resLen);
  }

  /**
   * https://leetcode.com/problems/zigzag-conversion/
   *
   * <pre>
   *   The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
   *   (you may want to display this pattern in a fixed font for better legibility)
   *
   * P   A   H   N
   * A P L S I I G
   * Y   I   R
   * And then read line by line: "PAHNAPLSIIGYIR"
   *
   * Write the code that will take a string and make this conversion given a number of rows:
   *
   * string convert(string s, int numRows);
   *
   *
   * Example 1:
   *
   * Input: s = "PAYPALISHIRING", numRows = 3
   * Output: "PAHNAPLSIIGYIR"
   * Example 2:
   *
   * Input: s = "PAYPALISHIRING", numRows = 4
   * Output: "PINALSIGYAHRPI"
   * Explanation:
   * P     I    N
   * A   L S  I G
   * Y A   H R
   * P     I
   * Example 3:
   *
   * Input: s = "A", numRows = 1
   * Output: "A"
   * </pre>
   */
  public static String convert(String s, int numRows) {
    if (numRows == 1 || numRows >= s.length()) {
      return s;
    }
    List<List<Character>> rows = new ArrayList<>();
    // init all lists
    for (int i = 0; i < numRows; i++) {
      rows.add(new ArrayList<>());
    }
    int idx = 0, d = 1;
    for (char c : s.toCharArray()) {
      rows.get(idx).add(c);
      if (idx == 0) {
        d = 1;
      } else if (idx == numRows - 1) {
        d = -1;
      }
      idx += d;
    }
    StringBuilder result = new StringBuilder();
    for (List<Character> row : rows) {
      for (char c : row) {
        result.append(c);
      }
    }
    return result.toString();
  }

  /**
   * https://leetcode.com/problems/string-to-integer-atoi/
   *
   * <pre>
   *   Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
   *
   * The algorithm for myAtoi(string s) is as follows:
   *
   * Whitespace: Ignore any leading whitespace (" ").
   * Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
   * Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
   * Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
   * Return the integer as the final result.
   *
   *
   *
   * Example 1:
   *
   * Input: s = "42"
   *
   * Output: 42
   *
   * Explanation:
   *
   * The underlined characters are what is read in and the caret is the current reader position.
   * Step 1: "42" (no characters read because there is no leading whitespace)
   *          ^
   * Step 2: "42" (no characters read because there is neither a '-' nor '+')
   *          ^
   * Step 3: "42" ("42" is read in)
   *            ^
   * Example 2:
   *
   * Input: s = " -042"
   *
   * Output: -42
   *
   * Explanation:
   *
   * Step 1: "   -042" (leading whitespace is read and ignored)
   *             ^
   * Step 2: "   -042" ('-' is read, so the result should be negative)
   *              ^
   * Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
   *                ^
   * Example 3:
   *
   * Input: s = "1337c0d3"
   *
   * Output: 1337
   *
   * Explanation:
   *
   * Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
   *          ^
   * Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
   *          ^
   * Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
   *              ^
   * Example 4:
   *
   * Input: s = "0-1"
   *
   * Output: 0
   *
   * Explanation:
   *
   * Step 1: "0-1" (no characters read because there is no leading whitespace)
   *          ^
   * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
   *          ^
   * Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
   *           ^
   * Example 5:
   *
   * Input: s = "words and 987"
   *
   * Output: 0
   *
   * Explanation:
   *
   * Reading stops at the first non-digit character 'w'.
   * </pre>
   */
  public static int myAtoi(String s) {
    int i = 0, n = s.length(), sign = 1, ans = 0;

    while (i < n && s.charAt(i) == ' ') {
      i++;
    }

    if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
      sign = (s.charAt(i) == '-') ? -1 : 1;
      i++;
    }
    while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
      int digit = s.charAt(i) - '0';
      try {
        var temp = Math.multiplyExact(ans, 10);
        ans = Math.addExact(temp, digit);
      } catch (ArithmeticException ignore) {
        return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      i++;
    }

    return ans * sign;
  }
}
