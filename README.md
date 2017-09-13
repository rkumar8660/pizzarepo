This application is mavenized java project and it used log4j, Junit and design patterns like Factory Design Pattern.

Create Executable Jar File
----------------------------

1) Go to Project directory and type
2) mvn clean install
3) It creates pizzaOrder-0.0.1-SNAPSHOT.jar in local repository and in target folder

Execution
----------

4) Go to target folder and execute mvn command with following parameters as explained below

	The following options can be pass as parameters while running executable jar.

	1)sourceFile
	2)destinationFile
	3)operation = [file,db] . if no operation provided defaulted to "file" . "db" operation only work with username and password parameter
	4)orderBy = [epoch,name]. epoch - arrange pizza orderBy epoch time , name - arrange pizza orderBy name. if nothing provided, defaulted to "epoch".
	5)username 
	6)password
	
Ex: from target folder

java -DsourceFile=C:/work/pizzaorder/sample_data_ordered.txt -DdestinationFile=C:/work/pizzaorder/sample_data_new.txt -jar pizzaOrder-0.0.1-SNAPSHOT.jar
java -DsourceFile=C:/work/pizzaorder/sample_data_ordered.txt -DdestinationFile=C:/work/pizzaorder/sample_data_new.txt -DorderBy=name -Doperation=file -jar  pizzaOrder-0.0.1-SNAPSHOT.jar
java  -Dusername=raj -Dpassword=test -DorderBy=name -Doperation=db -jar  pizzaOrder-0.0.1-SNAPSHOT.jar	


Test Case: Execute testcase from command prompt
-------------------------------------------------
1) Go to Project Folder and execute below command. It execute all test cases

mvn test -DsourceFile=C:/work/pizzaorder/sample_data_ordered.txt -DdestinationFile=C:/work/pizzaorder/sample_data_new.txt
