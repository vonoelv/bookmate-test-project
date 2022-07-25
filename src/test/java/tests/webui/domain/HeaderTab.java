package tests.webui.domain;

import config.Project;

public enum HeaderTab {
    SEARCH("Search", Project.config.baseUrl() + "/search"),
    BOOKS("Books", Project.config.baseUrl() + "/books"),
    AUDIOBOOKS("Audiobooks", Project.config.baseUrl() + "/audiobooks"),
    COMICS("Comics", Project.config.baseUrl() + "/comicbooks"),
    BOOKSHELVES("Bookshelves", Project.config.baseUrl() + "/bookshelves/all"),
    GIFT_CARDS("Gift cards", Project.config.baseUrl() + "/gifts");

    public final String name;
    public final String url;

    HeaderTab(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
