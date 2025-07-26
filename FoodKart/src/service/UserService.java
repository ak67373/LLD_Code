package service;

import models.Restaurant;
import models.User;
import utils.LoggedInUserContext;

import java.util.*;

public class UserService {
    private final Map<String, User> userMap = new HashMap<>();

    public void registerUser(String name, String gender, String phoneNumber, String pincode) {
        if (userMap.containsKey(phoneNumber)) {
            System.out.println("User already exists with phone: " + phoneNumber);
            return;
        }
        User user = new User(name, gender, phoneNumber, pincode);
        userMap.put(phoneNumber, user);
        System.out.println("User registered: " + name);
    }

    public void loginUser(String phoneNumber) {
        User user = userMap.get(phoneNumber);
        if (user == null) {
            System.out.println("User not found with phone: " + phoneNumber);
            return;
        }
        LoggedInUserContext.setCurrentUser(user);
        System.out.println("User logged in: " + user.getName());
    }

    public User getUser(String phoneNumber) {
        return userMap.get(phoneNumber);
    }
}
