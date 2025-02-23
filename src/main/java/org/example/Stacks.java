package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

class Stacks {

  public static void main(String[] args) {
    //    System.out.println(isValid("]]"));
    System.out.println(
        evalRPN(
            new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    System.out.println(carFleet(10, new int[] {10, 8, 0, 5, 3}, new int[] {2, 4, 1, 1, 3}));
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

  /**
   * https://leetcode.com/problems/car-fleet/description/ There are n cars at given miles away from
   * the starting mile 0, traveling to reach the mile target.
   *
   * <p>You are given two integer array position and speed, both of length n, where position[i] is
   * the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.
   *
   * <p>A car cannot pass another car, but it can catch up and then travel next to it at the speed
   * of the slower car.
   *
   * <p>A car fleet is a car or cars driving next to each other. The speed of the car fleet is the
   * minimum speed of any car in the fleet.
   *
   * <p>If a car catches up to a car fleet at the mile target, it will still be considered as part
   * of the car fleet.
   *
   * <p>Return the number of car fleets that will arrive at the destination.
   *
   * <p>Example 1:
   *
   * <p>Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
   *
   * <p>Output: 3
   *
   * <p>Explanation:
   *
   * <p>The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
   * The fleet forms at target. The car starting at 0 (speed 1) does not catch up to any other car,
   * so it is a fleet by itself. The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet,
   * meeting each other at 6. The fleet moves at speed 1 until it reaches target. Example 2:
   *
   * <p>Input: target = 10, position = [3], speed = [3]
   *
   * <p>Output: 1
   *
   * <p>Explanation:
   *
   * <p>There is only one car, hence there is only one fleet. Example 3:
   *
   * <p>Input: target = 100, position = [0,2,4], speed = [4,2,1]
   *
   * <p>Output: 1
   *
   * <p>Explanation:
   *
   * <p>The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4.
   * The car starting at 4 (speed 1) travels to 5. Then, the fleet at 4 (speed 2) and the car at
   * position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1
   * until it reaches target.
   */
  public static int carFleet(int target, int[] position, int[] speed) {
    var pair =
        IntStream.range(0, position.length)
            .mapToObj(i -> new int[] {position[i], speed[i]})
            .sorted((a, b) -> Integer.compare(b[0], a[0]))
            .toList();
    List<Double> stack = new ArrayList<>();
    for (int[] p : pair) {
      stack.addLast((double) (target - p[0]) / p[1]);
      if (stack.size() >= 2 && stack.getLast() <= stack.get(stack.size() - 2)) {
        stack.removeLast();
      }
    }
    return stack.size();
  }
}
