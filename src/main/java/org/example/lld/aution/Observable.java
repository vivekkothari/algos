package org.example.lld.aution;

interface Observable<T> {

  void addObserver(Observer<T> observer);

  void removeObserver(Observer<T> observer);

  void notifyObserver();
}
