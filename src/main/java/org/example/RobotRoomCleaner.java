package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** https://leetcode.com/problems/robot-room-cleaner/ */
public class RobotRoomCleaner {
  public interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();

    void turnRight();

    // Clean the current cell.
    void clean();
  }

  // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
  int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
  Set<List<Integer>> visited = new HashSet<>();
  Robot robot;

  public void goBack() {
    robot.turnRight();
    robot.turnRight();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }

  public void dfs(int row, int col, int d) {
    visited.add(List.of(row, col));
    robot.clean();
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    for (int i = 0; i < 4; ++i) {
      int newD = (d + i) % 4;
      int newRow = row + directions[newD][0];
      int newCol = col + directions[newD][1];

      if (!visited.contains(List.of(newRow, newCol)) && robot.move()) {
        dfs(newRow, newCol, newD);
        goBack();
      }
      // turn the robot following chosen direction : clockwise
      robot.turnLeft();
    }
  }

  public void cleanRoom(Robot robot) {
    this.robot = robot;
    dfs(0, 0, 0);
  }
}
