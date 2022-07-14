package drivers;

import com.codeborne.selenide.Configuration;
import config.Project;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static config.Project.config;
import static org.assertj.core.api.Assertions.assertThat;

public class WebUiDriver {

    public static void createDriver() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://bookmate.com";
        Configuration.browser = config.browser();
        if (!config.remoteDriver().isEmpty()) {
            Configuration.remote = config.remoteDriver();
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en-US");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (!config.remoteDriver().isEmpty()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
    }
}
