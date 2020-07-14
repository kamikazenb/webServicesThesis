package cz.utb.webservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Touch {

    @NotNull
    private final float x;
    private final float y;
    @NotBlank
    private final String touchType;
    private final String clientCreated;
    private final String serverReceived;
    private final int client_idclient;
    private final int serverType_idserverType = 2;


    public Touch(@JsonProperty("x") float x,
                 @JsonProperty("y") float y,
                 @JsonProperty("touchType") String touchType,
                 @JsonProperty("clientCreated") String clientCreated,
                 @JsonProperty("serverReceived") String serverReceived,
                 @JsonProperty("client_idclient") int client_idclient) {
        this.x = x;
        this.y = y;
        this.touchType = touchType;
        this.clientCreated = clientCreated;
        this.serverReceived = serverReceived;
        this.client_idclient = client_idclient;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getTouchType() {
        return touchType;
    }

    public String getClientCreated() {
        return clientCreated;
    }

    public String getServerReceived() {
        return serverReceived;
    }

    public int getClient_idclient() {
        return client_idclient;
    }

    public int getServerType_idserverType() {
        return serverType_idserverType;
    }
}
