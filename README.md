# Task Management and Data Pipeline System
## Project Overview
The Task Management and Data Pipeline System is a comprehensive enterprise solution designed to manage tasks, process data, and provide a robust RESTful API for user management. This system demonstrates expertise in object-oriented programming, concurrency structures, and advanced features in multiple programming languages.

## Project Description
This project exists to provide a self-contained task management system, a data pipeline for processing sales data, and a mock web API server for testing purposes. The task management system utilizes Java to create a robust and scalable solution, while the data pipeline is built using Python to efficiently process and aggregate sales data. The mock web API server is implemented using Node.js to provide a simple and effective RESTful API.

## Tech Stack
The following technologies are used in this project:
* **Programming Languages:** Java, Python, C, C++, JavaScript
* **Frameworks and Libraries:** Java Concurrent Collections, Python Logging, Node.js HTTP and URL modules
* **Data Storage:** In-memory database for the mock web API server

## Directory Structure
```markdown
.
├── TaskManagementSystem.java
├── StringProcessor.cpp
├── data_pipeline.py
├── file_io.c
├── main.c
├── item.h
├── server.js
├── .gitignore
├── inventory.h
├── file_io.h
├── Makefile
├── utils.c
├── utils.h
├── item.c
└── inventory.c
```

## Installation and Startup
To install and start the project, follow these steps:
1. Clone the repository using `git clone`.
2. Navigate to the project directory using `cd`.
3. Compile the Java code using `javac TaskManagementSystem.java`.
4. Compile the C and C++ code using `gcc` and `g++` respectively.
5. Run the Java code using `java TaskManagementSystem`.
6. Run the Python data pipeline using `python data_pipeline.py`.
7. Start the Node.js server using `node server.js`.

## Usage and API Examples
### Task Management System
* Create a new task: `Task task = new Task(1, "Task Title", "Task Description", TaskPriority.HIGH);`
* Update task status: `task.setStatus(TaskStatus.IN_PROGRESS);`
### Data Pipeline
* Generate sales data: `DataPipeline pipeline = new DataPipeline(); pipeline.generate_data();`
* Process sales data: `pipeline.process_data();`
### Mock Web API Server
* Get all users: `GET /users`
* Get user by ID: `GET /users/:id`
* Create new user: `POST /users` with JSON body containing `name` and `role`

## Contributing
Contributions are welcome and appreciated. To contribute, please fork the repository, make changes, and submit a pull request. Ensure that all changes are properly tested and documented.

## Licensing
This project is licensed under the MIT License. See the LICENSE file for details.