package caotinging.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import caotinging.domain.BaseDict;
import caotinging.service.BaseDictService;
import net.sf.json.JSONArray;

public class BaseDictAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private BaseDictService baseDictService;
	private String dict_type_code;
	
	@Override
	public String execute() throws Exception {
		if(dict_type_code != null) {
			List<BaseDict> baseDictList = baseDictService.loadFromBaseDict(dict_type_code);
			
			String json = JSONArray.fromObject(baseDictList).toString();
			
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		}
		return null;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	@Resource(name="baseDictService")
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
}
