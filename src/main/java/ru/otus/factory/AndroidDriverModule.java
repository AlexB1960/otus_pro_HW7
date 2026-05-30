package ru.otus.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import java.nio.file.Paths;

public class AndroidDriverModule extends AbstractModule {
  @Provides
  private WebDriver webDriver(AndroidDriverFactory factory) {
    return factory.create();
  }

  @Provides
  @Singleton
  private Capabilities capabilities() {
    return new UiAutomator2Options()
        .setApp("http://wiremock:8080/wishlist.apk")
        //.setApp(Paths.get("wiremock/__files/wishlist.apk").toAbsolutePath().toString())
        .fullReset()
        .clearDeviceLogsOnStart();
  }
}
