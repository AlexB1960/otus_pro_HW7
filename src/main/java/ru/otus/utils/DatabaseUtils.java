package ru.otus.utils;

import com.google.inject.Singleton;
import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Singleton
public class DatabaseUtils {
  private final String url = System.getProperty("databaseUrl");
  private final String username = System.getProperty("databaseUsername");
  private final String password = System.getProperty("databasePassword");

  @SneakyThrows
  public void prepareWishlistDescription(String login, String description) {
    String sql = "UPDATE wishlists SET description = ? WHERE user_id IN (SELECT id FROM users WHERE username = ?)";
    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, description);
      ps.setString(2, login);
      ps.executeUpdate();
    }
  }

  @SneakyThrows
  public void prepareGiftListDescription(String login, String description) {
    String sql = "UPDATE gifts SET description = ? WHERE wish_id IN (SELECT id FROM wishlists WHERE user_id IN (SELECT id FROM users WHERE username = ?))";
    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, description);
      ps.setString(2, login);
      ps.executeUpdate();
    }
  }

  @SneakyThrows
  public void prepareGiftListStatus(String login) {
    String sql = "UPDATE gifts SET is_reserved = false WHERE wish_id IN (SELECT id FROM wishlists WHERE user_id IN (SELECT id FROM users WHERE username = ?))";
    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setString(1, login);
      ps.executeUpdate();
    }
  }
}
