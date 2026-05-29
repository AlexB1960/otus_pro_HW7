package ru.otus.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class UserItem extends AbsComponent<UserItem> {
  private final SelenideElement userItem =
      root.$(id("ru.otus.wishlist:id/user_item"));
  private final SelenideElement userName =
      root.$(id("ru.otus.wishlist:id/username"));

  public UserItem(SelenideElement root) {
    super(root);
  }

  public void assertUserNameEqualsTo(String value) {
    userName.shouldHave(text(value).because("Имя пользователя"));
  }

  public void tapUser() {
    userItem.shouldBe(visible).as("Элемент пользователя в списке не виден").click();
  }
}
