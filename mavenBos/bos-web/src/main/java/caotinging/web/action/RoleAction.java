package caotinging.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Role;
import caotinging.service.IRoleService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	@Autowired
	private IRoleService roleService;
	private String functionIds;
	
	/**
	 * 保存一个新的role对象
	 */
	public String save() {
		roleService.save(getModel(), functionIds);
		return "tolist";
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
}
