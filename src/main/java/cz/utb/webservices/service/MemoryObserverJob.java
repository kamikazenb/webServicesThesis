package cz.utb.webservices.service;

import ch.rasc.sse.eventbus.SseEvent;
import ch.rasc.sse.eventbus.SseEventBus;
import cz.utb.webservices.api.SSEController;
import cz.utb.webservices.model.Dto;
import cz.utb.webservices.model.MemoryInfo;
import cz.utb.webservices.model.Touch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.PrePersist;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Random;

@Service
public class MemoryObserverJob {
    public  ApplicationEventPublisher eventPublisher;
    private final SseEventBus eventBus;
    private final static Random random = new Random();

    private static final Logger log = LoggerFactory.getLogger(MemoryObserverJob.class);

    public MemoryObserverJob(ApplicationEventPublisher eventPublisher,
                             SseEventBus eventBus) {
        this.eventPublisher = eventPublisher;
        this.eventBus = eventBus;
    }
    @Scheduled(initialDelay = 1000, fixedRate = 5_000)
    public void sendData() {
//        log.info("sending data");
//        StringBuilder sb = new StringBuilder("[");
//        for (int i = 0; i < 5; i++) {
//            sb.append(random.nextInt(31));
//            sb.append(",");
//        }
//        sb.replace(sb.length() - 1, sb.length(), "]");
//        this.eventPublisher.publishEvent(SseEvent.ofData(sb.toString()));
//        Dto dto = new Dto();
//        dto.setI(10);
//        dto.setS("test");
//        this.eventPublisher.publishEvent(SseEvent.of("dto", dto));
    }

    @Scheduled(fixedRate = 1000)
    public void doSomething() {

        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = memBean.getHeapMemoryUsage();
        MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();

        MemoryInfo mi = new MemoryInfo(heap.getUsed(), nonHeap.getUsed());
//        this.eventPublisher.publishEvent(mi);
    }
    @PrePersist
    public void afterAnyUpdate(Touch touch) {
        this.eventPublisher.publishEvent(SseEvent.of("touch", touch));
    }
}
