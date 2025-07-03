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
}
