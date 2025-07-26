package service;

import models.Restaurant;
import models.User;
import utils.LoggedInUserContext;

import java.util.*;

public class RestaurantService {

    private final Map<String, Restaurant> restaurantMap = new HashMap<>();

    public void registerRestaurant(String name, List<String> pincodes, String foodName, int price, int quantity) {
        if (!LoggedInUserContext.isLoggedIn()) {
            System.out.println("No user logged in.");
            return;
        }
        if (restaurantMap.containsKey(name)) {
            System.out.println("Restaurant already exists.");
            return;
        }

        Restaurant restaurant = new Restaurant(name, pincodes, foodName, price, quantity);
        restaurantMap.put(name, restaurant);
        System.out.println("Restaurant registered: " + name);
    }

    public void updateQuantity(String restaurantName, int quantity) {
        Restaurant restaurant = restaurantMap.get(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }

        restaurant.getFoodItem().increaseQuantity(quantity);
        System.out.println("Quantity updated for " + restaurantName);
    }

    public void updateLocation(String restaurantName, List<String> pincodes) {
        Restaurant restaurant = restaurantMap.get(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }

        restaurant.updateLocation(pincodes);
        System.out.println("Location updated for " + restaurantName);
    }

    public void rateRestaurant(String restaurantName, int rating, String comment) {
        Restaurant restaurant = restaurantMap.get(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }

        restaurant.addRating(rating, comment);
        System.out.println("Rated restaurant " + restaurantName + " with " + rating + " stars.");
    }

    public void showRestaurantsBy(String sortBy) {
        if (!LoggedInUserContext.isLoggedIn()) {
            System.out.println("No user logged in.");
            return;
        }

        User user = LoggedInUserContext.getCurrentUser();
        String userPincode = user.getPincode();

        List<Restaurant> available = new ArrayList<>();
        for (Restaurant r : restaurantMap.values()) {
            if (r.isServiceable(userPincode) && r.getFoodItem().getQuantity() > 0) {
                available.add(r);
            }
        }

        Comparator<Restaurant> comparator;
        if ("price".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparingInt(r -> r.getFoodItem().getPrice());
        } else if ("rating".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparingDouble(Restaurant::getAverageRating).reversed();
        } else {
            System.out.println("Invalid sort key.");
            return;
        }

        available.sort(comparator);

        for (Restaurant r : available) {
            System.out.println(r.getName() + " | " + r.getFoodItem().getName() + " | Rs." + r.getFoodItem().getPrice()
                    + " | Rating: " + String.format("%.1f", r.getAverageRating())
                    + " | Quantity: " + r.getFoodItem().getQuantity());
        }
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantMap.get(name);
    }
}
