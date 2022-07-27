package tests.web;

import tests.TestBase;
import tests.web.pages.MainPage;
import tests.web.pages.SearchPage;

public class WebTestBase extends TestBase {
    public static final MainPage mainPage = new MainPage();
    public static final SearchPage searchPage = new SearchPage();
}