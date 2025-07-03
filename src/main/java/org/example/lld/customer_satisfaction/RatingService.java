package org.example.lld.customer_satisfaction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingService {

  private final Map<Integer, RatingAggregate> store = new HashMap<>();

  private final AgentService agentService;
  private final RatingCalculationStrategy calculationStrategy =
      new RatingCalculationStrategy.RunningAverage();

  public RatingService(AgentService agentService) {
    this.agentService = agentService;
  }

  public void addRating(int agentId, int rating) {
    if (rating < 1 || rating > 5) {
      throw new IllegalArgumentException("Rating must be between 1 and 5");
    }
    RatingAggregate ratingAggregate = getRatingAggregate(agentId);
    ratingAggregate.addRating(rating);
  }

  public double getAverageRating(int agentId) {
    return calculationStrategy.calculate(getRatingAggregate(agentId));
  }

  public List<AgentService.Agent> listAgentsByRatings() {
    List<AgentService.Agent> agents = agentService.listAgents();
    return agents.stream().sorted(new AgentRanker()).toList();
  }

  private RatingAggregate getRatingAggregate(int agentId) {
    var agent =
        agentService.getById(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));
    return store.computeIfAbsent(agent.id(), (_) -> new RatingAggregate(agentId));
  }

  class AgentRanker implements Comparator<AgentService.Agent> {

    @Override
    public int compare(AgentService.Agent o1, AgentService.Agent o2) {
      RatingAggregate o2Agg = getRatingAggregate(o2.id());
      RatingAggregate o1Agg = getRatingAggregate(o1.id());
      int compare =
          Double.compare(
              calculationStrategy.calculate(o2Agg), calculationStrategy.calculate(o1Agg));
      if (compare != 0) return compare;
      compare = Integer.compare(o2Agg.getNumRatingsReceived(), o1Agg.getNumRatingsReceived());
      if (compare != 0) return compare;
      return o1.name().compareTo(o2.name());
    }
  }
}
