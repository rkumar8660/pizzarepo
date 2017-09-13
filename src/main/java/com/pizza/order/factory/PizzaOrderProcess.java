package com.pizza.order.factory;

public interface PizzaOrderProcess {
	public void processOrder(String sourceFileOrUserName,String destinationFileOrPassword,String order) throws Exception;
}
