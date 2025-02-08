package in.nineteen96.aws_candolim.strategy.service;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.db.entity.Vehicle;
import in.nineteen96.aws_candolim.db.service.impl.TicketService;
import in.nineteen96.aws_candolim.db.service.impl.VehicleService;
import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;
import in.nineteen96.aws_candolim.dto.response.CreateTicketResponse;
import in.nineteen96.aws_candolim.strategy.TicketCreationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class RegularTicket implements TicketCreationStrategy {

    private final TicketService ticketService;

    private final VehicleService vehicleService;

    public RegularTicket(TicketService ticketService, VehicleService vehicleService) {
        this.ticketService = ticketService;
        this.vehicleService = vehicleService;
    }

    @Override
    public BasicResponseOutput createTicket(CreateTicketRequestPayload request) {
        log.info("creating regular ticket");

        log.info("getting ticket payload");
        Ticket ticket = getTicketFromRequest(request);

        ticketService.save(ticket);

        log.info("getting vehicle payload");
        Vehicle vehicle = getVehicleFromRequest(request);
        vehicle.setTicket(ticket);
        vehicleService.save(vehicle);

        log.info("completed commissioned ticket");
        return CreateTicketResponse.builder()
                .message("commissioned ticket booking success")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
    }

    public Ticket getTicketFromRequest(CreateTicketRequestPayload request) {
        Ticket ticket = new Ticket();

        ticket.setSerialNumber(ticketService.getSerialNumber());
        ticket.setAmount(request.getAmount());
        ticket.setPassenger(request.getPassengers());
        ticket.setPaymentMode(request.getPaymentMode());
        ticket.setGstNumber(request.getGstNumber());
        ticket.setDeleted(false);

        return ticket;
    }

    public Vehicle getVehicleFromRequest(CreateTicketRequestPayload request) {
        Vehicle vehicle = new Vehicle();

        vehicle.setType(request.getVehicleType());
        vehicle.setCommissioned(request.isCommissioned());
        vehicle.setNumberSuffix(request.getVehicleNumberSuffix());

        return vehicle;
    }

}
