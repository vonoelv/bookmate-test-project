package tests.webui.domain;

import config.Project;

public enum Language {
    ENGLISH("English", Project.config.baseUrl()),
    RUSSIAN("Русский", Project.config.baseUrl().replace("//", "//ru.") + "/"),
    SPANISH("Español", Project.config.baseUrl().replace("//", "//es.") + "/"),
    DUTCH("Dansk", Project.config.baseUrl().replace("//", "//da.") + "/"),
    TURKISH("Türkçe", Project.config.baseUrl().replace("//", "//tr.") + "/"),
    BAHASA_INDONESIA("Bahasa Indonesia", Project.config.baseUrl().replace("//", "//id.") + "/"),
    SWEDISH("Svenska", Project.config.baseUrl().replace("//", "//sv.") + "/"),
    AZERBAIJANI("Azərbaycanca", Project.config.baseUrl().replace("//", "//az.") + "/");

    public final String langNative;
    public final String url;

    Language(String langNative, String url) {
        this.langNative = langNative;
        this.url = url;
    }
}
