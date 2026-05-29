package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import ru.otus.pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {//нужен дженерик <T> ???
  private final SelenideElement userButton =
      $(id("ru.otus.wishlist:id/users_menu"));

  public void tapUserMenu() {
    userButton.shouldBe(visible).as("Элемент меню Пользователь не виден").click();
  }
}
