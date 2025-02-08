package in.nineteen96.aws_candolim.dto;

import in.nineteen96.aws_candolim.util.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommissionDTO {

    private String id;

    private Double amount;

    private PaymentStatus status;

    private String contactName;

    private String contactNumber;

}
