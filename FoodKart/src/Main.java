import managers.OrderManager;
import managers.RestaurantManager;
import managers.UserManager;

import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        UserManager userManager = UserManager.getInstance();
        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        OrderManager orderManager = OrderManager.getInstance(restaurantManager);

        // Register users
        userManager.registerUser("Pralove", "M", "phoneNumber-1", "HSR");
        userManager.registerUser("Nitesh", "M", "phoneNumber-2", "BTM");
        userManager.registerUser("Vatsal", "M", "phoneNumber-3", "BTM");

        // Login and register restaurants
        userManager.loginUser("phoneNumber-1");
        restaurantManager.registerRestaurant("Food Court-1", Arrays.asList("BTM", "HSR"), "NI Thali", 100, 5);
        restaurantManager.registerRestaurant("Food Court-2", Arrays.asList("BTM"), "Burger", 120, 3);

        userManager.loginUser("phoneNumber-2");
        restaurantManager.registerRestaurant("Food Court-3", Arrays.asList("HSR"), "SI Thali", 150, 1);

        // Show restaurants and place orders
        userManager.loginUser("phoneNumber-3");
        restaurantManager.showRestaurantsBy("price");
        orderManager.placeOrder("Food Court-1", 2);
        orderManager.placeOrder("Food Court-2", 7); // Should fail due to insufficient quantity

        restaurantManager.rateRestaurant("Food Court-2", 3, "Good Food");
        restaurantManager.rateRestaurant("Food Court-1", 5, "Nice Food");

        restaurantManager.showRestaurantsBy("rating");

        // Update restaurant info
        userManager.loginUser("phoneNumber-1");
        restaurantManager.updateQuantity("Food Court-2", 5);
        restaurantManager.updateLocation("Food Court-2", Arrays.asList("BTM", "HSR"));
    }
}