package com.pizza.order.comparator;

import java.util.Comparator;
import java.util.Date;

import com.pizza.order.factory.Order;

public class OrderDateComparator implements Comparator<Order>{

	public int compare(Order o1, Order o2) {
		long order1Date =  Long.parseLong(o1.getEpochTime()) * 1000;
		long order2Date =  Long.parseLong(o2.getEpochTime()) * 1000;
		Date order1 = new Date(order1Date);
		Date order2 = new Date(order2Date);
		return order1.compareTo(order2);
	}

}
