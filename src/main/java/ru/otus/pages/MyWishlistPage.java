package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.google.inject.Singleton;
import ru.otus.components.WishlistContent;
import ru.otus.components.WishlistItem;

@Singleton  //каждая страница создается только одна!
public class MyWishlistPage extends AbsBasePage {
  //создаем контейнер вишлистов для текущего пользователя
  private final WishlistContent wishlistContent =
      new WishlistContent($(id("ru.otus.wishlist:id/wishlists_content")));

  //проверка видимости и размера контейнера с вишлистами
  public MyWishlistPage assertNumberOfWishlists(int value) {
    wishlistContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  //получение вишлиста из контейнера по индексу
  private WishlistItem getWishlistItem(int index) {
    return wishlistContent.get(index).shouldBe(visible);
  }

  //проверка заголовка вишлиста по индексу
  public MyWishlistPage assertWishlistTitle(int index, String value) {
    getWishlistItem(index).assertTitleEqualsTo(value);
    return this;
  }

  //проверка подзаголовка вишлиста по индексу
  public MyWishlistPage assertWishlistSubtitle(int index, String value) {
    getWishlistItem(index).assertSubtitleEqualsTo(value);
    return this;
  }

  //нажатие иконки для вызова редактирования вишлиста по индексу
  public void tapEditWishlist(int index) {
    getWishlistItem(index).tapEdit();
  }

  //нажатие на название вишлиста по индексу
  public void tapTitleWishlist(int index) {
    getWishlistItem(index).tapTitle();
  }
}
