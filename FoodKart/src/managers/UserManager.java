package managers;

import service.UserService;

public class UserManager {
    private static UserManager instance;
    private final UserService userService;

    private UserManager() {
        this.userService = new UserService();
    }

    public static UserManager getInstance() {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    public void registerUser(String name, String gender, String phoneNumber, String pincode) {
        userService.registerUser(name, gender, phoneNumber, pincode);
    }

    public void loginUser(String phoneNumber) {
        userService.loginUser(phoneNumber);
    }
}
