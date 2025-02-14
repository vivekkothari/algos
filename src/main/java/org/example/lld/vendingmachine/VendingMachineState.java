package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

interface VendingMachineState {
  void selectProduct(Product product);

  void insertCoin(Coin coin);

  void insertNote(Note note);

  void dispenseProduct();

  void returnChange();
}
