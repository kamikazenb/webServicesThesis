package cz.utb.webservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Touch {

    private final UUID id;
    //    @NotBlank
    @NotNull
    private final float x;

    public Touch(@JsonProperty("id") UUID id, @JsonProperty("x") float x) {
        this.id = id;
        this.x = x;
    }

    public UUID getId() {
        return id;
    }

    public float getX() {
        return x;
    }
}
