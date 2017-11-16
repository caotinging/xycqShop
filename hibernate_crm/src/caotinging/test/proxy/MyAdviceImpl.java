package caotinging.test.proxy;

public class MyAdviceImpl implements MyAdvice {

	@Override
	public void add() {
		System.out.println("测试动态代理！");

	}

}
