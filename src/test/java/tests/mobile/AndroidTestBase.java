package tests.mobile;

import tests.TestBase;
import tests.mobile.pages.MainBarPage;
import tests.mobile.pages.main.LibraryPage;
import tests.mobile.pages.main.MyBooksPage;
import tests.mobile.pages.main.ProfilePage;
import tests.mobile.pages.prelogin.WelcomePage;


public class AndroidTestBase extends TestBase {
    public static final WelcomePage welcomePage = new WelcomePage();
    public static final MainBarPage mainBarPage = new MainBarPage();
    public static final MyBooksPage myBooksPage = new MyBooksPage();
    public static final LibraryPage libraryPage = new LibraryPage();
    public static final ProfilePage profilePage = new ProfilePage();
}
