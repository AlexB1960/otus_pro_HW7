package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import ru.otus.components.TopFilterComponent;
import ru.otus.components.UserContent;
import ru.otus.components.UserItem;

@Singleton  //каждая страница создается только одна!
public class UsersListPage extends AbsBasePage {
  private final SelenideElement topFilterMenu =
      $(id("ru.otus.wishlist:id/top_app_bar"));
  private final TopFilterComponent topFilterComponent =
      new TopFilterComponent(topFilterMenu);
  //создаем контейнер всех пользователей
  private final UserContent userContent =
      new UserContent($(id("ru.otus.wishlist:id/users_content")));

  //проверка видимости и размера контейнера с пользователями
  public UsersListPage assertNumberOfUsers(int value) {
    userContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  //общий метод получения пользователя из контейнера по индексу
  private UserItem getUserItem(int index) {
    return userContent.get(index).shouldBe(visible);
  }

  //нажатие на пользователя из контейнера по индексу
  public void tapUserItem(int index) {
    getUserItem(index).tapUser();
  }

  //проверка имени отфильтрованного пользователя
  public UsersListPage assertFilterUser(int index, String login) {
    getUserItem(index).assertUserNameEqualsTo(login);
    return this;
  }

  //нажатие кнопки фильтра пользователей
  public void tapFilterButton() {
    topFilterComponent.clickFilterButton();
  }
}
