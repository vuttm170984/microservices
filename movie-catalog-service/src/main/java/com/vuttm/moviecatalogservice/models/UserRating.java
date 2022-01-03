package com.vuttm.moviecatalogservice.models;

import java.util.Arrays;
import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> ratings;
    
    public UserRating() {
    }
    
    public UserRating(String userId, List<Rating> ratings) {
        this.userId = userId;
        this.ratings = ratings;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public List<Rating> getRatings() {
        return ratings;
    }
    
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public void init(String userId) {
        this.userId = userId;
        this.ratings = Arrays.asList(
                new Rating("movie-a", 4),
                new Rating("movie-b", 3));
    }
}
