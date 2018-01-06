package caotinging.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import caotinging.dao.IFunctionDao;
import caotinging.dao.base.impl.BaseDaoImpl;
import caotinging.domain.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@Override
	public List<Function> findAllAsTree() {
		String hql = "from Function f where f.parentFunction is null";
		return (List<Function>) this.getHibernateTemplate().find(hql, null);
	}

}
