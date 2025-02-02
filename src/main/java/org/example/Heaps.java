package org.example;

import java.util.PriorityQueue;

class Heaps {

  public static void main(String[] args) {
    var queue = new PriorityQueue<Integer>(10);
    queue.add(10);
    System.out.println(queue);
    queue.add(15);
    System.out.println(queue);
    queue.add(5);
    System.out.println(queue);
  }
}
