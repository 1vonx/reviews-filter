package com.example.reviewsfilter.config;

import com.example.reviewsfilter.model.Review;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {
    public static List<Review> reviews = new ArrayList<>();
    ObjectMapper objectMapper;

    public DataInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        reviews = objectMapper.readValue(new URL("file:src/main/resources/json/reviews.json"), new TypeReference<List<Review>>(){});
        reviews.forEach(System.out::println);
    }
}
