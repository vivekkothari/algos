package org.example.ds;

import java.util.TreeMap;

class TopVotedCandidate {

  private final TreeMap<Integer, Integer> winner = new TreeMap<>();

  public TopVotedCandidate(int[] persons, int[] times) {
    var votes = new int[persons.length];
    int maxPerson = 0;
    for (int i = 0; i < persons.length; i++) {
      votes[persons[i]]++;
      if (votes[maxPerson] <= votes[persons[i]]) {
        maxPerson = persons[i];
        winner.put(times[i], maxPerson);
      }
    }
  }

  /**
   *
   *
   * <pre>
   *   Input
   * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
   * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
   * Output
   * [null, 0, 1, 1, 0, 0, 1]
   *
   * Explanation
   * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0],
   * [0, 5, 10, 15, 20, 25, 30]);
   * topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
   * topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
   * topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1],
   * and 1 is leading (as ties go to the most recent vote.)
   * topVotedCandidate.q(15); // return 0
   * topVotedCandidate.q(24); // return 0
   * topVotedCandidate.q(8); // return 1
   * </pre>
   */
  public int q(int t) {
    var entry = winner.floorEntry(t);
    return entry == null ? 0 : entry.getValue();
  }

  public static void main(String[] args) {
    TopVotedCandidate topVotedCandidate =
        new TopVotedCandidate(
            new int[] {0, 1, 1, 0, 0, 1, 0}, new int[] {0, 5, 10, 15, 20, 25, 30});
    topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
    topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
    topVotedCandidate.q(
        25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to
    // the most recent vote.)
    topVotedCandidate.q(15); // return 0
    topVotedCandidate.q(24); // return 0
    topVotedCandidate.q(8); // return 1
  }
}
