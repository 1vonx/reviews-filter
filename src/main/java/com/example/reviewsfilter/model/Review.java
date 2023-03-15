package com.example.reviewsfilter.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class Review {
    private Long id;
    private String reviewText;
    private Integer rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date reviewCreatedOnDate;

    public Review(Long id, String reviewText, Integer rating, Date reviewCreatedOnDate) {
        this.id = id;
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewCreatedOnDate = reviewCreatedOnDate;
    }
}
