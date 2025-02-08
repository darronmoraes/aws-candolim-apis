package in.nineteen96.aws_candolim.dto.request;

import in.nineteen96.aws_candolim.util.enums.PaymentMode;
import in.nineteen96.aws_candolim.util.enums.VehicleType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequestPayload {

    private int passengers;

    private double amount;

    private PaymentMode paymentMode;

    private String gstNumber;

    private String vehicleName;

    private String vehicleNumberSuffix;

    private VehicleType vehicleType;

    private boolean commissioned;

}
