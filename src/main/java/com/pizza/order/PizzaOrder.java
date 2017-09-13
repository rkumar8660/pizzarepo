package com.pizza.order;

import org.apache.log4j.Logger;

import com.pizza.order.factory.PizzaOrderProcess;
import com.pizza.order.factory.PizzaOrderProcessFactory;

public class PizzaOrder {
	
	private final static Logger logger = Logger.getLogger(PizzaOrder.class);
	
	public static void main(String[] arg) throws Exception {
		
		PizzaOrder pizzaOrder = new PizzaOrder();
		pizzaOrder.initiatePizzaOrder();
		
	}
	
	public String initiatePizzaOrder() {
		String message = "success";
		String sourceFile = System.getProperty("sourceFile");
		String destinationFile = System.getProperty("destinationFile");
		String operation =  System.getProperty("operation");
		String orderBy = System.getProperty("orderBy");
		String username = System.getProperty("username");
		String password = System.getProperty("password");

		
		if(operation != null && "file".equalsIgnoreCase(operation)){
			if(sourceFile == null || destinationFile == null){
				//displayUsage();
				message = "source or destination file is null";
			}
			else{
				message = processOrder(orderBy,sourceFile,destinationFile);
			}
		}
		else if(operation != null && "db".equalsIgnoreCase(operation)){
			if(username == null || password == null){
				//displayUsage();
				message ="username or password is null";
			}else{
				processDBOrder(orderBy,username,password);
			}
		}
		else if(operation != null){
			//displayUsage();
			message = "invalid operation";
		}
		else if(operation == null){
			operation = "file" ;//defaulted to File Operation
			if(sourceFile == null  || destinationFile == null){
				message = "source or destination file is null"; 
				//displayUsage();
			}
			else{
				message = processOrder(orderBy,sourceFile,destinationFile);
			}
		}
		logger.debug("\nOutput::##########################################"+message);
		displayUsage();
		
		return message;

	}
	
	public String processOrder(String orderBy,String sourceFile,String destinationFile){
		String message = "success";
		try{
			if(orderBy == null){
				orderBy = "epoch";
				PizzaOrderProcess  pizzaOrderProcess = PizzaOrderProcessFactory.getPizzaOrderProcess("file");
				pizzaOrderProcess.processOrder(sourceFile, destinationFile,orderBy);
			}
			else{
				if(!(("epoch".equalsIgnoreCase(orderBy)) || ("name".equalsIgnoreCase(orderBy)))){
					message = "Invalid orderBy Parameter::"+orderBy;
					displayUsage();
				}
				else{
					PizzaOrderProcess  pizzaOrderProcess = PizzaOrderProcessFactory.getPizzaOrderProcess("file");
					pizzaOrderProcess.processOrder(sourceFile, destinationFile,orderBy);
				}
			}

		}
		catch(Exception exp){
			message = "fail";
		}
		//logger.debug("Message::"+message);
		return message;
	}
	
	public void processDBOrder(String orderBy,String username,String password) {
		try{
			if(orderBy == null){
				orderBy = "epoch";
				PizzaOrderProcess  pizzaOrderProcess = PizzaOrderProcessFactory.getPizzaOrderProcess("db");
				pizzaOrderProcess.processOrder(username, password,orderBy);
			}
			else{
				if(!("epoch".equalsIgnoreCase(orderBy)) || ("name".equalsIgnoreCase(orderBy))){
					//logger.debug("Invalid orderBy Parameter::"+orderBy);
					displayUsage();
				}
				else{
					PizzaOrderProcess  pizzaOrderProcess = PizzaOrderProcessFactory.getPizzaOrderProcess("db");
					pizzaOrderProcess.processOrder(username, password,orderBy);
				}
			}

		}
		catch(Exception ex){
			
		}
	}
	
	private static void displayUsage(){
//		logger.debug("\n###############################################     START               ######################################################");
//		logger.debug("\nNo Operation Performed: See Usage below");
//		logger.debug("\njava -DsourceFile=C:/work/pizzaorder/sample_data_ordered.txt -DdestinationFile=C:/work/pizzaorder/sample_data_new.txt -jar pizzaOrder-0.0.1-SNAPSHOT.jar");
//		logger.debug("\njava -DsourceFile=C:/work/pizzaorder/sample_data_ordered.txt -DdestinationFile=C:/work/pizzaorder/sample_data_new.txt -DorderBy=epoch -Doperation=file -jar  pizzaOrder-0.0.1-SNAPSHOT.jar");
//		logger.debug("\njava  -Dusername=raj -Dpassword=test -DorderBy=name -Doperation=db -jar  pizzaOrder-0.0.1-SNAPSHOT.jar");
//		logger.debug("\n###############################################      END				  #######################################################"); 
	}
	
}
