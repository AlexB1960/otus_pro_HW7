package ru.otus;

import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.AndroidExtension;
import ru.otus.pages.EditGiftListPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyGiftListPage;
import ru.otus.pages.MyWishlistPage;
import ru.otus.utils.DatabaseUtils;

@ExtendWith(AndroidExtension.class)
public class GiftListEditTest {
  @Inject private LoginPage loginPage;
  @Inject private MyWishlistPage myWishlistPage;
  @Inject private MyGiftListPage myGiftListPage;
  @Inject private EditGiftListPage editGiftListPage;
  @Inject private DatabaseUtils databaseUtils;

  @Test
  void editGiftList() {
    String login = "aburlaka1";
    String giftListTitle = "Кофе";
    String giftListDescription = "Американо";
    String newGiftListDescription = "Выпей лучше водки";

    databaseUtils.prepareGiftListDescription(login, giftListDescription);
    loginPage.login(login, "123qwe");
    myWishlistPage
        .assertNumberOfWishlists(1)
        .tapTitleWishlist(0);
    myGiftListPage
        .assertNumberOfGiftLists(1)
        .assertGiftListTitle(0, giftListTitle)
        .assertGiftListSubtitle(0, giftListDescription)
        .tapEditGiftList(0);
    editGiftListPage
        .assertEditGiftListTitle("Изменить подарок")
        .editDescription(newGiftListDescription);
    myGiftListPage
        .assertNumberOfGiftLists(1)
        .assertGiftListTitle(0, giftListTitle)
        .assertGiftListSubtitle(0, newGiftListDescription);
  }
}
