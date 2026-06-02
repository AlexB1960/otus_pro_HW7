package ru.otus.components;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class GiftListContent extends AbsComponent<GiftListContent> {
  private final ElementsCollection items =
      root.$$(id("ru.otus.wishlist:id/gift_item")).as("Селектор элементов списка подарков");

  public GiftListContent(SelenideElement root) {
    super(root);
  }

  public GiftListItem get(int index) {
    return new GiftListItem(items.get(index));
  }

  public void assertSizeEqualTo(int expected) {
    items.shouldHave(size(expected));
  }
}
