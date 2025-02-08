package in.nineteen96.aws_candolim.controller;

import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.request.GetPaginatedRequest;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;
import in.nineteen96.aws_candolim.service.CreateTicketService;
import in.nineteen96.aws_candolim.service.GetTicketsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/tickets")
@RestController
@Slf4j
public class TicketController {

    @Autowired
    private CreateTicketService createTicket;

    @Autowired
    private GetTicketsService getTickets;

    @PostMapping
    public ResponseEntity<BasicResponseOutput> createTicket(@RequestBody CreateTicketRequestPayload request) {
        return new ResponseEntity<>(createTicket.invoke(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BasicResponseOutput> getTickets(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "createdAt", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String direction) {

        GetPaginatedRequest request = new GetPaginatedRequest();
        request.setPage(page);
        request.setSize(size);
        request.setKeyword(keyword);
        request.setSortBy(sortBy);
        request.setDirection(direction);
        return new ResponseEntity<>(getTickets.invoke(request), HttpStatus.OK);
    }
    
}
