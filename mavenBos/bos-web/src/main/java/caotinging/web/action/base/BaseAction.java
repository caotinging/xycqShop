package caotinging.web.action.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private static final long serialVersionUID = 1L;
	protected static final String HOME = "home";
	protected static final String LOGIN = "login";
	
	//需要实例化model：同样利用反射获取实例对象
	private T model;
	
	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> modelClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
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
