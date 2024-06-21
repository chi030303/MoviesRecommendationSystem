package movie.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.movie.entity.Movie;
import movie.movie.service.MovieService;
import movie.movie.service.UserServiceFeignClient;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @GetMapping("/{id}")
    public Movie getMovieDetails(@PathVariable("id") Integer id) {
        return movieService.getMovieById(id);
    }

    
    @GetMapping("")
    public List<Movie> getMovieMain() {
        
        // 获取当前登录的用户信息

        // 根据用户名获取用户ID
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return movieService.recMovies(userServiceFeignClient.isFirst(),jwt);
    }

    @PostMapping("/createMovie")
    public String createMovie(@RequestBody Movie movie){


        return movieService.createMovie(movie);
    }

    @GetMapping("/movieByGenre")
    public List<Movie> getMoviesByGenre(String genre){
        return movieService.getMoviesByGenres(genre);
    }

    @PostMapping("/search")
    public List<Movie> searchMovies(String keyword) {
        
        return movieService.likeSearchMovies(keyword);
    }
    
}
