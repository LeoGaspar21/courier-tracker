# Courier Tracker API

A RESTful API built with **Spring Boot** to manage packages and track delivery events.  
This project simulates a real-world courier tracking system, focusing on clean architecture, business rules, and good API practices.

---

## Features

- Create packages with recipient and expected delivery date
- Generate unique tracking codes
- Register tracking events (status updates with location)
- Retrieve package details by tracking code
- List all packages
- Update package information
- Delete packages (with business rules)
- Global exception handling with `@ControllerAdvice`
- Basic unit tests using JUnit and Mockito

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 In-Memory Database
- Bean Validation (Jakarta Validation)
- JUnit 5
- Mockito
- Maven

---

## API Endpoints

### Create a package
**POST** `/packages`

```json
{
  "recipientName": "Scott Summers",
  "expectedDeliveryDate": "2026-02-01T12:00:00"
}
```

---

### Get All Packages

Get  /packages


---
### Get package by tracking code

GET /packages/{trackingCode}

---

### Update a package
PUT /packages/{trackingCode}

```json
{
 "recipientName": "Jean Grey",
  "expectedDeliveryDate": "2026-02-10T10:00:00"
}
```
---

### Delete a package
Delivered packages cannot be deleted.

⚠️ Delivered packages cannot be deleted.

---

### Add tracking event

POST /packages/{trackingCode}/events

```json
{
  "status": "IN_TRANSIT",
  "location": "Distribution Center"
}
```

---
### Get tracking events

GET /packages/{trackingCode}/events

---
### Business Rules

A package with status DELIVERED cannot be deleted

If a package is not found, a custom ObjectNotFoundException is thrown

Business rule violations return proper HTTP status codes (e.g. 409 Conflict)

---

### Tests

The project includes unit tests for the service layer, covering:

Successful deletion

Prevention of deleting delivered packages

Exception handling when a package is not found

Package update behavior

Tests are implemented using JUnit 5 and Mockito.

---

### How to Run

Clone the repository

Open the project in your IDE

Run the application as a Spring Boot App

Access the API at:

```arduino
{
  http://localhost:8080
}
```
Use Postman or similar tool to test the endpoints

---

### Future Improvements

Integration tests

Pagination and filtering

Authentication and authorization

Swagger / OpenAPI documentation

Database persistence with PostgreSQL or MySQL

---

### Author

Developed by Leonardo Gaspar
Backend Developer | Java & Spring Boot




