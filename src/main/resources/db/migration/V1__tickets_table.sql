CREATE TABLE tickets(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    route_number VARCHAR(20) NOT NULL,
    departure_date TIMESTAMP NOT NULL,
    status VARCHAR(20) DEFAULT NULL
)