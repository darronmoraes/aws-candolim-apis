package in.nineteen96.aws_candolim.db.repo;

import in.nineteen96.aws_candolim.db.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepo extends JpaRepository<Commission, String> {
}
