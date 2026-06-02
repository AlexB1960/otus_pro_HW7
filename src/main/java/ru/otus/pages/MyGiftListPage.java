package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.google.inject.Singleton;
import ru.otus.components.GiftListContent;
import ru.otus.components.GiftListItem;

@Singleton
public class MyGiftListPage extends AbsBasePage {
  private final GiftListContent giftListContent =
      new GiftListContent($(id("ru.otus.wishlist:id/gifts_content")));

  public MyGiftListPage assertNumberOfGiftLists(int value) {
    giftListContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  private GiftListItem getGiftListItem(int index) {
    return giftListContent.get(index).shouldBe(visible);
  }

  public MyGiftListPage assertGiftListTitle(int index, String value) {
    getGiftListItem(index).assertTitleEqualsTo(value);
    return this;
  }

  public MyGiftListPage assertGiftListSubtitle(int index, String value) {
    getGiftListItem(index).assertSubtitleEqualsTo(value);
    return this;
  }

  public void tapEditGiftList(int index) {
    getGiftListItem(index).tapEdit();
  }

  public MyGiftListPage assertGiftReserved(int index, boolean isReserved) {
    getGiftListItem(index).assertSwitchStatus(isReserved);
    return this;
  }

  public MyGiftListPage tapeReserveSwitch(int index) {
    getGiftListItem(index).tapStatusSwitch();
    return this;
  }
}
