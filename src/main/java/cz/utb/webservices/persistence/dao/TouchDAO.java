package cz.utb.webservices.persistence.dao;

import cz.utb.webservices.persistence.model.Client;
import cz.utb.webservices.persistence.model.Touch;

import java.util.List;

public interface TouchDAO {

    public int insertTouch(List<Touch> touches, Client client);
    public int insertClient(Client client);

}
