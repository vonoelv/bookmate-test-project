package tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPage {

    private final SelenideElement currentEmail = $("#user-current-email");

    @Step("Verify current email is {expectedEmail}")
    public void checkEmail(String expectedEmail) {
        currentEmail.shouldHave(text(expectedEmail));
    }
}
