package tests.mobile.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Attachments.screenshotAs;

public class SearchPage {

    private final SelenideElement backButton = $(AppiumBy.id("com.bookmate:id/img_arrow_back"));
    private final SelenideElement inputSearch = $(AppiumBy.id("com.bookmate:id/input_search"));
    private final SelenideElement lastVisibleFilterTab =
            $(AppiumBy.xpath("//android.widget.HorizontalScrollView/android.widget.LinearLayout/*[last()]"));


    @Step("Enter search text: '{searchText}'")
    public SearchPage enterSearchText(String searchText) {
        inputSearch.sendKeys(searchText);
        screenshotAs("Screenshot");
        return this;
    }

    @Step("Verify text '{text}' is shown in the results list")
    public SearchPage checkTextIsInResults(String text) {
        $(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.TextView[@text='" + text + "']"))
                .shouldBe(Condition.visible);
        return this;
    }

    @Step("Select filter {itemType} for search results")
    public SearchPage selectFilter(String itemType) {
        while (!$(AppiumBy.xpath("//android.widget.LinearLayout[contains(@content-desc,'" + itemType + "')]")).isDisplayed()) {
            System.out.println("The tab" + itemType + " is not visible. Press the last visible and recheck...");
            lastVisibleFilterTab.click();
        }
        $(AppiumBy.xpath("//android.widget.LinearLayout[contains(@content-desc,'" + itemType + "')]")).click();
        return this;
    }
}
