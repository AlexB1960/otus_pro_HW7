package ru.otus.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

//компонент верхнего заголовка с кнопкой фильтра пользователей на странице UsersListPage
public class TopFilterComponent extends AbsComponent<TopFilterComponent> {
  private final SelenideElement filterButton =
      $(id("ru.otus.wishlist:id/filter"));

  public TopFilterComponent(SelenideElement root) {
    super(root);
  }

  //нажатие кнопки фильтра пользователей
  public void clickFilterButton() {
    filterButton.shouldBe(visible.because("Кнопка фильтра пользователей не видна")).click();
  }
}
