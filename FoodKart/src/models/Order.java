package models;

public class Order {
    private final User user;
    private final Restaurant restaurant;
    private final int quantity;
    private final int totalCost;

    public Order(User user, Restaurant restaurant, int quantity) {
        this.user = user;
        this.restaurant = restaurant;
        this.quantity = quantity;
        this.totalCost = restaurant.getFoodItem().getPrice() * quantity;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
