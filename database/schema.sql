CREATE DATABASE IF NOT EXISTS hospital CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hospital;
CREATE TABLE IF NOT EXISTS users (
 id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(80) NOT NULL UNIQUE,
 password_hash VARCHAR(100) NOT NULL, role ENUM('ADMIN','RECEPTIONIST') NOT NULL DEFAULT 'RECEPTIONIST',
 active BOOLEAN NOT NULL DEFAULT TRUE, created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS patients (
 id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(150) NOT NULL,
 date_of_birth DATE NOT NULL, gender ENUM('MALE','FEMALE','OTHER') NOT NULL,
 blood_group VARCHAR(5), phone VARCHAR(30) NOT NULL, email VARCHAR(150),
 address VARCHAR(255) NOT NULL, emergency_contact VARCHAR(150),
 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 CONSTRAINT chk_patient_age CHECK (date_of_birth >= '1900-01-01')
);
CREATE TABLE IF NOT EXISTS doctors (
 id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(150) NOT NULL, specialization VARCHAR(120) NOT NULL,
 department VARCHAR(120) NOT NULL, consultation_fee DECIMAL(10,2) NOT NULL DEFAULT 0.00,
 license_number VARCHAR(60) NOT NULL UNIQUE, phone VARCHAR(30) NOT NULL, email VARCHAR(150),
 room VARCHAR(30), available BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS appointments (
 id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, doctor_id INT NOT NULL,
 appointment_date DATE NOT NULL, start_time TIME NOT NULL, end_time TIME NOT NULL,
 status ENUM('BOOKED','COMPLETED','CANCELLED') NOT NULL DEFAULT 'BOOKED',
 notes VARCHAR(500), created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patients(id),
 CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id),
 active_slot_key VARCHAR(180) AS (
   IF(status = 'CANCELLED', NULL,
      CONCAT(doctor_id, ':', appointment_date, ':', start_time))
 ) STORED UNIQUE,
 INDEX idx_doctor_slot (doctor_id, appointment_date, start_time, status),
 CONSTRAINT chk_appointment_time CHECK (end_time > start_time),
 CONSTRAINT chk_appointment_date CHECK (appointment_date >= '2000-01-01')
);
