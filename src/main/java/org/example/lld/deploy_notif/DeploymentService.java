package org.example.lld.deploy_notif;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeploymentService {

  private final Map<Integer, Deployment> deployments = new ConcurrentHashMap<>();
  private final Map<Integer, DeploymentEvent> running = new ConcurrentHashMap<>();
  private final Map<Integer, DeploymentEvent> completed = new ConcurrentHashMap<>();
  private final UserService userService;

  public DeploymentService(UserService userService) {
    this.userService = userService;
  }

  public void deploymentStarted(Deployment deployment) {
    deployments.putIfAbsent(deployment.id(), deployment);
    if (!completed.containsKey(deployment.id())) {
      running.put(
          deployment.id(),
          new DeploymentEvent(deployment.id(), DeploymentEvent.Status.STARTED, Instant.now()));
    }
  }

  public void deploymentFinished(Deployment deployment, boolean isSuccess) {
    deployments.putIfAbsent(deployment.id(), deployment);
    running.remove(deployment.id());
    DeploymentEvent event =
        new DeploymentEvent(
            deployment.id(),
            isSuccess ? DeploymentEvent.Status.SUCCESS : DeploymentEvent.Status.FAILED,
            Instant.now());
    completed.put(deployment.id(), event);
    deployment.commitsToNotify().stream()
        .filter(commit -> userService.getById(commit.authorId()).isPresent())
        .forEach(
            commit ->
                System.out.printf(
                    "Hash %s, author, %s, status: %s",
                    commit.hash(),
                    userService
                        .getById(commit.authorId())
                        .map(UserService.User::name)
                        .orElseThrow(),
                    event.status()));
  }
}
