package com.example.reviewsfilter.web;

import com.example.reviewsfilter.config.DataInitializer;
import com.example.reviewsfilter.model.Review;
import com.example.reviewsfilter.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getReviews(@RequestParam(required = false) String ratingOrder,
                             @RequestParam(required = false) Integer minRating,
                             @RequestParam(required = false) String dateOrder,
                             @RequestParam(required = false) Boolean textPriority,
                             Model model){

        List<Review> reviewList;
        if(ratingOrder==null && minRating==null && dateOrder==null && textPriority==null)
            reviewList = this.reviewService.listAll();
        else
            reviewList = this.reviewService.listAllSortedAndFiltered(textPriority, ratingOrder, minRating, dateOrder);

        model.addAttribute("reviews", reviewList);
        return "filter";
    }
}
