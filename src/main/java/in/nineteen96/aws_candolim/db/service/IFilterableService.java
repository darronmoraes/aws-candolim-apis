package in.nineteen96.aws_candolim.db.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IFilterableService<T> {

    Page<T> paginateAndFilterByParams(Specification<T> specs, Pageable pageable);
}
