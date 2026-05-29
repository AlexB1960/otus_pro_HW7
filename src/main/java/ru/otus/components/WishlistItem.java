package ru.otus.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class WishlistItem extends AbsComponent<WishlistItem> {
  private final SelenideElement title =
      root.$(id("ru.otus.wishlist:id/title"));
  private final SelenideElement subtitle =
      root.$(id("ru.otus.wishlist:id/subtitle"));
  private final SelenideElement editButton =
      root.$(id("ru.otus.wishlist:id/edit_button"));

  public WishlistItem(SelenideElement root) {
    super(root);
  }

  public void assertTitleEqualsTo(String value) {
    title.shouldHave(text(value).because("Заголовок элемента списка желаний"));
  }

  public void assertSubtitleEqualsTo(String value) {
    subtitle.shouldHave(text(value).because("Подзаголовок элемента списка желаний"));
  }

  public void tapEdit() {
    editButton.shouldBe(visible).as("Кнопка редактирования не видна").click();
  }

  public void tapTitle() {
    title.shouldBe(visible).as("Заголовок элемента списка желаний не виден").click();
  }
}
