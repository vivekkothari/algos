package org.example.headfirstdesign.coffe;

abstract sealed class CondimentDecorator extends Beverage {

  protected final Beverage beverage;

  protected CondimentDecorator(Beverage beverage) {
    super("Condiment");
    this.beverage = beverage;
  }

  public abstract String getDescription();

  abstract int getCost();

  static final class Milk extends CondimentDecorator {

    Milk(Beverage beverage) {
      super(beverage);
    }

    @Override
    public String getDescription() {
      return beverage.getDescription() + ", Milk";
    }

    @Override
    int getCost() {
      return beverage.getCost() + 10;
    }
  }

  static final class Mocha extends CondimentDecorator {

    Mocha(Beverage beverage) {
      super(beverage);
    }

    @Override
    public String getDescription() {
      return beverage.getDescription() + ", Mocha";
    }

    @Override
    int getCost() {
      return beverage.getCost() + 12;
    }
  }

  static final class Soy extends CondimentDecorator {

    Soy(Beverage beverage) {
      super(beverage);
    }

    @Override
    public String getDescription() {
      return beverage.getDescription() + ", Soy";
    }

    @Override
    int getCost() {
      return beverage.getCost() + 13;
    }
  }

  static final class Whip extends CondimentDecorator {

    Whip(Beverage beverage) {
      super(beverage);
    }

    @Override
    public String getDescription() {
      return beverage.getDescription() + ", Whip";
    }

    @Override
    int getCost() {
      return beverage.getCost() + 14;
    }
  }
}
