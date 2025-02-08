package in.nineteen96.aws_candolim.strategy.service;

import in.nineteen96.aws_candolim.db.entity.Commission;
import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.db.entity.Vehicle;
import in.nineteen96.aws_candolim.db.service.impl.CommissionService;
import in.nineteen96.aws_candolim.db.service.impl.TicketService;
import in.nineteen96.aws_candolim.db.service.impl.VehicleService;
import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;
import in.nineteen96.aws_candolim.dto.response.CreateTicketResponse;
import in.nineteen96.aws_candolim.strategy.TicketCreationStrategy;
import in.nineteen96.aws_candolim.util.Constants;
import in.nineteen96.aws_candolim.util.enums.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CommissionedTicket implements TicketCreationStrategy {

    private final TicketService ticketService;

    private final VehicleService vehicleService;

    private final CommissionService commissionService;

    public CommissionedTicket(TicketService ticketService, VehicleService vehicleService, CommissionService commissionService) {
        this.ticketService = ticketService;
        this.vehicleService = vehicleService;
        this.commissionService = commissionService;
    }

    @Override
    public BasicResponseOutput createTicket(CreateTicketRequestPayload request) {
        log.info("creating commissioned ticket");

        log.info("getting ticket payload");
        Ticket ticket = getTicketFromRequest(request);

        ticketService.save(ticket);

        log.info("getting vehicle payload");
        Vehicle vehicle = getVehicleFromRequest(request);
        vehicle.setTicket(ticket);
        vehicleService.save(vehicle);

        log.info("generating commission");
        Commission commission = getCommission(request.getPassengers());
        commission.setVehicle(vehicle);
        commissionService.save(commission);

        String responseMessage = String.format("ticket-id %s\nvehicle-id %s\ncommission-id %s", ticket.getId(), vehicle.getId(), commission.getId());
        log.info(responseMessage);

        log.info("completed commissioned ticket");
        return CreateTicketResponse.builder()
                .message(responseMessage)
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
        vehicle.setName(request.getVehicleName());

        return vehicle;
    }

    public Commission getCommission(int passengersCount) {
        Commission commission = new Commission();

        Double commissionAmount = Constants.COMMISSION_AMOUNT * passengersCount;
        commission.setAmount(commissionAmount);
        commission.setStatus(PaymentStatus.Pending);

        return commission;
    }

}
