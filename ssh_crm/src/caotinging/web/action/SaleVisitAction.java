package caotinging.web.action;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.beans.PageBean;
import caotinging.domain.SaleVisit;
import caotinging.service.SaleVisitService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private static final long serialVersionUID = 1L;
	
	private SaleVisit saleVisit = new SaleVisit();
	private SaleVisitService saleVisitService;
	private Integer pageCount;
	private Integer currentPage;
	
	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@Resource(name="saleVisitService")
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	
	/**
	 * 获取客户拜访记录列表
	 * @return
	 */
	public String svList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		
		if(saleVisit != null && saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
			criteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		
		PageBean<SaleVisit> pageBean = saleVisitService.getPageBeanOfSaleVisit(criteria, pageCount, currentPage);
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "svList";
	}

	/**
	 * 保存salevisit
	 * @return
	 */
	public String saveSV() {
		if(saleVisit != null) {
			saleVisitService.saveSVByObj(saleVisit);
			return "tosvList";
		}else {
			return null;
		}
	}

	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
}
