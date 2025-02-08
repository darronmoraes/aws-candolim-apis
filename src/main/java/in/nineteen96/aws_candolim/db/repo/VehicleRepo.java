package in.nineteen96.aws_candolim.db.repo;

import in.nineteen96.aws_candolim.db.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, String> {
}
