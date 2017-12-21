package caotinging.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 基本的数据库操作---->mysql maven数据库
 * @author caoting
 * @param <T>
 */
public interface IBaseDao<T> {
	
	/**
	 * 保存对象
	 * @param id
	 */
	public void save(T obj);
	
	/**
	 * 保存或者更新一个对象
	 * @param id
	 */
	public void saveOrUpdate(T obj);
	
	/**
	 * 删除一个对象
	 * @param obj
	 */
	public void delete(T obj);
	
	/**
	 * 通过id删除一个对象
	 * @param id
	 */
	public void deleteById(Serializable id);
	
	/**
	 * 通过id更新一个对象
	 * @param id
	 */
	public void update(T obj);
	
	/**
	 * 通过id获取一个对象
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	
	/**
	 * 获取数据库中对象的集合
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 通过离线criteria查询对象集合
	 * @param criteria
	 * @return
	 */
	public List<T> findByCriteria(DetachedCriteria criteria);
	
}
