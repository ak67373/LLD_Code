package managers;

import service.RestaurantService;

import java.util.List;

public class RestaurantManager {
    private static RestaurantManager instance;
    private final RestaurantService restaurantService;

    private RestaurantManager() {
        this.restaurantService = new RestaurantService();
    }

    public static RestaurantManager getInstance() {
        if (instance == null) instance = new RestaurantManager();
        return instance;
    }

    public void registerRestaurant(String name, List<String> pincodes, String foodName, int price, int quantity) {
        restaurantService.registerRestaurant(name, pincodes, foodName, price, quantity);
    }

    public void updateQuantity(String restaurantName, int quantity) {
        restaurantService.updateQuantity(restaurantName, quantity);
    }

    public void updateLocation(String restaurantName, List<String> pincodes) {
        restaurantService.updateLocation(restaurantName, pincodes);
    }

    public void rateRestaurant(String restaurantName, int rating, String comment) {
        restaurantService.rateRestaurant(restaurantName, rating, comment);
    }

    public void showRestaurantsBy(String sortBy) {
        restaurantService.showRestaurantsBy(sortBy);
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }
}
