package org.example.llmquiz;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeSet;

class ChatGptAlgoQuiz {

  /** Write a Java function to find the second largest element in an ArrayList<Integer>. */
  public static Integer findSecondLargest(List<Integer> list) {
    var set = new PriorityQueue<Integer>(Comparator.reverseOrder());
    set.addAll(list);
    set.poll();
    return set.poll();
  }

  /**
   * Implement a method that returns the closest lower and higher numbers in a TreeSet<Integer>
   * given an input value.
   */
  public static Entry<Integer, Integer> findClosestNumbers(TreeSet<Integer> set, int target) {
    return Map.entry(set.lower(target), set.higher(target));
  }

  public static void main(String[] args) {
    System.out.println(findSecondLargest(List.of(3, 4, 6, 8, 8)));
  }
}
