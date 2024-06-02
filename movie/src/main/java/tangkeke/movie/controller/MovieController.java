package tangkeke.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tangkeke.movie.entity.Movie;
import tangkeke.movie.entity.User;
import tangkeke.movie.service.MovieService;
import tangkeke.movie.service.UserService;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    // @GetMapping("movie/{id}")
    // public String getMovieDetails(@PathVariable("id") String id, Model model) {
    //     System.out.println(id);
    //     Movie movie = movieService.getMovieById(id);
    //     model.addAttribute("movie", movie);
    //     return "movieDetails";
    // }

    @GetMapping("/{id}")
    public String getMovieDetails(@PathVariable("id") String id) {
        return movieService.getMovieById(id).toString();
    }
    @GetMapping("")
    public List<Movie> getMovieMain() {
        
        // 获取当前登录的用户信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        // 根据用户名获取用户ID
        User user = userService.findByUsername(username);
        if (!user.getFirst()) {
            System.out.println("first");
            return movieService.recMoviesFirst();
        }
        else{
            System.out.println("not first");
            return movieService.recMoviesPersonality(user.getUid());
        }
    }

}
