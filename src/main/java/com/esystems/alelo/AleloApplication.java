package com.esystems.alelo;

import com.esystems.alelo.entity.Person;
import com.esystems.alelo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AleloApplication {

	private static final Logger logger = LoggerFactory.getLogger(AleloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AleloApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(PersonRepository toDoRepository) {
		return (args) -> {
			toDoRepository.save(new Person(1L, "Person 1"));
			toDoRepository.save(new Person(2L, "Person 2"));
			toDoRepository.save(new Person(3L, "Person 3"));
			toDoRepository.save(new Person(4L, "Person 4"));
			logger.info("Data base generated");
		};
	}

}
