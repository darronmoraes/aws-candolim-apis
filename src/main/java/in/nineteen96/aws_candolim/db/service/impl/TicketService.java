package in.nineteen96.aws_candolim.db.service.impl;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import in.nineteen96.aws_candolim.db.repo.TicketRepo;
import in.nineteen96.aws_candolim.db.service.IBasicService;
import in.nineteen96.aws_candolim.db.service.IExtendedBasicService;
import in.nineteen96.aws_candolim.db.service.IFilterableService;
import in.nineteen96.aws_candolim.db.service.ISerialNumberService;
import in.nineteen96.aws_candolim.exception.TicketNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TicketService implements IBasicService<Ticket>, IExtendedBasicService<Ticket>, ISerialNumberService, IFilterableService<Ticket> {

    private final TicketRepo repo;

    public TicketService(TicketRepo repo) {
        this.repo = repo;
    }

    @Override
    public Ticket save(Ticket entity) {
        log.debug("saving ticket");
        return repo.save(entity);
    }

    @Override
    public Ticket findById(String id) {
        log.debug("finding ticket");
        return findOptionalById(id)
                .orElseThrow(() -> new TicketNotFoundException(String.format("ticket %s not found", id)));
    }

    @Override
    public Optional<Ticket> findOptionalById(String id) {
        log.debug("finding optional ticket");
        return repo.findById(id);
    }

    @Override
    public Ticket update(Ticket entity) {
        log.debug("updating existing ticket");
        return save(entity);
    }

    @Override
    public String getSerialNumber() {
        log.debug("getting serial number for ticket booking");
        return repo.getNextSerialNumber();
    }

    @Override
    public Page<Ticket> paginateAndFilterByParams(Specification<Ticket> specs, Pageable pageable) {
        log.info("finding tickets paginated and filtered");
        return repo.findAll(specs, pageable);
    }
}
