package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

class VendingMachine {

  private final VendingMachineState idleState;
  private final VendingMachineState readyState;
  private final VendingMachineState dispenseState;
  private final VendingMachineState returnChangeState;
  Inventory inventory;
  private Product selectedProduct;

  private VendingMachineState currentState;
  private int totalPayment;

  VendingMachine() {
    this.inventory = new Inventory();
    this.idleState = new VendingMachineIdleState(this);
    this.readyState = new VendingMachineReadyState(this);
    this.dispenseState = new VendingMachineDispenseState(this);
    this.returnChangeState = new VendingMachineReturnChangeState(this);
    currentState = idleState;
  }

  public void setSelectedProduct(Product selectedProduct) {
    this.selectedProduct = selectedProduct;
  }

  public int getTotalPayment() {
    return totalPayment;
  }

  public Product getSelectedProduct() {
    return selectedProduct;
  }

  public void setCurrentState(VendingMachineState currentState) {
    this.currentState = currentState;
  }

  public void addNote(Note note) {
    totalPayment += note.denomination();
  }

  public void addCoin(Coin coin) {
    totalPayment += coin.denomination();
  }

  public void insertCoin(Coin coin) {
    currentState.insertCoin(coin);
  }

  public void insertNote(Note note) {
    currentState.insertNote(note);
  }

  public VendingMachineState getReadyState() {
    return readyState;
  }

  public void dispenseProduct() {
    currentState.dispenseProduct();
  }

  public VendingMachineState getDispenseState() {
    return dispenseState;
  }

  public void resetPayment() {
    totalPayment = 0;
  }

  public void resetSelectedProduct() {
    selectedProduct = null;
  }

  public VendingMachineState getIdleState() {
    return idleState;
  }

  public void selectProduct(Product product) {
    currentState.selectProduct(product);
  }

  public void returnChange() {
    currentState.returnChange();
  }

  public VendingMachineState getReturnChangeState() {
    return returnChangeState;
  }
}
