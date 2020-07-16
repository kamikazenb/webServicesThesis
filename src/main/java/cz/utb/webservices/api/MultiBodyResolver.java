package cz.utb.webservices.api;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.Touch;


public class MultiBodyResolver {
    private Touch touch;
    private Client client;

    public MultiBodyResolver(Client client, Touch touch) {
        this.client = client;
        this.touch = touch;
    }

    public Touch getTouch() {
        return touch;
    }

    public Client getClient() {
        return client;
    }
}
