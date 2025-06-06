package org.example.lld.snake;

import java.util.Objects;

public class Cell {
  private final int row, col;
  private CellType type = CellType.EMPTY;

  public enum CellType {
    EMPTY,
    SNAKE,
    FOOD
  }

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public void setType(CellType type) {
    this.type = type;
  }

  public CellType getType() {
    return type;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cell cell)) return false;
    return row == cell.row && col == cell.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override
  public String toString() {
    return "Cell{" + "row=" + row + ", col=" + col + ", type=" + type + '}';
  }
}
