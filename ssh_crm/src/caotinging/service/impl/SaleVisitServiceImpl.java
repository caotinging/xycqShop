package caotinging.service.impl;

import javax.annotation.Resource;

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
	public void saveSVByObj(SaleVisit saleVisit) {
		Boolean res = saleVisitDao.save(saleVisit);
		if(!res) {
			throw new RuntimeException("客户拜访记录保存失败！");
		}
	}

}
