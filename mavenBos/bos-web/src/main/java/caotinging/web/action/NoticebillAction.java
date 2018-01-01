package caotinging.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Noticebill;
import caotinging.service.INoticebillService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.crm.Customer;
import caotinging.utils.crm.ICustomerService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ICustomerService customerServiceProxy;
	@Autowired
	private INoticebillService noticeService;

	/**
	 * 保存新的业务通知单
	 * 
	 * @return
	 */
	public String save() {
		try{
		noticeService.saveNoticebill(getModel());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "tolist";
	}

	/**
	 * 通过客户号码获取客户信息
	 * 
	 * @return
	 */
	public String getcustomerByTel() {
		Customer customer = customerServiceProxy.getCustomerByTel(getModel().getTelephone());
		java2JsonWrite(customer, null);
		return NONE;
	}

}
