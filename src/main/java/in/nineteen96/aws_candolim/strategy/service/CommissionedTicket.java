package in.nineteen96.aws_candolim.strategy.service;

import in.nineteen96.aws_candolim.db.entity.Commission;
import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.db.entity.Vehicle;
import in.nineteen96.aws_candolim.db.service.impl.CommissionService;
import in.nineteen96.aws_candolim.db.service.impl.TicketService;
import in.nineteen96.aws_candolim.db.service.impl.VehicleService;
import in.nineteen96.aws_candolim.dto.CommissionDTO;
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

import static in.nineteen96.aws_candolim.util.CommissionUtil.getCommission;
import static in.nineteen96.aws_candolim.util.CommissionUtil.getCommissionDTO;
import static in.nineteen96.aws_candolim.util.TicketUtil.getTicketDTO;
import static in.nineteen96.aws_candolim.util.TicketUtil.getTicketFromRequest;
import static in.nineteen96.aws_candolim.util.VehicleUtil.getVehicleDTO;
import static in.nineteen96.aws_candolim.util.VehicleUtil.getVehicleFromRequest;

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
        ticket.setSerialNumber(ticketService.getSerialNumber());
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

        TicketDTO ticketDTO = getTicketDTO(ticket);

        VehicleDTO vehicleDTO = getVehicleDTO(vehicle);

        CommissionDTO commissionDTO = getCommissionDTO(commission);

        CreateTicketResponse response = new CreateTicketResponse();
        response.setMessage("commissioned ticket booking success");
        response.setStatus(HttpStatus.CREATED);
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setTicket(ticketDTO);
        response.setVehicle(vehicleDTO);
        response.setCommission(commissionDTO);

        log.info("completed commissioned ticket");
        return response;
    }

}
