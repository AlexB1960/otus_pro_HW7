package ru.otus.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

@Singleton
public class EditGiftListPage extends AbsBasePage {
  private final SelenideElement title =
      $(id("ru.otus.wishlist:id/gift_edit_title"));
  private final SelenideElement descriptionInputField =
      $(id("ru.otus.wishlist:id/description_input"));
  private final SelenideElement saveButton =
      $(id("ru.otus.wishlist:id/save_button"));

  //Проверка заголовка гифтлиста
  public EditGiftListPage assertEditGiftListTitle(String expected) {
    title.shouldBe(visible).shouldHave(text(expected));
    return this;
  }

  //Изменение описания гифтлиста и проверка видимости этого изменения
  public void editDescription(String description) {
    descriptionInputField.shouldBe(visible).clear();
    descriptionInputField.sendKeys(description);
    saveButton.shouldBe(visible).click();
  }
}
