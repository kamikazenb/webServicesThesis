package cz.utb.webservices.api;

import cz.utb.webservices.persistence.model.Client;
import cz.utb.webservices.service.TouchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/rest")
@RestController
public class RestApiController {

    private static final Logger log = LoggerFactory.getLogger(RestApiController.class);
    private TouchService touchService;

    public RestApiController() {
    }

    @Autowired
    public RestApiController(TouchService touchService) {
        this.touchService = touchService;
    }

    @PostMapping("/touch")
    public void addTouch(@Valid @NonNull @RequestBody JSONhelper JSONhelper) {
        touchService.addTouch(JSONhelper.getTouches(), JSONhelper.getClient());
    }

    @PostMapping("/client")
    public void addClient(@Valid @NonNull @RequestBody Client client) {
        touchService.addClient(client);
    }
}
