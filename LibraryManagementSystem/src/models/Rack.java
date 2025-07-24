package models;

public class Rack {
    private final int rackNo;
    private BookCopy copy;

    public Rack(int rackNo) {
        this.rackNo = rackNo;
    }

    public boolean isFree() {
        return copy == null;
    }

    public void assign(BookCopy bc) {
        copy = bc;
        bc.setRackNo(rackNo);
    }

    public BookCopy remove() {
        BookCopy tmp = copy;
        copy = null;
        return tmp;
    }

    public BookCopy getCopy() {
        return copy;
    }

    public int getRackNo() {
        return rackNo;
    }
}
