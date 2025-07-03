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

  public SchedulePlan planSchedule(Movie newMovie) {
    // First check if we can add without removing anything
    if (canScheduleNewMovie(newMovie)) {
      return new SchedulePlan(true, newMovie, null, calculateTotalRevenue() + newMovie.revenue());
    }

    // Find the best screening to replace
    var bestReplacement = findBestReplacement(newMovie);
    if (bestReplacement != null) {
      var currentRevenue = calculateTotalRevenue();
      var replacedMovieRevenue =
          getMovie(bestReplacement.movieId()).map(Movie::revenue).orElse(0.0);
      var newRevenue = currentRevenue - replacedMovieRevenue + newMovie.revenue();
      return new SchedulePlan(true, newMovie, bestReplacement, newRevenue);
    }

    return new SchedulePlan(false, newMovie, null, calculateTotalRevenue());
  }

  private Screening findBestReplacement(Movie newMovie) {
    var sortedScreenings = getSortedScreenings();
    Screening bestToReplace = null;
    double maxRevenueGain = 0;

    for (var i = 0; i < sortedScreenings.size(); i++) {
      var screening = sortedScreenings.get(i);
      var movie = getMovie(screening.movieId()).orElse(null);
      if (movie != null && canFitInSlot(screening, newMovie, sortedScreenings, i)) {
        var revenueGain = newMovie.revenue() - movie.revenue();
        if (revenueGain > maxRevenueGain) {
          maxRevenueGain = revenueGain;
          bestToReplace = screening;
        }
      }
    }

    return bestToReplace;
  }

  private boolean canFitInSlot(
      Screening screeningToReplace, Movie newMovie, List<Screening> sortedScreenings, int index) {
    var startTime = screeningToReplace.startTime();
    var endTime = startTime.plus(newMovie.duration());

    // wrap around
    if (endTime.isBefore(startTime)) return false;

    // Check if it fits before next screening
    if (index < sortedScreenings.size() - 1) {
      var nextScreening = sortedScreenings.get(index + 1);
      return !endTime.isAfter(nextScreening.startTime());
    } else {
      // Last screening - check if it fits before cinema closes
      return !endTime.isAfter(closeAt);
    }
  }

  public double calculateTotalRevenue() {
    return screenings.values().stream()
        .mapToDouble(screening -> getMovie(screening.movieId()).map(Movie::revenue).orElse(0.0))
        .sum();
  }

  public double calculateRevenuePerMovie() {
    var movieRevenues = new HashMap<Integer, Double>();

    for (var screening : screenings.values()) {
      var movie = getMovie(screening.movieId()).orElse(null);
      if (movie != null) {
        movieRevenues.merge(movie.id(), movie.revenue(), Double::sum);
      }
    }

    return movieRevenues.isEmpty()
        ? 0.0
        : movieRevenues.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
  }

  public Map<String, Double> getRevenueBreakdown() {
    var breakdown = new HashMap<String, Double>();

    for (var screening : screenings.values()) {
      getMovie(screening.movieId())
          .ifPresent(movie -> breakdown.merge(movie.title(), movie.revenue(), Double::sum));
    }

    return breakdown;
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

  public record SchedulePlan(
      boolean canSchedule, Movie newMovie, Screening toReplace, double expectedRevenue) {}
}
