package org.example;

import java.util.ArrayList;
import java.util.List;

class Recursion {

  public static void main(String[] args) {
    //    System.out.println(countAndSay(30));
    removeInvalidParentheses(")()()").forEach(System.out::println);
  }

  public static String countAndSay(int n) {
    return recur(n);
  }

  static String recur(int n) {
    if (n == 1) return "1";

    String s = recur(n - 1);
    return countFreq(s);
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
