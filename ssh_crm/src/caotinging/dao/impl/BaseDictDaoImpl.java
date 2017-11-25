package caotinging.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import caotinging.dao.BaseDictDao;
import caotinging.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {
	
	@Override
	public List<BaseDict> loadFromBaseDict(String dict_type_code) {
		DetachedCriteria criteria = DetachedCriteria.forClass(BaseDict.class);
		criteria.add(Restrictions.eq("dict_type_code", dict_type_code));
		
		List<BaseDict> baseDictList = findByCriteria(criteria);
		
		return baseDictList;
	}
}
