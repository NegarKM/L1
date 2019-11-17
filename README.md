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
(I used MySQL 5.7 Command Line Client)
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


# Step 5 - Include the user info

Extend the form to capture the user name and a list of all the submissions by user. Add another method to the service to return all text submissions and display them at the bottom of the page.

Table "User" is added to database and users should be added to database.

A login page is added before /addPost.

ShowPostsApp component is added to show all of the posts added by a specific user.

Tests are extended. Authentication is added to previous integration tests.
test user with following information should be added to test database: 
- username: test
- password: 123 


New APIs:
- /basicauth : to authenticate user
- /showPosts : to retrieve all posts added by a specific user

Changed APIs:
- /echo : receive user info and timestamp as input and save in database (not only echo anymore!).

 

# Step 6 - Replying to text

Extend your solution from previous challenge to allow “responding” to already posted texts and display responses, indented, below the related text entry.

DB changes: 
- Now we change the Post entity to have a tree structure. every Post can have a comment which is a Post entity again!

Server(Backend) changes: we add two apis:
- /addComment API: it gets parentPostID and a text as a comment and save a new Post which is added to the list of comments of the given parent. The response of the API is the information of the comment (Post entity)
- /getPostsByParentPostId: as we can't show all of the posts and their comments and after any changes in comments, we can't render the whole database(!!!) so we add this API so we can get just the comments of every post we need to show to the user. it gets the parentPostId in the request and return all the comments regarding to that Post. Again the response contains a list of Post entity.

Test changes:
- Tests are extended to test new APIs

  
