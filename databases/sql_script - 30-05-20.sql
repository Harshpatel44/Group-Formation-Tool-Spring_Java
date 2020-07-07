-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: db-5308.cs.dal.ca    Database: CSCI5308_9_DEVINT
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.21-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adminUser`
--

DROP TABLE IF EXISTS `adminUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminUser` (
  `adminId` varchar(10) NOT NULL,
  PRIMARY KEY (`adminId`),
  CONSTRAINT `adminUser_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminUser`
--

LOCK TABLES `adminUser` WRITE;
/*!40000 ALTER TABLE `adminUser` DISABLE KEYS */;
/*!40000 ALTER TABLE `adminUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assistantCourse`
--

DROP TABLE IF EXISTS `assistantCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assistantCourse` (
  `courseId` varchar(10) DEFAULT NULL,
  `assistantId` varchar(10) DEFAULT NULL,
  KEY `assistantId` (`assistantId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `assistantCourse_ibfk_1` FOREIGN KEY (`assistantId`) REFERENCES `teachingAssistant` (`assistantId`),
  CONSTRAINT `assistantCourse_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assistantCourse`
--

LOCK TABLES `assistantCourse` WRITE;
/*!40000 ALTER TABLE `assistantCourse` DISABLE KEYS */;
INSERT INTO `assistantCourse` VALUES ('CSCI3','B00234561');
/*!40000 ALTER TABLE `assistantCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `courseId` varchar(10) NOT NULL,
  `courseName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('123','Data'),('231','Mobile'),('CSCI1','course1'),('CSCI2','course2'),('CSCI3','course3');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `instructorId` varchar(10) NOT NULL,
  PRIMARY KEY (`instructorId`),
  CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`instructorId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES ('B00123456');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructorCourse`
--

DROP TABLE IF EXISTS `instructorCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructorCourse` (
  `courseId` varchar(10) DEFAULT NULL,
  `instructorId` varchar(10) DEFAULT NULL,
  KEY `instructorId` (`instructorId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `instructorCourse_ibfk_1` FOREIGN KEY (`instructorId`) REFERENCES `instructor` (`instructorId`),
  CONSTRAINT `instructorCourse_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructorCourse`
--

LOCK TABLES `instructorCourse` WRITE;
/*!40000 ALTER TABLE `instructorCourse` DISABLE KEYS */;
INSERT INTO `instructorCourse` VALUES ('CSCI2','B00123456');
/*!40000 ALTER TABLE `instructorCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentId` varchar(10) NOT NULL,
  `programName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`studentId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('123','data'),('B00123456','MACS'),('B00234561','MEC'),('B00345612','MCS');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentCourse`
--

DROP TABLE IF EXISTS `studentCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentCourse` (
  `courseId` varchar(10) DEFAULT NULL,
  `studentId` varchar(10) DEFAULT NULL,
  KEY `studentId` (`studentId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `studentCourse_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`),
  CONSTRAINT `studentCourse_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentCourse`
--

LOCK TABLES `studentCourse` WRITE;
/*!40000 ALTER TABLE `studentCourse` DISABLE KEYS */;
INSERT INTO `studentCourse` VALUES ('CSCI1','B00123456'),('CSCI2','B00234561'),('CSCI3','B00345612');
/*!40000 ALTER TABLE `studentCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachingAssistant`
--

DROP TABLE IF EXISTS `teachingAssistant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachingAssistant` (
  `assistantId` varchar(10) NOT NULL,
  PRIMARY KEY (`assistantId`),
  CONSTRAINT `teachingAssistant_ibfk_1` FOREIGN KEY (`assistantId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachingAssistant`
--

LOCK TABLES `teachingAssistant` WRITE;
/*!40000 ALTER TABLE `teachingAssistant` DISABLE KEYS */;
INSERT INTO `teachingAssistant` VALUES ('B00234561');
/*!40000 ALTER TABLE `teachingAssistant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userRoles`
--

DROP TABLE IF EXISTS `userRoles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userRoles` (
  `userId` varchar(10) DEFAULT NULL,
  `userRole` varchar(20) DEFAULT NULL,
  KEY `userId` (`userId`),
  CONSTRAINT `userRoles_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userRoles`
--

LOCK TABLES `userRoles` WRITE;
/*!40000 ALTER TABLE `userRoles` DISABLE KEYS */;
INSERT INTO `userRoles` VALUES ('B00123456','instructor'),('B00123456','student'),('B00234561','TA'),('B00234561','student'),('B00345612','student');
/*!40000 ALTER TABLE `userRoles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` varchar(10) NOT NULL,
  `passwd` varchar(10) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contactNo` int(12) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('123','123','dhruvesh','patel','dhruvesh@gmail.com',1234567890),('B00123456','pass1','f1','l1','f1@dal.ca',1234567890),('B00234561','pass2','f2','l2','f2@dal.ca',1223646890),('B00345612','pass3','f3','l3','f3@dal.ca',1832453590);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-31  0:54:33
