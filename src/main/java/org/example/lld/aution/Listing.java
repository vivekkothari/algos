package org.example.lld.aution;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

interface Listing {
  String getId();

  Duration getDuration();

  Product getProduct();

  void closeAuction();

  void placeBid(Bid bid);
}

class AuctionListing implements Listing {
  private final String id;
  private final Product product;
  private final int sellingPrice;
  private final Duration duration;
  private final User seller;
  private final List<Bid> bids;

  private AuctionStatus currentStatus;
  private int currentHighestBid;
  private User currentHighestBidder;

  AuctionListing(String id, Product product, int sellingPrice, Duration duration, User seller) {
    this.id = id;
    this.product = product;
    this.sellingPrice = sellingPrice;
    this.duration = duration;
    this.seller = seller;
    bids = new CopyOnWriteArrayList<>();
    currentStatus = AuctionStatus.ACTIVE;
    currentHighestBid = sellingPrice;
    currentHighestBidder = null;
  }

  public synchronized void placeBid(Bid bid) {
    if (currentStatus == AuctionStatus.ACTIVE && bid.amount() > currentHighestBid) {
      currentHighestBidder = bid.bidder();
      currentHighestBid = bid.amount();
      bids.add(bid);
    }
  }

  public synchronized void closeAuction() {
    if (currentStatus == AuctionStatus.ACTIVE) {
      currentStatus = currentHighestBidder == null ? AuctionStatus.CANCELLED : AuctionStatus.CLOSED;
    }
  }

  public String getId() {
    return id;
  }

  public Duration getDuration() {
    return duration;
  }

  public Product getProduct() {
    return product;
  }
}
