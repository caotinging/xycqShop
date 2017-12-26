package caotinging.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.domain.Region;
import caotinging.utils.PageBean;

public interface IRegionService {

	/**
	 * xls文件上传保存或更新到数据库
	 * @param regionList
	 */
	void fileUpload(List<Region> regionList);

	/**
	 * 获取region对象集合
	 * @param pageBean
	 */
	void getList(PageBean<Region> pageBean);

	/**
	 * 通过离线查询条件获取符合条件的region集合
	 * @param criteria
	 * @return
	 */
	List<Region> getListByCriteria(DetachedCriteria criteria);

}
