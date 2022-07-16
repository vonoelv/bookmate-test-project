package config;

import org.aeonbits.owner.ConfigFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class Project {

    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    static {
        String errorMessage = "'%s' value is null or empty";
        assertThat(config.runIn()).withFailMessage(errorMessage, "tool").isNotEmpty();
        switch (config.runIn()) {
            case "browser_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "browser_local":
                validateProperty(config.browser(), "browser");
                break;
            case "android_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "android_browserstack":
                validateProperty(config.user(), "user");
                validateProperty(config.key(), "key");
                break;
            case "android_emulator":
            case "android_real":
                validateProperty(config.remoteDriver(), "remoteDriver");
                validateProperty(config.deviceName(), "deviceName");
                validateProperty(config.platformVersion(), "platformVersion");
                break;
            default:
                throw new IllegalStateException("Unexpected 'tool' value: " + config);
        }
        System.out.println("CONFIG:");
        System.out.println(config.runIn());
        System.out.println(config.remoteDriver());
        System.out.println(config.browser());
        System.out.println(config.user());
        System.out.println(config.key());
    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue).withFailMessage("'%s' value is null or empty", propertyName).isNotEmpty();
    }

    public static boolean isRemoteDriver() {
        return !config.remoteDriver().isEmpty();
    }
}
