package tests.mobile.pages.prelogin;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TopicsSelectionPage {
    private final ElementsCollection topics =
            $$(AppiumBy.xpath("//android.widget.LinearLayout[@resource-id='com.bookmate:id/bubble_container']/android.widget.TextView[@resource-id='com.bookmate:id/text_view_content']"));
    private final SelenideElement continueButton = $(AppiumBy.id("com.bookmate:id/next"));
    private static final BooksSelectionPage booksSelectionPage = new BooksSelectionPage();

    @Step("Select topic {topic}")
    public TopicsSelectionPage selectTopic(String topic) {
        topics.findBy(text(topic)).click();
        return this;
    }

    @Step("Press continue button")
    public BooksSelectionPage pressContinueButton() {
        continueButton.click();
        return booksSelectionPage;
    }

}
