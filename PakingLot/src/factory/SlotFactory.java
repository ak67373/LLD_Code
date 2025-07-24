package factory;

import constants.VehicleType;
import models.Slot;

public class SlotFactory {
    public static Slot create(int floorNum, int slotNum) {
        VehicleType type = (slotNum == 1) ? VehicleType.TRUCK
                : (slotNum <= 3) ? VehicleType.BIKE
                : VehicleType.CAR;
        return new Slot(floorNum, slotNum, type);
    }
}
