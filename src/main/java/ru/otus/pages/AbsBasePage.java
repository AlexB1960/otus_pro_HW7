package ru.otus.pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import ru.otus.components.BottomMenuComponent;
import ru.otus.pageobject.AbsPageObject;

//методы и поля, общие для всех страниц
public abstract class AbsBasePage extends AbsPageObject { //нужен дженерик <T> ???
  private final SelenideElement bottomMenu = $(id("ru.otus.wishlist:id/bottom_navigation"));
  private final BottomMenuComponent bottomMenuComponent = new BottomMenuComponent(bottomMenu);

  public void tapUserMenu() {
    bottomMenuComponent.clickUserMenu();
  }
}
