package drivers;

import com.codeborne.selenide.Configuration;
import config.Project;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import static config.Project.isRemoteDriver;
import static java.lang.String.format;

public class WebUiDriver {

    public static void configure() {
        Configuration.browserSize = Project.config.browserSize();
        Configuration.baseUrl = Project.config.baseUrl();
        Configuration.browser = Project.config.browser();
        MutableCapabilities capabilities = new DesiredCapabilities();
        if (isRemoteDriver()) {
            Configuration.remote = Project.config.remoteDriver()
                    .replace("https://", format("https://%s:%s@", Project.config.user(), Project.config.key()));
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }

        switch (Project.config.browser()) {
            case "chrome":
                setChromeOptions(capabilities);
                break;
            case "firefox":
                setFirefoxOptions(capabilities);
                break;
            default:
                Configuration.browserCapabilities = capabilities;
        }
    }

    public static void setChromeOptions(MutableCapabilities capabilities) {
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-notifications")
                .addArguments("--lang=en-US")
                .setExperimentalOption("excludeSwitches", new String[]{"enable-automation"})
                .merge(capabilities);
    }

    public static void setFirefoxOptions(MutableCapabilities capabilities) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en-US");
        Configuration.browserCapabilities = new FirefoxOptions()
                .setProfile(profile)
                .merge(capabilities);
    }
}
