package caotinging.web.action;

import java.io.IOException;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Staff;
import caotinging.service.IStaffService;
import caotinging.utils.BosCommonUtils;
import caotinging.utils.PageBean;
import caotinging.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	private static final long serialVersionUID = 1L;

	private Staff staff = super.get();
	@Autowired
	private IStaffService staffService;
	private Integer page;
	private Integer rows;
	private String ids;
	
	/**
	 * 更改staff对象的信息
	 * @return
	 */
	public String updateStaff() {
		//staff中保存更新的信息
		if(staff != null && staff.getId() != null) {
			try{
				staffService.updateStaff(staff);
			}catch(Exception ex) {
				ex.printStackTrace();
				return ERROR;
			}
		}
		return LIST;
	}

	/**
	 * 对取派员进行逻辑删除操作
	 * @return
	 */
	public String deleteStaff(){
		if(ids != null ){
			try{
				staffService.deleteByIds(ids);
			}catch(Exception ex) {
				ex.printStackTrace();
				return ERROR;
			}
		}
		return LIST;
	}
	
	/**
	 * 获取取派员列表
	 * @return
	 */
	public String getList() {
		String json = "";
		
		if(page != null && rows != null) {
			PageBean<Staff> pageBean = new PageBean<Staff>();
			pageBean.setCurrentPage(page);
			pageBean.setPageCount(rows);
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
			//没有其他的筛选条件，直接设置
			pageBean.setCriteria(criteria);
			
			try{
				staffService.getList(pageBean);
				
				//把查询的数据封装成json格式
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String[]{"currentPage","pageCount","criteria"});
				
				json = JSONObject.fromObject(pageBean,config).toString();
				
			}catch(Exception ex) {
				return ERROR;
			}
		}
		
		try {
			BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
			BosCommonUtils.getResponseWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}
	
	/**
	 * 保存取派员信息
	 * @return
	 */
	public String saveStaff() {
		if(staff != null ) {
			//判断PDA的值是否为空
			if(staff.getHaspda()==null) {
				staff.setHaspda("0");
			}
			try{
				staffService.saveStaff(staff);
			}catch(Exception ex) {
				ex.printStackTrace();
				return ERROR;
			}
		}
		
		return "tolist";
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
