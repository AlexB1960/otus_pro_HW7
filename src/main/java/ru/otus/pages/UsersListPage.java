package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import ru.otus.components.TopFilterComponent;
import ru.otus.components.UserContent;
import ru.otus.components.UserItem;

@Singleton
public class UsersListPage extends AbsBasePage {
  private final SelenideElement topFilterMenu =
      $(id("ru.otus.wishlist:id/top_app_bar"));
  private final TopFilterComponent topFilterComponent =
      new TopFilterComponent(topFilterMenu);
  private final UserContent userContent =
      new UserContent($(id("ru.otus.wishlist:id/users_content")));

  public UsersListPage assertNumberOfUsers(int value) {
    userContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  private UserItem getUserItem(int index) {
    return userContent.get(index).shouldBe(visible);
  }

  public void tapUserItem(int index) {
    getUserItem(index).tapUser();
  }

  public UsersListPage assertFilterUser(int index, String login) {
    getUserItem(index).assertUserNameEqualsTo(login);
    return this;
  }

  public void tapFilterButton() {
    topFilterComponent.clickFilterButton();
  }
}
