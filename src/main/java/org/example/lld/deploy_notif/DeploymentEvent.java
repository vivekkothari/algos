package org.example.lld.deploy_notif;

import java.time.Instant;

public record DeploymentEvent(int deploymentId, Status status, Instant createdAt) {

  enum Status {
    STARTED,
    SUCCESS,
    FAILED
  }
}
