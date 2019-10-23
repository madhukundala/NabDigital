# Getting Started

Install the gradle for building this project 
Install the postman to call the api 

POST method  (http://localhost:8080/api/products) With request body 
    
    
    {
        "productName": "MadhuProduct",
        "productValue": "1.0$",
        "productCategory": "fruit"
    }
    
    

    
GET method  http://localhost:8080/api/products 

DELETE Method http://localhost:8080/api/product/2 

Get by category http://localhost:8080/api/products/fruit


Initially use the post method to insert records or updated records . 

Then the front app can access it . 

* This project is developed using SpringBoot and Rest microservices .
 

* Test/Junits are implemented using mockito .

Exception handling needs to be implemented using controller advice . 

Command to build the project  : gradle clean build . 
* To run project using command line 

./gradlew bootrun
 


### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/gradle-plugin/reference/html/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

