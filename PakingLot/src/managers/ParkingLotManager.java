package managers;

import models.Floor;
import models.Slot;
import models.Ticket;
import models.Vehicle;
import strategies.SlotAllocationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLotManager {
    private static ParkingLotManager instance;
    private String lotId;
    private final List<Floor> floors = new ArrayList<>();
    private final SlotAllocationStrategy allocator = new SlotAllocationStrategy();

    private ParkingLotManager() {}

    public static synchronized ParkingLotManager getInstance() {
        if (instance == null) instance = new ParkingLotManager();
        return instance;
    }

    public void createLot(String id, int floorCount, int slotsPerFloor) {
        lotId = id;
        floors.clear();
        for (int i = 1; i <= floorCount; i++) floors.add(new Floor(i, slotsPerFloor));
        System.out.printf("Created parking lot with %d floors and %d slots per floor%n", floorCount, slotsPerFloor);
    }

    public Optional<Ticket> parkVehicle(Vehicle vehicle) {
        Optional<Slot> s = allocator.findSlot(floors, vehicle.getType());
        if (!s.isPresent()) return Optional.empty();
        Slot slot = s.get();
        slot.park(vehicle);
        return Optional.of(new Ticket(lotId, slot, vehicle));
    }

    public Optional<Vehicle> unparkVehicle(String ticketId) {
        String[] parts = ticketId.split("_");
        if (parts.length != 3 || !parts[0].equals(lotId)) return Optional.empty();
        int f = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        if (f < 1 || f > floors.size()) return Optional.empty();
        if(floors.get(f - 1).getSlot(s) == null) return Optional.empty();
        Slot slot = floors.get(f - 1).getSlot(s);
        if (slot.isFree()) return Optional.empty();
        return Optional.of(slot.unpark());
    }

    public List<Floor> getFloors() { return floors; }
}
