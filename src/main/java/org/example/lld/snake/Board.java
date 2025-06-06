package org.example.lld.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
  private final Cell[][] cells;

  public Board(int rows, int cols) {
    this.cells = new Cell[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }
  }

  void generateFood() {
    List<Cell> emptyCells = new ArrayList<>();
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        if (cell.getType() == Cell.CellType.EMPTY) {
          emptyCells.add(cell);
        }
      }
    }

    if (emptyCells.isEmpty()) return;

    Cell chosen = emptyCells.get(ThreadLocalRandom.current().nextInt(emptyCells.size()));
    chosen.setType(Cell.CellType.FOOD);
    System.out.println("Food generated at: " + chosen.getRow() + ", " + chosen.getCol());
  }

  public Cell[][] getCells() {
    return cells;
  }

  void printBoard() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        switch (cell.getType()) {
          case EMPTY -> System.out.print(". ");
          case SNAKE -> System.out.print("S ");
          case FOOD -> System.out.print("F ");
        }
      }
      System.out.println();
    }
  }
}
