package tests.mobile.pages.prelogin;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BooksSelectionPage {

    private final ElementsCollection books =
            $$(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='com.bookmate:id/cover_toggle']"));

    private final SelenideElement continueButton = $(AppiumBy.id("com.bookmate:id/next"));

    @Step("Select book #{index}")
    public BooksSelectionPage selectBook(int index) {
        books.get(index).click();
        return this;
    }

    @Step("Press continue button")
    public BooksSelectionPage pressContinueButton() {
        continueButton.click();
        return this;
    }
}
