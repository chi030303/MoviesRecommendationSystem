package movie.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import movie.user.entity.UserAttribute;
import movie.user.entity.UserAttributeExample;

public interface UserAttributeMapper {
    long countByExample(UserAttributeExample example);

    int deleteByExample(UserAttributeExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserAttribute row);

    int insertSelective(UserAttribute row);

    List<UserAttribute> selectByExampleWithBLOBs(UserAttributeExample example);

    List<UserAttribute> selectByExample(UserAttributeExample example);

    UserAttribute selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") UserAttribute row, @Param("example") UserAttributeExample example);

    int updateByExampleWithBLOBs(@Param("row") UserAttribute row, @Param("example") UserAttributeExample example);

    int updateByExample(@Param("row") UserAttribute row, @Param("example") UserAttributeExample example);

    int updateByPrimaryKeySelective(UserAttribute row);

    int updateByPrimaryKeyWithBLOBs(UserAttribute row);

    int updateByPrimaryKey(UserAttribute row);
}