package com.vuttm.moviceinfoservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vuttm.moviceinfoservice.models.Movie;
import com.vuttm.moviceinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/api")
public class MovieResource {

    @Value("api.key")
    private String apiKey;
    
    private final RestTemplate restTemplate;
    
    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @RequestMapping("/movies/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = new MovieSummary();
        return new Movie(movieId, "Movie Name", "Movie Description");
    }
}
