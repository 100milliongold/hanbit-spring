package com.hanbit.spring;

public class ThirdBean {
	
	

	private String name;
	private FirstBean firstBean;
	private SecondBean secondBean;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	public void setFirstBean(FirstBean firstBean) {
		this.firstBean = firstBean;
	}

	public void setSecondBean(SecondBean sencondBean) {
		this.secondBean = sencondBean;
	}
	
	public void printCalc(int x, int y) {
		System.out.println(firstBean.plus(x, y));
		System.out.println(secondBean.minus(x, y));
	}

}
