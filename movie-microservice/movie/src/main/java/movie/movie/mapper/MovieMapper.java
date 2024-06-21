package movie.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import movie.movie.entity.Movie;
import movie.movie.entity.MovieExample;

public interface MovieMapper {
    long countByExample(MovieExample example);

    int deleteByExample(MovieExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(Movie row);

    int insertSelective(Movie row);

    List<Movie> selectByExampleWithBLOBs(MovieExample example);

    List<Movie> selectByExample(MovieExample example);

    Movie selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("row") Movie row, @Param("example") MovieExample example);

    int updateByExampleWithBLOBs(@Param("row") Movie row, @Param("example") MovieExample example);

    int updateByExample(@Param("row") Movie row, @Param("example") MovieExample example);

    int updateByPrimaryKeySelective(Movie row);

    int updateByPrimaryKeyWithBLOBs(Movie row);

    int updateByPrimaryKey(Movie row);

    List<Movie> selectTop30Movies();

    List<Movie> selectPersonalityMovies(Integer uid);

    List<Movie> selectMoviesByGenres(String genre);

    List<Movie> selectMByKeyword(String keyword);
}