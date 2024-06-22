package movie.genre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.genre.entity.Genre;
import movie.genre.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("genre")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping("allGenres")
	public List<Genre> allGenres() {
		return genreService.allGenres();
	}

	@PostMapping("createGenre")
	public String createGenre(String genre) {
		
		
		return genreService.createGenre(genre);
	}
	
	
}
