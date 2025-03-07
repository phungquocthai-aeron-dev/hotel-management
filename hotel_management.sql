CREATE DATABASE hotelmanagement;
USE hotelmanagement;

-- Table: staff
CREATE TABLE staff (
    staff_id VARCHAR(50) PRIMARY KEY,
    staff_name VARCHAR(255),
    staff_phone VARCHAR(20),
    password VARCHAR(255),
    dob DATE
);

-- Table: customer
CREATE TABLE customer (
    customer_id VARCHAR(50) PRIMARY KEY,
    customer_name VARCHAR(255),
    customer_phone VARCHAR(20),
    address VARCHAR(255)
);

-- Table: category
CREATE TABLE category (
    category_id VARCHAR(50) PRIMARY KEY,
    category_name VARCHAR(255),
    room_price FLOAT
);

-- Table: room
CREATE TABLE room (
    room_id INT PRIMARY KEY,
    room_number INT,
    status VARCHAR(50),
    category_id VARCHAR(50),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Table: room_rental
CREATE TABLE room_rental (
    rent_id VARCHAR(50) PRIMARY KEY,
    rental_date DATE,
    check_in_date DATE,
    check_out_date DATE,
    rental_status VARCHAR(50),
    room_id INT,
    customer_id VARCHAR(50),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Table: service
CREATE TABLE service (
    service_id VARCHAR(50) PRIMARY KEY,
    service_name VARCHAR(255),
    service_price FLOAT
);

-- Table: promotion
CREATE TABLE promotion (
    promotion_id VARCHAR(50) PRIMARY KEY,
    promotion_name VARCHAR(255),
    promotion_value FLOAT,
    promotion_description TEXT,
    promotion_start DATE,
    promotion_end DATE
);

-- Table: use_service
CREATE TABLE use_service (
    us_id VARCHAR(50) PRIMARY KEY,
    quantity INT,
    us_date DATE,
    service_id VARCHAR(50),
    rent_id VARCHAR(50),
    promotion_id VARCHAR(50),
    FOREIGN KEY (service_id) REFERENCES service(service_id),
    FOREIGN KEY (rent_id) REFERENCES room_rental(rent_id),
    FOREIGN KEY (promotion_id) REFERENCES promotion(promotion_id)
);

-- Table: invoice
CREATE TABLE invoice (
    invoice_id VARCHAR(50) PRIMARY KEY,
    invoice_date DATE,
    total_amount FLOAT,
    us_id VARCHAR(50),
    staff_id VARCHAR(50),
    FOREIGN KEY (us_id) REFERENCES use_service(us_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

-- Table: feedback
CREATE TABLE feedback (
    feedback_id VARCHAR(50) PRIMARY KEY,
    content TEXT,
    feedback_time DATE,
    us_id VARCHAR(50),
    customer_id VARCHAR(50),
    FOREIGN KEY (us_id) REFERENCES use_service(us_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Table: maintenance
CREATE TABLE maintenance (
    mtn_id VARCHAR(50) PRIMARY KEY,
    mtn_date DATE,
    mtn_description TEXT,
    mtn_fee FLOAT,
    mtn_end DATE,
    mtn_status VARCHAR(50),
    room_id INT,
    staff_id VARCHAR(50),
    FOREIGN KEY (room_id) REFERENCES room(room_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);
