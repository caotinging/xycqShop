package caotinging.test.springAop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:caotinging/test/springAop/applicationContext.xml")
public class TestDemoAop {
	
	private Target target;
	
	@Test
	public void fun1() {
		target.delete();
	}

	@Resource(name="target")
	public void setTarget(Target target) {
		this.target = target;
	}
	
}
