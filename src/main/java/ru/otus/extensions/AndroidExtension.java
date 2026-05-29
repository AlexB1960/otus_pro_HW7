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

@NullMarked
public class AndroidExtension implements TestInstancePostProcessor,
    BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {
  private static final Injector INJECTOR = Guice.createInjector(new AndroidDriverModule());

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    WebDriver driver = WebDriverRunner.getWebDriver();
    INJECTOR.getInstance(AndroidDriverFactory.class).quit(driver);
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    WebDriver driver = INJECTOR.getInstance(WebDriver.class);
    WebDriverRunner.setWebDriver(driver);
    Selenide.open();
  }

  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
    INJECTOR.injectMembers(testInstance);
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    WebDriver driver = WebDriverRunner.getWebDriver();
    String filename =
        "%s.%s".formatted(
            context.getRequiredTestClass().getSimpleName(),
            context.getRequiredTestMethod().getName()
        );
    //INJECTOR.getInstance(LogcatManager.class).saveLogs(driver, filename);
  }
}
