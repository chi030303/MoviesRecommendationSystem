package tangkeke.movie.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.integration.IntegrationGraphEndpoint;
import org.springframework.stereotype.Service;

import tangkeke.movie.entity.Movie;
import tangkeke.movie.entity.MovieExample;
import tangkeke.movie.entity.PopularMovie;
import tangkeke.movie.entity.PopularMovieExample;
import tangkeke.movie.entity.User;
import tangkeke.movie.entity.UserRecsExample;
import tangkeke.movie.mapper.MovieMapper;
import tangkeke.movie.mapper.PopularMovieMapper;
import tangkeke.movie.mapper.UserRecsMapper;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private PopularMovieMapper popularMovieMapper;

    @Autowired 
    private UserRecsMapper userRecsMapper;

    public Movie getMovieById(String mid){

        //MovieExample movieExample;
        return movieMapper.selectByPrimaryKey(mid);
    }

    public List<Movie> recMoviesFirst(){

        List<Movie> movies = movieMapper.selectTop30Movies();
        
        Collections.shuffle(movies);

        // 选择前10个随机数据
        int size = Math.min(10, movies.size());
        return movies.subList(0, size);
    }

    public List<Movie> recMoviesPersonality(Integer uid){

        return movieMapper.selectPersonalityMovies(uid);
    }

    public void addLike(User user,String genres){

        String currentGenres = user.getGenres();

        Set<String> genresSet = new HashSet<>();
        if (currentGenres != null && !currentGenres.isEmpty()) {
            genresSet.addAll(Arrays.asList(currentGenres.split("\\|")));
        }
        //MovieExample movieExample;
        genresSet.add(genres);

        String updatedGenres = genresSet.stream().collect(Collectors.joining("|"));
        
        // 更新用户的类型列表
        user.setGenres(updatedGenres);
    }
}
