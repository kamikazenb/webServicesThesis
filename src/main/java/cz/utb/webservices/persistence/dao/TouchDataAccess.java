package cz.utb.webservices.persistence.dao;

import cz.utb.webservices.persistence.model.Client;
import cz.utb.webservices.persistence.model.Touch;
import cz.utb.webservices.persistence.repository.ClientRepository;
import cz.utb.webservices.persistence.repository.TouchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("TouchDataAccess")
public class TouchDataAccess implements TouchDAO {
    private static final Logger log = LoggerFactory.getLogger(TouchDataAccess.class);
    private final TouchRepository touchRepository;
    private final ClientRepository clientRepository;

    public TouchDataAccess(TouchRepository touchRepository, ClientRepository clientRepository) {
        this.touchRepository = touchRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public int insertTouch(List<Touch> touches, Client client) {
        try {
            Optional<Client> optionalClient = clientRepository.findByToken(client.getToken());
            if (optionalClient.isPresent()) {
                Client b = optionalClient.get();
                for (Touch touch : touches) {
                    touch.setClient(b);
                    touchRepository.save(touch);
                }

                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public int insertClient(Client client) {
        if (touchRepository.count() > 1200) {
            touchRepository.deleteAll();
        }
        log.info("touches count: " + touchRepository.count());

        try {
            clientRepository.save(client);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
