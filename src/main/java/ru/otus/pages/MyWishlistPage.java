package ru.otus.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.google.inject.Singleton;
import ru.otus.components.WishlistContent;
import ru.otus.components.WishlistItem;

@Singleton
public class MyWishlistPage extends AbsBasePage {
  private final WishlistContent wishlistContent =
      new WishlistContent($(id("ru.otus.wishlist:id/wishlists_content")));

  public MyWishlistPage assertNumberOfWishlists(int value) {
    wishlistContent.shouldBe(visible).assertSizeEqualTo(value);
    return this;
  }

  private WishlistItem getWishlistItem(int index) {
    return wishlistContent.get(index).shouldBe(visible);
  }

  public MyWishlistPage assertWishlistTitle(int index, String value) {
    getWishlistItem(index).assertTitleEqualsTo(value);
    return this;
  }

  public MyWishlistPage assertWishlistSubtitle(int index, String value) {
    getWishlistItem(index).assertSubtitleEqualsTo(value);
    return this;
  }

  public void tapEditWishlist(int index) {
    getWishlistItem(index).tapEdit();
  }

  public void tapTitleWishlist(int index) {
    getWishlistItem(index).tapTitle();
  }
}
