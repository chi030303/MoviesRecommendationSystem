package tangkeke.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tangkeke.movie.entity.UserRecs;
import tangkeke.movie.entity.UserRecsExample;

public interface UserRecsMapper {
    long countByExample(UserRecsExample example);

    int deleteByExample(UserRecsExample example);

    int insert(UserRecs row);

    int insertSelective(UserRecs row);

    List<UserRecs> selectByExample(UserRecsExample example);

    int updateByExampleSelective(@Param("row") UserRecs row, @Param("example") UserRecsExample example);

    int updateByExample(@Param("row") UserRecs row, @Param("example") UserRecsExample example);
}