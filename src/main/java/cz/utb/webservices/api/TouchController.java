package cz.utb.webservices.api;

import cz.utb.webservices.model.Touch;
import cz.utb.webservices.service.TouchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    /*@GetMapping(path = "{id}")
    public Touch getTouchById(@PathVariable("id") UUID id) {
        return touchService.getTouchById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        touchService.deleteTouch(id);
    }

    @PutMapping(path = "{id}")
    public void updateTouch(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Touch touchUpdate) {
        touchService.updateTouch(id, touchUpdate);
    }*/
}
