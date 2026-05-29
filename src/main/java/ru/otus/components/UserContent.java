package ru.otus.components;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class UserContent extends AbsComponent<UserContent> {
  private final ElementsCollection users =
      root.$$(id("ru.otus.wishlist:id/user_item"))
          .as("Селектор пользователей");

  public UserContent(SelenideElement root) {
    super(root);
  }

  public UserItem get(int index) {
    return new UserItem(users.get(index));
  }

  public void assertSizeEqualTo(int expected) {
    users.shouldHave(size(expected));
  }
}
