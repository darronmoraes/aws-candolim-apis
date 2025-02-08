package in.nineteen96.aws_candolim.util;

import in.nineteen96.aws_candolim.db.entity.Commission;
import in.nineteen96.aws_candolim.util.enums.PaymentStatus;

public class CommissionUtil {

    public static Commission getCommission(int passengersCount) {
        Commission commission = new Commission();

        Double commissionAmount = Constants.COMMISSION_AMOUNT * passengersCount;
        commission.setAmount(commissionAmount);
        commission.setStatus(PaymentStatus.Pending);

        return commission;
    }

}
