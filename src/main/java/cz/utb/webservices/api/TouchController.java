package cz.utb.webservices.api;

import cz.utb.webservices.repository.ClientRepository;
import cz.utb.webservices.repository.TouchRepository;
import cz.utb.webservices.service.TouchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("api/v1/touch")
@RestController
public class TouchController {

    private final TouchService touchService;
    private final TouchRepository touchRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public TouchController(TouchService touchService,
                           TouchRepository touchRepository,
                           ClientRepository clientRepository) {
        this.touchService = touchService;
        this.touchRepository = touchRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody MultiBodyResolver multiBodyResolver) { //@RequestBody ze vyzaduje aby bol v JSON telo
        touchService.addTouch(multiBodyResolver.getTouch(), multiBodyResolver.getClient());
    }

//    @GetMapping
//    public List<Touch> getAllTouches() {
//        return touchService.getAllTouches();
//    }

  /*  @GetMapping("/stream-sse")
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
    }*/
}
