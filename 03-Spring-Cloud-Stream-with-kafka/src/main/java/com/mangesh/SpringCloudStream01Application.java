package com.mangesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringCloudStream01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStream01Application.class, args);
	}

}
