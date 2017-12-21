package caotinging.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import caotinging.dao.base.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	//运行时class对象
	private Class<T> entityClass;
	
	//利用构造方法反射出运行时class对象
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		//this是运行时的类class对象，通过反射方法获取运行时类的泛型父类ParameterizedType
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	//注入sessionFactory
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	
	@Override
	public void save(T obj) {
		this.getHibernateTemplate().save(obj);
	}

	@Override
	public void saveOrUpdate(T obj) {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}

	@Override
	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}
	
	@Override
	public void deleteById(Serializable id) {
		T obj = findById(id);
		if(obj==null) {
			throw new RuntimeException("删除数据错误：不存在一个id="+id+"的数据对象");
		}
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public void update(T obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		//获取运行类的简单类名作为查询的实体对象
		List<T> list = (List<T>) this.getHibernateTemplate().find("from "+entityClass.getSimpleName());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
