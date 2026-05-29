package ru.otus.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

@Singleton
public class FilterUsersPage extends AbsBasePage {
  private final SelenideElement title =
      $(id("ru.otus.wishlist:id/filter_title"));
  private final SelenideElement userName =
      $(id("ru.otus.wishlist:id/username_input"));
  private final SelenideElement submitButton =
      $(id("ru.otus.wishlist:id/apply_button"));

  public FilterUsersPage assertFilterTitle() {
    title.shouldBe(visible).shouldHave(text("Фильтр"));
    return this;
  }

  public void setFilterUserName(String filterUserName) {
    userName.shouldBe(visible).clear();
    userName.sendKeys(filterUserName);
    submitButton.shouldBe(visible).click();
  }
}
