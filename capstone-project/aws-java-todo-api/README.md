# Capstone Project

For capstone project, I decided to implement AWS Lambda functions in Java. Since we didn't cover this topic in the toDo, I felt it will be good idea to select a platform that hasn't been covered.

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
Since this is primarily a microservices project, all microservices have been deployed at https://8gmtxltswi.execute-api.us-east-2.amazonaws.com/dev
You will need postman to verify microservices.

Open postman and import https://github.com/myturf/cloud-developer/blob/master/capstone-project/aws-java-toDos-api/Capstone%20Project%20Rest%20API.postman_collection
Once you import collection you will see following requests:

* Create Course
* Get Courses
* Get Course
* Delete Course

Each of them can be independently tested and verified.
