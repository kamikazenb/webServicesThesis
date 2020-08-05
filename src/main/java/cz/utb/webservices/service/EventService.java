package cz.utb.webservices.service;

import ch.rasc.sse.eventbus.SseEvent;
import ch.rasc.sse.eventbus.SseEventBus;
import cz.utb.webservices.persistence.model.Touch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;

@Service
public class EventService {
    public  ApplicationEventPublisher eventPublisher;
    private final SseEventBus eventBus;

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    public EventService(ApplicationEventPublisher eventPublisher,
                        SseEventBus eventBus) {
        this.eventPublisher = eventPublisher;
        this.eventBus = eventBus;
    }

    @PrePersist
    public void afterAnyUpdate(Touch touch) {
        this.eventPublisher.publishEvent(SseEvent.of("token", touch));
    }
}
