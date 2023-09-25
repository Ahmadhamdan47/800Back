# 800Back

800Back is a Spring Boot project that serves as a backend application for managing products, clients, and sales. This repository contains the source code for the application.

## Table of Contents

- [Versions and Dependencies](#versions-and-dependencies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Versions and Dependencies

### Project Versions

- Spring Boot: `3.1.4`
- Java: `17`

### Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- H2 Database (for development)
- Spring Boot DevTools (for development)
- Logback Classic (for logging)

### Building and Running

To build and run the project, make sure you have Java 17 and Gradle installed on your system. Clone this repository and run the following command:

```bash
gradle bootRun
The application will start, and you can access it at http://localhost:8080.

Getting Started
To get started with the project, follow these steps:

Clone the repository to your local machine.
Build and run the application as mentioned in the Building and Running section.
Use a tool like Postman to interact with the API endpoints.
Usage
Products Management
Fetch products and view id, name, description, category, creation date.
Create a new product.
Update an existing product.
Clients Management
Fetch clients and view id, name, last name, mobile.
Create a new client.
Edit an existing client.
Sales Management
Fetch all sales operations and view id, creation date, client, seller, total.
Create new sales with multiple transactions.
Edit quantities and prices of the sale.
Logging
All update operations on sale transactions are logged for reference.

Contributing
If you'd like to contribute to this project, please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix.
Make your changes and commit them.
Push your changes to your forked repository.
Create a pull request to the main repository.
License
This project is licensed under the MIT License.

