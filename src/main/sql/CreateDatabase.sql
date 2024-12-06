create database center_management;

use center_management;

-- Role Table
CREATE TABLE Role (
                      ID INT PRIMARY KEY AUTO_INCREMENT,
                      Name VARCHAR(50) NOT NULL UNIQUE
);
insert into role (name) values
	('Tutor'),
    ('Officer'),
    ('Admin'),
    ('Student');

-- User Table (Base Entity)
CREATE TABLE User (
                      ID INT PRIMARY KEY AUTO_INCREMENT,
                      Email VARCHAR(100) NOT NULL UNIQUE,
                      Password VARCHAR(255) NOT NULL,
                      PhoneNumber VARCHAR(20) NOT NULL,
                      FullName VARCHAR(100) NOT NULL,
                      DateOfBirth DATE NOT NULL,
                      Address VARCHAR(255) NOT NULL,
                      Identity VARCHAR(50) NOT NULL,
                      Role_ID INT NOT NULL,
                      FOREIGN KEY (Role_ID) REFERENCES Role(ID)
);
-- Insert 1 admin
INSERT INTO User (Email, Password, PhoneNumber, FullName, DateOfBirth, Address, Identity, Role_ID)
VALUES 
('admin@edu-center.com', 'password123', '1234567890', 'Admin User', '1980-01-01', '123 Admin St.', '100000000001', 3);
-- Insert 2 officers
INSERT INTO User (Email, Password, PhoneNumber, FullName, DateOfBirth, Address, Identity, Role_ID)
VALUES 
('officer1@edu-center.com', 'password123', '1234567891', 'Officer One', '1985-02-01', '456 Officer Lane', '100000000002', 2),
('officer2@edu-center.com', 'password123', '1234567892', 'Officer Two', '1986-03-01', '789 Officer Blvd.', '100000000003', 2);
-- Insert 3 tutors
INSERT INTO User (Email, Password, PhoneNumber, FullName, DateOfBirth, Address, Identity, Role_ID)
VALUES 
('tutor1@edu-center.com', 'password123', '1234567893', 'Tutor One', '1990-04-01', '101 Tutor Rd.', '100000000004', 1),
('tutor2@edu-center.com', 'password123', '1234567894', 'Tutor Two', '1991-05-01', '102 Tutor Rd.', '100000000005', 1),
('tutor3@edu-center.com', 'password123', '1234567895', 'Tutor Three', '1992-06-01', '103 Tutor Rd.', '100000000006', 1);
-- Insert 15 students
INSERT INTO User (Email, Password, PhoneNumber, FullName, DateOfBirth, Address, Identity, Role_ID)
VALUES 
('student1@edu-center.com', 'password123', '1234567896', 'Student One', '2000-07-01', '201 Student Rd.', '100000000007', 4),
('student2@edu-center.com', 'password123', '1234567897', 'Student Two', '2000-08-01', '202 Student Rd.', '100000000008', 4),
('student3@edu-center.com', 'password123', '1234567898', 'Student Three', '2000-09-01', '203 Student Rd.', '100000000009', 4),
('student4@edu-center.com', 'password123', '1234567899', 'Student Four', '2000-10-01', '204 Student Rd.', '100000000010', 4),
('student5@edu-center.com', 'password123', '1234567900', 'Student Five', '2000-11-01', '205 Student Rd.', '100000000011', 4),
('student6@edu-center.com', 'password123', '1234567901', 'Student Six', '2000-12-01', '206 Student Rd.', '100000000012', 4),
('student7@edu-center.com', 'password123', '1234567902', 'Student Seven', '2001-01-01', '207 Student Rd.', '100000000013', 4),
('student8@edu-center.com', 'password123', '1234567903', 'Student Eight', '2001-02-01', '208 Student Rd.', '100000000014', 4),
('student9@edu-center.com', 'password123', '1234567904', 'Student Nine', '2001-03-01', '209 Student Rd.', '100000000015', 4),
('student10@edu-center.com', 'password123', '1234567905', 'Student Ten', '2001-04-01', '210 Student Rd.', '100000000016', 4),
('student11@edu-center.com', 'password123', '1234567906', 'Student Eleven', '2001-05-01', '211 Student Rd.', '100000000017', 4),
('student12@edu-center.com', 'password123', '1234567907', 'Student Twelve', '2001-06-01', '212 Student Rd.', '100000000018', 4),
('student13@edu-center.com', 'password123', '1234567908', 'Student Thirteen', '2001-07-01', '213 Student Rd.', '100000000019', 4),
('student14@edu-center.com', 'password123', '1234567909', 'Student Fourteen', '2001-08-01', '214 Student Rd.', '100000000020', 4),
('student15@edu-center.com', 'password123', '1234567910', 'Student Fifteen', '2001-09-01', '215 Student Rd.', '100000000021', 4);

-- Admin Table - Drop table because table has no additional information and no other relation
-- CREATE TABLE Admin (
--                        ID INT PRIMARY KEY AUTO_INCREMENT,
--                        User_ID INT NOT NULL UNIQUE,
--                        FOREIGN KEY (User_ID) REFERENCES User(ID)
-- );
# drop table admin;

-- Student Status Table
CREATE TABLE Student_Status (
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                Name VARCHAR(50) NOT NULL UNIQUE
);
insert into student_status (name) values
	('Dropped out'), 
    ('Suspended'), 
    ('Studying'),
    ('Waiting for class');

-- Tuition Status Table
CREATE TABLE Tuition_Status (
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                Name VARCHAR(50) NOT NULL UNIQUE
);
insert into tuition_status (name) values
	('Paid'),
    ('Unpaid');

-- Subject Table
CREATE TABLE Subject (
                         ID INT PRIMARY KEY AUTO_INCREMENT,
                         Name VARCHAR(100) NOT NULL UNIQUE
);
insert into subject (name) values 
	('Java Web Fullstack'),
    ('PHP Web Backend'),
    ('Web Frontend');

-- Tutor Table
CREATE TABLE Tutor (
                       ID INT PRIMARY KEY AUTO_INCREMENT,
                       User_ID INT NOT NULL UNIQUE,
                       FOREIGN KEY (User_ID) REFERENCES User(ID)
);

insert into tutor (user_id)
select id from user
where role_id = 1;

-- Officer Table - Drop because has no additional information and no other relation
-- CREATE TABLE Officer (
--                          ID INT PRIMARY KEY AUTO_INCREMENT,
--                          User_ID INT NOT NULL UNIQUE,
--                          FOREIGN KEY (User_ID) REFERENCES User(ID)
-- );
# drop table officer;


-- Class Table
CREATE TABLE Clazz (
                       ID INT PRIMARY KEY AUTO_INCREMENT,
                       Name VARCHAR(100) NOT NULL,
                       Tutor_ID INT NOT NULL,
                       Subject_ID INT NOT NULL,
                       FOREIGN KEY (Tutor_ID) REFERENCES Tutor(ID),
                       FOREIGN KEY (Subject_ID) REFERENCES Subject(ID)
);
insert into clazz (name, tutor_id, subject_id) values
	('JV101-HN', 1, 1),
	('PHP102-DN', 2, 2),
	('JV103-HCM', 3, 3);

-- Student Table
CREATE TABLE Student (
                         ID INT PRIMARY KEY AUTO_INCREMENT,
                         User_ID INT NOT NULL UNIQUE,
                         Tuition_Status_ID INT NOT NULL,
                         Student_Status_ID INT NOT NULL,
                         Class_ID INT,
                         FOREIGN KEY (User_ID) REFERENCES User(ID),
                         FOREIGN KEY (Tuition_Status_ID) REFERENCES Tuition_Status(ID),
                         FOREIGN KEY (Student_Status_ID) REFERENCES Student_Status(ID),
                         FOREIGN KEY (Class_ID) REFERENCES Clazz(ID)
);
-- Insert 15 students with specific details
INSERT INTO Student (User_ID, Tuition_Status_ID, Student_Status_ID, Class_ID)
VALUES
(7, 1, 1, 1),   -- Student 1
(8, 1, 2, 1),   -- Student 2
(9, 2, 3, 1),   -- Student 3
(10, 2, 4, 1),  -- Student 4
(11, 1, 1, 2),  -- Student 5
(12, 1, 2, 2),  -- Student 6
(13, 2, 3, 2),  -- Student 7
(14, 2, 4, 2),  -- Student 8
(15, 1, 1, 3),  -- Student 9
(16, 1, 2, 3),  -- Student 10
(17, 2, 3, 3),  -- Student 11
(18, 2, 4, 3),  -- Student 12
(19, 1, 1, 1),  -- Student 13
(20, 1, 2, 2),  -- Student 14
(21, 2, 3, 3);  -- Student 15


-- Exam Session Table
CREATE TABLE Exam_Session (
                              ID INT PRIMARY KEY AUTO_INCREMENT,
                              Name VARCHAR(100) NOT NULL,
                              Exam_Date DATE NOT NULL
);
INSERT INTO Exam_Session (Name, Exam_Date)
VALUES
('Exam Module 1', '2022-06-06'),
('Exam Module 2', '2022-07-07'),
('Exam Module 3', '2022-08-08'),
('Exam Module 4', '2022-09-09'),
('Exam Module 5', '2022-10-10'),
('Exam Module 6', '2022-12-12');

-- Exam Result Table
CREATE TABLE Exam_Result (
                             Exam_Session_ID INT,
                             Student_ID INT,
                             Theory_Score DECIMAL(5,2),
                             Practical_Score DECIMAL(5,2),
                             Average_Score DECIMAL(5,2) GENERATED ALWAYS AS ((Theory_Score + Practical_Score) / 2) STORED,
                             PRIMARY KEY (Exam_Session_ID, Student_ID),
                             FOREIGN KEY (Exam_Session_ID) REFERENCES Exam_Session(ID),
                             FOREIGN KEY (Student_ID) REFERENCES Student(ID)
);
-- Insert scores for all students in all exam sessions
INSERT INTO Exam_Result (Exam_Session_ID, Student_ID, Theory_Score, Practical_Score)
VALUES
-- Exam Session 1
(1, 1, 85.00, 90.00),
(1, 2, 78.50, 82.00),
(1, 3, 88.00, 91.00),
(1, 4, 75.50, 80.00),
(1, 5, 82.00, 85.50),
(1, 6, 79.00, 84.00),
(1, 7, 87.50, 89.50),
(1, 8, 92.00, 95.00),
(1, 9, 70.50, 75.00),
(1, 10, 88.00, 86.50),
(1, 11, 78.00, 81.00),
(1, 12, 85.00, 87.50),
(1, 13, 90.00, 92.50),
(1, 14, 77.50, 79.00),
(1, 15, 83.50, 84.50),
-- Exam Session 2
(2, 1, 88.00, 85.00),
(2, 2, 82.00, 79.50),
(2, 3, 91.00, 87.50),
(2, 4, 77.50, 74.50),
(2, 5, 85.50, 83.00),
(2, 6, 80.00, 78.50),
(2, 7, 89.50, 85.00),
(2, 8, 93.50, 92.00),
(2, 9, 72.50, 70.50),
(2, 10, 87.50, 89.00),
(2, 11, 79.50, 76.50),
(2, 12, 86.50, 88.00),
(2, 13, 91.50, 90.00),
(2, 14, 78.50, 77.00),
(2, 15, 84.00, 83.00),
-- Exam Session 3
(3, 1, 90.00, 88.00),
(3, 2, 81.50, 79.00),
(3, 3, 89.50, 88.50),
(3, 4, 76.00, 73.00),
(3, 5, 85.00, 84.50),
(3, 6, 81.50, 80.50),
(3, 7, 88.50, 87.00),
(3, 8, 94.00, 93.00),
(3, 9, 74.00, 72.00),
(3, 10, 86.00, 88.50),
(3, 11, 80.50, 79.50),
(3, 12, 87.00, 89.00),
(3, 13, 92.50, 90.50),
(3, 14, 79.00, 77.50),
(3, 15, 85.50, 82.50),
-- Exam Session 4
(4, 1, 89.00, 90.00),
(4, 2, 80.00, 82.00),
(4, 3, 90.00, 91.00),
(4, 4, 78.50, 75.50),
(4, 5, 84.50, 86.00),
(4, 6, 80.00, 79.00),
(4, 7, 87.00, 89.50),
(4, 8, 93.00, 94.00),
(4, 9, 73.50, 76.00),
(4, 10, 88.00, 87.50),
(4, 11, 81.00, 82.00),
(4, 12, 86.00, 88.50),
(4, 13, 90.50, 92.00),
(4, 14, 77.50, 78.00),
(4, 15, 84.50, 85.50),
-- Exam Session 5
(5, 1, 91.00, 92.50),
(5, 2, 82.50, 83.00),
(5, 3, 88.50, 89.50),
(5, 4, 75.50, 77.50),
(5, 5, 85.50, 86.50),
(5, 6, 79.50, 81.50),
(5, 7, 86.50, 88.50),
(5, 8, 92.50, 93.50),
(5, 9, 72.50, 74.00),
(5, 10, 89.00, 90.50),
(5, 11, 80.00, 81.50),
(5, 12, 87.50, 89.50),
(5, 13, 91.50, 93.00),
(5, 14, 79.50, 80.00),
(5, 15, 83.50, 85.00),
-- Exam Session 6
(6, 1, 92.00, 91.00),
(6, 2, 81.00, 80.00),
(6, 3, 89.00, 90.00),
(6, 4, 74.50, 73.50),
(6, 5, 86.00, 85.50),
(6, 6, 78.00, 80.00),
(6, 7, 85.00, 84.50),
(6, 8, 94.50, 95.00),
(6, 9, 73.00, 71.50),
(6, 10, 88.50, 89.50),
(6, 11, 79.50, 78.50),
(6, 12, 86.50, 87.50),
(6, 13, 92.50, 91.50),
(6, 14, 78.50, 77.50),
(6, 15, 83.00, 82.50);


















