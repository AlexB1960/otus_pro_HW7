package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.google.inject.Singleton;
import ru.otus.components.GiftListContent;
import ru.otus.components.GiftListItem;

@Singleton  //каждая страница создается только одна!
public class MyGiftListPage extends AbsBasePage {
  //создаем контейнер гифтлистов для текущего пользователя
  private final GiftListContent giftListContent =
      new GiftListContent($(id("ru.otus.wishlist:id/gifts_content")));

  //проверка видимости и размера контейнера с гифтлистами
  public MyGiftListPage assertNumberOfGiftLists(int value) {
    giftListContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  //получение гифтлиста из контейнера по индексу
  private GiftListItem getGiftListItem(int index) {
    return giftListContent.get(index).shouldBe(visible);
  }

  //проверка заголовка гифтлиста по индексу
  public MyGiftListPage assertGiftListTitle(int index, String value) {
    getGiftListItem(index).assertTitleEqualsTo(value);
    return this;
  }

  //проверка подзаголовка гифтлиста по индексу
  public MyGiftListPage assertGiftListSubtitle(int index, String value) {
    getGiftListItem(index).assertSubtitleEqualsTo(value);
    return this;
  }

  //нажатие иконки для вызова редактирования гифтлиста по индексу
  public void tapEditGiftList(int index) {
    getGiftListItem(index).tapEdit();
  }

  //проверка резервирования гифтлиста по индексу
  public MyGiftListPage assertGiftReserved(int index, boolean isReserved) {
    getGiftListItem(index).assertSwitchStatus(isReserved);
    return this;
  }

  //
  public MyGiftListPage tapeReserveSwitch(int index) {
    getGiftListItem(index).tapStatusSwitch();
    return this;
  }
}
