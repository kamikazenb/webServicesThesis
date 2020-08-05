package cz.utb.webservices.service;

import cz.utb.webservices.persistence.dao.TouchDAO;
import cz.utb.webservices.persistence.model.Client;
import cz.utb.webservices.persistence.model.Touch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class TouchService {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final TouchDAO serverDao;

    @Autowired
    public TouchService(@Qualifier("TouchDataAccess") TouchDAO serverDao) {
        this.serverDao = serverDao;
    }

    public int addTouch(List<Touch> touches, Client client) {
        return serverDao.insertTouch(touches, client);
    }

    public int addClient(Client client) {

        return serverDao.insertClient(client);
    }


}
