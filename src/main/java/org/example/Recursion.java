package org.example;

class Recursion {

  public static void main(String[] args) {
    System.out.println(countAndSay(30));
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
}
