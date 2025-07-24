package commands;

import constants.VehicleType;
import factory.VehicleFactory;
import managers.ParkingLotManager;
import models.Ticket;
import models.Vehicle;

import java.util.Optional;

public class CommandExecutor {
    private final ParkingLotManager mgr = ParkingLotManager.getInstance();

    public void execute(String[] parts) {
        switch (parts[0]) {
            case "create_parking_lot" -> mgr.createLot(parts[1],
                    Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            case "park_vehicle" -> {
                Vehicle v = VehicleFactory.create(parts[1], parts[2], parts[3]);
                Optional<Ticket> t = mgr.parkVehicle(v);
                System.out.println(t.map(ticket -> "Parked vehicle. Ticket ID: " + ticket.getId())
                        .orElse("Parking Lot Full"));
            }
            case "unpark_vehicle" -> {
                Optional<Vehicle> v = mgr.unparkVehicle(parts[1]);
                System.out.println(v.map(vehicle -> String.format(
                                "Unparked vehicle with Registration Number: %s and Color: %s",
                                vehicle.getRegNo(), vehicle.getColor()))
                        .orElse("Invalid Ticket"));
            }
            case "display" -> {
                VehicleType vt = VehicleType.valueOf(parts[2]);
                mgr.getFloors().forEach(f -> {
                    switch (parts[1]) {
                        case "free_count" -> System.out.printf(
                                "No. of free slots for %s on Floor %d: %d%n",
                                vt, f.getFloorNum(), f.freeCount(vt));
                        case "free_slots" -> System.out.printf(
                                "Free slots for %s on Floor %d: %s%n",
                                vt, f.getFloorNum(), f.freeSlots(vt) == "" ? "0" : f.freeSlots(vt));
                        case "occupied_slots" -> System.out.printf(
                                "Occupied slots for %s on Floor %d: %s%n",
                                vt, f.getFloorNum(), f.occupiedSlots(vt) == "" ? "0" : f.occupiedSlots(vt));
                    }
                });
            }
            default -> {
            }
        }
    }
}
