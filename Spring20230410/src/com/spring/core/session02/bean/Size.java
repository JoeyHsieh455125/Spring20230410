package com.spring.core.session02.bean;

public class Size {
	private String name;

	public Size() {
		super();
	}

	public Size(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Size [name=" + name + "]";
	}
	
}
