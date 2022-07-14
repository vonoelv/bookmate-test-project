package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@Sources("classpath:config/config${propertyFilePostfix}.properties")
public interface ProjectConfig extends Config {

    String remoteDriver();
    String browser();
    String tool();
    String user();
    String key();
    String deviceName();
    String platformVersion();
}

