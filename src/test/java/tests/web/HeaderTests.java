package tests.web;


import config.Project;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tests.web.domain.HeaderTab;
import tests.web.domain.Language;

import static com.codeborne.selenide.Selenide.open;

@Tag("Web")
@Epic("Web")
@Feature("Header")
@Owner("vonoelv")
class HeaderTests extends WebTestBase {

    @BeforeEach
    @Override
    @Step("Open {Project.config.baseUrl()}")
    public void beforeEach() {
        open("/");
        mainPage.acceptCookies();
    }

    @Test
    @DisplayName("Overview page is open by click on bookmate logo")
    void checkClickOnLogo() {
        mainPage.clickOnBookmateLogo()
                .checkPageIsOpen(Project.config.baseUrl() + "/");
    }

    @ParameterizedTest(name = "{arguments}")
    @EnumSource(HeaderTab.class)
    @DisplayName("Ability to switch to each header tab:")
    void checkHeaderTabsOpening(HeaderTab headerTab) {
        Allure.getLifecycle().updateTestCase(test ->
                test.setName("Ability to switch to each header tab: [Header]"));

        mainPage.openHeaderTab(headerTab)
                .checkPageIsOpen(headerTab.url);
    }

    @Test
    @DisplayName("Ability to open Manage Subscription page")
    void checkSubscriptionPageOpening() {
        mainPage.openSubscriptionPage()
                .checkPageIsOpen(Project.config.baseUrl() + "/subscription?dscvr=header");
    }

    @Test
    @DisplayName("Ability to open login dialog")
    void checkLoginDialogOpening() {
        mainPage.openLoginDialog()
                .checkLoginPageIsVisible();
    }

    @ParameterizedTest(name = "{arguments}")
    @EnumSource(Language.class)
    @DisplayName("Ability to switch to each language:")
    void checkLanguageSwitching(Language language) {
        Allure.getLifecycle().updateTestCase(test ->
                test.setName("Ability to switch to each language: [Language]"));

        mainPage.selectLanguage(language.langNative);
        if (!language.langNative.equals("English")) {
            mainPage.acceptCookies();
        }
        mainPage.checkPageIsOpen(language.languageDomain);
    }
}
