package cz.utb.webservices.api;

import ch.rasc.sse.eventbus.SseEvent;
import ch.rasc.sse.eventbus.SseEventBus;
//import cz.utb.webservices.service.TouchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class SSEController {
    private static final Logger log = LoggerFactory.getLogger(SSEController.class);
    private final SseEventBus eventBus;

    public SSEController(SseEventBus eventBus) {
        this.eventBus = eventBus;
    }
    @GetMapping("/sse/{id}")
    public SseEmitter register(@PathVariable("id") String id, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        return this.eventBus.createSseEmitter(UUID.randomUUID().toString(),  SseEvent.DEFAULT_EVENT, "token");
    }
}

