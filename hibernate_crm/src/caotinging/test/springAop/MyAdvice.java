package caotinging.test.springAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("myAdvice")
public class MyAdvice {
	
	@Pointcut("execution(* caotinging.test.springAop.Target.*(..))")
	public void pc(){}
	
	@Before("execution(* caotinging.test.springAop.Target.*(..))")
	public void before() {
		System.out.println("这是前置方法！");
	}
	
	@AfterReturning("MyAdvice.pc()")
	public void afterReturning() {
		System.out.println("这是后置无异常方法！");
	}
	
	@AfterThrowing("MyAdvice.pc()")
	public void afterThorwing() {
		System.out.println("这是异常抛出方法！");
	}
	
	@After("MyAdvice.pc()")
	public void after() {
		System.out.println("这是一定会发生的后置方法！");
	}
	
	@Around("MyAdvice.pc()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("这是环绕方法的前置语句！");
		Object object = pjp.proceed();
		System.out.println("这是环绕方法的后置语句！");
		return object;
	}
}
