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
