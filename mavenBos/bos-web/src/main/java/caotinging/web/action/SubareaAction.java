package caotinging.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Subarea;
import caotinging.service.ISubareaService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISubareaService subareaService;
	private Subarea subarea = super.getModel();

	/**
	 * 保存分区subarea对象
	 * 
	 * @return
	 */
	public String save() {
		if (subarea != null) {
			subareaService.saveSubarea(subarea);
		}
		return "tolist";
	}

}
