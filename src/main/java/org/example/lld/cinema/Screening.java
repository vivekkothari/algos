package org.example.lld.cinema;

import java.time.LocalTime;

public record Screening(int movieId, LocalTime startTime) {

  Screening(int movieId, int startTime) {
    this(movieId, LocalTime.of(startTime / 100, startTime % 100));
  }

  String id() {
    return movieId + startTime().toString();
  }
}
