# Todo List API (Spring Boot)

This is a simple RESTful API for managing a todo list using Spring Boot, Hibernate, and MySQL.

## Features
- Create, Read, Update, Delete (CRUD) for todo items
- Each todo has a title and description

## Technologies
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL

## Requirements
- JDK 17+
- Maven
- MySQL Server

## Running the App

1. Clone the repo:
```bash
git clone <your-repo-url>
cd <task_based_on_ai_prompts/todolist-api>
```

2. Create a MySQL database and insert data:
```sql
-- Create the database
CREATE DATABASE IF NOT EXISTS tododb;
```
Optional:
```sql
USE tododb;
-- Drop table if it exists (optional)
DROP TABLE IF EXISTS todo;

-- Create the `todo` table
CREATE TABLE todo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
);

-- Insert sample data
INSERT INTO todo (title, description) VALUES
('Buy groceries', 'Milk, Bread, Eggs'),
('Finish Spring Boot project', 'Due this weekend'),
('Call mom', 'Remind her about dinner on Sunday');
```

3. Update `src/main/resources/application.properties` **(do not commit credentials)**:
```properties
spring.application.name=todolist-api
spring.datasource.url=jdbc:mysql://localhost:3306/tododb
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

4. Run the app:
```bash
./mvnw spring-boot:run
```

5. Access API:
- Base URL: `http://localhost:8080/api/todos`
- Use Postman or curl for testing.

## Run Quality Checks
```bash
mvn verify
```

## Answers to the tasks questions:
- Was it easy to complete the task using AI? - Yes
- How long did task take you to complete? - 3 hours
- Was the code ready to run after generation? What did you have to change to make it usable? - The generated code was ready to run. I didn't have to change much.
- Which challenges did you face during completion of the task? - Efforts to hide the credentials in .env
- Which specific prompts you learned as a good practice to complete the task? - Can you generate [...] for [...] using [...].
