package in.nineteen96.aws_candolim.service;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.db.service.impl.TicketService;
import in.nineteen96.aws_candolim.db.specs.TicketSpecs;
import in.nineteen96.aws_candolim.dto.TicketDTO;
import in.nineteen96.aws_candolim.dto.request.GetPaginatedRequest;
import in.nineteen96.aws_candolim.dto.response.GetPaginatedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class GetTicketsService {

    @Autowired
    private TicketService ticketService;

    public GetPaginatedResponse<TicketDTO> invoke(GetPaginatedRequest request) {
        log.info("attempt to get paginated and criteria based tickets");

        Sort.Direction sortDirection = request.getDirection().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        int page = request.getPage() - 1;
        Pageable pageable = PageRequest.of(page, request.getSize(), Sort.by(sortDirection, request.getSortBy()));

        Specification<Ticket> ticketSpecifications = TicketSpecs.getTicketSpecs(request);

        log.info("db query fired");
        Page<Ticket> ticketsPage = ticketService.paginateAndFilterByParams(ticketSpecifications, pageable);

        log.info("mapping tickets");
        List<TicketDTO> ticketDTOs = ticketsPage.stream()
                .map(ticket -> {
            TicketDTO dto = new TicketDTO();
            dto.setId(ticket.getId());
            dto.setAmount(ticket.getAmount());
            dto.setSerialNumber(ticket.getSerialNumber());
            dto.setPaymentMode(ticket.getPaymentMode());
            dto.setPassenger(ticket.getPassenger());
            dto.setGstNumber(ticket.getGstNumber());

            return dto;
        }).toList();

        log.info("setting response");
        GetPaginatedResponse<TicketDTO> response = new GetPaginatedResponse<>();
        response.setResults(ticketDTOs);
        response.setCurrentPage(request.getPage());
        response.setStatus(HttpStatus.OK);
        response.setTotalPages(ticketsPage.getTotalPages());
        response.setTotalElements(ticketsPage.getTotalElements());
        response.setMessage("fetched paginated and filtered tickets");
        response.setSuccess(true);
        response.setTimestamp(LocalDateTime.now());

        log.info("completed fetching tickets");
        return response;
    }

}
