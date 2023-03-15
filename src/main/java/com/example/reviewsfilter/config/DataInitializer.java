package com.example.reviewsfilter.config;

import com.example.reviewsfilter.model.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Configuration
public class DataInitializer {
    ObjectMapper objectMapper;

    public DataInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        List<Review> reviews = objectMapper.readValue(new URL("file:src/main/resources/json/reviews.json"), new TypeReference<List<Review>>(){});
        reviews.forEach(System.out::println);
    }
}
