package in.nineteen96.aws_candolim.dto.response;

import in.nineteen96.aws_candolim.dto.CommissionDTO;
import in.nineteen96.aws_candolim.dto.TicketDTO;
import in.nineteen96.aws_candolim.dto.VehicleDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketResponse extends BasicResponseOutput {

    private TicketDTO ticket;

    private VehicleDTO vehicle;

    private CommissionDTO commission;

}
