package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

class VendingMachineIdleState implements VendingMachineState {

  private final VendingMachine vendingMachine;

  public VendingMachineIdleState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    if (vendingMachine.inventory.isAvailable(product)) {
      vendingMachine.setSelectedProduct(product);
      vendingMachine.setCurrentState(vendingMachine.getReadyState());
      System.out.println("Product selected: " + product.name());
    } else {
      System.out.println("Product not available: " + product.name());
    }
  }

  @Override
  public void insertCoin(Coin coin) {
    System.out.println("Please select a product first.");
  }

  @Override
  public void insertNote(Note note) {
    System.out.println("Please select a product first.");
  }

  @Override
  public void dispenseProduct() {
    System.out.println("Please select a product and make payment.");
  }

  @Override
  public void returnChange() {
    System.out.println("No change to return.");
  }
}
