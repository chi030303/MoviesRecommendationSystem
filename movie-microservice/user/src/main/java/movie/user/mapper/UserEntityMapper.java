package movie.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import movie.user.entity.UserEntity;
import movie.user.entity.UserEntityExample;

public interface UserEntityMapper {
    long countByExample(UserEntityExample example);

    int deleteByExample(UserEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserEntity row);

    int insertSelective(UserEntity row);

    List<UserEntity> selectByExample(UserEntityExample example);

    UserEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    int updateByExample(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    int updateByPrimaryKeySelective(UserEntity row);

    int updateByPrimaryKey(UserEntity row);
}