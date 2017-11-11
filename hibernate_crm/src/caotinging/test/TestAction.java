package caotinging.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return
	 */
	public String execute() {
		System.out.println("测试Struts2搭建");
		return SUCCESS;
	}
}