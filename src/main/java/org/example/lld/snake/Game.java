package org.example.lld.snake;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
  private static final int NONE = 0;
  private static final int UP = 1;
  private static final int RIGHT = 2;
  private static final int DOWN = 3;
  private static final int LEFT = 4;

  private final Board board;
  private final Snake snake;
  private int direction;
  boolean isGameOver;

  public Game(Board board, Snake snake) {
    this.board = board;
    this.snake = snake;
  }

  public void changeDirection(int newDirection) {
    if (!isOppositeDirection(direction, newDirection)) {
      this.direction = newDirection;
    }
  }

  private boolean isOppositeDirection(int current, int next) {
    return (current == UP && next == DOWN)
        || (current == DOWN && next == UP)
        || (current == LEFT && next == RIGHT)
        || (current == RIGHT && next == LEFT);
  }

  public void update() {
    if (isGameOver || direction == NONE) return;

    Cell nextCell = getNextCell(snake.getHead());
    if (nextCell == null) return;

    if (snake.checkCollision(nextCell)) {
      isGameOver = true;
      System.out.println("Game Over! Snake collided with itself.");
      return;
    }

    if (nextCell.getType() == Cell.CellType.FOOD) {
      snake.grow(nextCell);
      board.generateFood();
    } else {
      snake.move(nextCell);
    }
  }

  private Cell getNextCell(Cell head) {
    int newRow = head.getRow();
    int newCol = head.getCol();

    switch (direction) {
      case UP -> newRow--;
      case DOWN -> newRow++;
      case LEFT -> newCol--;
      case RIGHT -> newCol++;
      default -> {
        isGameOver = true;
        return null;
      }
    }

    Cell[][] cells = board.getCells();
    //    if (newRow < 0 || newRow >= cells.length || newCol < 0 || newCol >= cells[0].length) {
    //      isGameOver = true;
    //      System.out.println("Game Over! Snake hit the wall.");
    //      return null;
    //    }
    int wrappedRow = (newRow + cells.length) % cells.length;
    int wrappedCol = (newCol + cells[0].length) % cells[0].length;
    return cells[wrappedRow][wrappedCol];
  }

  public static void main(String[] args) throws InterruptedException {
    Board board = new Board(10, 10);
    Cell head = new Cell(5, 5);
    Snake snake = new Snake(head);
    Game game = new Game(board, snake);
    game.changeDirection(RIGHT);

    board.generateFood();

    while (!game.isGameOver) {
      int newDir = ThreadLocalRandom.current().nextInt(UP, LEFT + 1);
      game.changeDirection(newDir);

      if (ThreadLocalRandom.current().nextDouble() < 0.1) {
        board.generateFood();
      }

      game.update();
      board.printBoard();
      System.out.println("================================");
      Thread.sleep(300);
    }
    System.out.println("Final Snake Length: " + snake.getLength());
  }
}
