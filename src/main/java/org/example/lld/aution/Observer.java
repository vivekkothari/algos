package org.example.lld.aution;

interface Observer<T> {

  /** Unique ID of an observer. */
  String id();

  /** Notifies the observer with the data. */
  void update(T data);
}
