package tangkeke.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tangkeke.movie.entity.PopularMovie;
import tangkeke.movie.entity.PopularMovieExample;

public interface PopularMovieMapper {
    long countByExample(PopularMovieExample example);

    int deleteByExample(PopularMovieExample example);

    int insert(PopularMovie row);

    int insertSelective(PopularMovie row);

    List<PopularMovie> selectByExample(PopularMovieExample example);

    int updateByExampleSelective(@Param("row") PopularMovie row, @Param("example") PopularMovieExample example);

    int updateByExample(@Param("row") PopularMovie row, @Param("example") PopularMovieExample example);
}