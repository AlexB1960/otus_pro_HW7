package ru.otus.components;

import static com.codeborne.selenide.Condition.*;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class GiftListItem extends AbsComponent<GiftListItem> {
  private final SelenideElement title =
      root.$(id("ru.otus.wishlist:id/title")).as("Заголовок элемента списка подарков");
  private final SelenideElement subtitle =
      root.$(id("ru.otus.wishlist:id/subtitle")).as("Подзаголовок элемента списка подарков");
  private final SelenideElement editButton =
      root.$(id("ru.otus.wishlist:id/edit_button")).as("Кнопка редактирования подарка");
  private final SelenideElement statusSwitch =
      root.$(id("ru.otus.wishlist:id/reserved")).as("Селектор резервирования подарка");

  public GiftListItem(SelenideElement root) {
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

  public void tapStatusSwitch() {
    statusSwitch.shouldBe(visible.because("Селектор резервирования подарка не виден")).click();
  }

  public void assertSwitchStatus(boolean statusValue) {
    statusSwitch.shouldHave(attribute("checked", String.valueOf(statusValue)));
  }
}