package cz.utb.webservices.api;

import ch.rasc.sse.eventbus.SseEventBus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.utb.webservices.model.Client;
import cz.utb.webservices.model.MemoryInfo;
import cz.utb.webservices.model.Touch;
//import cz.utb.webservices.service.TouchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import javax.persistence.PrePersist;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class SSEController {
    private static final Logger log = LoggerFactory.getLogger(SSEController.class);
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();


    @GetMapping("/memory")
    public SseEmitter handle(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");

        SseEmitter emitter = new SseEmitter();
        // SseEmitter emitter = new SseEmitter(180_000L);

        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        return emitter;
    }

    @EventListener
    public void onMemoryInfo(MemoryInfo memoryInfo) {
        log.info("onMemoryInfo");
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(memoryInfo);

                // close connnection, browser automatically reconnects
                // emitter.complete();

                // SseEventBuilder builder = SseEmitter.event().name("second").data("1");
                // SseEventBuilder builder =
                // SseEmitter.event().reconnectTime(10_000L).data(memoryInfo).id("1");
                // emitter.send(builder);
            }
            catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        this.emitters.removeAll(deadEmitters);
    }



    @GetMapping("/stream")
    public Flux<ServerSentEvent<String>> streamEvents() {
        String json = "";
        try {
            json = new ObjectMapper().
                    writeValueAsString(new Client("a", "a"));
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

