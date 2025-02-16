package org.example.headfirstdesign.coffe;

import org.example.headfirstdesign.coffe.Beverage.DarkRoast;
import org.example.headfirstdesign.coffe.Beverage.Decaf;
import org.example.headfirstdesign.coffe.Beverage.Espresso;
import org.example.headfirstdesign.coffe.Beverage.HouseBlend;
import org.example.headfirstdesign.coffe.CondimentDecorator.Milk;
import org.example.headfirstdesign.coffe.CondimentDecorator.Mocha;
import org.example.headfirstdesign.coffe.CondimentDecorator.Soy;
import org.example.headfirstdesign.coffe.CondimentDecorator.Whip;

class CoffeMaker {

  static Builder builder() {
    return new Builder();
  }

  static class Builder {

    BeverageBuilder houseBlend() {
      return new BeverageBuilder(new HouseBlend());
    }

    BeverageBuilder darkRoast() {
      return new BeverageBuilder(new DarkRoast());
    }

    BeverageBuilder decaf() {
      return new BeverageBuilder(new Decaf());
    }

    BeverageBuilder espresso() {
      return new BeverageBuilder(new Espresso());
    }
  }

  static class BeverageBuilder {
    private Beverage beverage;

    BeverageBuilder(Beverage beverage) {
      this.beverage = beverage;
    }

    BeverageBuilder soy() {
      beverage = new Soy(beverage);
      return this;
    }

    BeverageBuilder milk() {
      beverage = new Milk(beverage);
      return this;
    }

    BeverageBuilder mocha() {
      beverage = new Mocha(beverage);
      return this;
    }

    BeverageBuilder whip() {
      beverage = new Whip(beverage);
      return this;
    }

    Beverage build() {
      return beverage;
    }
  }
}
