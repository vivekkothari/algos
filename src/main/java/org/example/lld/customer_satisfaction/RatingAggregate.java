package org.example.lld.customer_satisfaction;

import java.time.Instant;

public class RatingAggregate {
  private final int agentId;
  private int numRatingsReceived;
  private int totalRatingsReceived;
  private Instant lastReceivedAt;

  RatingAggregate(int agentId) {
    this.agentId = agentId;
  }

  synchronized void addRating(int rating) {
    numRatingsReceived++;
    totalRatingsReceived += rating;
    lastReceivedAt = Instant.now();
  }

  public int getAgentId() {
    return agentId;
  }

  public int getNumRatingsReceived() {
    return numRatingsReceived;
  }

  public int getTotalRatingsReceived() {
    return totalRatingsReceived;
  }

  public Instant getLastReceivedAt() {
    return lastReceivedAt;
  }
}
