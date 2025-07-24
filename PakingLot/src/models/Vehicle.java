package models;

import constants.VehicleType;

public class Vehicle {
    private final VehicleType type;
    private final String regNo, color;

    public Vehicle(VehicleType type, String regNo, String color) {
        this.type = type; this.regNo = regNo; this.color = color;
    }
    public VehicleType getType() { return type; }
    public String getRegNo() { return regNo; }
    public String getColor() { return color; }
}

