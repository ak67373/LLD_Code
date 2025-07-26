package models;

public class FoodItem {
    private final String name;
    private final int price;
    private int quantity;

    public FoodItem(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int delta) {
        this.quantity += delta;
    }

    public boolean reduceQuantity(int delta) {
        if (quantity >= delta) {
            this.quantity -= delta;
            return true;
        }
        return false;
    }
}
