package tests.ui;

import tests.TestBase;
import tests.ui.pages.MainPage;
import tests.ui.pages.SearchPage;

public class UiTestBase extends TestBase {
    public static final MainPage mainPage = new MainPage();
    public static final SearchPage searchPage = new SearchPage();
}