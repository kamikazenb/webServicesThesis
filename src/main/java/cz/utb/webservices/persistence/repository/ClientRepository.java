package cz.utb.webservices.repository;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

Optional<Client> findByToken(String token);
}
