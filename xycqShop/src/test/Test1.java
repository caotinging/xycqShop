package test;

import org.junit.Test;

import caotinging.utils.BeanFactory;

public class Test1 {

	@Test
	public void test1() {
		Object obj = BeanFactory.getBean("adminDao");
		System.out.println(obj);
	}
}
