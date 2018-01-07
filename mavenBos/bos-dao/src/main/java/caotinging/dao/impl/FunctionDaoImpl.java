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
	public List<Function> findMenuByUserId(String id) {
		String hql = "select distinct f from Function f left join f.roles r left join r.users u where u.id=? and f.generatemenu = '1' order by f.zindex desc";
		List<?> list = this.getHibernateTemplate().find(hql, id);
		return (List<Function>) list;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findAllMenu() {
		String hql = "select distinct f from Function f where f.generatemenu = '1' order by f.zindex desc";
		List<?> list =  this.getHibernateTemplate().find(hql);
		return (List<Function>) list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findFunctionsByUserId(String id) {
		String hql = "select f from Function f left join f.roles r left join r.users u where u.id=?";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql, id);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findAllAsTree() {
		String hql = "from Function f where f.parentFunction is null";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}
}
