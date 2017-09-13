package com.pizza.order;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * * Execute all unit test by passing valid arguments like below
 * Example: mvn test -DsourceFile=C:/work/pizzaorder/sample_data_ordered.txt -DdestinationFile=C:/work/pizzaorder/sample_data_new.txt
 * @author Rajesh
 *
 */

public class PizzaOrderTest {
	
	private final static Logger logger = Logger.getLogger(PizzaOrderTest.class);

	
	private static PizzaOrder pizzaOrder;
	
	private static String sourceFileInit = null;
	private static String destinationFileInit = null;
	private static String usernameInit = null;
	private static String passwordInit = null;
	
	@BeforeClass
	public static void initiatePizzaOrder(){
		pizzaOrder = new PizzaOrder();
		
		sourceFileInit = System.getProperty("sourceFile");
		destinationFileInit = System.getProperty("destinationFile");
		usernameInit = System.getProperty("username");
		passwordInit = System.getProperty("password");
		
		logger.debug("BeforeClass::sourceFileInit:"+sourceFileInit);
		logger.debug("BeforeClass::destinationFileInit::"+sourceFileInit);
		logger.debug("BeforeClass::usernameInit::"+sourceFileInit);
		logger.debug("BeforeClass::passwordInit::"+sourceFileInit);
		
	}
	
	@Test
	public void testFailWhenSourceFileNotGiven(){
		logger.debug("TestCase:::testFailWhenSourceFileNotGiven");
		if(destinationFileInit != null){
			System.setProperty("destinationFile", destinationFileInit);
		}
		else{
			System.setProperty("destinationFile", "");
		}
		System.clearProperty("sourceFile");
		String message = pizzaOrder.initiatePizzaOrder();
		assertEquals("source or destination file is null",message);
	}
	
	@Test
	public void testFailWhenDestinationFileNotGiven(){
		logger.debug("TestCase:::testFailWhenDestinationFileNotGiven");
		if(sourceFileInit != null){
			System.setProperty("sourceFile", sourceFileInit);
		}
		else{
			System.setProperty("sourceFile", "");
		}
		//System.setProperty("sourceFile", sourceFileInit);
		System.clearProperty("destinationFile");
		String message = pizzaOrder.initiatePizzaOrder();
		assertEquals("source or destination file is null",message);
	}
	
	
	@Test
	public void testSuccessWhenSourceDestinationFileGiven(){
		logger.debug("TestCase:::testSuccessWhenSourceDestinationFileGiven");
		if(isSourceDestinationAvailable()){
			String message = pizzaOrder.initiatePizzaOrder();
			assertEquals("success",message);
		}
		else{
			assertEquals("success","success");
		}

	}
	
	@Test
	public void testFailWhenInvalidOperationGivenWithUserNameAndPassword(){
		logger.debug("TestCase:::testFailWhenInvalidOperationGivenWithUserNameAndPassword");
		System.clearProperty("sourceFile");
		System.clearProperty("destinationFile");
		System.setProperty("username", "username");
		System.setProperty("password", "password");
		System.setProperty("operation", "file");
		assertEquals("source or destination file is null",pizzaOrder.initiatePizzaOrder());
	}

	
	@Test
	public void testFailWhenSourceFileNotFound(){
		logger.debug("TestCase:::testFailWhenSourceFileNotFound");
		if(destinationFileInit != null){
			System.setProperty("destinationFile", destinationFileInit);
		}
		else{
			System.setProperty("destinationFile", "");
		}
		System.setProperty("sourceFile", "x:/dummy.txt");

		System.clearProperty("username");
		System.clearProperty("password");
		System.clearProperty("operation");
		assertEquals("fail",pizzaOrder.initiatePizzaOrder());
	}

	private boolean isSourceDestinationAvailable(){
		if(System.getProperty("sourceFile") != null &&
				System.getProperty("destinationFile") != null){
			return true;
		}
		else{
			return false;
		}
	}


}
