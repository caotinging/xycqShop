package caotinging.mapper;

import caotinging.pojo.orderItem;
import caotinging.pojo.orderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface orderItemMapper {
    int countByExample(orderItemExample example);

    int deleteByExample(orderItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(orderItem record);

    int insertSelective(orderItem record);

    List<orderItem> selectByExample(orderItemExample example);

    orderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") orderItem record, @Param("example") orderItemExample example);

    int updateByExample(@Param("record") orderItem record, @Param("example") orderItemExample example);

    int updateByPrimaryKeySelective(orderItem record);

    int updateByPrimaryKey(orderItem record);
}