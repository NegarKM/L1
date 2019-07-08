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
