package factory;

import models.Book;
import models.BookCopy;

import java.util.List;

public class BookCopyFactory {
    public static List<BookCopy> createCopies(Book book, List<String> copyIds) {
        return copyIds.stream().map(id -> new BookCopy(id, book)).toList();
    }
}
