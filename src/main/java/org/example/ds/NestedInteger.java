package org.example.ds;


import java.util.ArrayList;
import java.util.List;

public interface NestedInteger {
  boolean isInteger();

  boolean isList();

  Integer getInteger();

  List<NestedInteger> getList();

  NestedInteger setInteger(int value);

  NestedInteger add(NestedInteger nestedInteger);

  static NestedInteger of(int value) {
    return new NestedIntegerImpl(value);
  }

  static NestedInteger of() {
    return new NestedIntegerImpl();
  }

  class NestedIntegerImpl implements NestedInteger {
    @Override
    public String toString() {
      return "{" + "value=" + value + ", list=" + list + '}';
    }

    private Integer value;
    private List<NestedInteger> list;

    // Constructor initializes an empty nested list.
    NestedIntegerImpl() {
      this.list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    NestedIntegerImpl(int value) {
      this.value = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
      return value != null;
    }

    @Override
    public boolean isList() {
      return list != null;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
      return value;
    }

    // Set this NestedInteger to hold a single integer.
    public NestedInteger setInteger(int value) {
      this.value = value;
      this.list = null; // Ensure it's no longer a nested list
      return this;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    @Override
    public NestedInteger add(NestedInteger ni) {
      if (this.list == null) {
        this.list = new ArrayList<>();
        this.value = null; // Ensure it's no longer a single integer
      }
      this.list.add(ni);
      return this;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
      return list != null ? list : new ArrayList<>();
    }
  }
}
