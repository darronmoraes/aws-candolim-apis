package in.nineteen96.aws_candolim.controller;

import in.nineteen96.aws_candolim.dto.request.CreateTicketRequestPayload;
import in.nineteen96.aws_candolim.dto.response.BasicResponseOutput;
import in.nineteen96.aws_candolim.service.CreateTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/tickets")
@RestController
@Slf4j
public class TicketController {

    @Autowired
    private CreateTicketService createTicket;

    @PostMapping
    public ResponseEntity<BasicResponseOutput> createTicket(@RequestBody CreateTicketRequestPayload request) {
        return new ResponseEntity<>(createTicket.invoke(request), HttpStatus.CREATED);
    }
    
}
