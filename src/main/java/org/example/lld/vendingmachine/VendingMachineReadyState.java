package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

class VendingMachineReadyState implements VendingMachineState {
  private final VendingMachine vendingMachine;

  public VendingMachineReadyState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    System.out.println("Product already selected. Please make payment.");
  }

  @Override
  public void insertCoin(Coin coin) {
    vendingMachine.addCoin(coin);
    System.out.println("Coin inserted: " + coin);
    checkPaymentStatus();
  }

  @Override
  public void insertNote(Note note) {
    vendingMachine.addNote(note);
    System.out.println("Note inserted: " + note);
    checkPaymentStatus();
  }

  @Override
  public void dispenseProduct() {
    var shortfall = vendingMachine.getSelectedProduct().price() - vendingMachine.getTotalPayment();
    System.out.println("Please make payment first. You are short by INR" + shortfall);
  }

  @Override
  public void returnChange() {
    dispenseProduct();
  }

  private void checkPaymentStatus() {
    if (vendingMachine.getTotalPayment() >= vendingMachine.getSelectedProduct().price()) {
      vendingMachine.setCurrentState(vendingMachine.getDispenseState());
    }
  }
}
