package models;

public class Ticket {
    private final String id;
    private final Vehicle vehicle;

    public Ticket(String lotId, Slot slot, Vehicle vehicle) {
        this.id = String.format("%s_%d_%d", lotId, slot.getFloorNum(), slot.getSlotNum());
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
