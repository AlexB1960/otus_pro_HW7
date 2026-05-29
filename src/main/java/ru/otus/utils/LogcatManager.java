package ru.otus.utils;

import com.google.inject.Singleton;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Singleton
public class LogcatManager {
  @SneakyThrows
  public void saveLogs(WebDriver driver, String filename) {
    List<String> logs = driver.manage()
        .logs()
        .get("logcat")
        .getAll()
        .stream()
        .map(Objects::toString)
        .toList();
    Files.write(Paths.get(filename + ".log"), logs);
  }
}
