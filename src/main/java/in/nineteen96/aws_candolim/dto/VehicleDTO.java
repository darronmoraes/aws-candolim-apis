package in.nineteen96.aws_candolim.dto;

import in.nineteen96.aws_candolim.util.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDTO {

    private String id;

    private String numberSuffix;

    private VehicleType type;

    private String name;

}
