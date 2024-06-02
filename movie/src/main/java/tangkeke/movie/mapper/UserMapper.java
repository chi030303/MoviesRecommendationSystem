package tangkeke.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tangkeke.movie.entity.User;
import tangkeke.movie.entity.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(User row);

    int insertSelective(User row);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    int updateByExampleWithBLOBs(@Param("row") User row, @Param("example") UserExample example);

    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKeyWithBLOBs(User row);

    int updateByPrimaryKey(User row);
}