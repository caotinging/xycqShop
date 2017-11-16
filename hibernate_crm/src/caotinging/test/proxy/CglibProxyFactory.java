package caotinging.test.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibProxyFactory {

	public static MyAdvice getMyAdvice() {
		Enhancer enhancer = new Enhancer();
		
		enhancer.setSuperclass(MyAdviceImpl.class);
		
		enhancer.setCallback(new MethodInterceptor() {
			/**
			 * 参数：Object为由CGLib动态生成的代理类实例，
			 * Method为上文中实体类所调用的被代理的方法引用，
			 * Object[]为参数值列表，
			 * MethodProxy为生成的代理类对方法的代理引用。
			 */
			@Override
			public Object intercept(Object proxyObj, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
				System.out.println("cglib代理前置内容！");
				Object object = methodProxy.invokeSuper(proxyObj, params);
				
				System.out.println("cglib代理后置内容！");
				return object;
			}
		});
		
		MyAdvice create = (MyAdvice) enhancer.create();
		return create;
	}
	
}
