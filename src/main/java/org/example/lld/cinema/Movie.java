package org.example.lld.cinema;

import java.time.Duration;
import java.time.LocalTime;

public record Movie(int id, String title, Duration duration) {

  Movie(int id, String title, int durationInMinutes) {
    this(id, title, Duration.ofMinutes(durationInMinutes));
  }

  public boolean canFit(LocalTime previous, LocalTime current) {
    return Duration.between(previous, current).compareTo(duration) >= 0;
  }
}
