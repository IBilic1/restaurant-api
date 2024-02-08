DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS order_;
DROP TABLE IF EXISTS Prescription;
DROP TABLE IF EXISTS Medicine;
DROP TABLE IF EXISTS Manufacturer;
DROP TABLE IF EXISTS _user;

CREATE TABLE _user
(
    id        INT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email     VARCHAR(255) UNIQUE,
    password  VARCHAR(255),
    role      VARCHAR(50)
);
CREATE TABLE Appointment
(
    id            BIGINT PRIMARY KEY auto_increment,
    start_date_time DATETIME,
    end_date_time   DATETIME,
    address       VARCHAR(255),
    doctor_id     BIGINT,
    patient_id    BIGINT,
    FOREIGN KEY (doctor_id) REFERENCES _user (id),
    FOREIGN KEY (patient_id) REFERENCES _user (id)
);

CREATE TABLE Manufacturer
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(255),
    address VARCHAR(255)
);

CREATE TABLE Medicine
(
    id             BIGINT PRIMARY KEY auto_increment,
    name           VARCHAR(255),
    description    VARCHAR(255),
    manufacturer_id BIGINT,
    quantity_in_stock FLOAT,
    FOREIGN KEY (manufacturer_id) REFERENCES Manufacturer (id)
);

CREATE TABLE Prescription
(
    id        BIGINT PRIMARY KEY auto_increment,
    patient_id BIGINT,
    doctor_id  BIGINT,
    FOREIGN KEY (patient_id) REFERENCES _user (id),
    FOREIGN KEY (doctor_id) REFERENCES _user (id)
);

CREATE TABLE order_
(
    id             BIGINT PRIMARY KEY,
    description    VARCHAR(255),
    amount         DOUBLE,
    dose_gap        DOUBLE,
    medicine_id     BIGINT,
    prescription_id BIGINT,
    FOREIGN KEY (medicine_id) REFERENCES Medicine (id),
    FOREIGN KEY (prescription_id) REFERENCES Prescription (id)
);

-- Insert into Manufacturer table
INSERT INTO Manufacturer (id, name, address)
VALUES (1, 'Manufacturer1', 'Address1'),
       (2, 'Manufacturer2', 'Address2');

-- Insert into Medicine table
INSERT INTO Medicine (name, description, manufacturer_id)
VALUES ('Medicine1', 'Description1', 1),
       ('Medicine2', 'Description2', 2);

-- Insert into user table
INSERT INTO _user (id, first_name, last_name, email, password, role)
VALUES
    (1, 'John', 'Doe', 'john@example.com', 'password123', 'USER'),
    (2, 'Doctor', 'Doe', 'doctor@example.com', 'password456', 'ADMIN');

-- Insert into Prescription table
INSERT INTO Prescription (patient_id, doctor_id)
VALUES (1, 2),
       ( 1, 2);
-- Insert into order_ table
INSERT INTO order_ (id, description, amount, dose_gap, medicine_id, prescription_id)
VALUES (1, 'Order1', 10.5, 2.0, 1, 1),
       (2, 'Order2', 15.0, 1.5, 2, 2);

-- Example data insertion for Appointment table
INSERT INTO Appointment (start_date_time, end_date_time, address, doctor_id, patient_id)
VALUES
    ('2024-01-13 08:00:00', '2024-01-13 09:00:00', '123 Main St', 2, 1);

