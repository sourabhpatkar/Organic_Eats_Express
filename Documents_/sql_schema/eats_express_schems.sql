use eats_express;

 -- Admin table
 CREATE TABLE admin_details(
 admin_id VARCHAR(20) PRIMARY KEY,
 admin_name VARCHAR(50) NOT NULL,
 admin_email VARCHAR(30) NOT NULL UNIQUE,
 pasword VARCHAR(20) NOT NULL ,
 mo_no VARCHAR(20) NOT NULL
 );
 -- Insert Query for Admin
INSERT INTO admin_details (admin_id, admin_name, admin_email, pasword, mo_no) 
VALUES 
('A001', 'Alice Green', 'alice.green@example.com', 'adminpass123', '1234567890'),
('A002', 'Bob White', 'bob.white@example.com', 'adminpass456', '0987654321'),
('A003', 'Carol Black', 'carol.black@example.com', 'adminpass789', '1122334455'),
('A004', 'David Blue', 'david.blue@example.com', 'adminpass101', '5566778899'),
('A005', 'Eve Brown', 'eve.brown@example.com', 'adminpass202', '6677889900');

-- drop table admin_details;
 
 -- Customer table
CREATE TABLE customer(
cust_id VARCHAR(20) PRIMARY KEY,
cust_name VARCHAR(50) NOT NULL,
cust_email VARCHAR(30)NOT NULL UNIQUE,
pasword VARCHAR(20) NOT NULL,
mobile_no VARCHAR(20) NOT NULL,
address VARCHAR(200) NOT NULL
);
-- insert query for Customer
INSERT INTO customer (cust_id, cust_name, cust_email, pasword, mobile_no, address) 
VALUES 
('C001', 'John Doe', 'john.doe@example.com', 'password123', '1234567890', '123 Main St, Anytown, USA'),
('C002', 'Jane Smith', 'jane.smith@example.com', 'password456', '0987654321', '456 Oak St, Anytown, USA'),
('C003', 'Alice Johnson', 'alice.johnson@example.com', 'password789', '1122334455', '789 Pine St, Anytown, USA'),
('C004', 'Bob Brown', 'bob.brown@example.com', 'password101', '5566778899', '321 Elm St, Anytown, USA'),
('C005', 'Charlie Davis', 'charlie.davis@example.com', 'password202', '6677889900', '654 Maple St, Anytown, USA');


CREATE TABLE category(
cat_id VARCHAR(20) PRIMARY KEY,
cat_name VARCHAR(50) NOT NULL
);
INSERT INTO category (cat_id, cat_name) 
VALUES 
('C001', 'Vegetables'),
('C002', 'Fruits'),
('C003', 'Dairy Products'),
('C004', 'Beverages'),
('C005', 'Bakery Products');

-- Product table
CREATE TABLE product(
prod_id VARCHAR(20) PRIMARY KEY,
prod_name VARCHAR(50) NOT NULL,
price DOUBLE NOT NULL,
quantity int NOT NULL,
cat_id Varchar(20),
CONSTRAINT fk_cat_id FOREIGN KEY(cat_id) REFERENCES category(cat_id)
ON DELETE SET NULL
ON UPDATE CASCADE
);
-- insert query for product
INSERT INTO product (prod_id, prod_name, price, quantity, cat_id) 
VALUES 
('P001', 'Tomato', 1.99, 100, 'C001'),
('P002', 'Apple', 2.99, 150, 'C002'),
('P003', 'Milk', 1.49, 200, 'C003'),
('P004', 'Orange Juice', 3.49, 80, 'C004'),
('P005', 'Bread', 2.49, 50, 'C005');

-- adding admin id in product table 
ALTER TABLE product
ADD COLUMN admin_id VARCHAR(20);

--  adding foreign key in product for Admin_id
ALTER TABLE product
ADD CONSTRAINT fk_admin_id 
FOREIGN KEY(admin_id) REFERENCES admin_details(admin_id)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- adding admin details
UPDATE product
SET admin_id = CASE 
    WHEN prod_id = 'P001' THEN 'A001'
    WHEN prod_id = 'P002' THEN 'A002'
    WHEN prod_id = 'P003' THEN 'A003'
    WHEN prod_id = 'P004' THEN 'A004'
    WHEN prod_id = 'P005' THEN 'A004'
    END;


-- order will be maid by admin as well so its details?
CREATE TABLE order_details(
order_id VARCHAR(20) PRIMARY KEY,
cust_id VARCHAR(20),
prod_id VARCHAR(20),
payment_id Varchar(20),
payable_amount DOUBLE NOT NULL,
CONSTRAINT fk_cust_id FOREIGN KEY(cust_id) REFERENCES customer(cust_id)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT fk_prod_id FOREIGN KEY(prod_id) REFERENCES product(prod_id)
ON DELETE SET NULL
ON UPDATE CASCADE
);
-- insert query for order details 
INSERT INTO order_details (order_id, cust_id, prod_id, payment_id, payable_amount) 
VALUES 
('O001', 'C001', 'P001', 'PAY001', 19.99),
('O002', 'C002', 'P002', 'PAY002', 29.99),
('O003', 'C003', 'P003', 'PAY003', 14.99),
('O004', 'C004', 'P004', 'PAY004', 34.99),
('O005', 'C005', 'P005', 'PAY005', 24.99);

-- payment table
CREATE TABLE payment(
payment_id VARCHAR(20) PRIMARY KEY,
order_id VARCHAR(20),
cust_id VARCHAR(20),
amount DOUBLE NOT NULL,
payment_date DATE,
CONSTRAINT fk_pay_order_id 
FOREIGN KEY(order_id) REFERENCES order_details(order_id)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT fk_pay_cust_id 
FOREIGN KEY(cust_id) REFERENCES customer(cust_id)
ON DELETE SET NULL
ON UPDATE CASCADE
);
-- insert query for payment
INSERT INTO payment (payment_id, order_id, cust_id, amount, payment_date) 
VALUES 
('PAY001', 'O001', 'C001', 19.99, '2024-06-01'),
('PAY002', 'O002', 'C002', 29.99, '2024-06-02'),
('PAY003', 'O003', 'C003', 14.99, '2024-06-03'),
('PAY004', 'O004', 'C004', 34.99, '2024-06-04'),
('PAY005', 'O005', 'C005', 24.99, '2024-06-05');
--  adding foreign key in order_details for payment_id
ALTER TABLE order_details
ADD CONSTRAINT fk_payment_id 
FOREIGN KEY(payment_id) REFERENCES payment(payment_id)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- delivery table 
CREATE TABLE delivery(
delivery_id VARCHAR(20) PRIMARY KEY,
order_id VARCHAR(20),
cust_id VARCHAR(20),
delivery_partner VARCHAR(50) NOT NULL,
delivery_date DATE NOT NULL,
otp_id VARCHAR(20),
CONSTRAINT fk_del_order_id 
FOREIGN KEY(order_id) REFERENCES order_details(order_id)
ON DELETE SET NULL
ON UPDATE CASCADE,
CONSTRAINT fk_del_cust_id 
FOREIGN KEY(cust_id) REFERENCES customer(cust_id)
ON DELETE SET NULL
ON UPDATE CASCADE
);
-- insert query for delivery 
INSERT INTO delivery (delivery_id, order_id, cust_id, delivery_partner, delivery_date, otp_id) VALUES
('D001', 'O001', 'C001', 'FastDelivery Inc.', '2024-06-29', 'OTP001'),
('D002', 'O002', 'C002', 'Express Logistics', '2024-06-30', 'OTP002'),
('D003', 'O003', 'C003', 'Swift Couriers', '2024-07-01', 'OTP003'),
('D004', 'O004', 'C004', 'Speedy Deliveries', '2024-07-02', 'OTP004'),
('D005', 'O005', 'C005', 'Quick Shippers', '2024-07-03', 'OTP005');

-- drop table delivery;
-- drop table otp;
-- delete from delivery;

-- OTP tables
CREATE TABLE otp(
otp_id VARCHAR(20) PRIMARY KEY,
otp_data VARCHAR(20) NOT NULL,
delivery_id VARCHAR (20),
CONSTRAINT fk_delivery_id 
FOREIGN KEY(delivery_id) REFERENCES delivery(delivery_id)
ON DELETE SET NULL
ON UPDATE CASCADE
);
-- insert query for otp id into delivery table
INSERT INTO otp (otp_id, otp_data, delivery_id) VALUES
('OTP001', '987654', 'D001'),
('OTP002', '234567', 'D002'),
('OTP003', '876543', 'D003'),
('OTP004', '345678', 'D004'),
('OTP005', '654321', 'D005');


-- FOREIGN KEY ADDITION INTO DELIVERY TABLE
ALTER TABLE delivery
ADD CONSTRAINT fk_otp_id 
FOREIGN KEY(otp_id) REFERENCES otp(otp_id)
ON DELETE SET NULL
ON UPDATE CASCADE;

show tables;
