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
        Comparator<Review> ratingComparator = null;
        Comparator<Review> dateComparator = null;
        List<Review> sortedReviews = this.listAll();
        if (minRating != null)
            sortedReviews = this.reviewRepository.findAllByMinRating(minRating);

        if (ratingOrder != null && !ratingOrder.isEmpty()) {
            if (ratingOrder.equalsIgnoreCase("highest"))
                ratingComparator = Comparator.comparing(Review::getRating).reversed();
            else
                ratingComparator = Comparator.comparing(Review::getRating);
        }

        if (dateOrder != null && !dateOrder.isEmpty()) {
            if (dateOrder.equalsIgnoreCase("newest"))
                dateComparator = Comparator.comparing(Review::getReviewCreatedOnDate).reversed();
            else
                dateComparator = Comparator.comparing(Review::getReviewCreatedOnDate);
        }

        if (textPriority != null && textPriority) {
            if (ratingComparator != null && dateComparator != null)
                comparator = Comparator.comparing(Review::hasText).reversed()
                        .thenComparing(ratingComparator)
                        .thenComparing(dateComparator);
            else if (ratingComparator != null)
                comparator = Comparator.comparing(Review::hasText).reversed()
                        .thenComparing(ratingComparator);
            else if (dateComparator != null)
                comparator = Comparator.comparing(Review::hasText).reversed()
                        .thenComparing(dateComparator);
            else
                comparator = Comparator.comparing(Review::hasText).reversed();
        } else if (ratingComparator != null && dateComparator != null)
            comparator = ratingComparator.thenComparing(dateComparator);
        else if (ratingComparator != null)
            comparator = ratingComparator;
        else if (dateComparator != null)
            comparator = dateComparator;

        if (comparator != null)
            sortedReviews.sort(comparator);

        return sortedReviews;
    }
}
