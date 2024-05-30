package tangkeke.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tangkeke.movie.entity.Movie;
import tangkeke.movie.entity.MovieExample;
import tangkeke.movie.mapper.MovieMapper;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    public Movie getMovieById(String mid){

        //MovieExample movieExample;
        return movieMapper.selectByPrimaryKey(mid);
    }
}
