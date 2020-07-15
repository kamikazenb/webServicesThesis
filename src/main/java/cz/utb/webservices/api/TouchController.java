package cz.utb.webservices.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cz.utb.webservices.model.Touch;
import cz.utb.webservices.service.TouchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;


@RequestMapping("api/v1/touch")
@RestController
public class TouchController {

    private final TouchService touchService;

    @Autowired
    public TouchController(TouchService touchService) {
        this.touchService = touchService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Touch touch) { //@RequestBody ze vyzaduje aby bol v JSON telo
        touchService.addTouch(touch);
    }

    @GetMapping
    public List<Touch> getAllTouches() {
        return touchService.getAllTouches();
    }

    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        String json = "";
        try {
            json = new ObjectMapper().
                    writeValueAsString(new Touch(0, 0, "TouchUp", "Created", "recived", 5));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final String result = json;
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data(result)
                        .build());
    }
}
