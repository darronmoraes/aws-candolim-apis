package in.nineteen96.aws_candolim.util;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;

public class TicketUtil {

    public static Ticket getTicketFromRequest(CreateTicketRequestPayload request) {
        Ticket ticket = new Ticket();

        ticket.setAmount(request.getAmount());
        ticket.setPassenger(request.getPassengers());
        ticket.setPaymentMode(request.getPaymentMode());
        ticket.setGstNumber(request.getGstNumber());
        ticket.setDeleted(false);

        return ticket;
    }
}
