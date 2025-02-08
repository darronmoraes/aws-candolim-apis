package in.nineteen96.aws_candolim.util;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.dto.TicketDTO;
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

    public static TicketDTO getTicketDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .amount(ticket.getAmount())
                .gstNumber(ticket.getGstNumber())
                .passenger(ticket.getPassenger())
                .paymentMode(ticket.getPaymentMode())
                .serialNumber(ticket.getSerialNumber())
                .build();
    }
}
