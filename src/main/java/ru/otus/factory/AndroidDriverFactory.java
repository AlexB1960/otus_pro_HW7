package ru.otus.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.otus.emulator.Emulator;
import ru.otus.emulator.EmulatorProvider;
import io.appium.java_client.android.AndroidDriver;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

//фабрика создания эмуляторов через GUICE / Inject
@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class AndroidDriverFactory {
  //эти переменные передаются через аргументы конструктора !
  private EmulatorProvider emulatorProvider;
  private Capabilities capabilities;

  //забираем из очереди свободный эмулятор и на его базе создаем AndroidDriver driver
  @SneakyThrows
  public WebDriver create() {
    Emulator emulator = emulatorProvider.takeAndGet();
    AndroidDriver driver =
        new AndroidDriver(
            new URI("http://127.0.0.1:%d/wd/hub".formatted(emulator.getPort())).toURL(), capabilities
        //new URL("http://127.0.0.1:%d/".formatted(emulator.getPort())), capabilities
        );
    //настраиваем неявное ожидание
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    return driver;
  }

  //возвращаем эмулятор обратно в очередь и закрываем драйвер
  public void quit(WebDriver driver) {
    emulatorProvider.putBack();
    driver.quit();
  }
}
