# 🎟️ Ticketing System - Java Microservices

This project is a microservices-based Ticketing System built with Java and Spring Boot. It uses Docker and Docker Compose to orchestrate services.

## 🧱 Microservices

- `booking-service`: Handles ticket bookings
- `inventoryservice`: Manages seat inventory
- `api-gateway`: Entry point for clients (Spring Cloud Gateway)
- Other infrastructure: Redis, Kafka, Zookeeper, Keycloak, Postgres, etc.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/TicketingSystem.git
cd TicketingSystem

**2. Run the Application**
Make sure Docker is running, then run:

docker compose -f docker-compose-root.yaml up --build

This will:
 - Build all services
 - Start containers
 - Launch the system

**3. Access the Services**

 - API Gateway	http://localhost:8181
 - Inventory Service http://localhost:8081
 - Booking Service http://localhost:8082
 - Swagger UI	http://localhost:8182/
 - Kafka UI	http://localhost:8084
 - PGAdmin	http://localhost:5050
 - Keycloak	http://localhost:8092
