package server.convert.autovehicle;

import lib.dto.autovehicle.VehicleDto;
import server.model.autovehicle.Vehicle;

public class VehicleConvetor {

    private VehicleConvetor() {
    }

    public static VehicleDto convert(Vehicle vehicle){
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getVehicleName(),
                vehicle.getSerialNumber()
        );
    }

    public static Vehicle convert(VehicleDto vehicleDto){
        return new Vehicle(
                vehicleDto.getVehicleName(),
                vehicleDto.getSerialNumber()
        );


    }
}
