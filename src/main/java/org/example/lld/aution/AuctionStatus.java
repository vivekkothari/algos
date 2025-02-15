package org.example.lld.aution;

public enum AuctionStatus {
  // Auction is accepting bids
  ACTIVE,
  // Auction was won by a bidder
  CLOSED,
  // Auction was cancelled before it could be won
  CANCELLED,
  // Not accepting bids
  PAUSED
}
