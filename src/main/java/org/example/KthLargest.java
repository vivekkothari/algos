package org.example;

import java.util.PriorityQueue;

public class KthLargest {

  private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  private final int k;

  public KthLargest(int k, int[] nums) {
    this.k = k;
    for (int num : nums) {
      minHeap.add(num);
    }
  }

  public int add(int val) {
    // Add to our minHeap if we haven't processed k elements yet
    // or if val is greater than the top element (the k-th largest)
    if (minHeap.size() < k || minHeap.peek() < val) {
      minHeap.add(val);
      if (minHeap.size() > k) {
        minHeap.remove();
      }
    }
    return minHeap.peek();
  }

  public static void main(String[] args) {
    KthLargest kthLargest = new KthLargest(3, new int[] {4, 5, 8, 2});
    System.out.println(kthLargest.add(3));
    System.out.println(kthLargest.add(5));
    System.out.println(kthLargest.add(10));
    System.out.println(kthLargest.add(9));
    System.out.println(kthLargest.add(4));
  }
}
