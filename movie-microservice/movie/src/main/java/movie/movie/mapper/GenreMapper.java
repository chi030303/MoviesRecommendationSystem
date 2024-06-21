package movie.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import movie.movie.entity.Genre;
import movie.movie.entity.GenreExample;

public interface GenreMapper {
    long countByExample(GenreExample example);

    int deleteByExample(GenreExample example);

    int deleteByPrimaryKey(String genre);

    int insert(Genre row);

    int insertSelective(Genre row);

    List<Genre> selectByExample(GenreExample example);

    int updateByExampleSelective(@Param("row") Genre row, @Param("example") GenreExample example);

    int updateByExample(@Param("row") Genre row, @Param("example") GenreExample example);
}