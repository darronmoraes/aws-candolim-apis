package in.nineteen96.aws_candolim.strategy;

import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;

public interface TicketCreationStrategy {

    BasicResponseOutput createTicket(CreateTicketRequestPayload request);
}
