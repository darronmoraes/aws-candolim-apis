package in.nineteen96.aws_candolim.db.repo;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, String> {
}
