package com.vuttm.RatingsService.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuttm.RatingsService.model.Rating;
import com.vuttm.RatingsService.model.UserRating;

@RestController
@RequestMapping("/api/ratings")
public class RatingResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 5);
    }
    
    @RequestMapping("/user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.init(userId);
        return userRating;
    }
}
