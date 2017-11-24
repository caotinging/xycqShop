package caotinging.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import caotinging.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<?> tClass;
	
	public BaseDaoImpl() {
		//获取运行时T的class类型
		//1.获取当前类型的带有泛型类型的父类：当前运行不是这个BaseDapImpl而是继承了这个类的子类，因此这个方法获取的实际上是BaseDaoImpl
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//2.获取BaseDaoImpl运行期间的泛型类型
		this.tClass = (Class<?>) type.getActualTypeArguments()[0];
//		this.getHibernateTemplate() = super.getHibernateTemplate();
		//这样也是没有用的，因为super.getHibernateTemplate();也是返回的this.getHibernateTemplate();
	}
	
	@Override
	public Boolean save(T t) {
		try{
			this.getHibernateTemplate().save(t);
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delete(T t) {
		try{
			this.getHibernateTemplate().delete(t);
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delete(Serializable id) {
		try{
			T t = this.findById(id);
			this.getHibernateTemplate().delete(t);
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean update(T t) {
		try{
			this.getHibernateTemplate().update(t);
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Long getCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<?> list = this.getHibernateTemplate().findByCriteria(criteria);
		criteria.setProjection(null);
		
		if(list != null && list.size() >0) {
			return (Long) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Long getTotalCount() {
		List<?> list = this.getHibernateTemplate().find("select count(*) from "+tClass.getName());
		
		if(list != null && list.size() >0) {
			return (Long) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<T> getList(DetachedCriteria criteria, Integer start, Integer count) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, start, count);
		
		if(list != null && list.size() >0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() >0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplate().find("from "+tClass.getName());
		if(list != null && list.size() >0) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public T findById(Serializable id) {
		//获取运行时T的class类型
		@SuppressWarnings("unchecked")
		T res = (T) this.getHibernateTemplate().get(tClass, id);
		return res;
	}

}
