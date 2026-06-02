package ru.otus.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class WishlistItem extends AbsComponent<WishlistItem> {
  private final SelenideElement title =
      root.$(id("ru.otus.wishlist:id/title")).as("Заголовок элемента списка желаний");
  private final SelenideElement subtitle =
      root.$(id("ru.otus.wishlist:id/subtitle")).as("Подзаголовок элемента списка желаний");
  private final SelenideElement editButton =
      root.$(id("ru.otus.wishlist:id/edit_button")).as("Кнопка редактирования элемента списка желаний");

  public WishlistItem(SelenideElement root) {
    super(root);
  }

  public void assertTitleEqualsTo(String value) {
    title.shouldHave(text(value));
  }

  public void assertSubtitleEqualsTo(String value) {
    subtitle.shouldHave(text(value));
  }

  public void tapEdit() {
    editButton.shouldBe(visible.because("Кнопка редактирования не видна")).click();
  }

  public void tapTitle() {
    title.shouldBe(visible.because("Заголовок элемента списка желаний не виден")).click();
  }
}
