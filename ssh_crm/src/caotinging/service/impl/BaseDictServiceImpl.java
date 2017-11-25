package caotinging.service.impl;

import java.util.List;

import javax.annotation.Resource;

import caotinging.dao.BaseDictDao;
import caotinging.domain.BaseDict;
import caotinging.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao baseDictDao; 
	
	@Resource(name="baseDictDao")
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> loadFromBaseDict(String dict_type_code) {
		return baseDictDao.loadFromBaseDict(dict_type_code);
	}

}
