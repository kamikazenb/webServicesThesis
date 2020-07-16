package cz.utb.webservices.dao;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;

public interface TouchDAO {

    public int insertTouch(Touch touch, Client client);
    public int insertClient(Client client);

}
