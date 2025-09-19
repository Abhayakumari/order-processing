# Order Processing Microservice
Take Home Assignment: A small RESTful service that allows users to create product orders, stores them in a database, and processes them asynchronously

##  Run the App
```bash
./mvnw spring-boot:run
 API Endpoints
 Add Product

curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Keyboard", "price": 49.99}'
 Create Order

curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"productId": 1}'
 Get Order Status

curl http://localhost:8080/orders/1

 Assumptions
	• Products must exist before creating orders.
	• Order processing delay is randomized between 2–3 seconds.
Order result is randomly either COMPLETED or FAILED.
