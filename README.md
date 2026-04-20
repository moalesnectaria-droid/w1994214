# w1994214
A RESTful API built using JAX-RS for Smart Campus management, handling rooms, sensors, and sensor readings with proper error handling and resource relationships.
# Smart Campus REST API

## How to Run
1. Open project in NetBeans
2. Run Main.java
3. API runs at http://localhost:8081/api/v1

---

## Sample Requests (curl)

### Create Room
curl -X POST http://localhost:8081/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"name":"Library","capacity":50}'

### Get Rooms
curl http://localhost:8081/api/v1/rooms

### Create Sensor
curl -X POST http://localhost:8081/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"type":"CO2","status":"ACTIVE","currentValue":400,"roomId":"ROOM_ID"}'

### Add Reading
curl -X POST http://localhost:8081/api/v1/sensors/SENSOR_ID/readings \
-H "Content-Type: application/json" \
-d '{"value":450}'

---

# REPORT (Answers)

## Part 1

### Q1: JAX-RS lifecycle
JAX-RS resource classes are by default created per request. This means a new instance is created for each incoming HTTP request. This helps avoid concurrency issues, but shared data structures like HashMaps must be managed carefully to prevent race conditions.

---

### Q2: HATEOAS
Hypermedia allows clients to discover available actions dynamically through links in responses. This reduces dependency on static documentation and improves flexibility.

---

## Part 2

### Q: IDs vs full objects
Returning only IDs reduces bandwidth but requires extra requests. Returning full objects is easier for clients but increases response size.

---

### Q: DELETE idempotency
DELETE is idempotent because deleting the same resource multiple times results in the same final state (resource not existing).

---

## Part 3

### Q: @Consumes JSON
If a client sends a different format, JAX-RS will return HTTP 415 Unsupported Media Type.

---

### Q: QueryParam vs Path
Query parameters are better for filtering because they are optional and flexible, while path parameters are used for identifying specific resources.

---

## Part 4

### Q: Sub-resource locator
It improves modularity by separating logic into smaller classes instead of one large controller.

---

## Part 5

### Q: 422 vs 404
422 is more accurate because the request is valid but contains invalid data (non-existent roomId).

---

### Q: Stack trace risk
Exposing stack traces reveals internal structure, class names, and vulnerabilities, which attackers can exploit.

---

### Q: Logging filters
Filters allow centralized logging without repeating code in every endpoint.
