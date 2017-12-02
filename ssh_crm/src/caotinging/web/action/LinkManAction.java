package caotinging.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.beans.PageBean;
import caotinging.domain.LinkMan;
import caotinging.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private static final long serialVersionUID = 1L;
	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;
	private String linkManName;
	private Integer currentPage;
	private Integer pageCount;
	private Long cust_id;
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	@Resource(name="linkManService")
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
/*	public String getLkmListByCustId() {
		if (cust_id != null) {
			DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
			criteria.add(Restrictions.eq("customer.cust_id", cust_id));

			List<LinkMan> list = linkManService.getLkmListByCustId(criteria);
			
			 * TargetStrategy ts = new TargetStrategy(Customer.class);
			 * //这里表示仅转换Student中的id和name属性 ts.setFields(new String[] {"cust_id",
			 * "cust_name"}); ts.setReverse(true);
			 

			// 实例化hibernate的代理类
			Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
			String json = gson.toJson(list);
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("response.getWrite().write error！");
			}
		}
		return null;
	}*/
	
	/**
	 * 修改联系人信息
	 * @return
	 */
	public String modifyLkm() {
		if(linkMan != null && linkMan.getLkm_id()!=null) {
			LinkMan lkm = linkManService.getLinkManById(linkMan.getLkm_id());
			ActionContext.getContext().put("linkman", lkm);
		} else{
			return "error";
		}
		return "modifyLkm";
	}

	/**
	 * 根据客户id/联系人姓名模糊查询获取联系人列表
	 * @return
	 */
	public String lkmList() {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(StringUtils.isNotBlank(linkManName)) {
			criteria.add(Restrictions.like("lkm_name", "%"+linkManName+"%"));
		}
		//根据选择的客户id获取联系人列表
		if(linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() != null) {
			criteria.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		PageBean<LinkMan> pageBean = linkManService.getLkmList(criteria, currentPage, pageCount);
		ActionContext.getContext().put("pageBean", pageBean);
		
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

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
}
