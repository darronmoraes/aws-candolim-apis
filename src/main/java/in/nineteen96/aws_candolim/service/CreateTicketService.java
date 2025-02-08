package in.nineteen96.aws_candolim.service;

import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;
import in.nineteen96.aws_candolim.strategy.service.CommissionedTicket;
import in.nineteen96.aws_candolim.strategy.service.RegularTicket;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class CreateTicketService {

    @Autowired
    private CommissionedTicket commissionedTicket;

    @Autowired
    private RegularTicket regularTicket;

    public BasicResponseOutput invoke(CreateTicketRequestPayload payload) {
        log.info("attempt to create ticket");

        boolean isCommissioned = payload.isCommissioned();

        if (isCommissioned) {
            return commissionedTicket.createTicket(payload);
        }

        return regularTicket.createTicket(payload);
    }
}
