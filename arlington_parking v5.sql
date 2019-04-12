-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: arlington_parking
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parkingtype` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `parkingarea_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `capacity` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `cart` float DEFAULT NULL,
  `camera` float DEFAULT NULL,
  `history` float DEFAULT NULL,
  `availablespots` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'Basic','West Garage',226,5,15.95,2.95,1.95,226),(2,'Midrange','West Garage',749,2,15.95,2.95,1.95,749),(3,'Midrange','West Garage',750,3,15.95,2.95,1.95,750),(4,'Midrange','West Garage',750,4,15.95,2.95,1.95,750),(5,'Premium','West Garage',229,1,15.95,2.95,1.95,229),(6,'Access','West Garage',19,1,15.95,2.95,1.95,19),(7,'Basic','Maverick',199,1,15.95,2.95,1.95,199),(8,'Access','Maverick',20,1,15.95,2.95,1.95,20),(9,'Basic','Davis',150,1,15.95,2.95,1.95,150),(10,'Access','Davis',19,1,15.95,2.95,1.95,19),(11,'Basic','Nedderman',180,1,15.95,2.95,1.95,179),(12,'Access','Nedderman',20,1,15.95,2.95,1.95,20),(13,'Premium','Commons',25,1,0,0,0,20);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name_on_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Allow null, if null then the name on card is user.first + â€˜ â€˜ +user.last, therefore on web page add check box â€œuse same name as person making reservationâ€',
  `card_number` char(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `card_expires` date NOT NULL,
  `card_security_code` char(3) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_user` (`user_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `reservation_uname` varchar(15) NOT NULL,
  `reservation_parkingId` int(11) NOT NULL,
  PRIMARY KEY (`reservation_id`),
  UNIQUE KEY `reservation_id_UNIQUE` (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (28,'05:00:00','08:00:00','siddhx1',0),(38,'06:00:00','07:00:00','null',1),(45,'07:00:00','08:00:00','null',1),(47,'08:00:00','12:00:00','null',1),(60,'14:00:00','15:00:00','null',1),(61,'15:00:00','16:00:00','null',1),(62,'16:00:00','16:15:00','null',1),(63,'16:15:00','16:30:00','null',1),(64,'16:30:00','17:00:00','siddhx1',1),(66,'18:45:00','19:00:00','null',1),(67,'19:00:00','19:01:00','null',1),(68,'19:01:00','19:02:00','null',1),(69,'19:02:00','19:03:00','null',1),(70,'19:03:00','19:04:00','null',1),(71,'19:04:00','19:05:00','null',1),(72,'19:05:00','19:06:00','null',1),(73,'19:06:00','19:07:00','null',1),(74,'19:07:00','19:08:00','null',1),(75,'19:08:00','19:09:00','null',1),(76,'19:09:00','19:10:00','null',1),(77,'19:10:00','19:11:00','null',1),(78,'19:11:00','19:12:00','null',1),(79,'19:13:00','19:14:00','null',1),(80,'19:15:00','19:16:00','null',1),(81,'19:17:00','19:18:00','null',1),(82,'19:17:00','19:18:00','null',1),(83,'19:25:00','19:26:00','null',1),(84,'03:00:00','07:00:00','null',1),(85,'03:00:00','07:00:00','null',1),(86,'03:00:00','07:00:00','null',1),(87,'00:00:00','00:00:00','null',1),(88,'22:00:00','22:01:00','null',7),(89,'22:00:00','22:01:00','null',6),(90,'15:00:00','19:15:00','siddhx1',1),(91,'22:00:00','22:45:00','siddhx1',5),(93,'00:00:00','06:00:00','siddhx1',2),(94,'03:00:00','06:00:00','siddhx1',1),(95,'05:15:00','08:15:00','siddhx1',10),(96,'03:00:00','07:00:00','siddhx1',7),(97,'18:45:00','19:30:00','siddhx1',11);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utaid` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `phone` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'customer',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `zipcode` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `parkingpermitype` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `noshow_status` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `violation_status` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`,`username`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'1234567890','manager','sid','agar','Ctyne#0570482','sid@email.com','1234567890','Manager','tex','408 st','76013','Basic','arlington','1','1',1),(4,'1001577570','siddhx1','Siddharth','Agarwal','Ctyne#0570482','siddharth@email.com','4154491722','User','texas','408 st','76012','Basic','arlington','1','1',1),(5,'1001577570','admin','sid','agar','Ctyne#0570482','email@g.com','4154491222','Admin','tex','408 st','76012','Basic','arlington',NULL,NULL,1),(6,'1001577577','test','test','user','Ctyne#0570482','Sid@gmail.com','4154491720','Manager','texas','408 kerby st','76016','Basic','arlington','2',NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-12 15:53:26
