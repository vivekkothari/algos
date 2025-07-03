package org.example.lld.customer_satisfaction;

import static org.junit.jupiter.api.Assertions.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TimeBasedRatingServiceTest {

  private final AgentService agentService = new AgentService();
  private final TimeBasedRatingService ratingService = new TimeBasedRatingService(agentService);

  @Test
  public void testDuplicateRatingPrevention() {
    var vivek = agentService.addAgent("vivek");

    // First rating should work
    ratingService.addRatingForReply("reply1", vivek.id(), 5);

    // Second rating for same reply should fail
    assertThrows(
        IllegalStateException.class,
        () -> ratingService.addRatingForReply("reply1", vivek.id(), 4));

    // Different reply should work
    ratingService.addRatingForReply("reply2", vivek.id(), 3);

    assertEquals(4.0, ratingService.getAverageRating(vivek.id()));
  }

  @Test
  public void testMonthlyBestAgent() throws InterruptedException {
    var alice = agentService.addAgent("alice");
    var bob = agentService.addAgent("bob");

    // Add ratings for different agents
    ratingService.addRatingForReply("r1", alice.id(), 5);
    ratingService.addRatingForReply("r2", alice.id(), 4);
    ratingService.addRatingForReply("r3", bob.id(), 3);

    // Small delay to ensure different timestamps
    Thread.sleep(10);

    Map<YearMonth, AgentService.Agent> monthlyBest = ratingService.getBestAgentByMonth();

    // Should have current month's best agent
    assertFalse(monthlyBest.isEmpty());

    // Alice should be the best agent (higher average)
    YearMonth currentMonth = YearMonth.now();
    assertTrue(monthlyBest.containsKey(currentMonth));
    assertEquals("alice", monthlyBest.get(currentMonth).name());
  }

  @Test
  public void testExportRatingsOverTime() {
    var charlie = agentService.addAgent("charlie");
    var diana = agentService.addAgent("diana");

    // Add some ratings
    ratingService.addRatingForReply("r1", charlie.id(), 5);
    ratingService.addRatingForReply("r2", charlie.id(), 4);
    ratingService.addRatingForReply("r3", diana.id(), 3);

    Map<String, List<TimeBasedRatingService.MonthlyRating>> export =
        ratingService.exportAgentRatingsOverTime();

    // Should have data for both agents
    assertTrue(export.containsKey("charlie"));
    assertTrue(export.containsKey("diana"));

    // Charlie should have average of 4.5
    List<TimeBasedRatingService.MonthlyRating> charlieRatings = export.get("charlie");
    assertEquals(1, charlieRatings.size());
    assertEquals(4.5, charlieRatings.get(0).average(), 0.001);
    assertEquals(2, charlieRatings.get(0).count());

    // Diana should have average of 3.0
    List<TimeBasedRatingService.MonthlyRating> dianaRatings = export.get("diana");
    assertEquals(1, dianaRatings.size());
    assertEquals(3.0, dianaRatings.get(0).average(), 0.001);
    assertEquals(1, dianaRatings.get(0).count());
  }

  @Test
  public void testTieBreaker() {
    var eve = agentService.addAgent("eve");
    var frank = agentService.addAgent("frank");
    var grace = agentService.addAgent("grace");

    // All agents get same average rating
    ratingService.addRatingForReply("r1", eve.id(), 3);
    ratingService.addRatingForReply("r2", frank.id(), 3);
    ratingService.addRatingForReply("r3", grace.id(), 3);

    // Frank gets more ratings (tie-breaker)
    ratingService.addRatingForReply("r4", frank.id(), 3);

    List<AgentService.Agent> rankedAgents = ratingService.listAgentsByRatings();

    // Frank should be first (more ratings), then alphabetical order
    assertEquals("frank", rankedAgents.get(0).name());
    assertEquals("eve", rankedAgents.get(1).name());
    assertEquals("grace", rankedAgents.get(2).name());
  }

  @Test
  public void testInvalidRating() {
    var agent = agentService.addAgent("test");
    assertThrows(
        IllegalArgumentException.class,
        () -> ratingService.addRatingForReply("r1", agent.id(), 10));
    assertThrows(
        IllegalArgumentException.class, () -> ratingService.addRatingForReply("r2", agent.id(), 0));
  }

  @Test
  public void testAgentNotFound() {
    assertThrows(RuntimeException.class, () -> ratingService.addRatingForReply("r1", 9999, 5));
  }
}
