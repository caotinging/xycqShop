package caotinging.mapper;

import caotinging.pojo.itemCat;
import caotinging.pojo.itemCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface itemCatMapper {
    int countByExample(itemCatExample example);

    int deleteByExample(itemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(itemCat record);

    int insertSelective(itemCat record);

    List<itemCat> selectByExample(itemCatExample example);

    itemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") itemCat record, @Param("example") itemCatExample example);

    int updateByExample(@Param("record") itemCat record, @Param("example") itemCatExample example);

    int updateByPrimaryKeySelective(itemCat record);

    int updateByPrimaryKey(itemCat record);
}