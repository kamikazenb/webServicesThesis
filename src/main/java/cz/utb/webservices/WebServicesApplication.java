package cz.utb.webservices;

import ch.rasc.sse.eventbus.config.EnableSseEventBus;
import cz.utb.webservices.repository.ClientRepository;
import cz.utb.webservices.repository.TouchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableSseEventBus
@EnableScheduling
public class WebServicesApplication {

    private static final Logger log = LoggerFactory.getLogger(WebServicesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebServicesApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ClientRepository clientRepository, TouchRepository touchRepository) {
        return (args) -> {
            //here write start code
        };
    }

}
