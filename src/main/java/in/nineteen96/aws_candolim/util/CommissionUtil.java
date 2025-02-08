package in.nineteen96.aws_candolim.util;

import in.nineteen96.aws_candolim.db.entity.Commission;
import in.nineteen96.aws_candolim.dto.CommissionDTO;
import in.nineteen96.aws_candolim.util.enums.PaymentStatus;

public class CommissionUtil {

    public static Commission getCommission(int passengersCount) {
        Commission commission = new Commission();

        Double commissionAmount = Constants.COMMISSION_AMOUNT * passengersCount;
        commission.setAmount(commissionAmount);
        commission.setStatus(PaymentStatus.Pending);

        return commission;
    }

    public static CommissionDTO getCommissionDTO(Commission commission) {
        return CommissionDTO.builder()
                .id(commission.getId())
                .status(commission.getStatus())
                .amount(commission.getAmount())
                .contactName(commission.getContactName())
                .contactNumber(commission.getContactNumber())
                .build();
    }

}
