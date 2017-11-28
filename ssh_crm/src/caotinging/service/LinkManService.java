package caotinging.service;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
import caotinging.domain.LinkMan;

public interface LinkManService {

	/**
	 * 保存或修改linkMan信息
	 * @param linkMan
	 * @return 
	 */
	Boolean saveOrUpdate(LinkMan linkMan);

	/**
	 * 根据查询条件和分页条件查询指定页显示内容
	 * @param criteria 查询条件
	 * @param currentPage 当前页
	 * @param pageCount 每页显示条数
	 * @return 
	 */
	PageBean<LinkMan> getLkmList(DetachedCriteria criteria, Integer currentPage, Integer pageCount);

	/**
	 * 通过linkman的id获取LinMan对象
	 * @param lkm_id
	 * @return
	 */
	LinkMan getLinkManById(Long lkm_id);

}
