package tests.webui;


import config.Project;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.webui.domain.HeaderTab;
import tests.webui.domain.Language;

import static com.codeborne.selenide.Selenide.open;

@Tag("WebUI")
@Epic("WebUI")
@Feature("Header")
@Owner("vonoelv")
class HeaderTests extends WebUiTestBase {

    @BeforeEach
    @Override
    @Step("Open {Project.config.baseUrl()}")
    public void beforeEach() {
        open("/");
        mainPage.acceptCookiesIfNeeded();
    }

    @Test
    @DisplayName("Overview page is open by click on bookmate logo")
    void checkClickOnLogo() {
        mainPage.clickOnBookmateLogo()
                .checkPageIsOpen(Project.config.baseUrl() + "/");
    }

    @Test
    @DisplayName("Ability to switch to each header tab")
    void checkHeaderTabsOpening() {
        for (HeaderTab headerTab : HeaderTab.values()) {
            mainPage.openHeaderTab(headerTab)
                    .checkPageIsOpen(headerTab.url);
        }
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

    @Test
    @DisplayName("Ability to switch to each language")
    void checkLanguageSwitching() {
        for (Language language : Language.values()) {
            mainPage.selectLanguage(language.langNative)
                    .checkPageIsOpen(language.url);
        }
    }
}
