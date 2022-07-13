package tests.mobile;

import tests.TestBase;
import tests.mobile.pages.main.MyBooksPage;
import tests.mobile.pages.prelogin.WelcomePage;


public class MainTests extends TestBase {
    //ВОЗМОЖНО КЛАСС НЕ НУЖЕН УЖЕ
    public MyBooksPage loginWithAnyAvailableUser() {
        return new WelcomePage()
                .pressAlreadyRegistered()
                .pressEmailLogin()
                .LoginWith("jojiyik256@lenfly.com", "qwerty");
        //TODO: load the list of available users and chose anyone
    }

    public void openWithoutLogin() {
        //TODO: select some tags and at least 2 books to get to the app
    }
}
