package org.example.lld.customer_satisfaction;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class TimeBasedRatingService {

  private final Map<Integer, RatingAggregate> store = new HashMap<>();
  private final Set<String> ratedReplies = new HashSet<>();
  private final Map<Integer, List<Rating>> historicalRatings = new HashMap<>();

  private final AgentService agentService;
  private final RatingCalculationStrategy calculationStrategy =
      new RatingCalculationStrategy.RunningAverage();

  public TimeBasedRatingService(AgentService agentService) {
    this.agentService = agentService;
  }

  public void addRatingForReply(String replyId, int agentId, int rating) {
    if (rating < 1 || rating > 5) {
      throw new IllegalArgumentException("Rating must be between 1 and 5");
    }

    // Prevent duplicate ratings for the same reply
    if (ratedReplies.contains(replyId)) {
      throw new IllegalStateException("Reply " + replyId + " has already been rated");
    }

    // Verify agent exists and get agent name
    var agent =
        agentService.getById(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));

    // Record the rating
    var ratingAggregate = getRatingAggregate(agentId);
    ratingAggregate.addRating(rating);
    ratedReplies.add(replyId);

    // Store historical data
    historicalRatings
        .computeIfAbsent(agent.id(), _ -> new ArrayList<>())
        .add(new Rating(rating, Instant.now()));
  }

  public double getAverageRating(int agentId) {
    return calculationStrategy.calculate(getRatingAggregate(agentId));
  }

  public List<AgentService.Agent> listAgentsByRatings() {
    var agents = agentService.listAgents();
    return agents.stream().sorted(new AgentRanker()).toList();
  }

  public Map<YearMonth, AgentService.Agent> getBestAgentByMonth() {
    Map<YearMonth, Map<Integer, List<Rating>>> monthlyRatings = new HashMap<>();

    // Group ratings by month
    for (var entry : historicalRatings.entrySet()) {
      var agentId = entry.getKey();
      for (var rating : entry.getValue()) {
        var month = YearMonth.from(rating.timestamp().atZone(ZoneId.systemDefault()));
        monthlyRatings
            .computeIfAbsent(month, _ -> new HashMap<>())
            .computeIfAbsent(agentId, _ -> new ArrayList<>())
            .add(rating);
      }
    }

    // Find best agent for each month
    Map<YearMonth, AgentService.Agent> bestAgents = new HashMap<>();
    for (var monthEntry : monthlyRatings.entrySet()) {
      var month = monthEntry.getKey();
      var agentRatings = monthEntry.getValue();
      agentRatings.entrySet().stream()
          .max(Comparator.comparing(this::calculateMonthlyScore))
          .map(Map.Entry::getKey)
          .flatMap(agentService::getById)
          .ifPresent(agent -> bestAgents.put(month, agent));
    }

    return bestAgents;
  }

  public Map<String, List<MonthlyRating>> exportAgentRatingsOverTime() {
    Map<String, List<MonthlyRating>> export = new HashMap<>();

    for (Map.Entry<Integer, List<Rating>> entry : historicalRatings.entrySet()) {
      var agentId = entry.getKey();
      var monthlyRatings =
          entry.getValue().stream()
              .collect(
                  Collectors.groupingBy(
                      rating -> YearMonth.from(rating.timestamp().atZone(ZoneId.systemDefault()))));

      var monthlyAverages =
          monthlyRatings.entrySet().stream()
              .map(
                  monthEntry -> {
                    var month = monthEntry.getKey();
                    var ratings = monthEntry.getValue();
                    var average = ratings.stream().mapToInt(Rating::value).average().orElse(0.0);
                    return new MonthlyRating(month, average, ratings.size());
                  })
              .sorted(Comparator.comparing(MonthlyRating::month))
              .toList();
      export.put(agentService.getById(agentId).orElseThrow().name(), monthlyAverages);
    }

    return export;
  }

  private double calculateMonthlyScore(Map.Entry<Integer, List<Rating>> entry) {
    var ratings = entry.getValue();
    // Return just the average - let the comparator handle tie-breaking properly
    return ratings.stream().mapToInt(Rating::value).average().orElse(0.0);
  }

  private RatingAggregate getRatingAggregate(int agentId) {
    var agent =
        agentService.getById(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));
    return store.computeIfAbsent(agent.id(), (_) -> new RatingAggregate(agentId));
  }

  class AgentRanker implements Comparator<AgentService.Agent> {
    @Override
    public int compare(AgentService.Agent o1, AgentService.Agent o2) {
      var o2Agg = getRatingAggregate(o2.id());
      var o1Agg = getRatingAggregate(o1.id());

      var compare =
          Double.compare(
              calculationStrategy.calculate(o2Agg), calculationStrategy.calculate(o1Agg));
      if (compare != 0) return compare;

      compare = Integer.compare(o2Agg.getNumRatingsReceived(), o1Agg.getNumRatingsReceived());
      if (compare != 0) return compare;

      return o1.name().compareTo(o2.name());
    }
  }

  public record Rating(int value, Instant timestamp) {}

  public record MonthlyRating(YearMonth month, double average, int count) {}
}
