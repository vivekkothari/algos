package org.example.lld.deploy_notif;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class UserService {

  public record User(int id, String name) {}

  private final Map<Integer, User> store = new HashMap<>();

  public Optional<User> getById(int userId) {
    return Optional.ofNullable(store.get(userId));
  }

  public User register(String name) {
    User user = new User(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), name);
    store.put(user.id, user);
    return user;
  }
}
