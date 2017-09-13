package com.pizza.order.comparator;

import java.util.Comparator;

import com.pizza.order.factory.Order;

public class OrderNameComparator implements Comparator<Order>{

	public int compare(Order o1, Order o2) {
		return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
	}

}
