package org.example.lld.cinema;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Multiplex {

  private final LocalTime openAt = LocalTime.of(10, 0);
  private final LocalTime closeAt = LocalTime.of(23, 0);
  private final Duration durationOpenFor = Duration.between(openAt, closeAt);

  private final Map<Integer, Movie> runningMovies = new HashMap<>();
  private final Map<String, Screening> screenings = new HashMap<>();

  void addMovie(Movie movie) {
    runningMovies.put(movie.id(), movie);
  }

  void addScreening(Screening screening) {
    screenings.putIfAbsent(screening.id(), screening);
  }

  public boolean canScheduleNewMovie(Movie newMovie) {
    return canSchedule(newMovie);
  }

  private boolean canSchedule(Movie newMovie) {
    var duration = newMovie.duration();
    if (duration.compareTo(durationOpenFor) > 0) {
      return false;
    }

    var screenings = getSortedScreenings();
    if (screenings.isEmpty()) {
      return true;
    }

    // Check if new movie can fit before the first screening
    var firstScreening = screenings.getFirst();
    if (newMovie.canFit(openAt, firstScreening.startTime())) {
      return true;
    }

    // Check gaps between screenings
    for (var i = 1; i < screenings.size(); i++) {
      var previous = screenings.get(i - 1);
      var current = screenings.get(i);

      // Get the movie duration for the previous screening to calculate end time
      var previousMovie = getMovie(previous.movieId()).orElse(null);

      if (previousMovie != null) {
        var previousEndTime = previous.startTime().plus(previousMovie.duration());
        if (newMovie.canFit(previousEndTime, current.startTime())) {
          return true;
        }
      }
    }

    // Check if new movie can fit after the last screening
    var lastScreening = screenings.getLast();
    var lastMovie = getMovie(lastScreening.movieId()).orElse(null);

    if (lastMovie != null) {
      var lastEndTime = lastScreening.startTime().plus(lastMovie.duration());
      return newMovie.canFit(lastEndTime, closeAt);
    }

    return false;
  }

  public List<Movie> getRunningMovies() {
    return List.copyOf(runningMovies.values());
  }

  public List<Screening> getScreenings() {
    return List.copyOf(getSortedScreenings());
  }

  private List<Screening> getSortedScreenings() {
    return screenings.values().stream().sorted(Comparator.comparing(Screening::startTime)).toList();
  }

  public Optional<Movie> getMovie(int movieId) {
    return Optional.ofNullable(runningMovies.get(movieId));
  }
}
