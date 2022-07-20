package tests.webui.domain;

public enum HeaderTab {
    SEARCH("Search", "https://bookmate.com/search"),
    BOOKS("Books", "https://bookmate.com/books"),
    AUDIOBOOKS("Audiobooks", "https://bookmate.com/audiobooks"),
    COMICS("Comics", "https://bookmate.com/comicbooks"),
    BOOKSHELVES("Bookshelves", "https://bookmate.com/bookshelves/all"),
    GIFT_CARDS("Gift cards", "https://bookmate.com/gifts");

    public final String name;
    public final String url;

    HeaderTab(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
