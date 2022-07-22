package drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static config.Project.config;

public class LocalMobileDriver implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        Configuration.browserSize = null;
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setCapability("uiautomator2ServerInstallTimeout", 90000);
        options.setDeviceName(config.deviceName());
        options.setPlatformVersion(config.platformVersion());
        options.setApp(getApk().getAbsolutePath());
        options.setLocale("en");
        options.setLanguage("en");
        options.setAppPackage("com.bookmate");
        options.setAppActivity("com.bookmate.app.LaunchActivity");

        try {
            return new AndroidDriver(new URL(config.remoteDriver()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File getApk() {
        return new File("src/test/resources/apk/Bookmate_8.0.3.apk");
    }
}
