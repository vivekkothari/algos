package org.example.lld.aution;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class ObservableListing implements Observable<Listing>, Listing {

  private final Map<String, Observer<Listing>> observers;
  private final Listing listing;
  private Collection<Object> itemName;

  ObservableListing(Listing listing) {
    this.listing = listing;
    observers = new HashMap<>();
  }

  @Override
  public void addObserver(Observer<Listing> observer) {
    observers.put(observer.id(), observer);
  }

  @Override
  public void removeObserver(Observer<Listing> observer) {
    observers.remove(observer.id());
  }

  @Override
  public void notifyObserver() {
    observers.values().forEach(observer -> observer.update(listing));
  }

  @Override
  public String getId() {
    return listing.getId();
  }

  @Override
  public Duration getDuration() {
    return listing.getDuration();
  }

  @Override
  public Product getProduct() {
    return listing.getProduct();
  }

  @Override
  public void closeAuction() {
    listing.closeAuction();
    notifyObserver();
  }

  @Override
  public void placeBid(Bid bid) {
    listing.placeBid(bid);
    addObserver(bid.bidder());
    notifyObserver();
  }
}
