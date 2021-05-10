package tr.com.app.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.app.scheduler.model.Presentation;

import java.util.stream.Stream;

@Repository
public interface PresentationRepository extends CrudRepository<Presentation, Long> {

    Stream<Presentation> findAllBy();

}
