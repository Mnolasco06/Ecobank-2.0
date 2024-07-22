# Ecobank 2.0

Ecobank 2.0 is a Java Spring Boot project designed to simulate a bank account system. The challenge code used in this project was part of a bootcamp of the Digital Innovation Bootcamp in partnership with Santander. It simulates a bank account system with various features aimed at providing a comprehensive banking experience.

## Features

- **User Management**: Manages user profiles and account details.
- **Account Management**: Tracks account balances, agency details, and credit information.
- **Card Management**: Handles card details including number and credit limits.
- **News Updates**: Provides latest news related to banking operations.

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
