package models;

import constants.VehicleType;

import java.util.*;
import java.util.stream.Collectors;

public class Floor {
    private final int floorNum;
    private final List<Slot> slots = new ArrayList<>();

    public Floor(int floorNum, int slotsPerFloor) {
        this.floorNum = floorNum;
        for (int i = 1; i <= slotsPerFloor; i++) {
            VehicleType t = (i == 1) ? VehicleType.TRUCK :
                    (i <= 3) ? VehicleType.BIKE :
                            VehicleType.CAR;
            slots.add(new Slot(floorNum, i, t));
        }
    }

    public List<Slot> getSlotsByType(VehicleType vt) {
        return slots.stream()
                .filter(s -> s.getType() == vt)
                .collect(Collectors.toList());
    }

    public Optional<Slot> findFirstFree(VehicleType vt) {
        return getSlotsByType(vt).stream()
                .filter(Slot::isFree)
                .findFirst();
    }

    public int freeCount(VehicleType vt) {
        return (int) slots.stream()
                .filter(s -> s.canFit(vt))
                .count();
    }

    public String freeSlots(VehicleType vt) {
        return slots.stream()
                .filter(s -> s.canFit(vt))
                .map(s -> String.valueOf(s.getSlotNum()))
                .collect(Collectors.joining(","));
    }

    public String occupiedSlots(VehicleType vt) {
        return slots.stream()
                .filter(s -> s.getType() == vt && !s.isFree())
                .map(s -> String.valueOf(s.getSlotNum()))
                .collect(Collectors.joining(","));
    }

    public Slot getSlot(int slotNum) {
        if (slotNum < 1 || slotNum > slots.size()) return null;
        return slots.get(slotNum - 1);
    }

    public int getFloorNum() {
        return floorNum;
    }
}
