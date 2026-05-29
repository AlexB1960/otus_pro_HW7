package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import ru.otus.components.UserContent;

@Singleton
public class UsersListPage extends AbsBasePage {
  private final SelenideElement filterButton =
      $(id("ru.otus.wishlist:id/filter"));

  private final UserContent userContent =
      new UserContent($(id("ru.otus.wishlist:id/users_content")));

  public UsersListPage assertNumberOfUsers(int value) {
    userContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  public void tapUserItem(int index) {
    userContent.get(index).shouldBe(visible).tapUser();
  }

  public UsersListPage assertFilterUser(int index, String login) {
    userContent.get(index).shouldBe(visible).assertUserNameEqualsTo(login);
    return this;
  }

  public void tapFilterButton() {
    filterButton.shouldBe(visible).as("Кнопка фильтра пользователей не видна").click();
  }
}
