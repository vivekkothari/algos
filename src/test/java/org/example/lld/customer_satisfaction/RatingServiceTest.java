package org.example.lld.customer_satisfaction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class RatingServiceTest {

  private final AgentService agentService = new AgentService();
  private final RatingService ratingService = new RatingService(agentService);

  @Test
  public void testAvgRating() {
    var vivek = agentService.addAgent("vivek");
    var john = agentService.addAgent("john");
    var frank = agentService.addAgent("frank");

    ratingService.addRating(vivek.id(), 2);
    ratingService.addRating(vivek.id(), 3);
    ratingService.addRating(vivek.id(), 4);
    ratingService.addRating(john.id(), 3);

    assertEquals(3, ratingService.getAverageRating(vivek.id()));
    assertEquals(3, ratingService.getAverageRating(john.id()));
    assertEquals(0, ratingService.getAverageRating(frank.id()));

    assertEquals(List.of(vivek, john, frank), ratingService.listAgentsByRatings());
  }

  @Test
  public void testInvalidRating() {
    var vivek = agentService.addAgent("vivek");
    assertThrows(IllegalArgumentException.class, () -> ratingService.addRating(vivek.id(), 10));
    assertThrows(IllegalArgumentException.class, () -> ratingService.addRating(vivek.id(), 0));
    assertThrows(IllegalArgumentException.class, () -> ratingService.addRating(vivek.id(), -1));
  }

  @Test
  public void testAgentNotFound() {
    assertThrows(
        RuntimeException.class, () -> ratingService.getAverageRating(9999), "Agent not found");
  }
}
