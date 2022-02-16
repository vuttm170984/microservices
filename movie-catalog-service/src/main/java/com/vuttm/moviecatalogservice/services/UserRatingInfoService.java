package com.vuttm.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vuttm.moviecatalogservice.models.Rating;
import com.vuttm.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfoService {

    @Autowired
    private RestTemplate restTemplate;
    
    // For the normal case
    private static final String USER_RATINGS_DATA_URL = "http://ratings-service/api/ratings/user/";
    
    // For the fallback case
    // private static final String USER_RATINGS_DATA_URL = "";
    
    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject(USER_RATINGS_DATA_URL + userId, UserRating.class);
    }
    
    public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Arrays.asList(
                new Rating("None", 0)
        ));
        return userRating;
    }
}
