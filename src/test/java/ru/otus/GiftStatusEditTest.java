package ru.otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.*;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class GiftStatusEditTest {
  @Inject private LoginPage loginPage;
  @Inject private MyWishlistPage myWishlistPage;
  @Inject private MyGiftListPage myGiftListPage;
  @Inject private EditGiftListPage editGiftListPage;
  @Inject private UsersListPage usersListPage;
  @Inject private FilterUsersPage filterUsersPage;
  @Inject private DatabaseUtils databaseUtils;

  @Test
  void editGiftStatus() {
    String login = "aburlaka3";
    String login2 = "aburlaka2";
    String wishlistTitle = "Напиток";
    String giftListTitle = "Кофе";

    databaseUtils.prepareGiftListStatus(login2);
    loginPage.login(login, "123qwe");
    myWishlistPage
        .assertNumberOfWishlists(0)
        .tapUserMenu();
    usersListPage
        .tapFilterButton();
    filterUsersPage
        .assertFilterTitle()
        .setFilterUserName(login2);
    usersListPage
        .assertNumberOfUsers(1)
        .assertFilterUser(0, login2)
        .tapUserItem(0);
    myWishlistPage
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(0, wishlistTitle)
        .tapTitleWishlist(0);
    myGiftListPage
        .assertNumberOfGiftLists(1)
        .assertGiftListTitle(0, giftListTitle)
        .assertGiftReserved(0, false)
        .tapeReserveSwitch(0)
        .assertGiftReserved(0, true);
  }

  // кнопка пользователи = ru.otus.wishlist:id/users_menu        ru.otus.wishlist:id/navigation_bar_item_icon_view  [2]или[2]  ru.otus.wishlist:id/navigation_bar_item_small_label_view
  // Отдельный пользователь в общем списке = ru.otus.wishlist:id/user_item
  // кнопка фильтра = ru.otus.wishlist:id/filter

  // нижняя шторка Фильтры = ru.otus.wishlist:id/users_filter_bottom_sheet
  // заголовок Фильтры = ru.otus.wishlist:id/filter_title
  // поле Имя пользователя = ru.otus.wishlist:id/username_input
  // кнопка Применить = ru.otus.wishlist:id/apply_button

  // отфильтрованный пользователь = ru.otus.wishlist:id/username

  // заголовок в компоненте Желания aburlaka2 = ru.otus.wishlist:id/top_app_bar
  // контейнер с желаниями = ru.otus.wishlist:id/wishlists
  // одно желание (компонент? можно кликнуть по нему) = ru.otus.wishlist:id/wishlist_item
  // заголовок одного желания = ru.otus.wishlist:id/title
  // описание одного желания = ru.otus.wishlist:id/subtitle

  // заголовок в компоненте aburlaka2: Кофе = ru.otus.wishlist:id/top_app_bar
  // элемент (компонент) одного подарка = ru.otus.wishlist:id/gift_item
  // название подарка = ru.otus.wishlist:id/title
  // описание подарка = ru.otus.wishlist:id/subtitle
  // переключатель статуса подарка = ru.otus.wishlist:id/reserved

}
