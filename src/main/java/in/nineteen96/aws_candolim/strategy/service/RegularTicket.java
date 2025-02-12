package in.nineteen96.aws_candolim.strategy.service;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.db.entity.Vehicle;
import in.nineteen96.aws_candolim.db.service.impl.TicketService;
import in.nineteen96.aws_candolim.db.service.impl.VehicleService;
import in.nineteen96.aws_candolim.dto.TicketDTO;
import in.nineteen96.aws_candolim.dto.VehicleDTO;
import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;
import in.nineteen96.aws_candolim.dto.response.CreateTicketResponse;
import in.nineteen96.aws_candolim.strategy.TicketCreationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static in.nineteen96.aws_candolim.util.TicketUtil.getTicketDTO;
import static in.nineteen96.aws_candolim.util.TicketUtil.getTicketFromRequest;
import static in.nineteen96.aws_candolim.util.VehicleUtil.getVehicleDTO;
import static in.nineteen96.aws_candolim.util.VehicleUtil.getVehicleFromRequest;

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
        ticket.setSerialNumber(ticketService.getSerialNumber());

        ticketService.save(ticket);

        log.info("getting vehicle payload");
        Vehicle vehicle = getVehicleFromRequest(request);
        vehicle.setTicket(ticket);
        vehicleService.save(vehicle);

        TicketDTO ticketDTO = getTicketDTO(ticket);

        VehicleDTO vehicleDTO = getVehicleDTO(vehicle);

        CreateTicketResponse response = new CreateTicketResponse();

        response.setMessage("commissioned ticket booking success");
        response.setStatus(HttpStatus.CREATED);
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setTicket(ticketDTO);
        response.setVehicle(vehicleDTO);

        log.info("completed commissioned ticket");
        return response;
    }

}
