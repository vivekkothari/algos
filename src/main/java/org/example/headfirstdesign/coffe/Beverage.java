package org.example.headfirstdesign.coffe;

import org.example.headfirstdesign.coffe.Beverage.DarkRoast;
import org.example.headfirstdesign.coffe.Beverage.Decaf;
import org.example.headfirstdesign.coffe.Beverage.Espresso;
import org.example.headfirstdesign.coffe.Beverage.HouseBlend;

abstract sealed class Beverage permits DarkRoast, Decaf, Espresso, HouseBlend, CondimentDecorator {
  private final String description;

  Beverage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  abstract int getCost();

  static final class DarkRoast extends Beverage {

    DarkRoast() {
      super("Dark Roast");
    }

    @Override
    int getCost() {
      return 90;
    }
  }

  static final class Decaf extends Beverage {

    Decaf() {
      super("Decaf");
    }

    @Override
    int getCost() {
      return 95;
    }
  }

  static final class Espresso extends Beverage {

    Espresso() {
      super("Espresso");
    }

    @Override
    int getCost() {
      return 99;
    }
  }

  static final class HouseBlend extends Beverage {

    HouseBlend() {
      super("House Blend");
    }

    @Override
    int getCost() {
      return 82;
    }
  }
}
