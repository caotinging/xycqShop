package caotinging.service;

import org.hibernate.criterion.DetachedCriteria;

import caotinging.beans.PageBean;
import caotinging.domain.SaleVisit;

public interface SaleVisitService {

	void saveSVByObj(SaleVisit saleVisit);

	PageBean<SaleVisit> getPageBeanOfSaleVisit(DetachedCriteria criteria, Integer pageCount, Integer currentPage);

	SaleVisit getSaleVisitById(String visit_id);

	void updateSaleVisit(SaleVisit saleVisit);

}
