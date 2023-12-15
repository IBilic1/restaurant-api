# Health Center Management System

Welcome to the Health Center Management System, a college project aimed at managing appointments, medicines, orders, prescriptions, and manufacturing relationships in a health center environment.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Configuration](#configuration)
- [Profiles](#profiles)
- [Database](#database)

## Introduction
The Health Center Management System is designed to streamline the management of appointments, medicines, orders, prescriptions, and manufacturing relationships within a health center. The system is built using Java 17, Spring Boot 3.0.1, and Gradle 8.3.

## Features
- **Appointment Management**: Schedule and manage appointments efficiently.
- **Medicine Management**: Keep track of available medicines and their details.
- **Order Management**: Manage orders for medicines and other supplies.
- **Prescription Handling**: Record and manage patient prescriptions.
- **Manufacturing Relationships**: Establish relationships between medicines and manufacturers.

## Technologies Used
- Java 17
- Spring Boot 3.0.1
- Gradle 8.3
- PostgreSQL Database

## Getting Started
### Prerequisites
Ensure you have the following software installed on your system:
- Java 17
- PostgreSQL Database

### Installation
1. Clone the repository: `git clone https://github.com/yourusername/health-center.git`
2. Navigate to the project directory: `cd health-center`

## Configuration
The application supports two profiles: `prod` and `local`. The profiles can be configured in the `application.properties` file.

## Profiles
- `prod`: Production profile with optimized configurations.
- `local`: Local development profile for debugging and testing.

## Database
The application requires a PostgreSQL database. Configure the database connection details in the `application.properties` file.
