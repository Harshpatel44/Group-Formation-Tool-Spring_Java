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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `courseId` varchar(10) NOT NULL,
  `courseName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('',''),('1234','data science123'),('20','big data12'),('CSC123','Data Analyser'),('CSCI1','course1'),('CSCI2','course2'),('CSCI3','course3'),('testid','testname');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgetPassword`
--

DROP TABLE IF EXISTS `forgetPassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgetPassword` (
  `userId` varchar(10) NOT NULL,
  `passKey` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgetPassword`
--

LOCK TABLES `forgetPassword` WRITE;
/*!40000 ALTER TABLE `forgetPassword` DISABLE KEYS */;
INSERT INTO `forgetPassword` VALUES ('B00100100','y3BvhYPGqW');
/*!40000 ALTER TABLE `forgetPassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optionManager`
--

DROP TABLE IF EXISTS `optionManager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optionManager` (
  `questionId` int(11) DEFAULT NULL,
  `optionRank` int(11) DEFAULT NULL,
  `optionsDesc` varchar(1000) DEFAULT NULL,
  KEY `questionId` (`questionId`),
  CONSTRAINT `optionManager_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `questionManager` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optionManager`
--

LOCK TABLES `optionManager` WRITE;
/*!40000 ALTER TABLE `optionManager` DISABLE KEYS */;
INSERT INTO `optionManager` VALUES (2,2,'answer2Question2'),(2,3,'answer3Question2'),(2,1,'answer1Question2'),(3,2,'answer2Question3'),(3,3,'answer3Question3'),(3,1,'answer1Question3');
/*!40000 ALTER TABLE `optionManager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionManager`
--

DROP TABLE IF EXISTS `questionManager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionManager` (
  `userId` varchar(10) DEFAULT NULL,
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `questionTopic` varchar(500) DEFAULT NULL,
  `questionDesc` varchar(1000) DEFAULT NULL,
  `dateStamp` date DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `userId` (`userId`),
  CONSTRAINT `questionManager_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionManager`
--

LOCK TABLES `questionManager` WRITE;
/*!40000 ALTER TABLE `questionManager` DISABLE KEYS */;
INSERT INTO `questionManager` VALUES ('B00100100',2,'question1Topic','question1Description','2020-06-14'),('B00123456',3,'question2Topic','question2Description','2020-06-14');
/*!40000 ALTER TABLE `questionManager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `roleId` int(2) NOT NULL AUTO_INCREMENT,
  `roleType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'student'),(2,'teachingAssistant'),(3,'instructor'),(4,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userCourse`
--

DROP TABLE IF EXISTS `userCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userCourse` (
  `courseId` varchar(10) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `roleId` int(2) NOT NULL,
  PRIMARY KEY (`courseId`,`userId`,`roleId`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `userCourse_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `userCourse_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`),
  CONSTRAINT `userCourse_ibfk_3` FOREIGN KEY (`courseId`) REFERENCES `courses` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userCourse`
--

LOCK TABLES `userCourse` WRITE;
/*!40000 ALTER TABLE `userCourse` DISABLE KEYS */;
INSERT INTO `userCourse` VALUES ('1234','B00854475',3),('20','B00234561',3),('20','B00854475',3),('CSC123','B00854475',3),('CSC123','b00878',1),('CSC123','b00879',1),('CSC123','b00880',1),('CSC123','b00881',1),('CSC123','b00882',1),('CSC123','b00883',1),('CSC123','b00884',1),('CSC123','b00885',1),('CSC123','b00886',1),('CSC123','b00890',1),('CSC123','b00891',1),('CSC123','b00892',1),('CSC123','b00893',1),('CSC123','b00894',1),('CSC123','b00895',1),('CSC123','b00896',1),('CSC123','b00897',1),('CSC123','b00898',1),('CSC123','b00898',2),('CSCI1','B00100100',3),('CSCI1','B00123456',2),('CSCI1','B00123456',3),('CSCI1','B00234561',3),('CSCI1','B00345612',3),('CSCI1','b00890',1),('CSCI1','b00891',1),('CSCI1','b00892',1),('CSCI1','b00893',1),('CSCI1','b00894',1),('CSCI1','b00895',1),('CSCI1','b00896',1),('CSCI1','b00897',1),('CSCI1','b00898',1),('CSCI2','B00234561',3),('CSCI2','b00878',1),('CSCI2','b00879',1),('CSCI2','b00880',1),('CSCI2','b00881',1),('CSCI2','b00882',1),('CSCI2','b00883',1),('CSCI2','b00884',1),('CSCI2','b00885',1),('CSCI2','b00886',1);
/*!40000 ALTER TABLE `userCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` varchar(10) NOT NULL,
  `passwd` varchar(355) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contactNo` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin','admin','admin','rutikapatel09@gmail.com','99999'),('B00100100','$2a$10$LHH/ZGFa/VAvbPqyPo5GLO/eYBEcUAb3ZEWjPW18vmxytvU55fnqe','harsh','patel','harshgp44@yahoo.in','123456789'),('B00123456','$2a$10$FHAId9RkO1pkWclrVrXkWOwj8fMN/VkzUxYOGB9Qt0AynfcyZ1Suq','f1','l1','rutikapatel09@gmail.com','1234567890'),('B00234561','pass2','f2','l2','f2@dal.ca','1223646890'),('B00345612','pass3','f3','l3','f3@dal.ca','1832453590'),('b00832234','$2a$10$0Wx/FwEdkYb08Dy2D22M1.NfZcLGXXFM0zc0DgkQ2/Ok8OHYwbd.O','Akashay','Hema','arjunstar14@gmail.com','9090990'),('B00852477','$2a$10$eStPdn5N494HqA0Ueru6LufiuOgWxmRVD0uOUAx9fi5EPFG2G3W7u','arjun','kh','arjunstar14@gmail.com','909090'),('B00852479','$2a$10$Yh2kPyJgCFJvm4KfRUha1eO2e/nkdRAH2XsuFHk1odWSXbM0pZggW','arjun','kh','arjunstar14@gmail.com','909090'),('B00854444','$2a$10$7OCYuvvKs9caFgp/D5.kN.GC6I8rPnSzvItYv9xqJjYfQNb..qOie','Akashay','hemaraj','arjunstar14@gmail.com','97878787'),('B00854475','$2a$10$hfcDbc24x2fM69pu9DQUK.pjnFtkqC83W4RTbGtFmWMYZ6Rl0sGte','Arjun','AA','arjunstar14@gmail.com','91123'),('B00854477','$2a$10$9Ss18FkoEbIutXabuF8sq.7ggOFEsGROXs2bVOMs06v1YgFTOgfBS','Akashay','hemaraj','arjunstar14@gmail.com','97878787'),('B00854575','$2a$10$undp.o1daADA42gWtDO4CuyOSZNg9N0d2h0iadTy8FO8lwAqJTE36','Abhinav','ramesh','abhinav.rmesh@gmail.com','12345'),('b00864436','$2a$10$BlMpP8j2xosJGc1Gsz1tyeAH/AN7zNBWluTk7nGW//ABv6XMoji5i','arjun','kh','arjunstar14@gmail.com','909090990'),('B00864475','$2a$10$5fipIfMQ14r23FDqT51eOu1ZbgEogfOFBAbcEgRt2lgPrH4.10t6O','arjun','kh','arjunstar14@gmail.com','909090'),('B00874475','$2a$10$hY2HpmkqvCRWd2aSfEbh/uOfG.NuJbnlmMBSLwy1quQ5xBsKL/BEO','arjun','kh','arjunstar14@gmail.com','90909090'),('b00878','$2a$10$EIs1bFE5NKk77v1zl6NvvePGXAdGIG6ehwW9PvaHv3.u3ndbl2BnO','wow1','sytem1','arjunstar14@gmail.com','944789'),('b00879','$2a$10$GZ182exfpKjIgEoImf8.0ewlRZLq5QzcagJBd6N.wRRObMy5ZsZNC','wow2','sytem2','arjunstar14@gmail.com','944789'),('b00880','$2a$10$BeovpsUf29f3yGVqsS1FEuvAY7vx3XrsWkWm5cHe.bxgWLf8u71j6','wow3','sytem3','arjunstar14@gmail.com','944789'),('b00881','$2a$10$50TqdmirmOnOtNc7N/FJsObQL6yVvuEvgoGgCwmdNTzQv7fE1XDI2','wow4','sytem4','arjunstar14@gmail.com','944789'),('b00882','$2a$10$fUOTAXd2T0aYu7yrUgz6EOT1txi50UONldADzXVryJI8fnDacvxOm','wow5','sytem5','arjunstar14@gmail.com','944789'),('b00883','$2a$10$787sQM/70MZHn9PPDMEQleKv3CiOjb2AlOjsFNT/UOvw1ZcJEMFrS','wow6','sytem6','arjunstar14@gmail.com','944789'),('b00884','$2a$10$X9SMfuSNDuTJAK.GUd9q4un.Bm0yJ868cpyAx1Y8410rZuA2.qdGy','wow7','sytem7','arjunstar14@gmail.com','944789'),('b00885','$2a$10$KVdEZ4IZ4TYzpuY4e1ZeWO4PRjRvzHNYYZa7GX3HpPBqy1zjTr8sa','wow8','sytem8','arjunstar14@gmail.com','944789'),('b00886','$2a$10$Bef3XLWpyBibgMsXVdG.YeUKYLQ/mV3uGqVw8iGP89kEXl2pD/91q','wow9','sytem9','arjunstar14@gmail.com','944789'),('b00890','$2a$10$uwm4lSN6o09N8vavovRgRudxeWwcXR0fTFkB6bfvrard5O5zlY9aa','wow10','sytem1','arjunstar14@gmail.com','988910'),('b00891','$2a$10$9ErS4T0uUsS24QGTQRwhM.R42CyditnJOtTwbpAcW7RZ/4.IEi.GW','wow11','sytem2','arjunstar14@gmail.com','988911'),('b00892','$2a$10$u.c3prY.ptZ/19TifAheLu6jdATK7gW9IwGVGE/laPGTw1TNBlRuC','wow12','sytem3','arjunstar14@gmail.com','988912'),('b00893','$2a$10$wUzGzBt5iCBLlo6nIPFnruPhhn3fnWBfL5CJK2Wy15jMh/qgdyWpC','wow13','sytem4','arjunstar14@gmail.com','988913'),('b00894','$2a$10$uM3N0yBaeAyPfLDUTuIcl.AG825MjIsy.ht/qXmfaS123cTomjcnS','wow14','sytem5','arjunstar14@gmail.com','988914'),('b00895','$2a$10$Q8GbNVtei1awwPCa3kfOJuryyNNrWq2sQw7rj6yAWVlpBPTlMo5w2','wow15','sytem6','arjunstar14@gmail.com','988915'),('b00896','$2a$10$zq4Ta.7/tb9mczaczdqhqOZfim6Wi5rCTLaAx2MvW6xuijPtXhiii','wow16','sytem7','arjunstar14@gmail.com','988916'),('b00897','$2a$10$LA3b79z/rzVGsAo4HE/dCOYFbrnjrCfNZtSHCM4kK52RGrYiIVDCC','wow17','sytem8','arjunstar14@gmail.com','988917'),('b00898','$2a$10$59L.Ooncxqui0f6ec8Fg6eu3e9nqfWqql0/qGKqZDy8qoPG9NXlGu','wow18','sytem9','arjunstar14@gmail.com','988918'),('B00999999','$2a$10$d9SOVsR1/WAV63ZFUFoMZOd9PiOimgT5EjKLBAmPWpr7FlR9r5VmO','harsh','patel','harshgp44@yahoo.in','1234578990'),('B22222222','$2a$10$AH226vUgXcWtrFarNggXtu6UPZACTRDi2DrWfWveeCRK45ur6.crm','arjun','kh','arjunstar14@gmail.com','909090'),('C00854475','$2a$10$siGzvlZVQkUH0zIH8zB/5OtamJksgVcQoLh1M5yQ1IQcoG..3pbFi','arjun','kh','arjunstar14@gmail.com','999999999');
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

-- Dump completed on 2020-06-14  2:01:06
