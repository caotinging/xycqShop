package caotinging.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IRoleDao;
import caotinging.domain.Function;
import caotinging.domain.Role;
import caotinging.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;

	@SuppressWarnings("unchecked")
	@Override
	public void save(Role model, String functionIds) {
		
		if(StringUtils.isNotBlank(functionIds)) {
			//保存role需要维护角色与权限的关系
			String[] functions = functionIds.split(",");
			for (String fId : functions) {
				Function function = new Function(fId);
				model.getFunctions().add(function);
			}
		}
		
		roleDao.save(model);
	}
}
