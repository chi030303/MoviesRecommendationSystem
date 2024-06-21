package movie.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import movie.movie.entity.GenresTopMovie;
import movie.movie.entity.GenresTopMovieExample;

public interface GenresTopMovieMapper {
    long countByExample(GenresTopMovieExample example);

    int deleteByExample(GenresTopMovieExample example);

    int insert(GenresTopMovie row);

    int insertSelective(GenresTopMovie row);

    List<GenresTopMovie> selectByExampleWithBLOBs(GenresTopMovieExample example);

    List<GenresTopMovie> selectByExample(GenresTopMovieExample example);

    int updateByExampleSelective(@Param("row") GenresTopMovie row, @Param("example") GenresTopMovieExample example);

    int updateByExampleWithBLOBs(@Param("row") GenresTopMovie row, @Param("example") GenresTopMovieExample example);

    int updateByExample(@Param("row") GenresTopMovie row, @Param("example") GenresTopMovieExample example);
}