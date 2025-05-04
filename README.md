# Recipes

A comprehensive web application for managing recipes, featuring a RESTful API built with Java Spring Boot and a responsive front-end developed using React.

## Table of Contents

- [Features](#features)
- [Stack](#stack)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Setup Instructions](#setup-instructions)
- [Configuration](#configuration)
- [Usage](#usage)

## Features

- **Recipe Management**: Create, read, update, and delete recipes.
- **User Authentication**: Secure user registration and login functionalities.
- **Search Functionality**: Efficiently search for recipes by various criteria.
- **Responsive Design**: Seamless user experience across devices.

## Stack

- **Backend**: Java Spring Boot
- **Frontend**: React
- **Database**: SQL Server

## Installation

### Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
- **Node.js**: Version 14 or higher
- **SQL Server**: Installed and running

### Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Shchoholiev/recipes.git
   cd recipes
   ```

2. **Backend Setup**:

   - Navigate to the backend directory:

     ```bash
     cd recipes
     ```

   - Build the project using Maven:

     ```bash
     mvn clean install
     ```

   - Run the Spring Boot application:

     ```bash
     mvn spring-boot:run
     ```

3. **Frontend Setup**:

   - Navigate to the frontend directory:

     ```bash
     cd recipesweb
     ```

   - Install dependencies:

     ```bash
     npm install
     ```

   - Start the React development server:

     ```bash
     npm start
     ```

## Configuration

- **Backend**:

  - Configure the `application.properties` file located in the `src/main/resources` directory to set up database connection details:

    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

## Usage

- **Accessing the Application**: Open your browser and navigate to `http://localhost:3000` to interact with the frontend interface.
- **Managing Recipes**: Use the web interface to add, edit, view, and delete recipes.
- **User Authentication**: Register a new account or log in to access personalized features.
- **Searching Recipes**: Utilize the search bar to find recipes based on keywords or ingredients.
