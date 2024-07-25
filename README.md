# Ecobank 2.0

Ecobank 2.0 is a Java Spring Boot project designed to simulate a bank account system. The challenge code used in this project was part of a bootcamp of the Digital Innovation One Bootcamp in partnership with Santander. It simulates a bank account system with various features aimed at providing a comprehensive banking experience.

## Features

- **User Management**: Manages user profiles and account details.
- **Account Management**: Tracks account balances, agency details, and credit information.
- **Card Management**: Handles card details including number and credit limits.
- **News Updates**: Provides latest news related to banking operations.

## How to Run

1. Clone the repository.
2. Import the project into your IDE (e.g., IntelliJ IDEA, NetBeans).
3. Ensure you have a running instance of MySQL or any other supported database.
4. Update the `application.properties` file with your database connection details.
5. Run the application using your IDE or via the command line using `mvn spring-boot:run`.
6. Access the application endpoints via [http://localhost:8080](http://localhost:8080).

## Contributing

Feel free to fork the repository and submit pull requests.

## Class Diagram

```mermaid
classDiagram
    class User {
        -String name
        -Account account
        -Feature[] features
        -Card card
        -News[] news
    }

    class Account {
        -String number
        -String agency
        -float balance
        -float credit
    }

    class Feature {
        -String icon
        -String description
    }

    class Card {
        -String number
        -float limit
    }

    class News {
        -String icon
        -String description
    }

    User *-- Account
    User *-- Feature 
    User *-- Card 
    User *-- News 











