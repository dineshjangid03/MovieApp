# Spring Boot MovieApp Project

This is a Spring Boot project that demonstrates a simple movie database application. It allows you to manage movies and their ratings.

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) 8 or later
- Apache Maven
- MySQL database

## Getting Started

1. Clone the repository:

   git clone https://github.com/dineshjangid03/MovieApp.git

2. Navigate to the project directory:
   
   cd MovieApp

3. Configure the database:

   Open the src/main/resources/application.properties file.
   Modify the database connection settings according to your setup.

4. Build the project:

   mvn clean install

5. Run the application:

   mvn spring-boot:run

6. Access the application:

   Open your web browser and visit http://localhost:8080 to access the application.


## Features
The application provides the following features:

- View the list of movies
- Add a new movie
- Get the longest duration movies
- Get the top-rated movies
- Update runtime minutes for all movies
- View genre-wise movies with numVotes subtotals
- Load movie data from a CSV file
- Load rating data from a CSV file


## API Endpoints
The following API endpoints are available:

- POST /api/v1/movies: Create a new movie.
- GET /api/v1/longest-duration-movies: Get the top 10 movies with the longest duration.
- GET /api/v1/top-rated-movies: Get the top-rated movies with an average rating greater than 6.0.
- GET /api/v1/genre-movies-with-subtotals: Get genre-wise movies with numVotes subtotals.
- POST /api/v1/update-runtime-minutes: Update runtime minutes for all movies.


## Technologies Used

- Java
- Hibernate
- Maven
- Spring Boot
- MySQL
