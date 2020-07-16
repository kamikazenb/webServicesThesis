package cz.utb.webservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idclient;
    private String name;
    @NotBlank
    private String token;
    @OneToMany(mappedBy="client")
    private Set<Touch> touchSet;

    protected Client(){           }

    public Client(String name,
                  @JsonProperty("token") String token){
        this.name = name;
        this.token = token;
    }
    public Client(@JsonProperty("token") String token){
        this.name = "";
        this.token = token;
    }
    @Override
    public String toString() {
        return String.format(
                "Touch[name='%s', token='%s']",
                name, token);
    }

    public String getToken() {
        return token;
    }
}
