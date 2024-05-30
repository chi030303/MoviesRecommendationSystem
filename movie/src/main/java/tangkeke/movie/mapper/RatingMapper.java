package tangkeke.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tangkeke.movie.entity.Rating;
import tangkeke.movie.entity.RatingExample;

public interface RatingMapper {
    long countByExample(RatingExample example);

    int deleteByExample(RatingExample example);

    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("mid") String mid);

    int insert(Rating row);

    int insertSelective(Rating row);

    List<Rating> selectByExampleWithBLOBs(RatingExample example);

    List<Rating> selectByExample(RatingExample example);

    Rating selectByPrimaryKey(@Param("uid") Integer uid, @Param("mid") String mid);

    int updateByExampleSelective(@Param("row") Rating row, @Param("example") RatingExample example);

    int updateByExampleWithBLOBs(@Param("row") Rating row, @Param("example") RatingExample example);

    int updateByExample(@Param("row") Rating row, @Param("example") RatingExample example);

    int updateByPrimaryKeySelective(Rating row);

    int updateByPrimaryKeyWithBLOBs(Rating row);

    int updateByPrimaryKey(Rating row);
}