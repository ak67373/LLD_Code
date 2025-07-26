package service;

import models.FoodItem;
import models.Order;
import models.Restaurant;
import models.User;
import utils.LoggedInUserContext;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Order> orderHistory = new ArrayList<>();
    private final RestaurantService restaurantService;

    public OrderService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void placeOrder(String restaurantName, int quantity) {
        if (!LoggedInUserContext.isLoggedIn()) {
            System.out.println("No user logged in.");
            return;
        }

        Restaurant restaurant = restaurantService.getRestaurantByName(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }

        FoodItem food = restaurant.getFoodItem();
        if (food.getQuantity() < quantity) {
            System.out.println("Not enough quantity available.");
            return;
        }

        food.reduceQuantity(quantity);
        User user = LoggedInUserContext.getCurrentUser();
        int totalCost = food.getPrice() * quantity;

        Order order = new Order(user, restaurant, quantity);
        orderHistory.add(order);

        System.out.println("Order placed: " + quantity + " x " + food.getName()
                + " from " + restaurant.getName() + ". Total: Rs." + totalCost);
    }

    public void showOrderHistory() {
        if (!LoggedInUserContext.isLoggedIn()) {
            System.out.println("No user logged in.");
            return;
        }

        User user = LoggedInUserContext.getCurrentUser();
        for (Order order : orderHistory) {
            if (order.getUser().equals(user)) {
                System.out.println("Restaurant: " + order.getRestaurant().getName()
                        + ", Item: " + order.getRestaurant().getFoodItem().getName()
                        + ", Qty: " + order.getQuantity()
                        + ", Total: Rs." + order.getTotalCost());
            }
        }
    }
}
