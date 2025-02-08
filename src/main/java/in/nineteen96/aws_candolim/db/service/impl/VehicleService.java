package in.nineteen96.aws_candolim.db.service.impl;

import in.nineteen96.aws_candolim.db.entity.Vehicle;
import in.nineteen96.aws_candolim.db.repo.VehicleRepo;
import in.nineteen96.aws_candolim.db.service.IBasicService;
import in.nineteen96.aws_candolim.exception.VehicleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VehicleService implements IBasicService<Vehicle> {

    private final VehicleRepo repo;

    public VehicleService(VehicleRepo repo) {
        this.repo = repo;
    }

    @Override
    public Vehicle save(Vehicle entity) {
        log.debug("saving vehicle");
        return repo.save(entity);
    }

    @Override
    public Vehicle findById(String id) {
        log.debug("finding vehicle");
        return findOptionalById(id)
                .orElseThrow(() -> new VehicleNotFoundException(String.format("vehicle %s not found", id)));
    }

    @Override
    public Optional<Vehicle> findOptionalById(String id) {
        log.debug("finding optional vehicle");
        return repo.findById(id);
    }

}
