package in.nineteen96.aws_candolim.util;

import in.nineteen96.aws_candolim.db.entity.Vehicle;
import in.nineteen96.aws_candolim.dto.VehicleDTO;
import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;

public class VehicleUtil {

    public static Vehicle getVehicleFromRequest(CreateTicketRequestPayload request) {
        Vehicle vehicle = new Vehicle();

        vehicle.setType(request.getVehicleType());
        vehicle.setCommissioned(request.isCommissioned());
        vehicle.setNumberSuffix(request.getVehicleNumberSuffix());
        vehicle.setName(request.getVehicleName());

        return vehicle;
    }

    public static VehicleDTO getVehicleDTO(Vehicle vehicle) {
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .type(vehicle.getType())
                .name(vehicle.getName())
                .numberSuffix(vehicle.getNumberSuffix())
                .build();
    }
}
