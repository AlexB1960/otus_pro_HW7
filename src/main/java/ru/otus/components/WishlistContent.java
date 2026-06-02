package ru.otus.components;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class WishlistContent extends AbsComponent<WishlistContent> {
  //контейнер с вишлистами одного root
  private final ElementsCollection items =
      root.$$(id("ru.otus.wishlist:id/wishlist_item")).as("Селектор элементов списка желаний");

  public WishlistContent(SelenideElement root) {
    super(root);
  }

  //получение одного вишлиста по индексу из контейнера
  public WishlistItem get(int index) {
    return new WishlistItem(items.get(index));
  }

  public void assertSizeEqualTo(int expected) {
    items.shouldHave(size(expected));
  }
}
