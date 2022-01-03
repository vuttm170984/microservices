package com.vuttm.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vuttm.moviecatalogservice.models.CatalogItem;
import com.vuttm.moviecatalogservice.models.Movie;
import com.vuttm.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/api")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    
    private static final String USER_RATINGS_DATA_URL = "http://ratings-service/api/ratings/user/";
    private static final String MOVIE_INFO_URI = "http://movie-info-service/api/movies/";
    
    @RequestMapping("/catalog/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = restTemplate.getForObject(USER_RATINGS_DATA_URL + userId, UserRating.class);
        
        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject(MOVIE_INFO_URI + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
