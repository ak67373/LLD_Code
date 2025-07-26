package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Restaurant {
    private final String name;
    private final Set<String> serviceablePincodes;
    private final FoodItem foodItem;
    private final List<Integer> ratings = new ArrayList<>();
    private final List<String> comments = new ArrayList<>();

    public Restaurant(String name, List<String> pincodes, String foodName, int price, int quantity) {
        this.name = name;
        this.serviceablePincodes = new HashSet<>(pincodes);
        this.foodItem = new FoodItem(foodName, price, quantity);
    }

    public String getName() {
        return name;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public Set<String> getServiceablePincodes() {
        return serviceablePincodes;
    }

    public void addRating(int rating, String comment) {
        ratings.add(rating);
        if (comment != null && !comment.isEmpty()) {
            comments.add(comment);
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        int sum = 0;
        for (int rate : ratings) sum += rate;
        return (double) sum / ratings.size();
    }

    public void updateLocation(List<String> newPincodes) {
        serviceablePincodes.clear();
        serviceablePincodes.addAll(newPincodes);
    }

    public boolean isServiceable(String pincode) {
        return serviceablePincodes.contains(pincode);
    }
}
