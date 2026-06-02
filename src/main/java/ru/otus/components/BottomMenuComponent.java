package ru.otus.components;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class BottomMenuComponent extends AbsComponent<BottomMenuComponent> {
  private final SelenideElement myButton =
      root.$(id("ru.otus.wishlist:id/mine_menu"));
  private final SelenideElement userButton =
      root.$(id("ru.otus.wishlist:id/users_menu"));
  private final SelenideElement profileButton =
      root.$(id("ru.otus.wishlist:id/profile_menu"));

  public BottomMenuComponent(SelenideElement root) {
    super(root);
  }

  public void clickUserMenu() {
    userButton.shouldBe(visible.because("Элемент меню Пользователь не виден")).click();
  }
}
