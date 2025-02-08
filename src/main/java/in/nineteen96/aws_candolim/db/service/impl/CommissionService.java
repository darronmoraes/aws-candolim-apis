package in.nineteen96.aws_candolim.db.service.impl;

import in.nineteen96.aws_candolim.db.entity.Commission;
import in.nineteen96.aws_candolim.db.repo.CommissionRepo;
import in.nineteen96.aws_candolim.db.service.IBasicService;
import in.nineteen96.aws_candolim.db.service.IExtendedBasicService;
import in.nineteen96.aws_candolim.exception.CommissionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CommissionService implements IBasicService<Commission>, IExtendedBasicService<Commission> {

    private final CommissionRepo repo;

    public CommissionService(CommissionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Commission save(Commission entity) {
        log.debug("saving commission");
        return repo.save(entity);
    }

    @Override
    public Commission findById(String id) {
        log.debug("finding commission");
        return findOptionalById(id)
                .orElseThrow(() -> new CommissionNotFoundException(String.format("commission %s not found", id)));
    }

    @Override
    public Optional<Commission> findOptionalById(String id) {
        log.debug("finding optional commission");
        return repo.findById(id);
    }

    @Override
    public Commission update(Commission entity) {
        log.debug("updating existing commission");
        return save(entity);
    }
}
