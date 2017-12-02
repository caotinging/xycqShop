package caotinging.web.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.domain.SaleVisit;
import caotinging.service.SaleVisitService;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private static final long serialVersionUID = 1L;
	
	private SaleVisit saleVisit = new SaleVisit();
	private SaleVisitService saleVisitService;
	
	@Resource(name="saleVisitService")
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
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
