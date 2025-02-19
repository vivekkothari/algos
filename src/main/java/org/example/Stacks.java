package org.example;

import java.util.Set;
import java.util.Stack;

class Stacks {

  public static void main(String[] args) {
    //    System.out.println(isValid("]]"));
    System.out.println(
        evalRPN(
            new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
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

  /**
   *
   *
   * <pre>
   *   You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.
   *
   * You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:
   *
   * An integer x.
   * Record a new score of x.
   * '+'.
   * Record a new score that is the sum of the previous two scores.
   * 'D'.
   * Record a new score that is the double of the previous score.
   * 'C'.
   * Invalidate the previous score, removing it from the record.
   * Return the sum of all the scores on the record after applying all the operations.
   *
   * The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that all operations are valid.
   *
   *
   *
   * Example 1:
   *
   * Input: ops = ["5","2","C","D","+"]
   * Output: 30
   * Explanation:
   * "5" - Add 5 to the record, record is now [5].
   * "2" - Add 2 to the record, record is now [5, 2].
   * "C" - Invalidate and remove the previous score, record is now [5].
   * "D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
   * "+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
   * The total sum is 5 + 10 + 15 = 30.
   * Example 2:
   *
   * Input: ops = ["5","-2","4","C","D","9","+","+"]
   * Output: 27
   * Explanation:
   * "5" - Add 5 to the record, record is now [5].
   * "-2" - Add -2 to the record, record is now [5, -2].
   * "4" - Add 4 to the record, record is now [5, -2, 4].
   * "C" - Invalidate and remove the previous score, record is now [5, -2].
   * "D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
   * "9" - Add 9 to the record, record is now [5, -2, -4, 9].
   * "+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
   * "+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
   * The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
   * Example 3:
   *
   * Input: ops = ["1","C"]
   * Output: 0
   * Explanation:
   * "1" - Add 1 to the record, record is now [1].
   * "C" - Invalidate and remove the previous score, record is now [].
   * Since the record is empty, the total sum is 0.
   * Constraints:
   *
   * 1 <= operations.length <= 1000
   * operations[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 104, 3 * 104].
   * For operation "+", there will always be at least two previous scores on the record.
   * For operations "C" and "D", there will always be at least one previous score on the record.
   * </pre>
   */
  public static int calPoints(String[] operations) {
    var stack = new Stack<Integer>();
    var res = 0;
    for (var operation : operations) {
      switch (operation) {
        case "C" -> res -= stack.pop();
        case "D" -> {
          stack.push(stack.peek() * 2);
          res += stack.peek();
        }
        case "+" -> {
          var top = stack.pop();
          var newTop = top + stack.peek();
          stack.push(top);
          stack.push(newTop);
          res += newTop;
        }
        case null, default -> {
          var item = Integer.parseInt(operation);
          stack.push(item);
          res += item;
        }
      }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
   *
   * <pre>
   *   You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
   *
   * Evaluate the expression. Return an integer that represents the value of the expression.
   *
   * Note that:
   *
   * The valid operators are '+', '-', '*', and '/'.
   * Each operand may be an integer or another expression.
   * The division between two integers always truncates toward zero.
   * There will not be any division by zero.
   * The input represents a valid arithmetic expression in a reverse polish notation.
   * The answer and all the intermediate calculations can be represented in a 32-bit integer.
   *
   *
   * Example 1:
   *
   * Input: tokens = ["2","1","+","3","*"]
   * Output: 9
   * Explanation: ((2 + 1) * 3) = 9
   * Example 2:
   *
   * Input: tokens = ["4","13","5","/","+"]
   * Output: 6
   * Explanation: (4 + (13 / 5)) = 6
   * Example 3:
   *
   * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
   * Output: 22
   * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
   * = ((10 * (6 / (12 * -11))) + 17) + 5
   * = ((10 * (6 / -132)) + 17) + 5
   * = ((10 * 0) + 17) + 5
   * = (0 + 17) + 5
   * = 17 + 5
   * = 22
   * </pre>
   */
  public static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    int i = 0;
    var operators = Set.of("+", "-", "/", "*");
    stack.push(Integer.parseInt(tokens[i++]));
    while (!stack.isEmpty() && i < tokens.length) {
      var token = tokens[i++];
      if (operators.contains(token)) {
        var l = stack.pop();
        var r = stack.pop();
        stack.push(
            switch (token) {
              case "+" -> r + l;
              case "-" -> r - l;
              case "*" -> r * l;
              case "/" -> r / l;
              case null, default -> throw new UnsupportedOperationException("invalid");
            });
      } else {
        stack.push(Integer.parseInt(token));
      }
    }
    return stack.peek();
  }
}
