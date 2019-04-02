package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Application that starts up the Spring Boot container.
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    /**
     * Starts the Spring Boot Application.
     *
     * @param args the command line args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
