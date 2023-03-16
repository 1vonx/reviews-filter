package com.example.reviewsfilter.repository;

import com.example.reviewsfilter.config.DataInitializer;
import com.example.reviewsfilter.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {
    public List<Review> findAll(){
        return DataInitializer.reviews;
    }

    public List<Review> findAllByMinRating(Integer minRating){
        return DataInitializer.reviews.stream()
                .filter(r->r.getRating()>=minRating)
                .toList();
    }
}
