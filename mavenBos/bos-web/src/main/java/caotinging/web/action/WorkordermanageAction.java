package caotinging.web.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.Workordermanage;
import caotinging.service.IWorkordermanageService;
import caotinging.utils.BosCommonUtils;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IWorkordermanageService workordermanageService;

	public String quickSave() {
		String flag = "1";
		try {
			workordermanageService.quickSave(getModel());
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
		}
		try {
			BosCommonUtils.getResponseWriter().print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
}
