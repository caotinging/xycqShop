package caotinging.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Role;
import caotinging.service.IRoleService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IRoleService roleService;
	private String functionIds;
	
	/**
	 * 获取所有角色信息
	 * @return
	 */
	public String findAllRole() {
		List<Role> list = roleService.findAll();
		java2JsonWrite(list, new String[]{"functions","users","description","functionsName"});
		return NONE;
	}
	
	/**
	 * 获取角色列表数据
	 * @return
	 */
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		java2JsonWrite(pageBean, new String[]{"currentPage","pageCount","criteria","functions","users"});
		return NONE;
	}
	
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
