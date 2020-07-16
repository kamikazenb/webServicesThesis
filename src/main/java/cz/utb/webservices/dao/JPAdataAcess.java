package cz.utb.webservices.dao;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;
import cz.utb.webservices.repository.ClientRepository;
import cz.utb.webservices.repository.TouchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("JPAdataAcess")
public class JPAdataAcess implements TouchDAO {
    private static final Logger log = LoggerFactory.getLogger(JPAdataAcess.class);
    private final TouchRepository touchRepository;
    private final ClientRepository clientRepository;

    public JPAdataAcess(TouchRepository touchRepository, ClientRepository clientRepository) {
        this.touchRepository = touchRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public int insertTouch(Touch touch, Client client) {

      Optional<Client> optionalClient = clientRepository.findByToken(client.getToken());
            if (optionalClient.isPresent()) {
                Client b = optionalClient.get();
                log.info(b.toString());
               touch.setClient(b);
                touchRepository.save(touch);
                log.info(touch.toString());
                return 1;
            }else {
                return 0;
            }
    }
}
