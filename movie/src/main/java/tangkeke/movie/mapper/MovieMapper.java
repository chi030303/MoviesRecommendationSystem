package tangkeke.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tangkeke.movie.entity.Movie;
import tangkeke.movie.entity.MovieExample;

public interface MovieMapper {
    long countByExample(MovieExample example);

    int deleteByExample(MovieExample example);

    int deleteByPrimaryKey(String mid);

    int insert(Movie row);

    int insertSelective(Movie row);

    List<Movie> selectByExampleWithBLOBs(MovieExample example);

    List<Movie> selectByExample(MovieExample example);

    Movie selectByPrimaryKey(String mid);

    int updateByExampleSelective(@Param("row") Movie row, @Param("example") MovieExample example);

    int updateByExampleWithBLOBs(@Param("row") Movie row, @Param("example") MovieExample example);

    int updateByExample(@Param("row") Movie row, @Param("example") MovieExample example);

    int updateByPrimaryKeySelective(Movie row);

    int updateByPrimaryKeyWithBLOBs(Movie row);

    List<Movie> selectTop30Movies();

    List<Movie> selectPersonalityMovies(Integer uid);
}