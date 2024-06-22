package movie.genre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.genre.entity.Genre;
import movie.genre.entity.GenreExample;
import movie.genre.mapper.GenreMapper;

@Service
public class GenreService {

	@Autowired
	private GenreMapper genreMapper;

	public List<Genre> allGenres(){
		GenreExample genreExample = new GenreExample();
		return genreMapper.selectByExample(genreExample);
	}

	public String createGenre(String genre){
		Genre genre1 = new Genre();
		genre1.setGenre(genre);
		genreMapper.insert(genre1);
		return "ok";
	}
}
