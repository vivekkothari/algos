package org.example.lld.last_lap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeroCalculatorTest {

  @Test
  public void testBasicScenario() {
    HeroCalculator calculator = new HeroCalculator();

    // D1: avg=100, last=110, gain=+10 (slower)
    calculator.recordLap("D1", 100);
    calculator.recordLap("D1", 110);

    // D2: avg=90, last=95, gain=+5 (slower)
    calculator.recordLap("D2", 90);
    calculator.recordLap("D2", 95);

    // D3: avg=70, last=60, gain=-10 (faster - hero!)
    calculator.recordLap("D3", 70);
    calculator.recordLap("D3", 60);

    assertEquals("D3", calculator.getLastLapHero());
  }

  @Test
  public void testSingleLapDriversIgnored() {
    HeroCalculator calculator = new HeroCalculator();

    // Single lap drivers
    calculator.recordLap("D1", 100);
    calculator.recordLap("D2", 90);

    // Multi-lap driver
    calculator.recordLap("D3", 80);
    calculator.recordLap("D3", 70);

    assertEquals("D3", calculator.getLastLapHero());
  }
}
