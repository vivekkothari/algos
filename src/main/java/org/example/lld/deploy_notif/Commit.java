package org.example.lld.deploy_notif;

import java.time.Instant;
import org.jetbrains.annotations.Nullable;

public record Commit(String hash, int authorId, Instant createdAt, @Nullable String revertHash) {

  Commit(String hash, int authorId) {
    this(hash, authorId, Instant.now(), null);
  }
}
