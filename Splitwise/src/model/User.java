package model;

public class User {
    private String id;              // Unique ID for the user
    private String name;            // User's name
    private String email;           // User's email
    private String mobile;          // User's mobile number

    public User(String id, String name, String email, String mobile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }
}
