package org.example.lld.deploy_notif;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record Deployment(int id, Collection<Commit> commits, Instant createdAt) {

  List<Commit> commitsToNotify() {
    var revertHashes =
        commits.stream()
            .map(Commit::revertHash)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    return commits.stream().filter(c -> !revertHashes.contains(c.hash())).toList();
  }
}
