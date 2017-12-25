package caotinging.web.action.base;

import java.lang.reflect.ParameterizedType;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.utils.PageBean;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private static final long serialVersionUID = 1L;
	protected static final String HOME = "home";
	protected static final String LOGIN = "login";
	protected static final String LIST = "list";
	protected PageBean<T> pageBean = new PageBean<T>();
	protected DetachedCriteria criteria = null;
	
	public void setRows(Integer rows) {
		pageBean.setPageCount(rows);
	}
	
	public void setPage(Integer page) {
		pageBean.setCurrentPage(page);
	}
	
	//需要实例化model：同样利用反射获取实例对象
	private T model;
	
	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> modelClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		
		//为离线查询对象指定实体类
		criteria = DetachedCriteria.forClass(modelClass);
		pageBean.setCriteria(criteria);
		
		try {
			model = modelClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		return model;
	}

	public T get() {
		return model;
	}

}
