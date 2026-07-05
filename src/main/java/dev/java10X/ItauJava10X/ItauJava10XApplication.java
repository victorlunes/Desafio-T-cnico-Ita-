package dev.java10X.ItauJava10X;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ItauJava10XApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItauJava10XApplication.class, args);
	}

}
