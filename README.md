# Assignment - Mini Stock Exchange

## Technologies Used
  - **Language:** Java
  - **Database:** MySQL
  - **FrontEnd:** JSP (For Testing)
  - **Server:** Glassfish

## Project Details
1. **Initial Setup**
	1. Created a maven jersey web application project _stockexchange_ and configured to run on a glassfish server.
	2. Added dependencies to the _pom.xml_ such as junit, concurrent-junit, commondbcp2, mysql_connector
2. **Technical Details**
	1. Added a ReSTful resource to access the following API endpoints
		- DB SNAPSHOT (http://localhost:8080/stockexchange/webapi/exchange/dbsnapshot)
		- EXCHANGE ENDPOINT (http://localhost:8080/stockexchange/webapi/exchange/matchcompany?countrycode=US&category=Automobile&basebid=10)
		- LOGS SCREENSHOT (http://localhost:8080/stockexchange/webapi/exchange/logs_screenshot)

	2. Created database tables to include company details, operating countries and categories. _Refer exchange.sql_
	3. Tested the API endpoints concurrently using _concurrent-junit_.
3. **Application Run**
	1. Start the server. (Glassfish Server)
	2. Run the _stockexchange_ application on the server by RightClick the project folder, select _Run As_ and  _Run on Server_.
	3. In the run wizard select next.
	4. In the next screen move _stockexchange_ from Available section to Configured Section.
	5. Then click Finish.
	6. The application will run and open the index page (http://localhost:8080/stockexchange)
	7. In the index page two API's are exposed as links Database Snapshot and Logs Screenshot.
	8. To exchange a stock hit the endpoint using the url http://localhost:8080/stockexchange/webapi/exchange/matchcompany?countrycode=US&category=Automobile&basebid=10 with correct query parameters
	9. Results will be displayed as response on next page as plain text.
	10. Generated _log files (logs.txt)_ is available in the servers domain directory. In Glassfish server it is located in the directory **C:\java_ee_7\glassfish4\glassfish\domains\domain1\config**
	11. Tests can be run concurrently by running the _ExchangeTest.java_ in the test directory.
