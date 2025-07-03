package org.example.lld.customer_satisfaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class AgentService {
  record Agent(int id, String name) {}

  private final Map<Integer, Agent> agentStore = new HashMap<>();

  Optional<Agent> getById(int id) {
    return Optional.ofNullable(agentStore.get(id));
  }

  Agent addAgent(String name) {
    int id = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
    Agent agent = new Agent(id, name);
    agentStore.put(id, agent);
    return agent;
  }

  List<Agent> listAgents() {
    return List.copyOf(agentStore.values());
  }
}
