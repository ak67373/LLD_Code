package models;

public class User {
    private final String name;
    private final String gender;
    private final String phoneNumber;
    private String pincode;

    public User(String name, String gender, String phoneNumber, String pincode) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
