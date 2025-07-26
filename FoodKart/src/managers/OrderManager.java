package managers;

import service.OrderService;

public class OrderManager {
    private static OrderManager instance;
    private final OrderService orderService;

    private OrderManager(RestaurantManager restaurantManager) {
        this.orderService = new OrderService(restaurantManager.getRestaurantService());
    }

    public static OrderManager getInstance(RestaurantManager restaurantManager) {
        if (instance == null) instance = new OrderManager(restaurantManager);
        return instance;
    }

    public void placeOrder(String restaurantName, int quantity) {
        orderService.placeOrder(restaurantName, quantity);
    }

    public void showOrderHistory() {
        orderService.showOrderHistory();
    }
}
