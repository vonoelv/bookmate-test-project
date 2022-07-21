package config;

import org.aeonbits.owner.ConfigFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class App {

    public static AppConfig config = ConfigFactory.create(AppConfig.class);

    static {
        System.out.println(System.getProperties());
        validateProperty(config.login(), "login");
        validateProperty(config.password(), "password");
        validateProperty(config.xCsrfToken(), "xCsrfToken");
        validateProperty(config.csrf(), "csrf");
        validateProperty(config.bms(), "bms");
        validateProperty(config.book1Uuid(), "book1Uuid");
        validateProperty(config.book2Uuid(), "book2Uuid");
        System.out.println(config);
    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue).withFailMessage("'%s' value is null or empty", propertyName).isNotEmpty();
    }
}
