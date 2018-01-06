package caotinging.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Function;
import caotinging.service.IFunctionService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IFunctionService functionService;
	
	/**
	 * 权限分页查询
	 * @return
	 */
	public String pageQuery() {
		/*
		 * 这里出现了一个有意思的问题：分页插件传递的参数page刚好与我们压到栈顶的function model里的属性page相同
		 * 因此我们的pagebean对象的currentPage获取不到这个page参数的值
		 * 因此我们只能手动从model里获取出来赋值给pageBean
		 */
		try{
		pageBean.setCurrentPage(Integer.parseInt(getModel().getPage()));
		functionService.pageQuery(pageBean);
		//将分页数据转json
		java2JsonWrite(pageBean, new String[]{"currentPage","pageCount","criteria","parentFunction","children","roles"});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 获取所有父功能点
	 * @return
	 */
	public String findAllAsTree() {
		List<Function> list = functionService.findAllAsTree();
		java2JsonWrite(list, new String[]{"parentFunction","code","description","page","generatemenu","zindex","roles"});
		return NONE;
	}
	
	/**
	 * 保存一个新的功能/权限
	 * @return
	 */
	public String save() {
		try{
			functionService.save(getModel());
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "tolist";
	}
}
