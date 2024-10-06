# bank-account-api
Current account management API using Java and Spring Boot, providing endpoints for user and account creation, user information retrieval and transaction handling  

How to run the application   
- Clone the repository to your local machine
- In IntelliJ, you can simply start the server by executing the `Run 'Main.main()'` command
- By default, the server will start on port 8081, as configured in application.properties  

To test the API you can use any REST client, such as the RESTED Client extension for Chrome  
1. Create a new user  
Method: POST  
URL: http://localhost:8081/users/create  
Headers: Content-Type application/json  
Body (JSON): "id", "name", "surname"  
2. Create a new account for an existing user  
Method: POST  
URL: http://localhost:8081/accounts/create  
Headers: Content-Type application/json  
Body (JSON): "customerId", "initialCredit"  
3. Retrieve user information  
Method: GET  
URL: http://localhost:8081/users/{userId}  
Replace {userId} with the actual user ID to get the information for that user  
4. Retrieve account information  
Method: GET  
URL: http://localhost:8081/accounts/{accountId}  
Replace {accountId} with the actual account ID to get the information for that specific account


Notes:  
This application uses in-memory storage, so data will be lost when the application stops  
Console logs are enabled for error handling. You can view the logs in the console to monitor application behavior  


Unit Testing  
This application includes unit testing implemented in Groovy. You can run the tests using the Gradle tasks available in IntelliJ:  
1. Open the Gradle tool window (usually located on the right side of the IntelliJ IDE)  
2. Under the "verification" section, find the command `test`  
3. Click on `test` to run the test suite  

