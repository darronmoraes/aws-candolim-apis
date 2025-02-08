package in.nineteen96.aws_candolim.dto;

import in.nineteen96.aws_candolim.util.enums.PaymentMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDTO {

    private String id;

    private String serialNumber;

    private Double amount;

    private Integer passenger;

    private PaymentMode paymentMode;

    private String gstNumber;

}
