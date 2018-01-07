package caotinging.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import caotinging.dao.IFunctionDao;
import caotinging.dao.base.impl.BaseDaoImpl;
import caotinging.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findAllAsTree() {
		String hql = "from Function f where f.parentFunction is null";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findFunctionsByUserId(String id) {
		String hql = "select f from Function f left join f.roles r left join r.users u where u.id=?";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

}
