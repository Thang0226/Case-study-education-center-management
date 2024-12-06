create database center_management;

use center_management;

-- Role Table
CREATE TABLE Role (
                      ID INT PRIMARY KEY AUTO_INCREMENT,
                      Name VARCHAR(50) NOT NULL UNIQUE
);


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

-- Admin Table
CREATE TABLE Admin (
                       ID INT PRIMARY KEY AUTO_INCREMENT,
                       User_ID INT NOT NULL UNIQUE,
                       FOREIGN KEY (User_ID) REFERENCES User(ID)
);

-- Student Status Table
CREATE TABLE Student_Status (
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                Name VARCHAR(50) NOT NULL UNIQUE
);

-- Tuition Status Table
CREATE TABLE Tuition_Status (
                                ID INT PRIMARY KEY AUTO_INCREMENT,
                                Name VARCHAR(50) NOT NULL UNIQUE
);

-- Subject Table
CREATE TABLE Subject (
                         ID INT PRIMARY KEY AUTO_INCREMENT,
                         Name VARCHAR(100) NOT NULL UNIQUE
);

-- Tutor Table
CREATE TABLE Tutor (
                       ID INT PRIMARY KEY AUTO_INCREMENT,
                       User_ID INT NOT NULL UNIQUE,
                       FOREIGN KEY (User_ID) REFERENCES User(ID)
);


-- Officer Table
CREATE TABLE Officer (
                         ID INT PRIMARY KEY AUTO_INCREMENT,
                         User_ID INT NOT NULL UNIQUE,
                         FOREIGN KEY (User_ID) REFERENCES User(ID)
);

-- Class Table
CREATE TABLE Clazz (
                       ID INT PRIMARY KEY AUTO_INCREMENT,
                       Name VARCHAR(100) NOT NULL,
                       Tutor_ID INT NOT NULL,
                       Subject_ID INT NOT NULL,
                       FOREIGN KEY (Tutor_ID) REFERENCES Tutor(ID),
                       FOREIGN KEY (Subject_ID) REFERENCES Subject(ID)
);

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

-- Exam Session Table
CREATE TABLE Exam_Session (
                              ID INT PRIMARY KEY AUTO_INCREMENT,
                              Name VARCHAR(100) NOT NULL,
                              Exam_Date DATE NOT NULL
);

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

