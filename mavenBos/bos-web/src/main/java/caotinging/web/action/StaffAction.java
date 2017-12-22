package caotinging.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Staff;
import caotinging.service.IStaffService;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	private static final long serialVersionUID = 1L;

	private Staff staff = super.get();
	@Autowired
	private IStaffService staffService;
	
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
	
}
