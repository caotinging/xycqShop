package caotinging.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import caotinging.utils.BosCommonUtils;
import caotinging.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private static final long serialVersionUID = 1L;
	protected static final String HOME = "home";
	protected static final String LOGIN = "login";
	protected static final String LIST = "list";
	protected PageBean<T> pageBean = new PageBean<T>();
	protected DetachedCriteria criteria = null;
	
	public void setRows(Integer rows) {
		if(rows == null) {
			rows = 0;
		}
		pageBean.setPageCount(rows);
	}
	
	public void setPage(Integer page) {
		if(page == null) {
			page = 1;
		}
		pageBean.setCurrentPage(page);
	}
	
	public void setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
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
	
	public void java2JsonWrite(List<?> list, String[] excludeStrs) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludeStrs);
		String json = JSONArray.fromObject(list, config).toString();
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		try {
			BosCommonUtils.getResponseWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("java2JsonWrite error!");
		}
	}
	
	public void java2JsonWrite(Object obj, String[] excludeStrs) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludeStrs);
		String json = JSONObject.fromObject(obj,config).toString();
		BosCommonUtils.getResponse().setContentType("application/json;charset=utf-8");
		try {
			BosCommonUtils.getResponseWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("java2JsonWrite error!");
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
