package com.vuttm.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuttm.moviecatalogservice.models.CatalogItem;
import com.vuttm.moviecatalogservice.models.UserRating;
import com.vuttm.moviecatalogservice.services.MovieInfoService;
import com.vuttm.moviecatalogservice.services.UserRatingInfoService;

@RestController
@RequestMapping("/api")
public class CatalogResource {

    // @Autowired
    // private RestTemplate restTemplate;
    
    // @Autowired
    // private WebClient.Builder webClientBuilder;
    
    @Autowired
    MovieInfoService movieInfo;
    
    @Autowired
    UserRatingInfoService userRatingInfo;
    
    @RequestMapping("/catalog/{userId}")
    // @HystrixCommand(fallbackMethod = "getFallbackCatalog") // Not quite what we want
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingInfo.getUserRating(userId);
        
        return userRating.getRatings().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
    
    // public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        // return Arrays.asList(new CatalogItem("No movie", "", 0));
    // }
}
