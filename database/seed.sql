USE hospital;
-- Demo login: admin / password. Replace this hash before production use.
INSERT INTO users (username, password_hash, role)
SELECT 'admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');
INSERT INTO doctors (name, specialization, department, license_number, phone, email, room, consultation_fee, available)
SELECT 'Dr. Ananya Sharma', 'Cardiology', 'Clinical Services', 'LIC-ANANYA-001', '+91-9000000001', 'ananya@example.test', '101', 1200.00, TRUE
WHERE NOT EXISTS (SELECT 1 FROM doctors WHERE name = 'Dr. Ananya Sharma');
INSERT INTO doctors (name, specialization, department, license_number, phone, email, room, consultation_fee, available)
SELECT 'Dr. Vikram Patel', 'General Medicine', 'Outpatient', 'LIC-VIKRAM-002', '+91-9000000002', 'vikram@example.test', '102', 800.00, TRUE
WHERE NOT EXISTS (SELECT 1 FROM doctors WHERE name = 'Dr. Vikram Patel');
