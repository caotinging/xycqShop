package caotinging.web.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Subarea;
import caotinging.service.ISubareaService;
import caotinging.utils.BosCommonUtils;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubareaService subareaService;
	private Subarea subarea = super.getModel();

	/**
	 * 获取subarea对象集合返回页面进行展示
	 * @return
	 */
	public String getPageList() {
		subareaService.getPageList(this.pageBean);
		String json = BosCommonUtils.pageBeanJson(pageBean, new String[]{"currentPage", "pageCount", "criteria", "decidedzone", "subareas"});
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		try {
			BosCommonUtils.getResponseWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return NONE;
	}
	
	/**
	 * 保存分区subarea对象
	 * @return
	 */
	public String save() {
		if (subarea != null) {
			subareaService.saveSubarea(subarea);
		}
		return "tolist";
	}

}
