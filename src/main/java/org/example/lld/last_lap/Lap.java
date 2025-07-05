package org.example.lld.last_lap;

import java.time.Duration;

public record Lap(String driver, Duration lapDuration, boolean isPitStop) {

  Lap(String driver, Duration lapDuration) {
    this(driver, lapDuration, false);
  }
}
