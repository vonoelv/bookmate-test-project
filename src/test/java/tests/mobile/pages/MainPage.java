package tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import tests.mobile.pages.main.ActivityPage;
import tests.mobile.pages.main.LibraryPage;
import tests.mobile.pages.main.MyBooksPage;
import tests.mobile.pages.main.ProfilePage;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement myBooksMenuBarItem = $(AppiumBy.id("com.bookmate:id/my_books"));
    private final SelenideElement libraryMenuBarItem = $(AppiumBy.id("com.bookmate:id/showcase"));
    private final SelenideElement activityMenuBarItem = $(AppiumBy.id("com.bookmate:id/feed"));
    private final SelenideElement profileMenuBarItem = $(AppiumBy.id("com.bookmate:id/profile"));

    private MyBooksPage myBooksPage;
    private LibraryPage libraryPage;
    private ActivityPage activityPage;
    private ProfilePage profilePage;

    @Step("Open My Books page")
    public MyBooksPage openMyBooks() {
        myBooksMenuBarItem.click();
        return new MyBooksPage();
    }

    @Step("Open Library page")
    public LibraryPage openLibrary() {
        libraryMenuBarItem.click();
        return new LibraryPage();
    }

    @Step("Open Activity page")
    public ActivityPage openActivity() {
        activityMenuBarItem.click();
        return new ActivityPage();
    }

    @Step("Open Profile page")
    public ProfilePage openProfile() {
        profileMenuBarItem.click();
        return new ProfilePage();
    }
}
