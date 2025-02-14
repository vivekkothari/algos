package org.example.lld.vendingmachine;

public interface Currency {

  int denomination();

  public enum Coin implements Currency {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private final int denomination;

    Coin(int denomination) {
      this.denomination = denomination;
    }

    @Override
    public int denomination() {
      return denomination;
    }
  }

  public enum Note implements Currency {
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500);

    private final int denomination;

    Note(int denomination) {
      this.denomination = denomination;
    }

    @Override
    public int denomination() {
      return denomination;
    }
  }
}
