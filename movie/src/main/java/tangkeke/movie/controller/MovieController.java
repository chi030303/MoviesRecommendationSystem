package tangkeke.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tangkeke.movie.entity.Movie;
import tangkeke.movie.service.MovieService;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{id}")
    public String getMovieDetails(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "movieDetails";
    }
}
