package tests.web.domain;

import config.Project;

public enum Language {
    ENGLISH("English", ""),
    RUSSIAN("Русский", "ru."),
    SPANISH("Español", "es."),
    DUTCH("Dansk", "da."),
    TURKISH("Türkçe", "tr."),
    BAHASA_INDONESIA("Bahasa Indonesia", "id."),
    SWEDISH("Svenska", "sv."),
    AZERBAIJANI("Azərbaycanca", "az.");

    public final String langNative;
    public final String languageDomain;

    Language(String langNative, String languageDomain) {
        String baseUrlWithoutProtocolPrefix = Project.config.baseUrl().replace("https://", "");
        this.langNative = langNative;
        this.languageDomain = "https://" + languageDomain + baseUrlWithoutProtocolPrefix + "/";
    }
}
