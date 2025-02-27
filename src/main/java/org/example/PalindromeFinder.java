package org.example;

import java.util.HashSet;
import java.util.Set;

public interface PalindromeFinder {
  /*
  Returns the set of all subsequences of s which are palindromes.
  For example, given the string “abcbrta”, you should return “a”,“abba”,“abcba”,“aca”,“bcb”,
  “aba”, etc.
  */
  Set<String> findPalindromes(String s);
}

class PalindromeFinderImpl implements PalindromeFinder {

  public Set<String> findPalindromes(String s) {
    var res = new HashSet<String>();
    permute(s, res, new StringBuilder(), 0);
    return res;
  }

  private void permute(String s, Set<String> res, StringBuilder sb, int start) {
    if (!sb.isEmpty()) {
      if (isPalindrome(sb)) {
        res.add(sb.toString());
      }
    }
    for (int i = start; i < s.length(); i++) {
      sb.append(s.charAt(i));
      permute(s, res, sb, i + 1);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  private boolean isPalindrome(CharSequence s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
      if (s.charAt(l) != s.charAt(r)) {
        return false;
      }
      l++;
      r--;
    }
    return true;
  }

  public static void main(String[] args) {
    PalindromeFinder finder = new PalindromeFinderImpl();
    finder.findPalindromes("abqwerba").forEach(System.out::println);
  }
}
