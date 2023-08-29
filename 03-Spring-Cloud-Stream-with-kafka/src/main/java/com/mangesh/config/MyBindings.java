package com.mangesh.config;

import java.util.function.Function;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBindings {

    @Bean
    public Function<KStream<String, String>, KStream<String, String>> process() {
        return input -> input.mapValues(value -> value.toUpperCase());
    }

    // Add more functions and beans for additional bindings as needed
}
