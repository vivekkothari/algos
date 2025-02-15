package org.example.lld.elevator;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.regex.Pattern;

class Lift {
  final int id;
  private int currentFloor;
  private boolean isOpen;
  private final Queue<int[]> requests;
  int time;
  private final Lock lock;

  public Lift(int id) {
    this.id = id;
    this.currentFloor = 0;
    this.isOpen = false;
    this.requests = new ConcurrentLinkedQueue<>();
    this.time = 0;
    this.lock = new ReentrantLock();
  }

  public boolean isIdle() {
    return requests.isEmpty();
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void addRequest(int startFloor, int destFloor) {
    lock.lock();
    try {
      requests.add(new int[] {startFloor, destFloor});
    } finally {
      lock.unlock();
    }
  }

  public void processRequests() {
    while (!requests.isEmpty()) {
      int[] request = fetchRequest();
      if (request != null) {
        handleRequest(request[0], request[1]);
      }
    }
  }

  private int[] fetchRequest() {
    lock.lock();
    try {
      return requests.poll();
    } finally {
      lock.unlock();
    }
  }

  private void handleRequest(int startFloor, int destFloor) {
    moveToFloor(startFloor);
    operateDoor();
    moveToFloor(destFloor);
    operateDoor();
  }

  private void moveToFloor(int floor) {
    while (currentFloor != floor) {
      simulateMovement();
      updateFloor(floor);
      printStatus();
    }
  }

  private void updateFloor(int floor) {
    if (currentFloor < floor) currentFloor++;
    else currentFloor--;
    time++;
  }

  private void operateDoor() {
    openDoor();
    closeDoor();
  }

  private void openDoor() {
    isOpen = true;
    time++;
    printStatus();
    simulateDoorAction();
  }

  private void closeDoor() {
    isOpen = false;
    time++;
    printStatus();
  }

  private void simulateMovement() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void simulateDoorAction() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void printStatus() {
    System.out.println(
        "T="
            + time
            + " LIFT "
            + id
            + " -- > "
            + currentFloor
            + "("
            + (isOpen ? "OPEN" : "CLOSE")
            + ")");
  }
}

public class LiftSystem {
  private final List<Lift> lifts;
  private final int numFloors;
  private final ExecutorService executor;

  public LiftSystem(int numLifts, int numFloors) {
    this.lifts = Collections.synchronizedList(new ArrayList<>());
    this.numFloors = numFloors;
    this.executor = Executors.newFixedThreadPool(numLifts);

    for (int i = 1; i <= numLifts; i++) {
      lifts.add(new Lift(i));
    }
  }

  public void processRequest(int start, int end) {
    if (!isValidFloor(start) || !isValidFloor(end)) {
      System.out.println(
          "Invalid floor request: "
              + start
              + " to "
              + end
              + ". Must be between 0 and "
              + (numFloors - 1));
      return;
    }

    Lift bestLift = findBestLift(start);
    if (bestLift != null) {
      bestLift.addRequest(start, end);
      executor.execute(bestLift::processRequests);
    }
  }

  private boolean isValidFloor(int floor) {
    return floor >= 0 && floor < numFloors;
  }

  private Lift findBestLift(int start) {
    Lift bestLift = null;
    int minDistance = Integer.MAX_VALUE;

    synchronized (lifts) {
      for (Lift lift : lifts) {
        int distance = Math.abs(lift.getCurrentFloor() - start);
        if (distance < minDistance) {
          minDistance = distance;
          bestLift = lift;
        }
      }
    }
    return bestLift;
  }

  public void shutdown() {
    executor.shutdown();
    while (!executor.isTerminated()) {}
    lifts.forEach(lift -> System.out.println("LIFT " + lift.id + ": " + lift.time + " SECONDS"));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("No of Lifts: ");
    int n = scanner.nextInt();
    System.out.print("No of Floors: ");
    int m = scanner.nextInt();

    LiftSystem liftSystem = new LiftSystem(n, m);

    while (true) {
      System.out.print("Enter starting and ending floor (eg 0-3, 1,5) (or non-integer to exit): ");
      if (!scanner.hasNext(Pattern.compile("\\d+-\\d+"))) break;
      var split = scanner.next().split("-");
      int start = Integer.parseInt(split[0]);
      int end = Integer.parseInt(split[1]);
      liftSystem.processRequest(start, end);
    }

    liftSystem.shutdown();
    scanner.close();
  }
}
