package org.example.lld.last_lap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PitStopTest {

  @Test
  public void testPitStopExcluded() {
    HeroCalculator calculator = new HeroCalculator();

    // D1: Normal laps (80, 85) avg=82.5, last=85, gain=+2.5
    calculator.recordLap("D1", 80);
    calculator.recordLap("D1", 85);

    // D2: Has pit stop (90, 120-pit, 75)
    // Including pit: avg=(90+120+75)/3=95, last=75, gain=-20
    // Excluding pit: avg=(90+75)/2=82.5, last=75, gain=-7.5
    calculator.recordLap("D2", 90);
    calculator.recordLap("D2", 120, true); // pit stop
    calculator.recordLap("D2", 75);

    // Test including pit stops
    assertEquals("D2", calculator.getLastLapHero()); // D2 has -20 gain

    // Test excluding pit stops
    assertEquals("D2", calculator.getLastLapHeroExcludingPit()); // D2 has -7.5 gain
  }

  @Test
  public void testLastLapIsPitStop() {
    HeroCalculator calculator = new HeroCalculator();

    // D1: Normal laps ending with pit stop
    calculator.recordLap("D1", 80);
    calculator.recordLap("D1", 85);
    calculator.recordLap("D1", 130, true); // last lap is pit stop

    // Should still calculate gain properly
    String hero = calculator.getLastLapHero();
    assertEquals("D1", hero);
  }
}
