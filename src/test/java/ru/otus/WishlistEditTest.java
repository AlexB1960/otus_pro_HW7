package ru.otus;

import com.google.inject.Inject;
import ru.otus.extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.pages.EditWishlistPage;
import ru.otus.pages.LoginPage;
import ru.otus.pages.MyWishlistPage;
import ru.otus.utils.DatabaseUtils;

//Логин и пароль СУБД сохраняем в свойствах этого файла - More Run/Debug -> Modify Run Configuration... -> -ea
@ExtendWith(AndroidExtension.class)
public class WishlistEditTest {
  @Inject private LoginPage loginPage;
  @Inject private MyWishlistPage myWishlistPage;
  @Inject private EditWishlistPage editWishlistPage;
  @Inject private DatabaseUtils databaseUtils;

  @Test
  void editWishlist() {
    String login = "aburlaka";
    String wishlistTitle = "Заголовок вишлиста";
    String wishlistDescription = "Начальное описание сразу после логина";
    String newWishlistDescription = "Новое описание после редактирования";

    databaseUtils.prepareWishlistDescription(login, wishlistDescription);
    loginPage.login(login, "123qwe");
    myWishlistPage
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(0, wishlistTitle)
        .assertWishlistSubtitle(0, wishlistDescription)
        .tapEditWishlist(0);
    editWishlistPage
        .assertEditWishlistTitle("Изменить список желаний")
        .editDescription(newWishlistDescription);
    myWishlistPage
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(0, wishlistTitle)
        .assertWishlistSubtitle(0, newWishlistDescription);
  }
}
