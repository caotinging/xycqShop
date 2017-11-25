package caotinging.service;

import java.util.List;

import caotinging.domain.BaseDict;

public interface BaseDictService {

	/**
	 * 从数据字典base_dict表中获取相同dict_type_code的一组数据
	 * @param dict_type_code
	 * @return
	 */
	List<BaseDict> loadFromBaseDict(String dict_type_code);

}
