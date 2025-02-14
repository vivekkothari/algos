package org.example.lld.parkinglot;

import org.example.lld.parkinglot.Vehicle.VehicleType;

class ParkingSpot {

  // Nullable
  private final int number;
  private final VehicleType type;
  private Vehicle parkedVehicle;

  ParkingSpot(int number, VehicleType type) {
    this.number = number;
    this.type = type;
  }

  public boolean isAvailable(VehicleType type) {
    return parkedVehicle == null && type == this.type;
  }

  public boolean isEmpty() {
    return parkedVehicle == null;
  }

  public synchronized void parkVehicle(Vehicle vehicle) {
    assert isAvailable(type);
    parkedVehicle = vehicle;
  }

  public synchronized void unparkVehicle() {
    parkedVehicle = null;
  }

  public int number() {
    return number;
  }

  public boolean isParked(Vehicle vehicle) {
    return vehicle.equals(parkedVehicle);
  }

  public VehicleType type() {
    return type;
  }

  public String displayAvailability() {
    return "Spot %s is %s"
        .formatted(
            number,
            isEmpty()
                ? "Available"
                : "Occupied by %s which is a %s"
                    .formatted(parkedVehicle.numberPlate(), parkedVehicle.type()));
  }
}
