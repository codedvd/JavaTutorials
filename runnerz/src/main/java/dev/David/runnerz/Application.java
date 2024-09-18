package dev.David.runnerz;

import dev.David.runnerz.run.Location;
import dev.David.runnerz.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		log.info("I Said Something changed!");
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1,
					"First Run",
					LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
					5,
					Location.OUTDOOR);

            log.info("Run: {}", run);
		};
	}
}
