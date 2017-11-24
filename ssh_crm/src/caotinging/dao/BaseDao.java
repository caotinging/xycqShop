package caotinging.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 提取dao层最基本的增删改查操作
 * @author caoting
 */
public interface BaseDao<T> {
	
	/**
	 * 增加一条数据
	 * @param t
	 * @return 执行结果
	 */
	public Boolean save(T t);
	
	/**
	 * 根据对象删除数据
	 * @param t
	 * @return 执行结果
	 */
	public Boolean delete(T t);
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @return 执行结果
	 */
	public Boolean delete(Serializable id); 
	
	/**
	 * 更新数据信息
	 * @param t
	 * @return 执行结果
	 */
	public Boolean update(T t);
	
	/**
	 * 根据查询条件获取该条件下数据总数
	 * @param criteria
	 * @return 符合条件的数据总个数
	 */
	public Long getCount(DetachedCriteria criteria);
	
	/**
	 * 查询所有数据的总个数
	 * @return 所有数据的总个数
	 */
	public Long getTotalCount();
	
	/**
	 * 根据给定的查询条件以及起始索引和查询个数查询数据
	 * @param criteria 查询条件
	 * @param start 起始索引
	 * @param count 查询个数
	 * @return 查询结果
	 */
	public List<T> getList(DetachedCriteria criteria, Integer start, Integer count);
	
	/**
	 * 根据查询条件查询数据
	 * @param criteria 查询条件
	 * @return 查询结果
	 */
	public List<T> findByCriteria(DetachedCriteria criteria);
	
	/**
	 * 查询数据库中所有数据
	 * @return 查询结果
	 */
	public List<T> findAll();
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return 查询结果
	 */
	public T findById(Serializable id);
}
