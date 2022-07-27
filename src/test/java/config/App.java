package config;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class App {

    public static AppConfig config = ConfigFactory.create(AppConfig.class);
    private static final Logger logger = LoggerFactory.getLogger(Project.class);

    static {
        validateProperty(config.login(), "login");
        validateProperty(config.password(), "password");
        validateProperty(config.xCsrfToken(), "xCsrfToken");
        validateProperty(config.csrf(), "csrf");
        validateProperty(config.bms(), "bms");
        validateProperty(config.book1Uuid(), "book1Uuid");
        validateProperty(config.book2Uuid(), "book2Uuid");
        logger.info(config.toString());
    }

    public static void validateProperty(String propertyValue, String propertyName) {
        assertThat(propertyValue)
                .withFailMessage("'%s' value is null or empty", propertyName)
                .isNotEmpty();
    }
}
