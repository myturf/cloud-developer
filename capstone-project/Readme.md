# Capstone Project

For capstone project, I decided to implement AWS Lambda functions in Java. Since we didn't cover this topic, I felt it will be good idea to select a platform that hasn't been covered.

## Application 
This is a toDo catalogue application, that allows you to submit toDos and their ratings. I consists of four microservices:
* Create a toDo and provide rating
* List all toDos
* Get toDo details
* Delete a toDo


## Approach
I went through several blogs and documents and learnt that AWS provides AWS Lambda deployment package that you can add as maven dependency in your project and reference in your code.
Each of these microservice is independent of each other. In AWS Lambda Java implementation you can map each class to a service, keeping all services independent of each other.
Similarly serverless framework provides supports for java runtime.

I have included several screenshots for development, deployment and testing.

## Build and Verification 
### Backend
Back end is a java project that uses maven as build tool. Steps to build and deploy to AWS are:

* Go into `aws-java-todo-api` directory 
* Run `mvn package` , this will build `todo-api-develop.jar` that will be used by serverless framework
* run `sls deploy` and that will deploy application to AWS

### Front End
Frot end is a react based application that can be run locally. To run and test:
* Go into `client` directory 
* Run `npm install`
* Run `npm start` This will launch the application at `http://localhost:3000'

### Rest Services
Since this is primarily a microservices project, all microservices have been deployed at https://b8oru25hj7.execute-api.us-east-2.amazonaws.com/develop
You will need postman to verify microservices.

Open postman and import the postman collection from the repository. Once you import collection you will see following requests:

* Create To Do
* Get To Dos
* Delete To Do

Each of them can be independently tested and verified.

## User Interface 
Application uses basic interface that allows you to create and delete to do tasks. It is integrated with Auth0. 

### Login
![image](https://user-images.githubusercontent.com/26393091/102825034-01e2aa80-43ac-11eb-9b6c-b421407a3421.png)

### Auth0
![image](https://user-images.githubusercontent.com/26393091/102825106-22ab0000-43ac-11eb-9692-97763e62d94f.png)

### Create To Do
![image](https://user-images.githubusercontent.com/26393091/102825202-4cfcbd80-43ac-11eb-854f-7f87d4c3d59e.png)

### Delete To Do
![image](https://user-images.githubusercontent.com/26393091/102825306-803f4c80-43ac-11eb-8bed-d3333a98e4ef.png)



