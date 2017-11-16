package caotinging.test.springAop;

import org.springframework.stereotype.Component;

@Component("target")
public class Target {
	
	public void add() {
		System.out.println("添加用户！");
	}
	
	public void delete() {
		System.out.println("删除用户！");
	}
	
	public void edit() {
		System.out.println("更改用户！");
	}
	
	public void find() {
		System.out.println("查找用户！");
	}
}
