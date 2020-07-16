package cz.utb.webservices.dao;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;
import cz.utb.webservices.repository.ClientRepository;
import cz.utb.webservices.repository.TouchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
    public int insertTouch(Touch touch, Client client) {

      Optional<Client> optionalClient = clientRepository.findByToken(client.getToken());
      try {
          if (optionalClient.isPresent()) {
              Client b = optionalClient.get();
              touch.setClient(b);
              touchRepository.save(touch);
              return 1;
          }else {
              return 0;
          }
      }catch (Exception e){
          return 0;
      }

    }

    @Override
    public int insertClient(Client client) {
        try {
            clientRepository.save(client);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
