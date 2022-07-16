package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Sources({"system:properties",
        "classpath:config/project.properties",
        "classpath:config/project-${runIn}.properties"
})
public interface ProjectConfig extends Config {

    String remoteDriver();

    String browser();

    String runIn();

    String user();

    String key();

    String deviceName();

    String platformVersion();
}

