package cz.utb.webservices.persistence.repository;

import cz.utb.webservices.persistence.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

Optional<Client> findByToken(String token);
}
