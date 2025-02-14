package org.example.lld.vendingmachine;

import org.example.lld.vendingmachine.Currency.Coin;
import org.example.lld.vendingmachine.Currency.Note;

class VendingMachineDemo {

  public static void main(String[] args) {
    VendingMachine vendingMachine = new VendingMachine();

    // Add products to the inventory
    Product coke = new Product("Coke", 30);
    Product pepsi = new Product("Pepsi", 25);
    Product water = new Product("Water", 15);

    vendingMachine.inventory.addProduct(coke, 5);
    vendingMachine.inventory.addProduct(pepsi, 3);
    vendingMachine.inventory.addProduct(water, 2);

    // Select a product
    vendingMachine.selectProduct(coke);

    // Insert coins
    vendingMachine.insertCoin(Coin.FIVE);
    vendingMachine.insertCoin(Coin.TEN);

    // Insert a note
    vendingMachine.insertNote(Note.FIFTY);

    // Dispense the product
    vendingMachine.dispenseProduct();

    // Return change
    vendingMachine.returnChange();

    // Select another product
    vendingMachine.selectProduct(pepsi);

    // Insert insufficient payment
    vendingMachine.insertCoin(Coin.ONE);

    // Try to dispense the product
    vendingMachine.dispenseProduct();

    // Insert more coins
    vendingMachine.insertCoin(Coin.TWO);

    // Dispense the product
    vendingMachine.dispenseProduct();

    // Return change
    vendingMachine.returnChange();

    vendingMachine.insertCoin(Coin.TWO);
    vendingMachine.insertNote(Note.TWENTY);

    // Dispense the product
    vendingMachine.dispenseProduct();

    // Return change
    vendingMachine.returnChange();
  }
}
