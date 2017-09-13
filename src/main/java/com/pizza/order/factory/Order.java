package com.pizza.order.factory;

import java.util.Date;

public class Order {
	
	private String name;
	private String epochTime;
	
	public Order(String name, String epochTime){
		this.name = name;
		this.epochTime = epochTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(String epochTime) {
		this.epochTime = epochTime;
	}

	
	
	

}
