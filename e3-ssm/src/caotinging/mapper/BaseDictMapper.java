package caotinging.mapper;

import java.util.List;

import caotinging.pojo.BaseDict;

public interface BaseDictMapper {

	//查询数据字典的方法
	public List<BaseDict> findListByTypeCode(String typeCode);
	
}
