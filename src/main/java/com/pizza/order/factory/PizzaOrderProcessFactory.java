package com.pizza.order.factory;

public class PizzaOrderProcessFactory {
	public static PizzaOrderProcess getPizzaOrderProcess(String orderProcessType){
		if("db".equalsIgnoreCase(orderProcessType)){
			return new DatabasePizzaOrderProcess();
		}
		else if("file".equalsIgnoreCase(orderProcessType)){
			return new FilePizzaOrderProcess();
		}
		else{
			return null;
		}
	}
}
