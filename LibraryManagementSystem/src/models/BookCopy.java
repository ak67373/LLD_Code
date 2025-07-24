package models;

public class BookCopy implements Comparable<BookCopy> {
    private final String copyId;
    private final Book book;
    private int rackNo = -1;
    private String borrowedBy, dueDate;

    public BookCopy(String copyId, Book book) {
        this.copyId = copyId;
        this.book = book;
    }

    public String getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public int getRackNo() {
        return rackNo;
    }

    public void setRackNo(int r) {
        rackNo = r;
    }

    public boolean isBorrowed() {
        return borrowedBy != null;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void borrow(String userId, String dueDate) {
        this.borrowedBy = userId;
        this.dueDate = dueDate;
        this.rackNo = -1;
    }

    public void returned(int rackNo) {
        this.borrowedBy = null;
        this.dueDate = null;
        this.rackNo = rackNo;
    }

    @Override
    public int compareTo(BookCopy other) {
        return this.copyId.compareTo(other.copyId);
    }
}