package tests.webui;

import com.github.kklisura.cdt.services.exceptions.WebSocketServiceException;
import helpers.Cdp;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.webui.pages.MainPage;
import tests.webui.pages.SearchPage;

import java.net.URISyntaxException;

import static com.codeborne.selenide.Selenide.open;
import static config.Project.config;

@Tag("WebUI")
@Story("Search")
@Owner("vonoelv")
class SearchTests extends TestBase {

    public SearchPage searchPage = new SearchPage();
    public MainPage mainPage = new MainPage();

    @BeforeEach
    @Override
    public void beforeEach() throws Exception {
        open("about:blank");
        Cdp.setAgentOverrideDependingOnTool(config.tool());
        open("");
        mainPage.acceptCookiesIfNeeded();
    }

    @Test
    @DisplayName("An existing book can be found")
    void searchTest() {
        open("/search");
        searchPage
                .performSearch("Rikki-Tikki-Tavi")
                .checkTitleIsInResults("Rikki-Tikki-Tavi");
    }
}
