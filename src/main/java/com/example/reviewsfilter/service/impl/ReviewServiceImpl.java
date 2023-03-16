package com.example.reviewsfilter.service.impl;

import com.example.reviewsfilter.model.Review;
import com.example.reviewsfilter.repository.ReviewRepository;
import com.example.reviewsfilter.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> listAll() {
        return this.reviewRepository.findAll();
    }

    @Override
    public List<Review> listAllSortedAndFiltered(Boolean textPriority,
                                                 String ratingOrder,
                                                 Integer minRating,
                                                 String dateOrder) {
        Comparator<Review> comparator = null;
        List<Review> sortedReviews = this.listAll();
        if (minRating != null)
            sortedReviews = new ArrayList<>(this.reviewRepository.findAllByMinRating(minRating));

        if (textPriority !=null && textPriority) {
            if (ratingOrder != null && !ratingOrder.isEmpty()) {
                if (dateOrder != null && !dateOrder.isEmpty()) {
                    comparator = Comparator.comparing(Review::hasText).reversed()
                            .thenComparing(Review::getRating).reversed()
                            .thenComparing(Review::getReviewCreatedOnDate).reversed();
                }
                comparator = Comparator.comparing(Review::hasText).reversed()
                        .thenComparing(Review::getRating).reversed();
            } else if (dateOrder != null && !dateOrder.isEmpty())
                comparator = Comparator.comparing(Review::hasText).reversed()
                        .thenComparing(Review::getReviewCreatedOnDate).reversed();
            comparator = Comparator.comparing(Review::hasText).reversed();
        } else {
            if (ratingOrder != null && !ratingOrder.isEmpty()) {
                if (dateOrder != null && !dateOrder.isEmpty()) {
                    comparator = Comparator.comparing(Review::getRating).reversed()
                            .thenComparing(Review::getReviewCreatedOnDate).reversed();
                }
                comparator = Comparator.comparing(Review::getRating).reversed();
            } else if (dateOrder != null && !dateOrder.isEmpty())
                comparator = Comparator.comparing(Review::getReviewCreatedOnDate).reversed();
        }

        if (comparator != null)
            sortedReviews.sort(comparator);

        return sortedReviews;
    }
}
