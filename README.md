# OT Digital Twin - ControlPoint Assignment

This is my submission for the ControlPoint internship assignment. It's a simple full-stack application inspired by an OT Digital Twin, built with Java 21, Spring Boot, React, and Vite.

## How to run the project

### Option 1: Docker (Recommended)
I set up a `docker-compose` file to make it easy to run everything without worrying about local dependencies. Make sure you have Docker installed and run:
```bash
docker-compose up --build
```
Once it starts:
- The frontend will be available at: `http://localhost:5173`
- The backend API is at: `http://localhost:8080/api/assets`
- The H2 database console is at: `http://localhost:8080/h2-console` (username: `sa`, leave password blank)

### Option 2: Run manually
If you prefer to run them separately, you'll need Java 21 and Node.js (18+).

**Backend:**
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
npm run dev
```

---

## Design Decisions

I wanted to keep the architecture clean and strictly follow the assignment requirements without over-engineering it. Here is a quick breakdown of why I built things the way I did:

### 1. Backend Architecture
I went with a standard layered approach (`Controller` -> `Service` -> `Repository` -> `Model`). I kept the controllers as thin as possible so they only handle HTTP routing, and pushed all the actual business logic (like generating the sensor simulations) down into the `@Transactional` service layer.

### 2. DTOs instead of returning Entities
I deliberately created separate DTO records (`AssetResponse`, `SensorReadingResponse`) instead of returning the JPA entities directly from the API. Returning entities with `@ManyToOne` relationships usually leads to infinite recursion or lazy-loading crashes when Spring tries to serialize them into JSON. Using DTOs felt like the safest way to decouple the database schema from the API contract.

### 3. Lombok & Entity Relationships
For the `SensorReading` entity, I used a real `@ManyToOne` relationship to tie it to the `Asset`. To cut down on boilerplate, I used Lombok's `@Getter` and `@Setter`. I specifically avoided using the `@Data` annotation on the entities because it auto-generates `hashCode()` and `equals()` methods, which can cause stack overflow issues when dealing with JPA relationships.

### 4. Database IDs
I decided to stick with a standard `Long` (`GenerationType.IDENTITY`) for the primary keys rather than UUIDs. While UUIDs are great for large distributed systems, standard auto-incrementing numbers index much faster in relational databases and made the API much easier to test and debug manually.

### 5. HTTP Polling vs. WebSockets
To simulate the sensor data, the Spring Boot backend runs a `@Scheduled` task every 5 seconds to generate random readings and status updates. On the frontend, I used a standard `setInterval` to poll the API every 5 seconds. I considered using WebSockets for a true real-time stream, but since the assignment mentioned keeping the scope intentionally small, I felt polling was a simpler, stateless approach that was easier to review and test.

### 6. Docker & Nginx Proxy
To avoid hardcoding `localhost:8080` in the frontend code and dealing with annoying CORS issues, I configured Vite (for dev) and Nginx (for Docker) to act as a reverse proxy. The frontend just makes requests to `/api`, and Nginx cleanly routes it to the backend container. It keeps the setup portable so it runs on anyone's machine out of the box.
