package caotinging.testws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CXFTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		HelloService proxy = (HelloService) context.getBean("myClient");
		String string = proxy.sayHello("tom");
		System.out.println(string);
	}
}
