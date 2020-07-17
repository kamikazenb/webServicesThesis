package cz.utb.webservices.api;

import cz.utb.webservices.model.Client;
import cz.utb.webservices.service.TouchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/rest")
@RestController
public class MyRestApiController {

    private static final Logger log = LoggerFactory.getLogger(MyRestApiController.class);
    private TouchService touchService;

    public MyRestApiController() {
    }

    @Autowired
    public MyRestApiController(TouchService touchService) {
        this.touchService = touchService;
    }

    @PostMapping("/touch")
    public void addTouch(@Valid @NonNull @RequestBody MultiBodyResolver multiBodyResolver) {
        touchService.addTouch(multiBodyResolver.getTouch(), multiBodyResolver.getClient());
    }

    @PostMapping("/client")
    public void addClient(@Valid @NonNull @RequestBody Client client) {
        touchService.addClient(client);
    }
}
