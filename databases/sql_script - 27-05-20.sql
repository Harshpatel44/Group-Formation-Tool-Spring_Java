
/******************************
** File: sql_script
** Desc: SQL script for initial database
** Auth: Harsh Patel
** Date: 27-5-20
**************************
** Change History
**************************
** PR   Date        Author  Description
** --   --------   -------   ------------------------------------
*******************************/

use CSCI5308_9_DEVINT;

CREATE TABLE users(
	userId varchar(10),
    passwd varchar(10),
    firstName varchar(50),
    lastName varchar(50),
    email varchar(50),
    contactNo int(12),
    PRIMARY KEY (userId)
);

CREATE TABLE userTypes(
	userId varchar(10),
    usertype varchar(20),
    FOREIGN KEY (userId) REFERENCES users(userId)
);

CREATE TABLE course(
	courseId varchar(10),
    courseName varchar(30),
    PRIMARY KEY (courseId)
);

CREATE TABLE instructor(
	instructorId varchar(10),
    PRIMARY KEY	(instructorId),
    FOREIGN KEY (instructorId) REFERENCES users(userId)
);

CREATE TABLE student(
	studentId varchar(10),
    programName varchar(100),
    PRIMARY KEY	(studentId),
    FOREIGN KEY (studentId) REFERENCES users(userId)
);

CREATE TABLE teachingAssistant(
	assistantId varchar(10),
    PRIMARY KEY	(assistantId),
    FOREIGN KEY (assistantId) REFERENCES users(userId)
);

CREATE TABLE adminUser(
	adminId varchar(10),
    PRIMARY KEY	(adminId),
    FOREIGN KEY (adminId) REFERENCES users(userId)
);

CREATE TABLE instructorCourse(
	courseId varchar(10),
    instructorId varchar(10),
    FOREIGN KEY (instructorId) REFERENCES instructor(instructorId),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);

CREATE TABLE studentCourse(
	courseId varchar(10),
    studentId varchar(10),
    FOREIGN KEY (studentId) REFERENCES student(studentId),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);

CREATE TABLE assistantCourse(
	courseId varchar(10),
    assistantId varchar(10),
    FOREIGN KEY (assistantId) REFERENCES teachingAssistant(assistantId),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
);
