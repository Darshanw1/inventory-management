# Inventory & Warehouse Management System

A backend inventory and warehouse management system built using Java
and Spring Boot.

## Features

- Product management
- Warehouse management
- Supplier management
- Stock management
- Stock-in and stock-out operations
- Purchase management
- Automatic stock updates
- JWT authentication
- Role-based authorization
- Input validation
- Global exception handling
- Transaction management
- MySQL database
- RESTful APIs

## Technology Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST APIs
- Postman

## Architecture

Client
→ REST Controller
→ DTO
→ Service
→ Repository
→ MySQL

## Authentication

The application uses JWT-based authentication.

Roles:

- ADMIN
- STAFF

ADMIN users can manage products and warehouses.

STAFF users can access permitted operational APIs.

## Main Modules

### Products

CRUD operations for products.

### Warehouses

CRUD operations for warehouse locations.

### Stock

Tracks product quantity separately for each warehouse.

### Suppliers

Stores supplier information.

### Purchases

Creates purchase orders and automatically updates warehouse stock.

## Example Purchase Flow

Supplier
→ Purchase
→ Purchase Items
→ Product
→ Warehouse
→ Stock Update

## API Examples

POST /api/auth/register

POST /api/auth/login

GET /api/products

POST /api/products

GET /api/warehouses

POST /api/warehouses

GET /api/stock

PUT /api/stock/{id}/stock-in

PUT /api/stock/{id}/stock-out

POST /api/purchases

GET /api/purchases

## Database

MySQL database is used with Spring Data JPA and Hibernate.

## Testing

APIs were tested using Postman including:

- Authentication
- CRUD operations
- JWT authorization
- Role-based access
- Validation errors
- Stock operations
- Purchase operations
- Insufficient stock scenarios