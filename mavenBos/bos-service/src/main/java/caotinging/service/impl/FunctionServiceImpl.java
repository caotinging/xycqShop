package caotinging.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caotinging.dao.IFunctionDao;
import caotinging.domain.Function;
import caotinging.service.IFunctionService;
import caotinging.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	
	@Autowired
	private IFunctionDao functionDao;

	@Override
	public List<Function> findAllAsTree() {
		List<Function> list = functionDao.findAllAsTree();
		return list;
	}

	@Override
	public void save(Function model) {
		//页面中的父功能点单选框，可能没有值、但是会传递一个空字符串过来，而父功能点又是外键，空字符串没有对应会报错
		//因此这里需要做一下判断
		if(!StringUtils.isNotBlank(model.getParentFunction().getId())) {
			model.getParentFunction().setId(null);
		}
		functionDao.save(model);
	}

	@Override
	public void pageQuery(PageBean<Function> pageBean) {
		functionDao.queryPageBeanList(pageBean);
	}

	@Override
	public List<Function> findAllMenu() {
		return functionDao.findAllMenu();
	}

	@Override
	public List<Function> findMenuByUserId(String id) {
		return functionDao.findMenuByUserId(id);
	}

}
