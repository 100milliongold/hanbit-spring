package com.hanbit.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FourthBean {
	
	@Autowired
	private ThirdBean thirdBean;
	
	public void print() {
		thirdBean.printCalc(7, 8);
	}
}
