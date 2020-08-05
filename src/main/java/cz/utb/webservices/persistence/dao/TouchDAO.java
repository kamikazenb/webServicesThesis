package cz.utb.webservices.dao;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;

import java.util.List;

public interface TouchDAO {

    public int insertTouch(List<Touch> touches, Client client);
    public int insertClient(Client client);

}
