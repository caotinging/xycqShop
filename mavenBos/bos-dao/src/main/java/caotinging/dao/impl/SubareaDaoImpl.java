package caotinging.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import caotinging.dao.ISubareaDao;
import caotinging.dao.base.impl.BaseDaoImpl;
import caotinging.domain.Subarea;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Subarea> getNDidSubarea() {
		String hql = "from caotinging.domain.Subarea where decidedzone is null";
		List<?> list = this.getHibernateTemplate().find(hql);
		if(list == null || list.size() == 0)
			return null;
		return (List<Subarea>) list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getHighchartsData() {
		String hql = "select r.province,count(*) from Subarea s left join s.region r group by r.province";
		List<?> list = this.getHibernateTemplate().find(hql);
		if(list == null || list.size() == 0)
			return null;
		return (List<Object>) list;
	}

}
