package caotinging.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Decidedzone;
import caotinging.domain.Staff;
import caotinging.service.IDecidedzoneService;
import caotinging.utils.BosCommonUtils;
import caotinging.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDecidedzoneService decidedzoneService;
	
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
		
		String json = JSONArray.fromObject(list).toString();
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		BosCommonUtils.getResponseWriter().print(json);
		
		return NONE;
	}
}
