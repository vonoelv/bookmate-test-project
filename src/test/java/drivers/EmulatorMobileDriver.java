package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.ProjectConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.CapabilityType.APPLICATION_NAME;

@ParametersAreNonnullByDefault
public class EmulatorMobileDriver implements WebDriverProvider {
    static final ProjectConfig CFG = ConfigFactory.create(ProjectConfig.class);

    EmulatorMobileDriver() {
        assertThat(CFG.remoteDriver()).withFailMessage("CFG.remoteDriver() is null or empty").isNotEmpty();
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setDeviceName("Pixel_4_API_30");
        options.setPlatformVersion("11.0");
        options.setCapability(APPLICATION_NAME, "Appium");
        options.setApp(getApk().getAbsolutePath());
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

    private File getApk() {
        return new File("src/test/resources/apk/Bookmate_8.0.3.apk");
    }
}
