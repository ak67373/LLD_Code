package models;

import constants.VehicleType;

public class Slot {
    private final int floorNum, slotNum;
    private final VehicleType type;
    private Vehicle vehicle;

    public Slot(int floorNum, int slotNum, VehicleType type) {
        this.floorNum = floorNum;
        this.slotNum = slotNum;
        this.type = type;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public boolean canFit(VehicleType vt) {
        return type == vt && isFree();
    }

    public void park(Vehicle v) {
        vehicle = v;
    }

    public Vehicle unpark() {
        Vehicle v = vehicle;
        vehicle = null;
        return v;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public VehicleType getType() {
        return type;
    }
}
