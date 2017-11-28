package caotinging.web.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private static final long serialVersionUID = 1L;
	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;

	@Resource(name="linkManService")
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	public String lkmList() {
//		linkManService.getLkmList()
		
		return "lkmList";
	}
	
	/**
	 * 保存或修改linkMan信息
	 * @return
	 */
	public String saveOrUpdeteLinkMan() {
		Boolean isSuccess = linkManService.saveOrUpdate(linkMan);
		if(isSuccess)
			return "toLkmList";
		else
			return "error";
	}

	@Override
	public LinkMan getModel() {
		return linkMan;
	}
}
