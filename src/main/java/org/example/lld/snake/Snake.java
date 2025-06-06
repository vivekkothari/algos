package org.example.lld.snake;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
  private final Deque<Cell> body = new LinkedList<>();

  public Snake(Cell head) {
    body.addFirst(head);
    head.setType(Cell.CellType.SNAKE);
  }

  public void move(Cell newHead) {
    body.addFirst(newHead);
    newHead.setType(Cell.CellType.SNAKE);

    Cell tail = body.removeLast();
    tail.setType(Cell.CellType.EMPTY);
  }

  public void grow(Cell newHead) {
    if (newHead.getType() != Cell.CellType.FOOD) {
      throw new IllegalArgumentException("Can't grow on non-food cell.");
    }
    body.addFirst(newHead);
    newHead.setType(Cell.CellType.SNAKE);
  }

  public boolean checkCollision(Cell cell) {
    return body.contains(cell);
  }

  public Cell getHead() {
    return body.getFirst();
  }

  public int getLength() {
    return body.size();
  }
}
