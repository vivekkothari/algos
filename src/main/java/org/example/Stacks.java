package org.example;

import java.util.Stack;

class Stacks {

  public static void main(String[] args) {
    System.out.println(isValid("]]"));
  }

  /**
   *
   *
   * <pre>
   *   You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.
   *
   * The input string s is valid if and only if:
   *
   * Every open bracket is closed by the same type of close bracket.
   * Open brackets are closed in the correct order.
   * Every close bracket has a corresponding open bracket of the same type.
   * Return true if s is a valid string, and false otherwise.
   *
   * Example 1:
   *
   * Input: s = "[]"
   *
   * Output: true
   * Example 2:
   *
   * Input: s = "([{}])"
   *
   * Output: true
   * Example 3:
   *
   * Input: s = "[(])"
   *
   * Output: false
   * Explanation: The brackets are not closed in the correct order.
   * </pre>
   */
  public static boolean isValid(String s) {
    if (s.length() % 2 == 1) {
      return false;
    }
    var stack = new Stack<Character>();
    for (var c : s.toCharArray()) {
      switch (c) {
        case '(', '{', '[':
          stack.push(c);
          break;
        case ')':
          if (stack.isEmpty() || stack.peek() != '(') {
            return false;
          } else {
            stack.pop();
          }
          break;
        case '}':
          if (stack.isEmpty() || stack.peek() != '{') {
            return false;
          } else {
            stack.pop();
          }
          break;
        case ']':
          if (stack.isEmpty() || stack.peek() != '[') {
            return false;
          } else {
            stack.pop();
          }
          break;
      }
    }
    return stack.isEmpty();
  }
}
