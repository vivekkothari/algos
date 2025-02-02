package org.example;

class LinkedList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    var curr = head;
    while (curr != null) {
      var next = curr.next; // Save the next node
      curr.next = prev; // Reverse the link
      prev = curr; // Move prev forward
      curr = next; // Move curr forward
    }
    return prev;
  }

  private static void print(ListNode head) {
    var current = head;
    while (current != null) {
      System.out.print(current.val + " -> ");
      current = current.next;
    }
    System.out.print("null");
    System.out.println();
  }

  public static void main(String[] args) {
    var head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    print(head);
    print(reverseList(head));
    System.out.println(hasCycle(head));
  }

  public static boolean hasCycle(ListNode head) {
    var fast = head;
    var slow = head;
    while (fast != null && fast.next != null) {
      slow = slow.next; // Move slow by 1 step
      fast = fast.next.next; // Move fast by 2 steps

      if (slow == fast) { // If slow and fast meet, there is a cycle
        return true;
      }
    }
    return false;
  }
}
