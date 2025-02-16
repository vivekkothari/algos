package org.example.headfirstdesign.coffe;

class Barista {
  public static void main(String[] args) {
    var beverage = CoffeMaker.builder().houseBlend().soy().milk().mocha().mocha().whip().build();
    System.out.println(beverage.getDescription());
    System.out.println(beverage.getCost());

    beverage = CoffeMaker.builder().darkRoast().soy().milk().mocha().mocha().whip().build();
    System.out.println(beverage.getDescription());
    System.out.println(beverage.getCost());

    beverage = CoffeMaker.builder().decaf().soy().milk().mocha().mocha().whip().build();
    System.out.println(beverage.getDescription());
    System.out.println(beverage.getCost());

    beverage = CoffeMaker.builder().espresso().milk().mocha().mocha().whip().build();
    System.out.println(beverage.getDescription());
    System.out.println(beverage.getCost());
  }
}
