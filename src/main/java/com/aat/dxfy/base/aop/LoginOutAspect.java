package com.aat.dxfy.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * @time 2014-01-05
 * @author xingle
 * @version v1.0
 * @function 对用户的登录业务进行监控测试
 * @info java spring aop  
 *
 */
@Component
@Aspect
public class LoginOutAspect {

	/**
	 * @info 对查询用户的记录
	 */
	@Pointcut("execution(* com.aat.dxfy.base.service..*.queryUserLogin(..))")
	public void cutLoginOut() {
	}

	/**
	 * @info 进行记录
	 * @param jp
	 */
	@After("cutLoginOut()")
	public void after(JoinPoint jp) {
		//System.out.println("" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
		Object[] args = jp.getArgs();
		if(null!=args&&args.length>0){
			System.out.println(args[0]+" 被查询了...");
		}
	}
	
	//
}
