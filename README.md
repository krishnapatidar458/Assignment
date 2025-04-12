Here's a `README.md` file you can use for your project:

# AutoSave API

This is an API developed using **Spring Boot**, which allows students to submit their test answers with automatic saving of responses to a PostgreSQL database. It supports tracking of attempted, skipped, and reviewed questions, with the ability to handle concurrency, validate inputs, and store timestamps of each submission.

## Features

- **Save/Update Responses**: Students can submit their answers, and the API saves or updates their responses.
- **Status Tracking**: Each response can have a status (Attempted, Skipped, or Review).
- **Concurrency Handling**: The API handles multiple submissions from the same student gracefully.
- **Timestamping**: The time each answer is saved or updated is tracked.
- **Basic Validations**: Validates input data to ensure the submission is correct and complete.
- **Graceful Error Handling**: Provides clear error messages in case of missing data or issues.

## Tech Stack

- **Spring Boot**: Backend framework.
- **JPA (Java Persistence API)**: For ORM and database interaction.
- **PostgreSQL**: Used as the database.
- **Docker**: For containerizing the application and database.

## Endpoints

### POST `/autosave`

This endpoint saves or updates the responses of a student for a test.

**Request Body**:
```json
{
  "studentId": 1,
  "testId": 1,
  "responses": [
    {
      "questionId": 101,
      "answer": "A",
      "status": "Attempted"
    },
    {
      "questionId": 102,
      "answer": "",
      "status": "Skipped"
    }
  ]
}
```

**Response**:
- **200 OK**: Responses saved successfully.
- **404 Not Found**: If the student, test, or question is not found.
- **500 Internal Server Error**: If an unexpected error occurs.

## How to Run Locally

1. **Set up the PostgreSQL database**:
   - Install PostgreSQL or use a Docker container.
   - Create a database called `autosave_db`.

2. **Configure `application.properties`**:
   - Update `application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/autosave_db
     spring.datasource.username=postgres
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     server.port=8080
     ```

3. **Run the application**:
   - You can run the application using an IDE or the following command:
     ```bash
     ./mvnw spring-boot:run
     ```

4. **Test the API**:
   - Use Postman or any HTTP client to send a `POST` request to `http://localhost:8080/autosave` with the sample JSON body.

## How to Run with Docker

1. **Build the project**:
   - Run the following command to clean and package the project:
     ```bash
     ./mvnw clean package
     ```

2. **Run the application with Docker Compose**:
   - Use Docker Compose to start the app and database services:
     ```bash
     docker-compose up --build
     ```

3. **Test the API**:
   - After the containers are up, you can test the API by sending a `POST` request to `http://localhost:8080/autosave`.

4. **Stop the services**:
   - To stop the services, run:
     ```bash
     docker-compose down
     ```

## SQL Schema

Here is the SQL script to create the necessary tables in PostgreSQL:

```sql
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE test (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE question (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL
);

CREATE TABLE student_answer (
    id SERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL,
    test_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    answer TEXT,
    status VARCHAR(50),
    saved_at TIMESTAMP NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (test_id) REFERENCES test(id),
    FOREIGN KEY (question_id) REFERENCES question(id)
);
```

---
