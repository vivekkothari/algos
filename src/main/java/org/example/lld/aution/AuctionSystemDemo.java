package org.example.lld.aution;

import java.time.Duration;
import java.util.List;

class AuctionSystemDemo {
  public static void main(String[] args) {
    try (var system = new AuctionSystem()) {
      var vivek = new User("1", "Vivek", "vivek@foo.co");
      system.registerUser(vivek);
      var kothari = new User("2", "Kothari", "kothari@foo.co");
      system.registerUser(kothari);

      var listing1 =
          new AuctionListing(
              "1", new Product("1", "Fan", "Celing fan"), 10, Duration.ofSeconds(10), vivek);
      system.createAuctionListing(listing1);
      var listing2 =
          new AuctionListing("2", new Product("2", "AC", "AC"), 100, Duration.ofSeconds(10), vivek);
      system.createAuctionListing(listing2);

      // Search auction listings
      List<Listing> searchResults = system.searchAuctionListings("fan");
      System.out.println("Search Results:");
      for (var listing : searchResults) {
        System.out.println(listing.getProduct());
      }

      // Place bids
      Bid bid1 = new Bid("1", vivek, 150);
      Bid bid2 = new Bid("2", kothari, 200);
      system.placeBid(listing1.getId(), bid1);
      system.placeBid(listing1.getId(), bid2);
    }
  }
}
