package org.example.ds;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

/** LinkedIn DSA round3 question. */
public interface MemberConnectionsDistance {
  int calculateDistance(Member member1, Member member2);

  interface Member {
    int memberId();

    String memberName();

    static Member of(int memberId, String memberName) {
      return new MemberImpl(memberId, memberName);
    }

    record MemberImpl(int memberId, String memberName) implements Member {}
  }

  interface MemberConnections {
    List<Member> getConnections(Member member);
  }

  class MemberConnectionsMap implements MemberConnections {

    private final Map<Member, List<Member>> memberListMap;

    public MemberConnectionsMap(Map<Member, List<Member>> memberListMap) {
      this.memberListMap = memberListMap;
    }

    @Override
    public List<Member> getConnections(Member member) {
      return memberListMap.getOrDefault(member, List.of());
    }
  }
}

class MemberConnectionsDistanceImpl implements MemberConnectionsDistance {

  private final MemberConnections memberConnections;

  MemberConnectionsDistanceImpl(MemberConnections memberConnections) {
    this.memberConnections = memberConnections;
  }

  @Override
  public int calculateDistance(Member member1, Member member2) {
    if (member1.memberId() == member2.memberId()) {
      return 0;
    }
    Queue<Member> queue1 = new LinkedList<>();
    Queue<Member> queue2 = new LinkedList<>();
    queue1.offer(member1);
    queue2.offer(member2);

    Map<Member, Integer> visited1 = new HashMap<>();
    Map<Member, Integer> visited2 = new HashMap<>();
    visited1.put(member1, 0);
    visited2.put(member2, 0);
    while (!queue1.isEmpty() && !queue2.isEmpty()) {
      int result = searchLevel(queue1, visited1, visited2);
      if (result != -1) {
        return result;
      }

      result = searchLevel(queue2, visited2, visited1);
      if (result != -1) {
        return result;
      }
    }
    while (!queue1.isEmpty()) {
      int result = searchLevel(queue1, visited1, visited2);
      if (result != -1) {
        return result;
      }
    }
    while (!queue2.isEmpty()) {
      int result = searchLevel(queue2, visited2, visited1);
      if (result != -1) {
        return result;
      }
    }
    return -1;
  }

  private int searchLevel(
      Queue<Member> queue, Map<Member, Integer> visited, Map<Member, Integer> otherVisited) {
    int size = queue.size();
    for (int i = 0; i < size; i++) {
      Member member = queue.poll();
      int distance = visited.get(member);

      for (Member connection : memberConnections.getConnections(member)) {
        if (otherVisited.containsKey(connection)) {
          return distance + 1 + otherVisited.get(connection);
        }
        if (!visited.containsKey(connection)) {
          visited.put(connection, distance + 1);
          queue.offer(connection);
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Member vivek = Member.of(1, "Vivek");
    Member kalpak = Member.of(2, "Kalpak");
    Member kunal = Member.of(3, "Kunal");
    Member kaushal = Member.of(4, "Kaushal");
    Member vishwesh = Member.of(5, "Vishwesh");
    Member ashutosh = Member.of(6, "Ashutosh");
    Member gaurav = Member.of(7, "Gaurav");
    Member naveen = Member.of(8, "Naveen");
    Member hemant = Member.of(9, "Hemant");
    MemberConnections memberConnections =
        new MemberConnectionsMap(
            Map.of(
                vishwesh, List.of(kalpak, hemant),
                vivek, List.of(vishwesh, ashutosh),
                kalpak, List.of(vishwesh),
                ashutosh, List.of(gaurav, naveen),
                naveen, List.of(kaushal),
                gaurav, List.of(kunal),
                kunal, List.of(kaushal)));

    MemberConnectionsDistance distance = new MemberConnectionsDistanceImpl(memberConnections);
    System.out.println(distance.calculateDistance(vivek, kunal));
  }
}
