package org.example.lld.aution;

import java.time.Instant;

public record Bid(String id, User bidder, int amount, Instant bidAt) {
  Bid(String id, User bidder, int amount) {
    this(id, bidder, amount, Instant.now());
  }
}
