package config;

import org.aeonbits.owner.ConfigFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class Project {

    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    static {
        String errorMessage = "'%s' value is null or empty";
        assertThat(config.tool()).withFailMessage(errorMessage, "tool").isNotEmpty();
        switch (config.tool()) {
            case "ui_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "ui_local":
                validateProperty(config.browser(), "browser");
                break;
            case "mobile_selenoid":
                validateProperty(config.remoteDriver(), "remoteDriver");
            case "mobile_browserstack":
                validateProperty(config.user(), "user");
                validateProperty(config.key(), "key");
                break;
            case "mobile_emulator":
                validateProperty(config.remoteDriver(), "remoteDriver");
                break;
            default:
                throw new IllegalStateException("Unexpected 'tool' value: " + config);
        }
        System.out.println("CONFIG:");
        System.out.println(config.tool());
        System.out.println(config.remoteDriver());
        System.out.println(config.browser());
        System.out.println(config.user());
        System.out.println(config.key());
    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue).withFailMessage("'%s' value is null or empty", propertyName).isNotEmpty();
    }
}
