package in.nineteen96.aws_candolim.db.repo;

import in.nineteen96.aws_candolim.db.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepo extends JpaRepository<Ticket, String>, JpaSpecificationExecutor<Ticket> {

    @Query(value = "SELECT get_next_serial_number()", nativeQuery = true)
    String getNextSerialNumber();
}
