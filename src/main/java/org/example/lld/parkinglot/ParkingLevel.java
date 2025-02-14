package org.example.lld.parkinglot;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.example.lld.parkinglot.Vehicle.VehicleType;

class ParkingLevel {
  private final int level;
  private final List<ParkingSpot> parkingSpots;

  private ParkingLevel(int level, List<ParkingSpot> parkingSpots) {
    this.level = level;
    this.parkingSpots = parkingSpots;
  }

  static ParkingLevel getInstance(int level, int motorCycles, int cars, int trucks) {
    return new ParkingLevel(
        level,
        Stream.<List<ParkingSpot>>builder()
            .add(createParkingSpots(motorCycles, VehicleType.MOTOR_CYCLE))
            .add(createParkingSpots(cars, VehicleType.CAR))
            .add(createParkingSpots(trucks, VehicleType.TRUCK))
            .build()
            .flatMap(List::stream)
            .toList());
  }

  private static List<ParkingSpot> createParkingSpots(int num, VehicleType motorCycle) {
    return IntStream.range(0, num).mapToObj(i -> new ParkingSpot(i, motorCycle)).toList();
  }

  public synchronized boolean parkVehicle(Vehicle vehicle) {
    return parkingSpots.stream()
        .filter(spot -> spot.isAvailable(vehicle.type()))
        .findFirst()
        .map(
            spot -> {
              spot.parkVehicle(vehicle);
              return true;
            })
        .orElse(false);
  }

  public synchronized boolean unparkVehicle(Vehicle vehicle) {
    for (var parkingSpot : parkingSpots) {
      if (parkingSpot.isParked(vehicle)) {
        parkingSpot.unparkVehicle();
        return true;
      }
    }
    return false;
  }

  public void displayAvailability() {
    System.out.println("Level " + level + " Availability:");
    for (var spot : parkingSpots) {
      System.out.println(spot.displayAvailability());
    }
  }
}
