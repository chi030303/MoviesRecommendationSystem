package movie.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test1")
public class TestController {

	@GetMapping("ok")
    public String getMovieDetails() {
        return "success";
    }
}
