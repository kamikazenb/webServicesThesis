package cz.utb.webservices.persistence.repository;

import cz.utb.webservices.persistence.model.Touch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TouchRepository extends CrudRepository<Touch, Long> {
    List<Touch> findByTouchType(String touchType);
    Touch findById(long id);
    long count();

    @Override
    void deleteAll();
}
