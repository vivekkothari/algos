package org.example.lld.last_lap;

import java.time.Duration;

public class LapStats {
  private final String driver;
  private Duration totalLapTime;
  private Duration totalLapTimeExcludingPitStops;
  private int totalLaps;
  private int totalLapsExcludingPitStops;
  private Lap lastLap;

  public LapStats(String driver) {
    this.driver = driver;
    this.totalLapTime = Duration.ZERO;
    this.totalLapTimeExcludingPitStops = Duration.ZERO;
  }

  public String driver() {
    return driver;
  }

  public Duration totalLapTime() {
    return totalLapTime;
  }

  public int totalLaps() {
    return totalLaps;
  }

  public Lap lastLap() {
    return lastLap;
  }

  public void recordLap(Lap lap) {
    if (!lap.driver().equals(driver)) {
      throw new IllegalArgumentException("Driver mismatch");
    }
    this.lastLap = lap;
    totalLaps++;
    totalLapTime = totalLapTime.plus(lap.lapDuration());
    if (!lap.isPitStop()) {
      totalLapsExcludingPitStops++;
      totalLapTimeExcludingPitStops = totalLapTimeExcludingPitStops.plus(lap.lapDuration());
    }
  }

  public Duration getLastLapGain() {
    return getLastLapGain(lastLap, totalLaps, totalLapTime);
  }

  public Duration getLastLapGainExcludingPitStops() {
    int totalLaps = totalLapsExcludingPitStops;
    Duration totalLapTime = totalLapTimeExcludingPitStops;
    if (lastLap.isPitStop()) {
      totalLaps += 1;
      totalLapTime = totalLapTime.plus(lastLap.lapDuration());
    }
    return getLastLapGain(lastLap, totalLaps, totalLapTime);
  }

  private static Duration getLastLapGain(Lap lastLap, int totalLaps, Duration totalLapTime) {
    if (totalLaps == 0) return Duration.ZERO;
    Duration avg = totalLapTime.dividedBy(totalLaps);
    return lastLap.lapDuration().minus(avg);
  }
}
