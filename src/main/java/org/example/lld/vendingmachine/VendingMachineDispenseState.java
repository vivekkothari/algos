package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

class VendingMachineDispenseState implements VendingMachineState {

  private final VendingMachine vendingMachine;

  VendingMachineDispenseState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    System.out.println("Product already selected. Please collect the dispensed product.");
  }

  @Override
  public void insertCoin(Coin coin) {
    System.out.println("Payment already made. Please collect the dispensed product.");
  }

  @Override
  public void insertNote(Note note) {
    System.out.println("Payment already made. Please collect the dispensed product.");
  }

  @Override
  public void dispenseProduct() {
    vendingMachine
        .inventory
        .dispenseProduct(vendingMachine.getSelectedProduct())
        .ifPresentOrElse(
            product -> {
              System.out.printf("Product %s dispensed%n", product.name());
              vendingMachine.setCurrentState(vendingMachine.getReturnChangeState());
            },
            () -> System.out.println("Selected product not available anymore"));
  }

  @Override
  public void returnChange() {
    System.out.println("Please collect the dispensed product first.");
  }
}
