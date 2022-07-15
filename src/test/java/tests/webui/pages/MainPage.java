package tests.webui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class MainPage {

    private final SelenideElement overview = $("header__controls header__controls_left").$(".logo");
    private final SelenideElement search = $("header-nav__links").$(withText("Search"));
    private final SelenideElement books = $("header-nav__links").$(withText("Books"));
    private final SelenideElement audiobooks = $("header-nav__links").$(withText("Audiobooks"));
    private final SelenideElement comics = $("header-nav__links").$(withText("Comics"));
    private final SelenideElement bookshelves = $("header-nav__links").$(withText("Bookshelves"));
    private final SelenideElement giftCards = $("header-nav__links").$(withText("Gift cards"));
    private final SelenideElement buySubscription = $(".subscription-button");
    private final SelenideElement login = $(".login-button");
    private final SelenideElement languageSelector = $(".languages-button");
    private final SelenideElement languagesList = $(".languages__list");


    private final LoginPage loginPage = new LoginPage();
    private final SearchPage searchPage = new SearchPage();

    @Step("Open search")
    public SearchPage openSearch() {
        search.click();
        return searchPage;
    }

    @Step("Open login")
    public LoginPage openLoginDialog() {
        login.click();
        return loginPage;
    }

    @Step("Select site language: {language}")
    public MainPage selectLanguage(String language) {
        languageSelector.hover();
        languagesList.$(withText("English")).click();
        acceptCookiesIfNeeded();
        return this;
    }

    @Step("Open in main menu")
    public MainPage openInMainMenu(String menuItem) {
        $(".showcase").shouldBe(Condition.visible);
        $(".header__avatar").click();
        $(".header-dropdown").$(withText("Settings")).click();
        return this;
    }

    @Step("Logging with email:{email} and password:{password}")
    public MainPage login(String email, String password) {
        openLoginDialog();
        loginPage.login(email, password);
        return this;
    }

    public MainPage acceptCookiesIfNeeded() {
        for (int i = 0; i <= 8; i++) {
            if ($(".cookies-message").isDisplayed()) {
                $(".cookies-message__button_primary").click();
                System.out.println("Cookies message found and clicked");
                return this;
            }
            sleep(500);
        }
        System.out.println("No cookies message found");
        return this;
    }
}
