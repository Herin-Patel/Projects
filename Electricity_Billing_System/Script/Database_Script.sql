CREATE DATABASE IF NOT EXISTS Bill_System;

USE Bill_System;

CREATE TABLE IF NOT EXISTS SignUp (
	meter_no VARCHAR(20),
    username VARCHAR(20),
    name VARCHAR(30),
    password VARCHAR(20),
    usertype VARCHAR(20)
);

SELECT * FROM SignUp;

CREATE TABLE IF NOT EXISTS Customer(
	name VARCHAR(30),
    meter_no VARCHAR(20),
    address VARCHAR(50),
    city VARCHAR(20),
    state VARCHAR(20),
    emai VARCHAR(30),
    phone_no VARCHAR(12)
);

ALTER TABLE Customer 
RENAME TO new_customer;

SELECT * FROM new_customer;

CREATE TABLE IF NOT EXISTS meter_info (
	meter_number VARCHAR(30),
    meter_location VARCHAR(30),
    meter_type VARCHAR(30),
    phase_code VARCHAR(30),
    bill_type VARCHAR(30),
    days VARCHAR(10)
);

SELECT * FROM meter_info;

CREATE TABLE IF NOT EXISTS tax (
	cost_per_unit VARCHAR(20),
    meter_rent VARCHAR(20),
    service_charge VARCHAR(20),
    service_tax VARCHAR(20),
    swacch_bharat VARCHAR(20),
    fixed_tax VARCHAR(20)
);

SELECT * FROM tax;

INSERT INTO tax VALUES ('10', '45', '20', '58', '5', '18');

CREATE TABLE IF NOT EXISTS bill (
	meter_no VARCHAR(20),
    month VARCHAR(20),
    unit VARCHAR(20),
    total_bill VARCHAR(20),
    status VARCHAR(20)
);

SELECT * FROM bill;
