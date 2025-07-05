package org.example.lld.deploy_notif;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeploymentNotificationServiceTest {

  @Mock private DeploymentService deploymentService;
  @Captor private ArgumentCaptor<Deployment> deploymentCaptor;

  @Test
  public void testCommitMerged_triggersDeployment_whenThresholdReached()
      throws InterruptedException {
    // Given
    var userService = new UserService();
    var deploymentNotificationService = new DeploymentNotificationService(deploymentService, 3);
    var vivek = userService.register("vivek");
    var kothari = userService.register("kothari");

    // When
    deploymentNotificationService.mergeCommit(new Commit("hash1", vivek.id()));
    deploymentNotificationService.mergeCommit(new Commit("hash2", vivek.id()));
    // Then
    verify(deploymentService, never()).deploymentStarted(any());

    // When
    deploymentNotificationService.mergeCommit(new Commit("hash3", kothari.id()));

    // Then
    verify(deploymentService, times(1)).deploymentStarted(deploymentCaptor.capture());
    Deployment capturedDeployment = deploymentCaptor.getValue();
    assertEquals(3, capturedDeployment.commits().size());
    List<String> commitHashes = capturedDeployment.commits().stream().map(Commit::hash).toList();
    assertTrue(commitHashes.containsAll(List.of("hash1", "hash2", "hash3")));

    deploymentNotificationService.close();
  }
}
