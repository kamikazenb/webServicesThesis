package cz.utb.webservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.utb.webservices.api.SSEController;
import cz.utb.webservices.service.MemoryObserverJob;
//import cz.utb.webservices.service.TouchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EntityListeners(MemoryObserverJob.class)
@Entity
@Table(name="touch")
public class Touch {
    private static final Logger log = LoggerFactory.getLogger(Touch.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtouch;
    @NotBlank
    @Column(name = "touchtype")
    private String touchType;
    @NotNull
    private float x;
    private float y;
    @NotBlank
    @Column(name = "clientcreated")
    private String clientCreated;
    @Column(name = "serverreceived")
    private String serverReceived;
    @ManyToOne
    @JoinColumn(referencedColumnName = "idclient", name="client_idclient", nullable=false)
    private Client client;

    public Touch(@JsonProperty("x") float x,
                 @JsonProperty("y") float y,
                 @JsonProperty("touchType") String touchType,
                 @JsonProperty("clientCreated") String clientCreated,
                 @JsonProperty("serverReceived") String serverReceived,
                 Client client) {
        this.x = x;
        this.y = y;
        this.touchType = touchType;
        this.clientCreated = clientCreated;
        this.serverReceived = serverReceived;
        this.client = client;
    }

    protected  Touch() {}

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

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return String.format(
                "Touch[touchType='%s', touchType='%s']",
                  touchType, clientCreated);
    }

}
