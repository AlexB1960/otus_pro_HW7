package ru.otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
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
  @DisplayName("Тест изменения статуса резервирования подарка другого пользователя")
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
}
