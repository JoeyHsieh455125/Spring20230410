package com.spring.core.session05.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// Aspect 切面程式

@Component // 可以被 Spring 管理
@Aspect // 我是一個 Aspect 切面程式
@Order(1) // 數字越小越先被執行(預設值是 int 的最大值)
// Order 的使用情境是在有多個切面程式在同一個橫切關注點要執行時
public class MyLoggerAspect {
	// 建立一個切入點(PointCut)方法
	@Pointcut(value = "execution(* com.spring.core.session05.aop.CalcImpl.*(..))")
	public void pt() {}
	
	@Pointcut(value = "execution(* com.spring.core.session05.aop.CalcImpl.div(..))")
	public void pt2() {}
	
	// 前置通知
	// Spring EL : execution
//	@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.add(Integer, Integer))") // 切入點表達式
//	@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.div(Integer, Integer))")
//	@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.*(Integer, Integer))") // 忽略 method name
//	@Before(value = "execution(public Integer com.spring.core.session05.aop.CalcImpl.*(..))") // 忽略方法名稱與參數
//	@Before(value = "execution(public Integer com.spring.core.session05.aop.*.*(..))") // 忽略類別名稱、方法名稱與參數
//	@Before(value = "execution(* com.spring.core.session05.aop.*.*(..))") // 忽略權限、回傳值、類別名稱、方法名稱與參數
//	@Before(value = "execution(* *(..))") // 全部攔截
	@Before(value = "pt()") // 透過切入點方法配置
//	@Before(value = "pt() && !pt2()") // 支援 &&, ||, !
	public void beforeAdvice(JoinPoint joinPoint) { // JoinPoint 連接點
		String methodname = joinPoint.getSignature().getName(); // 取得連接點的方法名稱
		Object[] args = joinPoint.getArgs(); // 取得方法參數
		System.out.printf("前置通知 - 方法名稱：%s 參數：%s \n", methodname, Arrays.toString(args));
	}
	
	// 後置通知(無論是否有例外都會執行，會自動配置在 Spring-AOP 機制中的 finally 區段中)
	@After(value = "pt()")
	public void afterAdvice() {
		System.out.println("後置通知");
	}
	
	// 異常通知(可以透過 throwing 來設定異常通知的變數名稱)
	@AfterThrowing(value = "pt()", throwing = "ex")
	public void afterThrowingAdvice(Exception ex) { // 方法參數 ex 必須與 throwing = "ex" 內容名稱相同
		System.out.printf("異常通知 - ex %s \n: ", ex);
	}
	
	// 返回通知(可以透過 result 來接受到方法的回傳值)
	// 若有異常發生，則返回通知不會執行
	@AfterReturning(value = "pt()", returning = "result")
	public void afterReturningAdvice(Object result) { // 返回值型態統一是 Object
		System.out.printf("返回通知 - result : %s \n", result);
	}
}
