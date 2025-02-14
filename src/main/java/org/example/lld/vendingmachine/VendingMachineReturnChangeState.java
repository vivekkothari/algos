package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

class VendingMachineReturnChangeState implements VendingMachineState {

  private final VendingMachine vendingMachine;

  VendingMachineReturnChangeState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    System.out.println("Please collect the change first.");
  }

  @Override
  public void insertCoin(Coin coin) {
    System.out.println("Please collect the change first.");
  }

  @Override
  public void insertNote(Note note) {
    System.out.println("Please collect the change first.");
  }

  @Override
  public void dispenseProduct() {
    System.out.println("Product already dispensed. Please collect the change.");
  }

  @Override
  public void returnChange() {
    double change = vendingMachine.getTotalPayment() - vendingMachine.getSelectedProduct().price();
    if (change > 0) {
      System.out.println("Change returned: INR" + change);
      vendingMachine.resetPayment();
    } else {
      System.out.println("No change to return.");
    }
    vendingMachine.resetSelectedProduct();
    vendingMachine.setCurrentState(vendingMachine.getIdleState());
  }
}
