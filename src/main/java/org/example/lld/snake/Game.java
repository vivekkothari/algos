package org.example.lld.snake;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
  public enum Direction {
    NONE,
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static Direction getRandomDirection() {
      int randomDir = ThreadLocalRandom.current().nextInt(1, 5);
      return switch (randomDir) {
        case 1 -> UP;
        case 2 -> RIGHT;
        case 3 -> DOWN;
        case 4 -> LEFT;
        default -> NONE;
      };
    }

    boolean isOppositeDirection(Direction next) {
      return (this == Direction.UP && next == Direction.DOWN)
          || (this == Direction.DOWN && next == Direction.UP)
          || (this == Direction.LEFT && next == Direction.RIGHT)
          || (this == Direction.RIGHT && next == Direction.LEFT);
    }
  }

  private final Board board;
  private final Snake snake;
  private Direction direction;
  boolean isGameOver;

  public Game(Board board, Snake snake) {
    this.board = board;
    this.snake = snake;
  }

  public void changeDirection(Direction newDirection) {
    if (direction == null) {
      this.direction = newDirection;
    } else if (!direction.isOppositeDirection(newDirection)) {
      this.direction = newDirection;
    }
  }

  public void update() {
    if (isGameOver || direction == Direction.NONE) return;

    Cell nextCell = getNextCell(snake.getHead());
    if (nextCell == null) return;

    if (snake.checkCollision(nextCell)) {
      isGameOver = true;
      System.out.println("Game Over! Snake collided with itself.");
      return;
    }

    if (nextCell.getType() == Cell.CellType.FOOD) {
      snake.grow(nextCell);
      board.foodEaten();
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
    Board board = new Board(10, 10, 3);
    Cell head = board.getCells()[0][0];
    Snake snake = new Snake(head);
    Game game = new Game(board, snake);
    game.changeDirection(Direction.DOWN);

    while (!game.isGameOver) {
      board.printBoard(snake.getHead());
      System.out.println("================================");
      game.changeDirection(Direction.getRandomDirection());

      if (ThreadLocalRandom.current().nextDouble() < 0.1) {
        board.generateFood();
      }

      game.update();
      Thread.sleep(1000);
    }
    System.out.println("Final Snake Length: " + snake.getLength());
  }
}
