package models;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class User {
    private final String userId;
    private final Set<BookCopy> borrowed = new TreeSet<>();

    public User(String userId) {
        this.userId = userId;
    }

    public boolean canBorrow() {
        return borrowed.size() < 5;
    }

    public void borrow(BookCopy bc) {
        borrowed.add(bc);
    }

    public void returned(BookCopy bc) {
        borrowed.remove(bc);
    }

    public Set<BookCopy> getBorrowed() {
        return borrowed;
    }
}