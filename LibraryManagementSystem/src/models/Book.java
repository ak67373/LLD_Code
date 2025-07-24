package models;

import java.util.List;

public class Book {
    private final String bookId, title;
    private final List<String> authors, publishers;

    public Book(String bookId, String title, List<String> authors, List<String> publishers) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getPublishers() {
        return publishers;
    }
}
