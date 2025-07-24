package strategies;

import constants.VehicleType;
import models.Floor;
import models.Slot;

import java.util.List;
import java.util.Optional;

public class SlotAllocationStrategy {
    public Optional<Slot> findSlot(List<Floor> floors, VehicleType type) {
        for (Floor f : floors) {
            Optional<Slot> s = f.findFirstFree(type);
            if (s.isPresent()) return s;
        }
        return Optional.empty();
    }
}
