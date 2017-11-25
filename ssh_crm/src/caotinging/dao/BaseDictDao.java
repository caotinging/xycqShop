package caotinging.dao;

import java.util.List;

import caotinging.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict> {

	/**
	 * 从数据库中根据dict_type_code获取一组数据
	 * @param dict_type_code
	 * @return
	 */
	List<BaseDict> loadFromBaseDict(String dict_type_code);

}
