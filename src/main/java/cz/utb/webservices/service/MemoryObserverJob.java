package cz.utb.webservices.service;

import cz.utb.webservices.api.SSEController;
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

@Service
public class MemoryObserverJob {
    public  ApplicationEventPublisher eventPublisher;
    private static final Logger log = LoggerFactory.getLogger(MemoryObserverJob.class);

    public MemoryObserverJob(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = 1000)
    public void doSomething() {
        log.info("do something");
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = memBean.getHeapMemoryUsage();
        MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();

        MemoryInfo mi = new MemoryInfo(heap.getUsed(), nonHeap.getUsed());
//        this.eventPublisher.publishEvent(mi);
    }
    @PrePersist
    public void afterAnyUpdate(Touch touch) {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = memBean.getHeapMemoryUsage();
        MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();

        MemoryInfo mi = new MemoryInfo(heap.getUsed(), nonHeap.getUsed());
        this.eventPublisher.publishEvent(mi);
    }

}
