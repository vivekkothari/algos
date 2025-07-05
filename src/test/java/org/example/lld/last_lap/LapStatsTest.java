package org.example.lld.last_lap;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class LapStatsTest {

  @Test
  public void testLastStats() {
    LapStats vivek = new LapStats("vivek");
    vivek.recordLap(new Lap("vivek", Duration.ofSeconds(10)));
    vivek.recordLap(new Lap("vivek", Duration.ofSeconds(15)));
    vivek.recordLap(new Lap("vivek", Duration.ofSeconds(5)));
    assertEquals(Duration.ofSeconds(-5), vivek.getLastLapGain());
  }

  @Test
  public void testLastStatsNoRecordedLaps() {
    LapStats vivek = new LapStats("vivek");
    assertEquals(Duration.ZERO, vivek.getLastLapGain());
  }

  @Test
  public void testRecordLapWithDifferentDriver() {
    LapStats vivek = new LapStats("vivek");
    assertThrows(
        IllegalArgumentException.class,
        () -> vivek.recordLap(new Lap("kothari", Duration.ofSeconds(1))));
  }
}
