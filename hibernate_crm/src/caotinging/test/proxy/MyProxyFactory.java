package caotinging.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyFactory {
	
	public MyAdvice getMyAdvice(final MyAdvice myAdvice) {
		MyAdvice instance = (MyAdvice) Proxy.newProxyInstance(myAdvice.getClass().getClassLoader(),
				myAdvice.getClass().getInterfaces(),
				new InvocationHandler(){
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("代理方法前置内容！");
						Object object = method.invoke(myAdvice, args);
						System.out.println("代理方法后置内容！");
						return object;
					}
		} );
		
		return instance;
	}
}
