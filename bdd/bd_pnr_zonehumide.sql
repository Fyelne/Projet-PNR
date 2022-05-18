-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_pnr
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `zonehumide`
--

DROP TABLE IF EXISTS `zonehumide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zonehumide` (
  `zh_id` int NOT NULL,
  `zh_temporaire` int DEFAULT NULL,
  `zh_profondeur` decimal(10,0) DEFAULT NULL,
  `zh_surface` decimal(10,0) DEFAULT NULL,
  `zh_typeMare` varchar(50) DEFAULT NULL,
  `zh_pente` varchar(50) DEFAULT NULL,
  `zh_ouverture` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`zh_id`),
  CONSTRAINT `dom_zhOuverture` CHECK ((`zh_ouverture` in (_utf8mb4'Abritee',_utf8mb4'Semi-Abritee',_utf8mb4'Ouverte'))),
  CONSTRAINT `dom_zhPente` CHECK ((`zh_pente` in (_utf8mb4'Raide',_utf8mb4'Abrupte',_utf8mb4'Douce'))),
  CONSTRAINT `dom_zhTypeMare` CHECK ((`zh_typeMare` in (_utf8mb4'Prairie',_utf8mb4'Etang',_utf8mb4'Marais',_utf8mb4'Mare')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonehumide`
--

LOCK TABLES `zonehumide` WRITE;
/*!40000 ALTER TABLE `zonehumide` DISABLE KEYS */;
INSERT INTO `zonehumide` VALUES (1,1,60,100,'Prairie','Douce','Abritee'),(2,1,15,20,NULL,'Abrupte','Abritee'),(3,1,40,25,NULL,'Douce',NULL),(4,1,NULL,NULL,NULL,NULL,'Ouverte'),(5,0,100,100,'Etang','Douce',NULL),(6,1,NULL,NULL,NULL,NULL,'Abritee'),(7,1,10,50,NULL,'Douce','Abritee'),(8,1,50,4,NULL,NULL,'Abritee'),(9,0,50,4,NULL,'Raide','Abritee'),(10,1,NULL,NULL,'Prairie',NULL,'Abritee'),(11,1,40,NULL,NULL,'Douce','Ouverte'),(12,0,100,150,NULL,'Abrupte','Ouverte'),(13,0,70,20,'Mare','Douce','Ouverte'),(14,0,60,8,NULL,'Douce','Abritee'),(15,0,130,300,'Etang','Douce','Ouverte'),(16,0,60,75,'Marais','Douce','Abritee'),(17,1,40,70,'Mare',NULL,'Abritee'),(18,1,10,100,'Prairie','Douce','Ouverte'),(19,0,40,30,'Mare','Douce',NULL),(20,1,5,20,NULL,'Douce',NULL),(21,1,50,2,NULL,'Raide',NULL),(22,0,NULL,NULL,NULL,NULL,'Abritee'),(23,0,30,100,NULL,'Douce','Abritee'),(24,0,150,40,'Mare','Douce','Abritee'),(25,1,30,20,NULL,'Abrupte','Abritee'),(26,1,50,NULL,NULL,'Douce','Ouverte'),(27,1,0,0,NULL,'Abrupte','Abritee'),(28,1,0,0,NULL,'Douce','Abritee'),(29,1,40,30,'Mare','Douce',NULL),(30,1,20,5,'Mare',NULL,'Ouverte'),(31,1,NULL,NULL,NULL,NULL,'Ouverte'),(32,0,100,1000,'Marais','Douce','Ouverte'),(33,1,30,20,NULL,'Douce',NULL),(34,0,NULL,NULL,NULL,NULL,NULL),(35,1,50,25,'Prairie','Douce','Ouverte'),(36,0,50,1000,'Marais','Douce','Ouverte'),(37,0,40,200,'Marais','Douce','Abritee'),(38,1,20,5,'Marais','Douce',NULL),(39,0,NULL,NULL,NULL,NULL,'Abritee'),(40,0,40,100,'Marais','Douce','Ouverte'),(41,1,NULL,NULL,NULL,NULL,'Abritee'),(42,1,50,16,NULL,'Douce',NULL),(43,0,200,5000,'Marais','Douce','Abritee'),(44,0,NULL,NULL,'Marais',NULL,'Abritee'),(45,0,40,100,'Marais','Douce','Ouverte'),(46,0,20,300,'Marais','Douce','Abritee'),(47,0,NULL,NULL,NULL,NULL,NULL),(48,1,50,10,NULL,'Douce','Ouverte'),(49,0,100,100,'Marais','Douce','Abritee'),(50,1,50,30,'Mare','Douce','Abritee'),(51,0,15,9,NULL,'Douce','Ouverte'),(52,0,200,2000,'Etang','Abrupte','Ouverte'),(53,0,50,60,'Mare','Douce','Abritee'),(54,1,NULL,NULL,'Prairie',NULL,'Abritee'),(55,1,50,50,'Mare','Douce','Abritee'),(56,1,30,240,NULL,'Douce',NULL),(57,1,50,25,NULL,'Douce','Abritee'),(58,0,NULL,NULL,NULL,NULL,NULL),(59,1,10,80,'Prairie','Douce','Ouverte'),(60,0,30,60,'Etang','Douce','Ouverte'),(61,1,-1,100,'Marais','Abrupte','Ouverte'),(62,0,NULL,NULL,'Mare',NULL,'Ouverte'),(63,0,100,30,'Mare','Douce','Ouverte'),(64,0,NULL,NULL,'Marais',NULL,'Abritee'),(65,0,100,1000,'Marais','Abrupte','Ouverte'),(66,1,5,500,'Prairie',NULL,NULL),(67,0,60,100,'Etang','Douce','Abritee'),(68,0,50,15,'Mare','Douce','Ouverte'),(69,0,20,15,'Marais','Douce','Abritee'),(70,1,50,100,'Marais','Abrupte','Abritee'),(71,1,0,0,'Prairie','Douce','Abritee'),(72,0,NULL,130,'Mare',NULL,'Abritee'),(73,1,15,100,'Prairie','Douce','Abritee'),(74,1,30,30,'Prairie','Douce','Abritee'),(75,0,30,200,'Marais',NULL,'Abritee'),(76,1,30,15,'Prairie','Douce','Abritee'),(77,1,20,20,'Marais','Douce','Ouverte'),(78,1,0,0,'Prairie','Douce','Abritee'),(79,0,NULL,1000,'Etang',NULL,'Abritee'),(80,1,20,40,'Marais',NULL,'Abritee'),(81,0,50,25,'Marais','Douce','Abritee'),(82,0,1,100,'Marais',NULL,'Abritee');
/*!40000 ALTER TABLE `zonehumide` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 13:16:41
