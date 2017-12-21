package caotinging.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import caotinging.domain.User;
import caotinging.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;

	private User user = super.get();
	
	
}
