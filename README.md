# Acme API Consumer

## Overview

The **Acme API Consumer** is a Spring Boot application designed to interact with external APIs to fetch and manage parcel shop data. This project demonstrates the use of Docker Compose for containerization and dependency management, along with standard practices in Java development.

## Features

- **Fetch Parcel Shops:** Retrieves parcel shop data from an external API.
- **Login Management:** Handles authentication and token management.
- **Persistence:** Saves retrieved parcel shop data into a MongoDB database.
- **Retry Mechanism:** Implements basic error handling with logging.

## Getting Started

### Prerequisites

- **Docker:** Ensure Docker is installed and running.
- **Docker Compose:** Required for container orchestration.
- **Java Development Kit (JDK):** JDK 11 or higher.
- **IDE:** Any Java IDE (e.g., IntelliJ IDEA, Eclipse) for local development.

### Running with Docker Compose

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/acme-api-consumer.git
   cd acme-api-consumer
   ```
2. **Build and Start Docker Containers:**

```bash
docker-compose up --build
```
This command will build the Docker images and start the necessary containers as defined in docker-compose.yml.

3. **Access the Application:**
After the containers are up and running, you can access the application at http://localhost:8080.

### Running Locally in an IDE
1. **Clone the Repository:**

```bash
git clone https://github.com/yourusername/acme-api-consumer.git
cd acme-api-consumer
```
2. **Import the Project:**
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

3. **Run the Application:**
Locate and run the main class AcmeApiConsumerApplication from src/main/java/com/acme/AcmeApiConsumerApplication.java. This will start the Spring Boot application.

4. **Access the Application:**
Once the application has started, you can access it at http://localhost:8080.

### Configuration
1. **Docker Compose Configuration:** The docker-compose.yml file includes configurations for the application and MongoDB database.
2. **Application Properties:** Customize the configuration in src/main/resources/application.properties or via environment variables.

### Additional Information
1. **Logging:** Logs are output to the console and can be configured in application.properties.
2. **Error Handling:** Basic error handling is implemented, with retry logic and exception logging.


Contact
For questions or further information, please contact lovemehta.me@gmail.com.
