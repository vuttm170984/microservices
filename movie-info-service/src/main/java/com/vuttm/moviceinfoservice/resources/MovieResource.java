package com.vuttm.moviceinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${api.key}")
    private String apiKey;
    
    private static final String THE_MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping("/movies/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "Movie Name", "Movie Description");
    }
    
    @RequestMapping("/external-movies/{movieId}")
    public Movie getExternalMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject(THE_MOVIE_DB_URL + movieId + "?api_key=" + apiKey
                , MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
