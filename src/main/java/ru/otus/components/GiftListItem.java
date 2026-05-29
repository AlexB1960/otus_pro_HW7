package ru.otus.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class GiftListItem extends AbsComponent<GiftListItem> {
  private final SelenideElement title =
      root.$(id("ru.otus.wishlist:id/title"));
  private final SelenideElement subtitle =
      root.$(id("ru.otus.wishlist:id/subtitle"));
  private final SelenideElement editButton =
      root.$(id("ru.otus.wishlist:id/edit_button"));
  private final SelenideElement statusSwitch =
      root.$(id("ru.otus.wishlist:id/reserved"));

  public GiftListItem(SelenideElement root) {
    super(root);
  }

  public void assertTitleEqualsTo(String value) {
    title.shouldHave(text(value).because("Заголовок элемента списка подарков"));
  }

  public void assertSubtitleEqualsTo(String value) {
    subtitle.shouldHave(text(value).because("Подзаголовок элемента списка подарков"));
  }

  public void tapEdit() {
    editButton.shouldBe(visible).as("Кнопка редактирования не видна").click();
  }

  public void tapStatusSwitch() {
    statusSwitch.shouldBe(visible).click();
  }

  public void assertSwitchStatus(boolean statusValue) {
    assert Boolean.parseBoolean(statusSwitch.getAttribute("checked"))==statusValue;
  }
}