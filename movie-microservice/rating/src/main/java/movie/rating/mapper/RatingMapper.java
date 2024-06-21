package movie.rating.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import movie.rating.entity.Rating;
import movie.rating.entity.RatingExample;

public interface RatingMapper {
    long countByExample(RatingExample example);

    int deleteByExample(RatingExample example);

    int insert(Rating row);

    int insertSelective(Rating row);

    List<Rating> selectByExampleWithBLOBs(RatingExample example);

    List<Rating> selectByExample(RatingExample example);

    int updateByExampleSelective(@Param("row") Rating row, @Param("example") RatingExample example);

    int updateByExampleWithBLOBs(@Param("row") Rating row, @Param("example") RatingExample example);

    int updateByExample(@Param("row") Rating row, @Param("example") RatingExample example);
}