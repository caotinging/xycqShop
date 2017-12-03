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
	private Integer page_Count;
	private Integer curr_Page;
	
	public Integer getPage_Count() {
		return page_Count;
	}

	public void setPage_Count(Integer page_Count) {
		this.page_Count = page_Count;
	}

	public Integer getCurr_Page() {
		return curr_Page;
	}

	public void setCurr_Page(Integer curr_Page) {
		this.curr_Page = curr_Page;
	}

	@Resource(name="saleVisitService")
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}
	
	public String updateSv() {
		if(saleVisit == null) {
			return "error";
		}
		
		saleVisitService.updateSaleVisit(saleVisit);
		
		return "tosvList";
	}
	
	/**
	 * 修改saleVisit信息
	 * @return
	 */
	public String modifySv() {
		if(saleVisit == null && saleVisit.getVisit_id() == null) {
			return null;
		}
		SaleVisit sv = saleVisitService.getSaleVisitById(saleVisit.getVisit_id());
		ActionContext.getContext().put("saleVisit", sv);
		return "modify";
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
		if(saleVisit != null && saleVisit.getUser() != null && saleVisit.getUser().getUser_id() != null) {
			criteria.add(Restrictions.eq("user.user_id", saleVisit.getUser().getUser_id()));
		}
		
		PageBean<SaleVisit> pageBean = saleVisitService.getPageBeanOfSaleVisit(criteria, curr_Page, page_Count);
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
