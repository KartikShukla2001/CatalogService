# Product Catalog API

## Overview

The Product Catalog API is a Spring Boot application that allows users to manage products in a catalog. This application supports various operations, including adding, updating, deleting, retrieving, and filtering products. The API utilizes MongoDB for data persistence and implements caching for improved performance.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [Unit Testing](#unit-testing)
- [Configuration](#configuration)

## Technologies Used

- Java 11
- Spring Boot
- Spring Data MongoDB
- Lombok
- Maven
- SLF4J for logging

## Features

- Create, Read, Update, Delete (CRUD) operations for products.
- Filter products by category and price range.
- Caching to enhance performance.
- RESTful API structure.

## API Endpoints

| HTTP Method | Endpoint                          | Description                                      |
|-------------|-----------------------------------|--------------------------------------------------|
| GET         | `/api/v1/products`               | Retrieve all products.                           |
| GET         | `/api/v1/products/{id}`          | Retrieve a product by ID.                        |
| POST        | `/api/v1/products`               | Add a new product.                               |
| PUT         | `/api/v1/products/{id}`          | Update an existing product by ID.                |
| DELETE      | `/api/v1/products/{id}`          | Delete a product by ID.                         |
| GET         | `/api/v1/products/filter`         | Filter products by category and price range.    |
| GET         | `/api/v2/products/filter`         | Filter products (v2).                            |
| GET         | `/api/v2/products/{id}`           | Retrieve a product by ID (v2).                   |

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MongoDB

### Running the Application
- To run the app lication cd<project-directory>
- ./gradlew build
- ./gradlew bootRun
  

### Configureation
You can configure the database and caching settings in the src/main/resources/application.properties file.

