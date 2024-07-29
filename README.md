# Java_App_With_MongoDB + Compass

This project demonstrates how to seamlessly integrate MongoDB with a Java application using MongoDB and MongoDB Compass. Follow along to set up the environment, configure the MongoDB connection, and perform CRUD (Create, Read, Update, Delete) operations.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Contributing](#contributing)
<!-- - [License](#license) -->


## Introduction
This JavaFX application connects to a MongoDB database to manage student information. The app allows users to add, read, update, and delete student records. MongoDB Compass is used to visually interact with the database.

## Features
- Add new student records
- Read student records
- Update existing student records
- Delete student records

## Prerequisites
- Java 17 or higher
- Apache Maven
- MongoDB server
- MongoDB Compass

## Installation
1. **Clone the repository:**
    ```sh
    git clone https://github.com/baldez300/java_App_with_MongoDB.git
    ```
   ***Go to the directory / project:***
    ```
    cd java_App_with_MongoDB
    ```   

2. **Add MongoDB Driver JAR:**
    - Download the MongoDB Driver JAR file from the [MongoDB Maven Repository](https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync).
    - Place the JAR file in the `main` directory of your project.
    - Update your `pom.xml` to include the MongoDB driver dependency.

3. **Update `pom.xml` with the same version of your JAR driver file eg:(<version>4.8.2</version>):**
    ```xml
    <dependencies>
        <!-- MongoDB Driver -->
        <dependency>
            <groupId>org.mongodb</groupId>
            <artifactId>mongodb-driver-sync</artifactId>
            <version>4.8.2</version>
        </dependency>
        <!-- Other dependencies -->
        ...
    </dependencies>
    ```

## Usage
1. **Configure MongoDB Connection:**
    - Create a `.env` file in the project root with your MongoDB URI:
      ```
      MONGODB_URI=mongodb+srv://<username>:<password>@cluster0.mongodb.net/?retryWrites=true&w=majority
      ```

## Configuration
- **FXML File (`hello-view.fxml`):** Contains the layout of the JavaFX interface.
- **CSS File (`style.css`):** Contains the styles for the JavaFX components.
- **Controller (`HelloController.java`):** Handles the user interactions and database operations.
- **Main Class (`HelloApplication.java`):** The entry point of the application.

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

