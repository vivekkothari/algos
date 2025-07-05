package org.example.lld.snake;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
  private final Cell[][] cells;
  private final int maxFoodItemOnBoard;
  private int foodCount = 0;

  public Board(int rows, int cols, int maxFoodItemOnBoard) {
    this.maxFoodItemOnBoard = maxFoodItemOnBoard;
    this.cells = new Cell[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }
  }

  void generateFood() {
    boolean foodGenerated = false;
    int attempts = 0;
    if (foodCount >= maxFoodItemOnBoard) {
      System.out.println("Max food items reached. Cannot generate more food.");
      return;
    }
    do {
      int r = ThreadLocalRandom.current().nextInt(cells.length);
      int c = ThreadLocalRandom.current().nextInt(cells[0].length);
      Cell cell = cells[r][c];
      if (cell.getType() == Cell.CellType.EMPTY) {
        cell.setType(Cell.CellType.FOOD);
        foodGenerated = true;
        foodCount++;
        System.out.println("Food generated at: " + r + ", " + c);
      } else {
        attempts++;
      }
    } while (!foodGenerated && attempts < 3);
  }

  public Cell[][] getCells() {
    return cells;
  }

  void foodEaten() {
    foodCount = Math.max(0, foodCount - 1);
  }

  void printBoard(Cell head) {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        boolean snakeHead = head.equals(cell);
        switch (cell.getType()) {
          case EMPTY -> System.out.print(".  ");
          case SNAKE -> {
            if (snakeHead) {
              System.out.print("🐍 ");
            } else {
              System.out.print("🟩 ");
            }
          }
          case FOOD -> System.out.print("🍎 ");
        }
      }
      System.out.println();
    }
  }
}
