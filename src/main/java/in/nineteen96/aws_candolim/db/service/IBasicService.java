package in.nineteen96.aws_candolim.db.service;

import java.util.Optional;

public interface IBasicService<T> {

    T save(T entity);

    T findById(String id);

    Optional<T> findOptionalById(String id);

}
