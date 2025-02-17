package org.example.lld.vendingmachine;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

class Inventory {

  private final ConcurrentMap<Product, AtomicInteger> inventory = new ConcurrentHashMap<>();

  public void addProduct(Product product, int quantity) {
    inventory.computeIfAbsent(product, p -> new AtomicInteger()).addAndGet(quantity);
  }

  public Optional<Product> dispenseProduct(Product product) {
    var currentQuantity =
        inventory.computeIfPresent(
            product,
            (p, q) -> {
              q.getAndUpdate(i -> i > 0 ? i - 1 : 0);
              return q;
            });
    return currentQuantity == null || currentQuantity.get() == 0
        ? Optional.empty()
        : Optional.of(product);
  }

  public boolean isAvailable(Product product) {
    return inventory.containsKey(product) && inventory.get(product).get() > 0;
  }
}
