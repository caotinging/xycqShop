package caotinging.service;

import java.util.List;

import caotinging.pojo.BaseDict;

public interface BaseDictService {

	public List<BaseDict> findListByTypeCode(String typeCode);
}
