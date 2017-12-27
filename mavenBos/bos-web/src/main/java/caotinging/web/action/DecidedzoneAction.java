package caotinging.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Decidedzone;
import caotinging.domain.Staff;
import caotinging.domain.Subarea;
import caotinging.service.IDecidedzoneService;
import caotinging.utils.BosCommonUtils;
import caotinging.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	private static final long serialVersionUID = 1L;

	private String staffid;
	private String[] subareaid;
	
	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String[] getSubareaid() {
		return subareaid;
	}

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	/**
	 * 保存新的定区对象
	 * @return
	 */
	public String save() {
		try{
			decidedzoneService.saveDecidedzone(this.getModel(), staffid, subareaid);
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "tolist";
	}
	
	/**
	 * 获取还没有分配定区的分区集合
	 * @return
	 * @throws IOException 
	 */
	public String getSubareaList() throws IOException {
		try{
		List<Subarea> list = decidedzoneService.getNDidSubarea();
		
		//将staff集合转为json：只保留id和name属性
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"region", "decidedzone", "startnum", "endnum", "single"});
		
		String json = JSONArray.fromObject(list, config).toString();
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		BosCommonUtils.getResponseWriter().print(json);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 获取没有作废的取派员的信息
	 * @return
	 * @throws IOException 
	 */
	public String getStaffList() throws IOException {
		List<Staff> list = decidedzoneService.getNDelStaff();
		
		//将staff集合转为json：只保留id和name属性
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"telephone", "station", "haspda", "deltag", "standard", "decidedzones"});
		
		String json = JSONArray.fromObject(list, config).toString();
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		BosCommonUtils.getResponseWriter().print(json);
		
		return NONE;
	}
}
