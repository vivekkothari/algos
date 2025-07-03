package org.example.lld.cinema;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class MultiplexTest {

  @Test
  public void testCanScheduleNewMovieInEmptySchedule() {
    Multiplex multiplex = new Multiplex();
    Movie newMovie = new Movie(1, "New Movie", 120); // 2 hours

    assertTrue(multiplex.canScheduleNewMovie(newMovie));
  }

  @Test
  public void testCanScheduleNewMovieWithExistingSchedule() {
    Multiplex multiplex = new Multiplex();

    // Add existing movies
    Movie movie1 = new Movie(1, "Movie 1", 120); // 2 hours
    Movie movie2 = new Movie(2, "Movie 2", 90); // 1.5 hours
    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);

    // Add screenings based on your JSON example (converted to LocalTime)
    // 660 minutes = 11:00 AM, 840 minutes = 2:00 PM, etc.
    multiplex.addScreening(new Screening(1, LocalTime.of(11, 0))); // 11:00 AM
    multiplex.addScreening(new Screening(1, LocalTime.of(14, 0))); // 2:00 PM
    multiplex.addScreening(new Screening(2, LocalTime.of(17, 0))); // 5:00 PM
    multiplex.addScreening(new Screening(1, LocalTime.of(20, 0))); // 8:00 PM

    // Try to schedule a new 1-hour movie
    Movie newMovie = new Movie(3, "New Movie", 60);
    assertTrue(multiplex.canScheduleNewMovie(newMovie)); // Should fit in gaps

    // Try to schedule a very long movie that won't fit
    Movie longMovie = new Movie(4, "Long Movie", 300); // 5 hours
    assertFalse(multiplex.canScheduleNewMovie(longMovie));
  }

  @Test
  public void testCanScheduleNewMovieBeforeFirstScreening() {
    Multiplex multiplex = new Multiplex();

    Movie existingMovie = new Movie(1, "Existing Movie", 120);
    multiplex.addMovie(existingMovie);
    multiplex.addScreening(new Screening(1, LocalTime.of(12, 0))); // 12:00 PM

    // New movie should fit before 12:00 PM (cinema opens at 10:00 AM)
    Movie newMovie = new Movie(2, "New Movie", 90); // 1.5 hours
    assertTrue(multiplex.canScheduleNewMovie(newMovie));
  }

  @Test
  public void testCanScheduleNewMovieAfterLastScreening() {
    Multiplex multiplex = new Multiplex();

    Movie existingMovie = new Movie(1, "Existing Movie", 120);
    multiplex.addMovie(existingMovie);
    multiplex.addScreening(new Screening(1, LocalTime.of(18, 0))); // 6:00 PM

    // New movie should fit after existing movie ends (8:00 PM) before cinema closes (11:00 PM)
    Movie newMovie = new Movie(2, "New Movie", 120); // 2 hours
    assertTrue(multiplex.canScheduleNewMovie(newMovie));
  }

  @Test
  public void testCannotScheduleMovieThatExceedsCinemaHours() {
    Multiplex multiplex = new Multiplex();

    // Movie longer than cinema operating hours (13 hours)
    Movie tooLongMovie = new Movie(1, "Too Long Movie", 14 * 60); // 14 hours
    assertFalse(multiplex.canScheduleNewMovie(tooLongMovie));
  }

  @Test
  public void testCannotScheduleMovieWhenNoGapsAvailable() {
    Multiplex multiplex = new Multiplex();

    // Fill the entire day with back-to-back movies
    Movie movie1 = new Movie(1, "Movie 1", 3 * 60); // 3 hours
    Movie movie2 = new Movie(2, "Movie 2", 4 * 60); // 4 hours
    Movie movie3 = new Movie(3, "Movie 3", 6 * 60); // 6 hours

    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);
    multiplex.addMovie(movie3);

    multiplex.addScreening(new Screening(1, LocalTime.of(10, 0))); // 10:00 AM - 1:00 PM
    multiplex.addScreening(new Screening(2, LocalTime.of(13, 0))); // 1:00 PM - 5:00 PM
    multiplex.addScreening(new Screening(3, LocalTime.of(17, 0))); // 5:00 PM - 11:00 PM

    // Try to add any movie - should fail
    Movie newMovie = new Movie(4, "New Movie", 60); // 1 hour
    assertFalse(multiplex.canScheduleNewMovie(newMovie));
  }

  @Test
  public void testGetMoviesAndScreenings() {
    Multiplex multiplex = new Multiplex();

    Movie movie1 = new Movie(1, "Movie 1", 120);
    Movie movie2 = new Movie(2, "Movie 2", 90);
    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);

    Screening screening1 = new Screening(1, LocalTime.of(11, 0));
    Screening screening2 = new Screening(2, LocalTime.of(14, 0));
    multiplex.addScreening(screening1);
    multiplex.addScreening(screening2);

    assertEquals(2, multiplex.getRunningMovies().size());
    assertEquals(2, multiplex.getScreenings().size());
    assertEquals(Optional.of(movie1), multiplex.getMovie(1));
    assertEquals(Optional.of(movie2), multiplex.getMovie(2));
    assertEquals(Optional.empty(), multiplex.getMovie(999));
  }

  @Test
  public void testPlanScheduleWithAvailableSlot() {
    Multiplex multiplex = new Multiplex();

    // Add existing movies with revenue
    Movie movie1 = new Movie(1, "Movie 1", 120, 500.0); // 2 hours, $500
    Movie movie2 = new Movie(2, "Movie 2", 90, 300.0); // 1.5 hours, $300
    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);

    // Add one screening - leaves gaps
    multiplex.addScreening(new Screening(1, LocalTime.of(14, 0))); // 2:00 PM

    // Try to schedule a new movie in available slot
    Movie newMovie = new Movie(3, "New Movie", 60, 400.0); // 1 hour, $400
    var plan = multiplex.planSchedule(newMovie);

    assertTrue(plan.canSchedule());
    assertNull(plan.toReplace()); // No replacement needed
    assertEquals(900.0, plan.expectedRevenue(), 0.01); // 500 + 400 = 900
  }

  @Test
  public void testPlanScheduleWithReplacement() {
    Multiplex multiplex = new Multiplex();

    // Create a full schedule
    Movie movie1 = new Movie(1, "Low Revenue Movie", 120, 200.0); // 2 hours, $200
    Movie movie2 = new Movie(2, "High Revenue Movie", 120, 800.0); // 2 hours, $800
    Movie movie3 = new Movie(3, "Medium Revenue Movie", 180, 400.0); // 3 hours, $400

    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);
    multiplex.addMovie(movie3);

    // Fill the schedule (10 AM - 11 PM = 13 hours)
    multiplex.addScreening(new Screening(1, LocalTime.of(10, 0))); // 10:00 AM - 12:00 PM
    multiplex.addScreening(new Screening(2, LocalTime.of(12, 0))); // 12:00 PM - 2:00 PM
    multiplex.addScreening(new Screening(3, LocalTime.of(14, 0))); // 2:00 PM - 5:00 PM
    multiplex.addScreening(new Screening(1, LocalTime.of(17, 0))); // 5:00 PM - 7:00 PM
    multiplex.addScreening(new Screening(2, LocalTime.of(19, 0))); // 7:00 PM - 9:00 PM
    multiplex.addScreening(new Screening(1, LocalTime.of(21, 0))); // 9:00 PM - 11:00 PM

    // Try to add a very profitable movie
    Movie newMovie = new Movie(4, "Blockbuster", 120, 1000.0); // 2 hours, $1000
    var plan = multiplex.planSchedule(newMovie);

    assertTrue(plan.canSchedule());
    assertNotNull(plan.toReplace());

    // Should replace the lowest revenue movie (movie1 screening)
    var replacedMovie = multiplex.getMovie(plan.toReplace().movieId()).orElse(null);
    assertEquals(movie1, replacedMovie);

    // Expected revenue = current total - replaced movie + new movie
    double currentRevenue = multiplex.calculateTotalRevenue();
    double expectedRevenue = currentRevenue - 200.0 + 1000.0;
    assertEquals(expectedRevenue, plan.expectedRevenue(), 0.01);
  }

  @Test
  public void testPlanScheduleCannotFit() {
    Multiplex multiplex = new Multiplex();

    // Fill the entire schedule so there's no space anywhere
    Movie movie1 = new Movie(1, "Movie 1", 4 * 60, 500.0); // 4 hours
    Movie movie2 = new Movie(2, "Movie 2", 4 * 60, 300.0); // 4 hours
    Movie movie3 = new Movie(3, "Movie 3", 5 * 60, 200.0); // 5 hours

    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);
    multiplex.addMovie(movie3);

    // Fill entire cinema hours (10 AM - 11 PM = 13 hours)
    multiplex.addScreening(new Screening(1, LocalTime.of(10, 0))); // 10:00 AM - 2:00 PM
    multiplex.addScreening(new Screening(2, LocalTime.of(14, 0))); // 2:00 PM - 6:00 PM
    multiplex.addScreening(new Screening(3, LocalTime.of(18, 0))); // 6:00 PM - 11:00 PM

    // Try to add a 2-hour movie - even though smaller, no slots available
    Movie newMovie = new Movie(4, "New Movie", 120 * 10, 1000.0); // 20 hours
    var plan = multiplex.planSchedule(newMovie);

    assertFalse(plan.canSchedule());
    assertNull(plan.toReplace());
    assertEquals(multiplex.calculateTotalRevenue(), plan.expectedRevenue(), 0.01);
  }

  @Test
  public void testCalculateTotalRevenue() {
    Multiplex multiplex = new Multiplex();

    Movie movie1 = new Movie(1, "Movie 1", 120, 500.0);
    Movie movie2 = new Movie(2, "Movie 2", 90, 300.0);
    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);

    // Add multiple screenings
    multiplex.addScreening(new Screening(1, LocalTime.of(11, 0))); // $500
    multiplex.addScreening(new Screening(2, LocalTime.of(14, 0))); // $300
    multiplex.addScreening(new Screening(1, LocalTime.of(17, 0))); // $500

    assertEquals(1300.0, multiplex.calculateTotalRevenue(), 0.01);
  }

  @Test
  public void testCalculateRevenuePerMovie() {
    Multiplex multiplex = new Multiplex();

    Movie movie1 = new Movie(1, "Movie 1", 120, 500.0);
    Movie movie2 = new Movie(2, "Movie 2", 90, 300.0);
    Movie movie3 = new Movie(3, "Movie 3", 90, 400.0);
    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);
    multiplex.addMovie(movie3);

    // Add screenings
    multiplex.addScreening(new Screening(1, LocalTime.of(11, 0))); // Movie 1: $500
    multiplex.addScreening(new Screening(2, LocalTime.of(14, 0))); // Movie 2: $300
    multiplex.addScreening(new Screening(1, LocalTime.of(17, 0))); // Movie 1: $500 (total $1000)

    // Revenue per movie = (1000 + 300) / 2 = 650
    assertEquals(650.0, multiplex.calculateRevenuePerMovie(), 0.01);
  }

  @Test
  public void testGetRevenueBreakdown() {
    Multiplex multiplex = new Multiplex();

    Movie movie1 = new Movie(1, "Action Movie", 120, 500.0);
    Movie movie2 = new Movie(2, "Comedy Movie", 90, 300.0);
    multiplex.addMovie(movie1);
    multiplex.addMovie(movie2);

    multiplex.addScreening(new Screening(1, LocalTime.of(11, 0))); // $500
    multiplex.addScreening(new Screening(2, LocalTime.of(14, 0))); // $300
    multiplex.addScreening(new Screening(1, LocalTime.of(17, 0))); // $500

    var breakdown = multiplex.getRevenueBreakdown();

    assertEquals(1000.0, breakdown.get("Action Movie"), 0.01);
    assertEquals(300.0, breakdown.get("Comedy Movie"), 0.01);
    assertEquals(2, breakdown.size());
  }
}
