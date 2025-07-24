package factory;

import constants.VehicleType;
import models.Vehicle;

public class VehicleFactory {
    public static Vehicle create(String type, String regNo, String color) {
        return new Vehicle(VehicleType.valueOf(type.toUpperCase()), regNo, color);
    }
}
