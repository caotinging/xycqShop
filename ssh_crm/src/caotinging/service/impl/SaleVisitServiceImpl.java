package caotinging.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
import caotinging.dao.SaleVisitDao;
import caotinging.domain.SaleVisit;
import caotinging.service.SaleVisitService;

public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao saleVisitDao;

	@Resource(name="saleVisitDao")
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}
	
	@Override
	public SaleVisit getSaleVisitById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}
	
	@Override
	public PageBean<SaleVisit> getPageBeanOfSaleVisit(DetachedCriteria criteria, Integer currentPage, Integer pageCount) {
		Integer count = saleVisitDao.getCount(criteria).intValue();
		if(count != 0) {
			PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>(currentPage, pageCount, count);
			
			List<SaleVisit> list = saleVisitDao.getList(criteria, pageBean.getStart(), pageBean.getPageCount());
			pageBean.setBeanList(list);
			
			return pageBean;
		}else
			return null;
	}
	
	@Override
	public void saveSVByObj(SaleVisit saleVisit) {
		Boolean res = saleVisitDao.save(saleVisit);
		if(!res) {
			throw new RuntimeException("客户拜访记录保存失败！");
		}
	}

	@Override
	public void updateSaleVisit(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
}
