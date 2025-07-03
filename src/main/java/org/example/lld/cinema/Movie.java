package org.example.lld.cinema;

import java.time.Duration;
import java.time.LocalTime;

public record Movie(int id, String title, Duration duration, double revenue) {

  Movie(int id, String title, int durationInMinutes) {
    this(id, title, Duration.ofMinutes(durationInMinutes), 0.0);
    if (durationInMinutes > 60 * 24) {
      throw new IllegalArgumentException("Movie too long");
    }
  }

  Movie(int id, String title, int durationInMinutes, double revenue) {
    this(id, title, Duration.ofMinutes(durationInMinutes), revenue);
  }

  public boolean canFit(LocalTime previous, LocalTime current) {
    return Duration.between(previous, current).compareTo(duration) >= 0;
  }
}
