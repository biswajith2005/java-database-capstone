## MySQL Database Schema

### Tables:
1. **doctors**
   - id INT PRIMARY KEY
   - name VARCHAR(255)
   - specialty VARCHAR(255)

2. **patients**
   - id INT PRIMARY KEY
   - name VARCHAR(255)
   - email VARCHAR(255)

3. **appointments**
   - id INT PRIMARY KEY
   - doctor_id INT FOREIGN KEY REFERENCES doctors(id)
   - patient_id INT FOREIGN KEY REFERENCES patients(id)
   - time DATETIME

4. **prescriptions**
   - id INT PRIMARY KEY
   - appointment_id INT FOREIGN KEY REFERENCES appointments(id)
   - medication TEXT
