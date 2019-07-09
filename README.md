# Step 1 - Hello world wide web

Create a "Hello World!" web page

## Run

To run the backend:
- cd [main]/server
- mvn spring-boot:run

Open http://localhost:8080/hello-world

to run the frontend:
- cd [main]/client
- npm install
- npm start

Open http://localhost:3000/


to run the tests:
- cd [main]/server
- mvn clean test


# Step 2 - Build the back-end for Echo

Create a REST web service with a method that returns the text that is passed to it.

## Run

To run the backend:
- cd [main]/server
- mvn spring-boot:run

Open http://localhost:8080/echo?text=I%20enjoy%20learning%20new%20things!

to run the tests:
- cd [main]/server
- mvn clean test

# Step 3 - Build the frontend for receiving the post text to use Echo service

Create a web form that has a text box, and a ‘Done’ button. When ‘Done’ is clicked make AJAX call, passing the entered text to the web service which was created in the previous step. Display the response from the web service call below the text box on the form.

## Run

To run the backend:
- cd [main]/server
- mvn spring-boot:run

To run the frontend:
- cd [main]/client
- npm install
- npm start

Open http://localhost:3000

To run the tests:
- cd [main]/server
- mvn clean test

# Step 4 - Bring in the database

The web service have been extended to add a method to store text passed from the form into the database using design patterns where appropriate. In addition to saving the text, the date and time are also stored when the data is saved.


## Run

To create database in mySQL run the following commands:
- show databases;
- create database loyaltyoneinterview;
- use loyaltyoneinterview;

To see all users:
- select user from mysql.user;

To see tables, columns of tables and their contents:
- show tables;
- show columns from tablename;
- select * from tablename; 

Follow the same pattern as previous step to run the application!

For running the tests, a "test" profile and a test database was added!

