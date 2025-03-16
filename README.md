Sales Tax Application

Overview

The Sales Tax Application is a Spring Boot-based service that calculates receipts for purchasing items. It computes sales tax based on the given rules:

A basic sales tax of 10% is applied to all goods except for books, food, and medical products.

An import duty of 5% is applied to all imported goods.

All sales tax amounts are rounded up to the nearest 0.05.

The application allows users to send a list of items via an API, and it returns the calculated receipt with item prices (including taxes), total sales taxes, and total cost.

Features

REST API for calculating receipts.

Accurate tax computations with support for:

Basic Sales Tax (10%).

Import Duty Tax (5%).

Proper rounding of tax calculations to the nearest 0.05.

Follows SOLID principles and is designed for extensibility.

Thoroughly tested with unit tests using JUnit 5.

Technologies Used

Java 17

Spring Boot

JUnit 5 for unit testing

Gradle for build and dependency management

Project Structure:

src

├── main

│ ├── java

│ │ └── com.example.salestax

│ │ ├── CommonsApplication.java # Entry point for the Spring Boot app

│ │ ├── controller

│ │ │ └── ReceiptController.java # REST Controller

│ │ ├── model

│ │ │ ├── Item.java # Represents an item in the receipt

│ │ ├── service

│ │ │ ├── BasicSalesTax.java # Handles tax computation logic

│ │ │ ├── ImportDutyTax.java # Handles tax computation logic for imported goods

│ │ │ ├── TaxCalculator.java # Handles tax computation logic

│ │ │ └── ReceiptGenerator.java # Handles receipt generation

│ │ │ ├── TaxCalculationStrategy.java # Handles tax computation Strategy

│ └── test

│ └── java

│ └── com.example.salestax

│ └── ReceiptControllerTest.java # Unit tests for the controller

API Endpoint

POST /api/receipt

Generates a receipt based on the given input items.

URL: http://localhost:8080/api/receipt

Method: POST

Content-Type: application/json

Request Body Example:

json

\[

{

"name": "book",

"isImported": false,

"isExempt": true,

"quantity": 1,

"price": 12.49

},

{

"name": "music CD",

"isImported": false,

"isExempt": false,

"quantity": 1,

"price": 14.99

},

{

"name": "chocolate bar",

"isImported": false,

"isExempt": true,

"quantity": 1,

"price": 0.85

}

\]

Response Example:

1 book : 12.49

1 music CD : 16.49

1 chocolate bar : 0.85

Sales Taxes: 1.50

Total: 29.83

Setup & Installation

Prerequisites

JDK 17 or higher installed.

Gradle installed

IDE (e.g., IntelliJ IDEA, Eclipse)

Clone the Repository

bash

git clone https://github.com/stostat/sales-tax.git

cd sales-tax

Build the Application

Testing the Application

Unit Tests

The project includes unit tests written in JUnit 5:

Test cases cover critical components such as:

Tax calculations.

Receipt generation.

Controller logic.

To run the tests:

You can test the REST API directly using:

Postman or cURL

An example curl command to test the /generate endpoint:

bash

curl -X POST http://localhost:8080/api/receipt \\

\-H "Content-Type: application/json" \\

\-d '\[

{"name":"book","isImported":false,"isExempt":true,"quantity":1,"price":12.49},

{"name":"music CD","isImported":false,"isExempt":false,"quantity":1,"price":14.99},

{"name":"chocolate bar","isImported":false,"isExempt":true,"quantity":1,"price":0.85}

\]'

Expected Output:

1 book : 12.49

1 music CD : 16.49

1 chocolate bar : 0.85

Sales Taxes: 1.50

Total: 29.83

Design Principles

This application follows the SOLID Principles:

Single Responsibility Principle (SRP): Each class focuses on a single responsibility:

Item represents product details.

TaxCalculator focuses on tax computation.

ReceiptGenerator generates receipts.

Open-Closed Principle (OCP): The tax calculation is easily extendable for new rules without modifying existing classes.

Dependency Inversion Principle (DIP): The controller depends on the ReceiptGenerator interface rather than its implementation, making it testable.

Testability: Classes are designed with unit testing in mind, using mocked dependencies where necessary.
