-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: smartcontactmanager
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `contactid` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `linked_inlink` varchar(255) DEFAULT NULL,
  `weblink` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `favourite` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `profile_pictureurl` varchar(255) DEFAULT NULL,
  `user_userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contactid`),
  UNIQUE KEY `UK92v40q7dfyegxxeeyfu5hxhsd` (`phone_number`),
  KEY `FKeurbvac18t5cgxtr3qcfkbs11` (`user_userid`),
  CONSTRAINT `FKeurbvac18t5cgxtr3qcfkbs11` FOREIGN KEY (`user_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES ('8c37ec48-f9eb-46f2-88da-12a9efc7943f','village-pota ,district-mahendragarh,Haryana-123027','https://www.google2.com','https://www.google.com','He is  my classmate','Mohit@gmail.com',_binary '\0','Mohit','8549392482','6acf6d3c-cd78-4ae7-b8d4-d39ad18b448c','https://res.cloudinary.com/dgdznrtb8/image/upload/v1767344870/6acf6d3c-cd78-4ae7-b8d4-d39ad18b448c.jpg','10cda822-f1b7-430e-a981-353d6670f67c'),('b295972c-e850-4cb4-9321-de05a483909c','village-pota ,district-mahendragarh,Haryana-123027','https://www.google2.com','https://www.google1.com','bhai hain mera bada','kaushikAmit532004@gmail.com',_binary '','Amit Kaushik','8607456178','a9c6e78c-a9ff-4ab3-9a3e-d50ba4263737','https://res.cloudinary.com/dgdznrtb8/image/upload/v1766822208/a9c6e78c-a9ff-4ab3-9a3e-d50ba4263737.jpg','10cda822-f1b7-430e-a981-353d6670f67c'),('b31491bd-63f6-40b1-aca3-2ec4897e3e7a','village-pota ,district-mahendragarh,Haryana-123027','https://www.google2.com','https://www.google.com','Her name is Anshu','gullu@gmail.com',_binary '','Anshu Kaushik','08607456179','ff141f7f-aa6a-4c84-ac12-99b8a383ab30','https://res.cloudinary.com/dgdznrtb8/image/upload/v1767344924/ff141f7f-aa6a-4c84-ac12-99b8a383ab30.jpg','10cda822-f1b7-430e-a981-353d6670f67c');
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_link`
--

DROP TABLE IF EXISTS `social_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_link` (
  `id` varchar(255) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `contacts_contactid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmcqxl2qkn20iwwcwr62lgan6` (`contacts_contactid`),
  CONSTRAINT `FKmcqxl2qkn20iwwcwr62lgan6` FOREIGN KEY (`contacts_contactid`) REFERENCES `contacts` (`contactid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_link`
--

LOCK TABLES `social_link` WRITE;
/*!40000 ALTER TABLE `social_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `about` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_verified` bit(1) NOT NULL,
  `emailverifyotp` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `phone_verified` bit(1) NOT NULL,
  `profilepic` varchar(255) DEFAULT NULL,
  `profilepic_url` varchar(255) DEFAULT NULL,
  `provider` enum('GITHUB','GOOGLE','SELF') DEFAULT NULL,
  `providerid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK4bgmpi98dylab6qdvf9xyaxu4` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('020a77b0-f095-4b09-9fc1-4eea1b956792','Father','2222.btech@igu.ac.in',_binary '\0',NULL,_binary '\0','Jatin Kaushik','$2a$10$pqlxJHa6MmL2GRX0YE2wJ.ShsjzSlHjjOsVBcoDppeZmklAKcPFeK','7006525854',_binary '\0',NULL,'https://res.cloudinary.com/dgdznrtb8/image/upload/v1766414497/defaultprofile_edduwq.avif','SELF',NULL),('0afb24e1-c67d-4166-bae9-90db7487bce2','Admin\r\n','kaushikkiller05@gmail.com',_binary '\0','5445',_binary '\0','Jatin Kaushik','$2a$10$YL/d01bFTuIStyfTyN8ipO.UbrfkSh6lA/jdTMp/J1A7AYWuKwEtq','8607456179',_binary '\0',NULL,'https://res.cloudinary.com/dgdznrtb8/image/upload/v1766414497/defaultprofile_edduwq.avif','SELF',NULL),('10cda822-f1b7-430e-a981-353d6670f67c','admin','kaushikjatin532004@gmail.com',_binary '\0','4199',_binary '','Jatin Kaushik','$2a$10$iCuCrLGl101hqvzfQ8JQtegl/gz3IjCE1B1sqxpR9EXS7XvDrY.AK','6607456179',_binary '\0','2800c8a5-66df-4746-a8b1-92bcdae34258','https://res.cloudinary.com/dgdznrtb8/image/upload/v1766657297/2800c8a5-66df-4746-a8b1-92bcdae34258.jpg','SELF',NULL),('f824e083-1b64-41ea-9ecd-baa6944b158d','Me','Kaushikboy5005@gmail.com',_binary '\0','3226',_binary '\0','Jatin Kaushik','$2a$10$.7qTTsIk809uxk28uaolh.Ms9MzvugHTsdIcq6BD1xryZ1HYmzzoq','1607456179',_binary '\0',NULL,'https://res.cloudinary.com/dgdznrtb8/image/upload/v1766414497/defaultprofile_edduwq.avif','SELF',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rolelist`
--

DROP TABLE IF EXISTS `user_rolelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_rolelist` (
  `user_userid` varchar(255) NOT NULL,
  `rolelist` varchar(255) DEFAULT NULL,
  KEY `FKn38l3jk4mfps2mea82s5p9g4o` (`user_userid`),
  CONSTRAINT `FKn38l3jk4mfps2mea82s5p9g4o` FOREIGN KEY (`user_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rolelist`
--

LOCK TABLES `user_rolelist` WRITE;
/*!40000 ALTER TABLE `user_rolelist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_rolelist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-02 21:34:44
