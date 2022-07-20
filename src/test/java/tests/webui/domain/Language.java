package tests.webui.domain;

public enum Language {
    ENGLISH("English", "https://bookmate.com/"),
    RUSSIAN("Русский", "https://ru.bookmate.com/"),
    SPANISH("Español", "https://es.bookmate.com/"),
    DUTCH("Dansk", "https://da.bookmate.com/"),
    TURKISH("Türkçe", "https://tr.bookmate.com/"),
    BAHASA_INDONESIA("Bahasa Indonesia", "https://id.bookmate.com/"),
    SWEDISH("Svenska", "https://sv.bookmate.com/"),
    AZERBAIJANI("Azərbaycanca", "https://az.bookmate.com/");

    public final String langNative;
    public final String url;

    Language(String langNative, String url) {
        this.langNative = langNative;
        this.url = url;
    }

}
