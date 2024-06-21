package movie.movie.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import movie.movie.entity.Movie;
import movie.movie.entity.MovieExample;
import movie.movie.mapper.MovieMapper;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;


    public Movie getMovieById(Integer mid){

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

    public List<Movie> recMovies(boolean first,Jwt jwt){

        if (first) {
            List<Movie> movies = movieMapper.selectTop30Movies();
        
            Collections.shuffle(movies);

            // 选择前10个随机数据
            int size = Math.min(10, movies.size());
            return movies.subList(0, size);
        }
        else{
            return movieMapper.selectPersonalityMovies(Integer.parseInt(jwt.getClaimAsString("sub")));
        }
    }

    // public List<Movie> recMovies(boolean first){


    //         List<Movie> movies = movieMapper.selectTop30Movies();
        
    //         Collections.shuffle(movies);

    //         // 选择前10个随机数据
    //         int size = Math.min(10, movies.size());
    //         return movies.subList(0, size);
        
    // }

    // public void addLike(User user,String genres){

    //     String currentGenres = user.getGenres();

    //     Set<String> genresSet = new HashSet<>();
    //     if (currentGenres != null && !currentGenres.isEmpty()) {
    //         genresSet.addAll(Arrays.asList(currentGenres.split("\\|")));
    //     }
    //     //MovieExample movieExample;
    //     genresSet.add(genres);

    //     String updatedGenres = genresSet.stream().collect(Collectors.joining("|"));
        
    //     // 更新用户的类型列表
    //     user.setGenres(updatedGenres);
    // }

    public String createMovie(Movie movie){
            movieMapper.insert(movie);
            return "创建成功";
    }

    public List<Movie> getMoviesByGenres(String genre){
        return movieMapper.selectMoviesByGenres(genre);
    }
}
