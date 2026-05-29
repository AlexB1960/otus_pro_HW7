package ru.otus.extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.*;
import ru.otus.factory.AndroidDriverFactory;
import ru.otus.factory.AndroidDriverModule;
import org.jspecify.annotations.NullMarked;
import org.openqa.selenium.WebDriver;
import ru.otus.utils.LogcatManager;

@NullMarked //эта аннотация указывает, что все касаемо context - не NULL
public class AndroidExtension implements TestInstancePostProcessor,
    BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {
  //Нужен только один инжектор
  private static final Injector INJECTOR = Guice.createInjector(new AndroidDriverModule());

  //после каждого теста возвращаем драйвер назад ???
  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    //просим Selenide отдать запомненный им драйвер
    WebDriver driver = WebDriverRunner.getWebDriver();
    //через фабрику эмуляторов закрываем через quit() соответствующий драйвер
    INJECTOR.getInstance(AndroidDriverFactory.class).quit(driver);
  }

  //перед каждым тестом создает драйвер и передает его в Selenide(который сам умеет хранить драйвера)
  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    //просим инжектор создать нам драйвер
    WebDriver driver = INJECTOR.getInstance(WebDriver.class);
    //передаем созданный драйвер в Selenide, который сам знает в каком потоке его хранить
    WebDriverRunner.setWebDriver(driver);
    //после этой команды тест начнет выполняться
    Selenide.open();
  }

  //Нужен, чтобы заполнить поля с аннотацией @Inject в тестах. Вызывается после создания объекта тестового класса для создания тестовых методов этого объекта
  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
    INJECTOR.injectMembers(testInstance);
  }

  //Запись логов методом из LogcatManager возможен только здесь из-за совместимости по времени с Аллюром
  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    //берем текущий драйвер, из которого будем записывать логи
    WebDriver driver = WebDriverRunner.getWebDriver();
    String filename =
        "%s.%s".formatted(
            context.getRequiredTestClass().getSimpleName(),
            context.getRequiredTestMethod().getName()
        );
    //записываем логи методом из LogcatManager
    //INJECTOR.getInstance(LogcatManager.class).saveLogs(driver, filename);
  }

}
