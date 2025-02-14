package org.example.lld.parkinglot;

sealed interface Vehicle {

  VehicleType type();

  String numberPlate();

  record Truck(String numberPlate) implements Vehicle {

    @Override
    public VehicleType type() {
      return VehicleType.TRUCK;
    }
  }

  record Car(String numberPlate) implements Vehicle {

    @Override
    public VehicleType type() {
      return VehicleType.CAR;
    }
  }

  record MotorCycle(String numberPlate) implements Vehicle {

    @Override
    public VehicleType type() {
      return VehicleType.MOTOR_CYCLE;
    }
  }

  enum VehicleType {
    TRUCK,
    CAR,
    MOTOR_CYCLE
  }
}
