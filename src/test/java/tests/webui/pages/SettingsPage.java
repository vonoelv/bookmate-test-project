package tests.webui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPage {

    private final SelenideElement currentEmail = $("#user-current-email");

    @Step
    public void checkEmail(String expectedEmail) {
        currentEmail.shouldHave(text(expectedEmail));
    }
}
