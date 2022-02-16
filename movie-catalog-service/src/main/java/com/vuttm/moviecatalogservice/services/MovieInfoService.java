package com.vuttm.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vuttm.moviecatalogservice.models.CatalogItem;
import com.vuttm.moviecatalogservice.models.Movie;
import com.vuttm.moviecatalogservice.models.Rating;

@Service
public class MovieInfoService {

    // Solve the Hystrix problem
    private static final String MOVIE_INFO_URI = "";
    
    // private static final String MOVIE_INFO_URI = "http://movie-info-service/api/movies/";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject(MOVIE_INFO_URI + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }
    
    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}
