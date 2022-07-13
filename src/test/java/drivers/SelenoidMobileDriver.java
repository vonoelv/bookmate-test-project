package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ProjectConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class SelenoidMobileDriver implements WebDriverProvider {
    static final ProjectConfig CFG = ConfigFactory.create(ProjectConfig.class);

    SelenoidMobileDriver() {
        assertThat(CFG.remoteDriver()).withFailMessage("CFG.remoteDriver() is null or empty").isNotEmpty();
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName("android");
        options.setPlatformName("8.1");
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        options.setApp(getApkUrl());
        options.setLocale("en");
        options.setLanguage("en");
        options.setAppPackage("com.bookmate");
        options.setAppActivity("com.bookmate.app.LaunchActivity");

        try {
            return new AndroidDriver(new URL(CFG.remoteDriver()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private URL getApkUrl() {
        try {
            return new URL("https://github.com/vonoelv/testapk/raw/master/src/test/resources/Bookmate_8.0.3.apk");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
