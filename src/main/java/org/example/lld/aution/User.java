package org.example.lld.aution;

record User(String id, String userName, String email) implements Observer<Listing> {

  @Override
  public void update(Listing data) {
    System.out.printf("%s Got update %s%n", this, data.getProduct());
  }
}
