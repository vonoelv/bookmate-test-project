package tests.webui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.webui.domain.HeaderTab;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    private final SelenideElement bookmateLogo = $(".header__controls.header__controls_left").$(".logo");
    private final SelenideElement headerTabsSection = $(".header-nav__links");
    private final SelenideElement buySubscription = $(".subscription-button");
    private final SelenideElement login = $(".login-button");
    private final SelenideElement languageSelector = $(".languages-button");
    private final SelenideElement languagesList = $(".languages__list");


    private final LoginPage loginPage = new LoginPage();

    @Step("Click on bookmate header logo")
    public MainPage clickOnBookmateLogo() {
        bookmateLogo.click();
        return this;
    }

    @Step("Open header tab {headerTab}")
    public MainPage openHeaderTab(HeaderTab headerTab) {
        headerTabsSection.$(withText(headerTab.name)).click();
        return this;
    }

    @Step("Click 'Buy subscription' button")
    public MainPage openSubscriptionPage() {
        buySubscription.click();
        return this;
    }

    @Step("Open login dialog")
    public LoginPage openLoginDialog() {
        login.click();
        return loginPage;
    }

    @Step("Select site language: {language}")
    public MainPage selectLanguage(String language) {
        languageSelector.hover();
        languagesList.$(withText(language)).click();
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

    @Step("Verify page {url} is open")
    public MainPage checkPageIsOpen(String url) {
        webdriver().shouldHave(url(url));
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
