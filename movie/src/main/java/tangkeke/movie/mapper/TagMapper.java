package tangkeke.movie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tangkeke.movie.entity.Tag;
import tangkeke.movie.entity.TagExample;

public interface TagMapper {
    long countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("mid") String mid, @Param("tag") String tag);

    int insert(Tag row);

    int insertSelective(Tag row);

    List<Tag> selectByExample(TagExample example);

    int updateByExampleSelective(@Param("row") Tag row, @Param("example") TagExample example);

    int updateByExample(@Param("row") Tag row, @Param("example") TagExample example);
}