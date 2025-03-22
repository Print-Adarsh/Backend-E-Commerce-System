# E-Commerce Product Management

Overview

This is a Spring Boot-based e-commerce backend that provides CRUD operations for managing products and categories. It integrates with a MySQL database for persistent storage and uses Redis for caching.

✅ RESTful API for managing products and categories
✅ Integration with MySQL database using JPA
✅ Redis caching for improved performance
✅ Exception handling and validation for API requests
✅ Pagination support for product listing
✅ External API integration with FakeStore

Tech Stack
Java 17

Spring Boot

Spring Data JPA

Spring Cache (Redis)

MySQL

REST APIs

Steps to Run

1.Clone the Repository

git clone https://github.com/your-username/your-repo.git

cd your-repo

2.Configure MySQL Database

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/productservices

spring.datasource.username=root

spring.datasource.password=your-password

spring.jpa.hibernate.ddl-auto=update

3.Run the Application

mvn spring-boot:run

4.API Endpoints

HTTP Method	Endpoint	Description

GET	/products/{id}	Get product by ID

POST	/products	Create a new product

GET	/products	List all products

GET	/products/{pageNo}/{pageSize}	Paginated product list

DELETE	/products/{id}	Delete a product

GET	/ping	Health check

Folder Structure
src
 ├── main
 
 │   ├── java/com/example/projectname
 
 │   │   ├── controller      # Controllers (API Endpoints)
 
 │   │   ├── dto             # Data Transfer Objects
 
 │   │   ├── model           # Database Entities
 
 │   │   ├── repository      # JPA Repositories
 
 │   │   ├── service         # Business Logic Layer
 
 │   │   ├── exception       # Custom Exception Handling
 
 │   ├── resources
 
 │   │   ├── application.properties  # Configuration Files

Learn More

Spring Boot dependency--https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis

Redis Installations -- https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/

Contributions

Feel free to submit pull requests or open issues for improvements! 

