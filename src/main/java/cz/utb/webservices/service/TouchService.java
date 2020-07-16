package cz.utb.webservices.service;

import cz.utb.webservices.dao.TouchDAO;
import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class TouchService {

    private final TouchDAO serverDao;

    @Autowired
    public TouchService(@Qualifier("TouchDataAccess") TouchDAO serverDao) {
        this.serverDao = serverDao;
    }

    public int addTouch(Touch touch, Client client) {
        return serverDao.insertTouch(touch, client);
    }

    public int addClient(Client client) {
        return serverDao.insertClient(client);
    }


}
