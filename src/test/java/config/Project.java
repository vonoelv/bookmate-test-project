package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class Project {

    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
    private static final Logger logger = LoggerFactory.getLogger(Project.class);

    static {
        if ("API".equals(System.getProperty("tag"))) {
            validateProperty(config.apiBaseUrl(), "apiBaseUrl");
        } else {
            validateEnvDependentProperties();
        }
        logger.info(config.toString());
    }

    private static void validateEnvDependentProperties() {
        validateProperty(config.runIn(), "runIn");
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
                throw new IllegalStateException("Unexpected 'runIn' value: " + config);
        }
    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue)
                .withFailMessage("'%s' value is null or empty", propertyName)
                .isNotEmpty();
    }

    public static boolean isRemoteDriver() {
        return !(config.remoteDriver() == null) && !config.remoteDriver().isEmpty();
    }
}
