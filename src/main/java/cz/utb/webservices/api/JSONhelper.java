package cz.utb.webservices.api;

import cz.utb.webservices.persistence.model.Client;
import cz.utb.webservices.persistence.model.Touch;

import java.util.List;


public class JSONhelper {
    private Touch touch;
    private Client client;
    private List<Touch> touches = null;

    public JSONhelper(List<Touch> touches, Touch touch) {
        this.touches = touches;
        this.touch = touch;
    }

/*    public Touch getTouch() {
        return touch;
    }*/

    public Client getClient() {
        return client;
    }

    public List<Touch> getTouches() {
        return touches;
    }

    public void setTouches(List<Touch> touches) {
        this.touches = touches;
    }
}
