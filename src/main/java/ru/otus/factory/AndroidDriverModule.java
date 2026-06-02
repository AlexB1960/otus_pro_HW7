package ru.otus.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import java.nio.file.Paths;

public class AndroidDriverModule extends AbstractModule {
  //через GUICE и фабрику создаем драйвер для каждого теста
  @Provides
  private WebDriver webDriver(AndroidDriverFactory factory) {
    return factory.create();
  }

  //создаем один общий capabilities для всех тестов
  @Provides
  @Singleton
  private Capabilities capabilities() {
    return new UiAutomator2Options()
        //здесь уже автоматически прописаны Платформа и Имя драйвера
        //.setDeviceName("emulator-5554")
        //.setApp("http://wiremock:8080/wishlist.apk") //вместо сервера, файл приложения скачивается с заглушки
        .setApp(Paths.get("wiremock/__files/wishlist.apk").toAbsolutePath().toString())
        .fullReset() //обеспечивает полную изоляцию тестов между собой
        .clearDeviceLogsOnStart(); //когда начинается новый тест, логи очищаются и каждый лог-файл будет иметь тесты только одного файла
  }
}
