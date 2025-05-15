package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  private static String toString(ListNode head) {
    var sb = new StringBuilder();
    var current = head;
    while (current != null) {
      sb.append(current.val).append(" -> ");
      current = current.next;
    }
    sb.append("null");
    return sb.toString();
  }

  public static void main(String[] args) {
    var head = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1)))));
    print(deleteDuplicates(head));
    //    print(head);
    //    var head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new
    // ListNode(5)))));
    //    print(merge2Lists(head, head2));
    //    print(reverseList(head));
    //    System.out.println(hasCycle(head));
    // 1,2,3,4,5
    // 5,1,2,3,4
    // 4,5,1,2,3
    // 3,4,5,1,2
    //    print(rotateKPlaces(head, 1));
    var intersect = new ListNode(8, new ListNode(4, new ListNode(5)));
    var headA = new ListNode(4, new ListNode(1, intersect));
    var headB = new ListNode(5, new ListNode(6, new ListNode(1, intersect)));
    System.out.println(getIntersectionNode(headA, headB));
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

  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    return divideAndConquer(lists, 0, lists.length - 1);
  }

  private static ListNode divideAndConquer(ListNode[] lists, int l, int r) {
    if (l == r) return lists[l];
    var mid = l + (r - l) / 2;
    var l1 = divideAndConquer(lists, l, mid);
    var l2 = divideAndConquer(lists, mid + 1, r);
    return merge2Lists(l1, l2);
  }

  private static ListNode merge2Lists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val < l2.val) {
      l1.next = merge2Lists(l1.next, l2);
      return l1;
    } else {
      l2.next = merge2Lists(l1, l2.next);
      return l2;
    }
  }

  /**
   * https://leetcode.com/problems/merge-two-sorted-lists/description/
   *
   * <pre>
   *   You are given the heads of two sorted linked lists list1 and list2.
   * Merge the two lists into one sorted list. The list should be made by splicing
   * together the nodes of the first two lists.
   *
   *  Return the head of the merged linked list.
   * Input: list1 = [1,2,4], list2 = [1,3,4]
   * Output: [1,1,2,3,4,4]
   * Example 2:
   *
   * Input: list1 = [], list2 = []
   * Output: []
   * Example 3:
   *
   * Input: list1 = [], list2 = [0]
   * Output: [0]
   * </pre>
   */
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

  /**
   *
   *
   * <pre>
   * 1 -> 2 -> 3 -> 4 -> 5
   * k = 2
   * 4 -> 5 -> 1 -> 2 -> 3
   * </pre>
   */
  private static ListNode rotateKPlaces(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }
    var length = 1;
    var oldTail = head;
    while (oldTail.next != null) {
      oldTail = oldTail.next;
      length++;
    }
    oldTail.next = head;
    var pivot = length - (k % length);
    var newTail = head;
    for (var i = 1; i < pivot; i++) {
      newTail = newTail.next;
    }
    var newHead = newTail.next;
    newTail.next = null;
    return newHead;
  }

  public static void reorderList(LinkedList.ListNode head) {
    List<ListNode> list = new ArrayList<>();
    var curr = head;
    while (curr != null) {
      list.add(curr);
      curr = curr.next;
    }
    int l = 0, r = list.size() - 1;
    while (l < r) {
      list.get(l).next = list.get(r);
      l++;
      if (l >= r) {
        break;
      }
      list.get(r).next = list.get(l);
      r--;
    }
    list.get(l).next = null;
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
   *
   * <pre>
   *   Given the head of a sorted linked list, delete all duplicates such that each element
   *   appears only once. Return the linked list sorted as well.
   * Input: head = [1,1,2]
   * Output: [1,2]
   *
   * Input: head = [1,1,2,3,3]
   * Output: [1,2,3]
   * </pre>
   */
  public static ListNode deleteDuplicates(ListNode head) {
    var res = head;

    while (head != null && head.next != null) {
      if (head.val == head.next.val) {
        head.next = head.next.next;
      } else {
        head = head.next;
      }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/intersection-of-two-linked-lists/
   *
   * <pre>
   *
   *   Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
   *   Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
   * Output: Intersected at '8'
   * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
   * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
   * - Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
   * </pre>
   */
  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    var lenA = 0;
    var lenB = 0;
    var currA = headA;
    while (currA.next != null) {
      currA = currA.next;
      lenA++;
    }
    var currB = headB;
    while (currB.next != null) {
      currB = currB.next;
      lenB++;
    }
    // Tail is different
    if (currA != currB) {
      return null;
    }
    currA = headA;
    currB = headB;
    if (lenA > lenB) {
      for (var i = 0; i < lenA - lenB; i++) {
        currA = currA.next;
      }
    } else {
      for (var i = 0; i < lenB - lenA; i++) {
        currB = currB.next;
      }
    }
    while (currA != currB) {
      currA = currA.next;
      currB = currB.next;
    }
    return currA;
  }

  public static ListNode getIntersectionNodeOptimised(ListNode headA, ListNode headB) {
    // boundary check
    if (headA == null || headB == null) return null;

    var a = headA;
    var b = headB;

    // if a & b have different len, then we will stop the loop after second iteration
    while (a != b) {
      // for the end of first iteration, we just reset the pointer to the head of another linkedlist
      a = a == null ? headB : a.next;
      b = b == null ? headA : b.next;
    }

    return a;
  }

  /**
   * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
   *
   * <pre>
   *   Given the head of a linked list, remove the nth node from the end of the list and return its head.
   *   Example 1:
   *   Input: head = [1,2,3,4,5], n = 2
   * Output: [1,2,3,5]
   * Example 2:
   *
   * Input: head = [1], n = 1
   * Output: []
   * Example 3:
   *
   * Input: head = [1,2], n = 1
   * Output: [1]
   * </pre>
   */
  public static ListNode removeNthFromEnd(ListNode head, int n) {
    var len = 0;
    var curr = head;
    while (curr != null) {
      len++;
      curr = curr.next;
    }
    var deleteAt = len - n;
    if (deleteAt == 0) {
      head = head == null ? null : head.next;
      return head;
    }
    curr = head;
    for (var i = 0; i < deleteAt - 1; i++) {
      curr = curr.next;
    }
    curr.next = curr.next == null ? null : curr.next.next;
    return head;
  }

  class RNode {
    int val;
    RNode next;
    RNode random;

    public RNode(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }

  public RNode copyRandomList(RNode head) {
    Map<RNode, RNode> oldToNew = new HashMap<>();
    var curr = head;
    while (curr != null) {
      var newNode = new RNode(curr.val);
      oldToNew.put(curr, newNode);
      curr = curr.next;
    }

    curr = head;
    while (curr != null) {
      var node = oldToNew.get(curr);
      node.next = oldToNew.get(curr.next);
      node.random = oldToNew.get(curr.random);
      curr = curr.next;
    }
    return oldToNew.get(head);
  }
}
