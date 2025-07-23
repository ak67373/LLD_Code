package model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;              // Unique ID for the user
    private String name;            // User's name
    private String email;           // User's email
    private String mobile;          // User's mobile number
    private Set<String> friends = new HashSet<>();

    public User(String id, String name, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void addFriend(User friend) {
        friends.add(friend.getId());
    }

    public Set<String> getFriends() {
        return friends;
    }
}
