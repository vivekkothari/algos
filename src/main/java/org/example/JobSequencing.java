package org.example;

import java.util.Arrays;

public class JobSequencing {
  record Job(int id, int deadline, int profit) {}

  static int maxProfit(Job... jobs) {
    Arrays.sort(jobs, (j1, j2) -> j2.profit - j1.profit);
    int profit = 0;
    int maxDeadline = Arrays.stream(jobs).mapToInt(Job::deadline).max().orElse(0);
    boolean[] slot = new boolean[maxDeadline + 1]; // 1-based index for time slots
    for (Job job : jobs) {
      // Try to schedule at the latest possible slot before or at deadline
      for (int j = job.deadline; j > 0; j--) {
        if (!slot[j]) {
          slot[j] = true;
          profit += job.profit;
          break;
        }
      }
    }
    return profit;
  }

  public static void main(String[] args) {
    System.out.println(
        maxProfit(new Job(1, 4, 40), new Job(2, 1, 10), new Job(3, 1, 40), new Job(4, 1, 30)));
  }
}
