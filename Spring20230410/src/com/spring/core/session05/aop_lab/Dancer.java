package com.spring.core.session05.aop_lab;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Dancer implements Performance{

	@Override
	public String perform() {
		// 業務邏輯
		System.out.println("舞者開始表演：跳舞, 轉圈, 翻滾...");
		
		if(new Date().getTime() % 3 == 0) {
			// 發生意外
			throw new RuntimeException("舞者跌倒");	
		}else {
			// ... 繼續跳
		}
		//...
		System.out.println("舞者表演結束");
		
		return "Hello World!!";
	}

}
