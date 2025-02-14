package org.example.lld.parkinglot;

import java.util.ArrayList;
import java.util.List;
import org.example.lld.parkinglot.Vehicle.Car;
import org.example.lld.parkinglot.Vehicle.MotorCycle;
import org.example.lld.parkinglot.Vehicle.Truck;

class ParkingLot {

  private static ParkingLot instance;

  private final List<ParkingLevel> parkingLevels;

  private ParkingLot(List<ParkingLevel> parkingLevels) {
    this.parkingLevels = parkingLevels;
  }

  public static ParkingLot getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (ParkingLot.class) {
      if (instance == null) {
        instance = new ParkingLot(new ArrayList<>());
      }
      return instance;
    }
  }

  public void addLevel(ParkingLevel parkingLevel) {
    // We can check if parkingLevel.level matches the index in this list.
    parkingLevels.add(parkingLevel);
  }

  public boolean parkVehicle(Vehicle vehicle) {
    for (var level : parkingLevels) {
      if (level.parkVehicle(vehicle)) {
        System.out.println("Vehicle parked successfully.");
        return true;
      }
    }
    System.out.println("Could not park vehicle.");
    return false;
  }

  public boolean unparkVehicle(Vehicle vehicle) {
    for (var level : parkingLevels) {
      if (level.unparkVehicle(vehicle)) {
        return true;
      }
    }
    return false;
  }

  public void displayAvailability() {
    for (var level : parkingLevels) {
      level.displayAvailability();
    }
  }

  public static void main(String[] args) {
    ParkingLot parkingLot = ParkingLot.getInstance();
    parkingLot.addLevel(ParkingLevel.getInstance(1, 5, 5, 5));
    parkingLot.addLevel(ParkingLevel.getInstance(2, 1, 2, 3));

    Vehicle car = new Car("ABC123");
    Vehicle truck = new Truck("XYZ789");
    Vehicle motorcycle = new MotorCycle("M1234");

    // Park vehicles
    parkingLot.parkVehicle(car);
    parkingLot.parkVehicle(truck);
    parkingLot.parkVehicle(motorcycle);

    // Display availability
    parkingLot.displayAvailability();

    // Unpark vehicle
    parkingLot.unparkVehicle(motorcycle);

    // Display updated availability
    parkingLot.displayAvailability();
  }
}
