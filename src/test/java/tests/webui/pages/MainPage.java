package tests.webui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.webui.domain.HeaderTab;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    public static final SelenideElement mainContent = $(".showcase");
    public static final SelenideElement headerAvatar = $(".header__avatar");
    public static final SelenideElement mainMenuDropdown = $(".header-dropdown");
    public static final SelenideElement cookiesMessage = $(".cookies-message");
    public static final SelenideElement cookiesMessageConfirmButton = $(".cookies-message__button_primary");
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
        return this;
    }

    @Step("Open in main menu")
    public MainPage openInMainMenu(String menuItem) {
        mainContent.shouldBe(Condition.visible);
        headerAvatar.click();
        mainMenuDropdown.$(withText(menuItem)).click();
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

    public MainPage acceptCookies() {
        cookiesMessageConfirmButton.click();
        return this;
    }
}
