# RENT A CAR MICROSERVICES

This project has been developed using a microservices architecture for a car rental business. The project includes microservices for Customer Service, Car Service, Rental Service, Notification Service, Discovery Server, and Api Gateway, utilizing Spring Boot and Spring Cloud frameworks.

## Microservices

1. **Customer Service**
   - Manages customer data.
   - Enables CRUD operations for customer information via RESTful API.
   - Collects customer information such as name, surname, email address, password, birthdate, and tracks the balance.
   - Uses PostgreSQL as the database.

2. **Car Service**
   - Manages vehicle information.
   - Establishes a model for each vehicle, with each model having a brand. Tracks the license plate, fee, model year, color, and availability information for each vehicle. Utilizes Regex for license plates to comply with Turkiye standards.
   - Captures car images from users and stores them in the Cloudinary cloud system. A user interface for uploading photos is created using the Thymeleaf library.
   - Stores information for cars, car images, models, and brands as separate collections in the MongoDB database.
    
3. **Rental Service**
   - Manages rental operations.
   - Collects car ID and customer ID for the car rental process. Tracks the start and end date of the rental process and the rental status.
   - Communicates synchronously with Car Service and Customer Service to track the availability of the car, daily rate, and customer balance for rental.
   - Uses Web Client for synchronous communication. Web Client is an HTTP client used to interact with web-based services that operate synchronously/asynchronously.
   - When the rental process is completed, it communicates asynchronously with the Notification Service to send rental information in mail format (rentalTopic). Kafka is used for this communication (similar function to RabbitMQ). 
   - Uses PostgreSQL as the database.

4. **Notification Service**
   - Manages notification processes to send notifications to users.
   - Provides updates on rental status and other important notifications.
   - Utilizes Kafka for asynchronous communication.

5. **Discovery Server**
   - Eureka-based discovery server that enables dynamic discovery and connection of services.

6. **Api Gateway**
   - API Gateway that directs and forwards external requests to internal services.
   - Uses OAuth 2.0 type of Authorization. JWT tokens are created through Keycloak by creating a client to send requests via the `localhost:8181` URL.

## Installation

Follow the steps below to start each service.

1. **Clone the Project**
   - `git clone https://github.com/username/ai-customer-support-chatbot.git cd microservices-rent-a-car`

2. **Install Package** 
    - Run the `mvn clean install` command in the project folder.

3. **Docker**
    - To run the project in Docker, you must first install it. `docker-compose up -d`
    - To stop the project, use `docker-compose stop`, to start it again use `docker-compose start`, to remove it from Docker use `docker-compose down`

## Video
  - [Video 1](https://vimeo.com/manage/videos/887836060/839fd8a31c)
  - [Video 2](https://vimeo.com/888510252/16ad302faa?share=copy)
  - [Video 3](https://vimeo.com/888511359/a643a218e3?share=copy)
    
## Project Schemas
  **UML DIAGRAM**
  <img src="https://github.com/halilibrhimtas/microservices-rent-a-car/assets/74383996/7b44a495-08f0-4df5-94c3-2343a558ee10"><br>

  **PROJECT STRUCTURE**
  <img src="https://github.com/halilibrhimtas/rent-a-car-microservices/assets/74383996/53db5454-3ad5-4c01-930d-f21b2c57f060"><br>

## Social Media
  - [Linkedin](https://www.linkedin.com/in/halilibrhimtas)
