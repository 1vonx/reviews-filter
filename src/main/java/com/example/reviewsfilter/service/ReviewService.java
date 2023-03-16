package com.example.reviewsfilter.service;

import com.example.reviewsfilter.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> listAll();
    List<Review> listAllSortedAndFiltered(Boolean textPriority, String ratingOrder, Integer minRating, String dateOrder);
}
