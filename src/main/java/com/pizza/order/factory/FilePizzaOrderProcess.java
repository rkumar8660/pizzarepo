package com.pizza.order.factory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pizza.order.PizzaOrder;
import com.pizza.order.comparator.OrderDateComparator;
import com.pizza.order.comparator.OrderNameComparator;

public class FilePizzaOrderProcess implements PizzaOrderProcess {
	
	private final static Logger logger = Logger.getLogger(FilePizzaOrderProcess.class);

	/**
	 * This method process orders existing in the give file and arrange it in order.
	 * @param sourceFile where to read the pizza order
	 * @param destinationFile where to write the order in lexicographical
	 */
	public void processOrder(String sourceFile,String destinationFile,String orderBy) throws Exception {
		if(sourceFile != null && destinationFile != null){
			List<Order> ordersList = readOrder(sourceFile);
			if("epoch".equalsIgnoreCase(orderBy)){
				Collections.sort(ordersList,new OrderDateComparator());  //Option to arrange in lexicographical order -  datetime
			}
			else{
				Collections.sort(ordersList,new OrderNameComparator()); //arrange in lexicographical order - order name
			}
			writeOrder(ordersList,destinationFile);
		}
		else{
			throw new Exception("Necessary Input is not available");
		}
	}
	
	private List<Order> readOrder(String sourceFile) throws Exception{
		Scanner sourceFilescanner = null;
		Scanner lineScanner = null;
		List<Order> ordersList = new ArrayList<Order>();
	    try {
	    	sourceFilescanner = new Scanner(new File(sourceFile));
	    	int index = 0;
		    while (sourceFilescanner.hasNextLine()) {
		    	if(index == 0){
		    		sourceFilescanner.nextLine();
		    		index++;
		    		continue;
		    	}

		    	index++;
		    	String fileLine = sourceFilescanner.nextLine();
		    	if(fileLine != null && fileLine.trim().length() == 0){
		    		continue;
		    	}
	           lineScanner = new Scanner(fileLine);
	           String name =  lineScanner.next();
	           String epochTime = lineScanner.next();
	           Order order = new Order(name,epochTime);
	           ordersList.add(order);
		    }
		    lineScanner.close();
		    sourceFilescanner.close();
	    } catch (FileNotFoundException e) {
	    	logger.debug("File:"+sourceFile+" Not Found...");
	        throw e;
	    }
	    return ordersList;
	}
	
	private boolean writeOrder(List<Order> ordersList,String destinationFile){
		boolean success = true;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile,false));
			writer.write("Order\t\t\tTime");
			writer.newLine();
			Iterator<Order> ordersIterator=ordersList.iterator();  
		    while(ordersIterator.hasNext()){  
			   Order order= ordersIterator.next();  
			   writer.write(order.getName()+"\t\t\t"+convertEpochSec(Long.parseLong(order.getEpochTime()),"dd/MM/yyyy HH:mm:ss"));
			   writer.newLine();
		   } 
		    writer.close();
		} catch (IOException e) {
			success = false;
		}
		return success;
		
	}
	
	public static String convertEpochSec(long epochSec, String dateFormatStr) {
	    Date date = new Date(epochSec * 1000);
	    SimpleDateFormat format = new SimpleDateFormat(dateFormatStr,
	            Locale.getDefault());
	    return format.format(date);
	    
	}
}
