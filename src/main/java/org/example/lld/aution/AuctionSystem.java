package org.example.lld.aution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class AuctionSystem implements AutoCloseable {
  private final ScheduledExecutorService executorService =
      Executors.newSingleThreadScheduledExecutor();
  private final Map<String, User> users = new ConcurrentHashMap<>();
  private final Map<String, Listing> auctionListings = new ConcurrentHashMap<>();

  public void registerUser(User user) {
    users.put(user.id(), user);
  }

  public User getUser(String userId) {
    return users.get(userId);
  }

  public void createAuctionListing(Listing listing) {
    auctionListings.put(listing.getId(), new ObservableListing(listing));
    startAuctionTimer(auctionListings.get(listing.getId()));
  }

  public void placeBid(String auctionListingId, Bid bid) {
    var listing = auctionListings.get(auctionListingId);
    if (listing != null) {
      listing.placeBid(bid);
    }
  }

  public List<Listing> searchAuctionListings(String keyword) {
    List<Listing> matchingListings = new ArrayList<>();
    for (var auctionListing : auctionListings.values()) {
      var product = auctionListing.getProduct();
      if (product.name().contains(keyword) || product.description().contains(keyword)) {
        matchingListings.add(auctionListing);
      }
    }
    return matchingListings;
  }

  private void startAuctionTimer(Listing auctionListing) {
    executorService.schedule(
        auctionListing::closeAuction,
        auctionListing.getDuration().toMillis(),
        TimeUnit.MILLISECONDS);
  }

  @Override
  public void close() {
    executorService.shutdown();
  }
}
