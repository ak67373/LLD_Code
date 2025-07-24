package managers;

import factory.BookCopyFactory;
import models.Book;
import models.BookCopy;
import models.Rack;
import models.User;

import java.util.*;

public class LibraryManager {
    private static LibraryManager instance;
    private List<Rack> racks = new ArrayList<>();
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, BookCopy> copies = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();

    private LibraryManager() {
    }

    public static synchronized LibraryManager getInstance() {
        if (instance == null) instance = new LibraryManager();
        return instance;
    }

    public void createLibrary(int n) {
        racks.clear();
        for (int i = 1; i <= n; i++) racks.add(new Rack(i));
        System.out.printf("Created library with %d racks%n", n);
    }

    public void addBook(String bookId, String title, List<String> authors,
                        List<String> publishers, List<String> copyIds) {
        Book book = books.computeIfAbsent(bookId, key -> new Book(bookId, title, authors, publishers));
        List<BookCopy> newCopies = BookCopyFactory.createCopies(book, copyIds);
        List<Integer> assigned = new ArrayList<>();

        for (BookCopy bc : newCopies) {
            Rack r = findFreeRack();
            if (r == null) break;
            r.assign(bc);
            copies.put(bc.getCopyId(), bc);
            assigned.add(r.getRackNo());
        }

        if (assigned.isEmpty()) {
            System.out.println("Rack not available");
        } else {
            System.out.println("Added Book to racks: " + String.join(",", assigned.stream()
                    .map(Object::toString).toList()));
        }
    }

    public void removeBookCopy(String copyId) {
        BookCopy bc = copies.get(copyId);
        if (bc == null || bc.getRackNo() < 0) {
            System.out.println("Invalid Book Copy ID");
            return;
        }
        Rack r = racks.get(bc.getRackNo() - 1);
        r.remove();
        copies.remove(copyId);
        System.out.printf("Removed book copy: %s from rack: %d%n", copyId, r.getRackNo());
    }

    public void borrowBook(String bookId, String userId, String dueDate) {
        if (!books.containsKey(bookId)) {
            System.out.println("Invalid Book ID");
            return;
        }
        Optional<BookCopy> opt = racks.stream()
                .filter(r -> !r.isFree() && r.getCopy().getBook().getBookId().equals(bookId))
                .map(Rack::getCopy)
                .findFirst();

        if (opt.isEmpty()) {
            System.out.println("Not available");
            return;
        }
        borrowBookCopy(opt.get().getCopyId(), userId, dueDate);
    }

    public void borrowBookCopy(String copyId, String userId, String dueDate) {
        BookCopy bc = copies.get(copyId);
        if (bc == null || bc.isBorrowed() || bc.getRackNo() < 0) {
            System.out.println("Invalid Book Copy ID");
            return;
        }
        User u = users.computeIfAbsent(userId, User::new);
        if (!u.canBorrow()) {
            System.out.println("Overlimit");
            return;
        }
        Rack r = racks.get(bc.getRackNo() - 1);
        r.remove();
        bc.borrow(userId, dueDate);
        u.borrow(bc);
        System.out.printf("Borrowed Book from rack: %d%n", r.getRackNo());
    }

    public void returnBookCopy(String copyId) {
        BookCopy bc = copies.get(copyId);
        if (bc == null || !bc.isBorrowed()) {
            System.out.println("Invalid Book Copy ID");
            return;
        }
        User u = users.get(bc.getBorrowedBy());
        Rack r = findFreeRack();
        bc.returned(r.getRackNo());
        u.returned(bc);
        r.assign(bc);
        System.out.printf("Returned book copy %s and added to rack: %d%n", copyId, r.getRackNo());
    }

    public void printBorrowed(String userId) {
        User u = users.get(userId);
        if (u != null) {
            u.getBorrowed().forEach(bc ->
                    System.out.printf("Book Copy: %s %s%n", bc.getCopyId(), bc.getDueDate()));
        }
    }

    public void search(String attr, String value) {
        List<BookCopy> list = new ArrayList<>(copies.values());
        switch (attr) {
            case "book_id" -> list.removeIf(bc -> !bc.getBook().getBookId().equals(value));
            case "author_id" -> list.removeIf(bc -> !bc.getBook().getAuthors().contains(value));
            case "publisher_id" -> list.removeIf(bc -> !bc.getBook().getPublishers().contains(value));
        }
        list.sort(Comparator.comparingInt(BookCopy::getRackNo));

        for (BookCopy bc : list) {
            System.out.printf("Book Copy: %s %s %s %s %s %d",
                    bc.getCopyId(),
                    bc.getBook().getBookId(),
                    bc.getBook().getTitle(),
                    String.join(",", bc.getBook().getAuthors()),
                    String.join(",", bc.getBook().getPublishers()),
                    bc.getRackNo());
            if (bc.isBorrowed()) {
                System.out.printf(" %s %s", bc.getBorrowedBy(), bc.getDueDate());
            }
            System.out.println();
        }
    }

    private Rack findFreeRack() {
        return racks.stream().filter(Rack::isFree).findFirst().orElse(null);
    }
}
