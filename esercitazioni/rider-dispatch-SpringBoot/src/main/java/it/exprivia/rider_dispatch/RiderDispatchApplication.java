package it.exprivia.rider_dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class RiderDispatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiderDispatchApplication.class, args);
	}

}
