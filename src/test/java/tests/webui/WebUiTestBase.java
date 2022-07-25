package tests.webui;

import tests.TestBase;
import tests.webui.pages.MainPage;
import tests.webui.pages.SearchPage;

public class WebUiTestBase extends TestBase {
    public static final MainPage mainPage = new MainPage();
    public static final SearchPage searchPage = new SearchPage();
}