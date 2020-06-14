use CSCI5308_9_DEVINT;
drop table userCourse;
drop table userRoles;
drop table courses;
drop table roles;
drop table users;

CREATE TABLE users(
	userId varchar(10),
    passwd varchar(10),
    firstName varchar(50),
    lastName varchar(50),
    email varchar(50),
    contactNo int(12),
    PRIMARY KEY (userId)
);

CREATE TABLE roles(
	roleId int(2) auto_increment,
    roleType varchar(20),
    PRIMARY KEY(roleId)
);

CREATE TABLE courses(
	courseId varchar(10),
    courseName varchar(30),
    PRIMARY KEY (courseId)
);


CREATE TABLE userCourse(
	courseId varchar(10),
    userId varchar(10),
    roleId int(2),
    FOREIGN KEY (userId) REFERENCES users(userId),
    FOREIGN KEY (roleId) REFERENCES roles(roleId),
    FOREIGN KEY (courseId) REFERENCES courses(courseId),
    PRIMARY KEY(courseId,userId,roleId)
);

CREATE TABLE questionManager(
	userId varchar(10),
    questionId int AUTO_INCREMENT,
    questionTopic varchar(500),
    questionDesc varchar(1000),
    dateStamp date,
    FOREIGN KEY (userId) REFERENCES users(userId),
    PRIMARY KEY(questionId)
);

CREATE TABLE optionManager(
	questionId int,
    optionRank int,
    optionsDesc varchar(1000),
    FOREIGN KEY (questionId) REFERENCES questionManager(questionId)
);

insert into roles(roleId,roleType)
values
(1,"student"),
(2,"teachingAssistant"),
(3,"instructor"),
(4,"admin")
;

insert into users (userId, passwd, firstName, lastName, email, contactNo)
values
("B00123456","pass1","f1","l1","f1@dal.ca",1234567890),
("B00234561","pass2","f2","l2","f2@dal.ca",1223646890),
("B00345612","pass3","f3","l3","f3@dal.ca",1832453590)
;

insert into courses(courseId, courseName)
values
("CSCI1","course1"),
("CSCI2","course2"),
("CSCI3","course3")
;

insert into userCourse(courseId, userId, roleId)
values
("CSCI1","B00123456",1),
("CSCI2","B00123456",2),
("CSCI3","B00123456",3),
("CSCI2","B00234561",2),
("CSCI3","B00234561",3),
("CSCI1","B00345612",3)
;

insert into questionManager(userId,questionTopic,questionDesc,dateStamp) 
values
	('B00100100','question1Topic','question1Description','2020-06-14'),
    ('B00123456','question2Topic','question2Description','2020-06-14')
    ;

insert into optionManager 
values
	(2,2,'answer2Question2'),
	(2,3,'answer3Question2'),
	(2,1,'answer1Question2'),
	(3,2,'answer2Question3'),
	(3,3,'answer3Question3'),
	(3,1,'answer1Question3')
;

