package com.vuttm.moviceinfoservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuttm.moviceinfoservice.models.Movie;

@RestController
@RequestMapping("/api")
public class MovieResource {

    @Value("api.key")
    private String apiKey;
    
    @RequestMapping("/movies/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "Movie Name", "Movie Description");
    }
}
