package com.mangesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Kakfa01Application {

	public static void main(String[] args) {
		SpringApplication.run(Kakfa01Application.class, args);
	}

}
