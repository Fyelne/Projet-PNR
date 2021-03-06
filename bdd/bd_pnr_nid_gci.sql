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
-- Table structure for table `nid_gci`
--

DROP TABLE IF EXISTS `nid_gci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nid_gci` (
  `idNid` int NOT NULL,
  `nomPlage` varchar(50) DEFAULT NULL,
  `raisonArretObservation` varchar(50) DEFAULT NULL,
  `nbEnvol` int DEFAULT NULL,
  `protection` int DEFAULT NULL,
  `bagueMale` varchar(50) DEFAULT NULL,
  `bagueFemelle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idNid`),
  CONSTRAINT `dom_raisonArretObservation` CHECK ((`raisonArretObservation` in (_utf8mb4'Envol',_utf8mb4'Inconnu',_utf8mb4'Maree',_utf8mb4'Pietinement',_utf8mb4'Prédation')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nid_gci`
--

LOCK TABLES `nid_gci` WRITE;
/*!40000 ALTER TABLE `nid_gci` DISABLE KEYS */;
INSERT INTO `nid_gci` VALUES (1,'LANDREZAC','inconnu',0,0,NULL,NULL),(2,'BEG LAnn','inconnu',0,0,NULL,NULL),(3,'SALINE',NULL,2,0,NULL,'FBV/MtWhV'),(4,'PENVINS',NULL,2,0,NULL,NULL),(5,'PENVINS',NULL,0,0,NULL,NULL),(6,'BRENEGUY','inconnu',0,1,NULL,NULL),(7,'BRENEGUY',NULL,2,1,NULL,NULL),(8,'PENVINS','inconnu',0,0,NULL,'FBV/MtWh#'),(9,'LANDREZAC',NULL,3,0,NULL,NULL),(10,'LANDREZAC','inconnu',0,0,NULL,NULL),(11,'GOH VELIN',NULL,2,0,NULL,NULL),(12,'GOH VELIN','inconnu',0,0,NULL,NULL),(13,'SALINE EXPLOITEE',NULL,2,0,NULL,NULL),(14,'BEDUME','inconnu',0,0,NULL,NULL),(15,'KERPHENIR',NULL,3,1,NULL,NULL),(16,'BRENEGUY','inconnu',0,1,NULL,NULL),(17,'BRENEGUY','inconnu',0,1,NULL,NULL),(18,'PENVINS',NULL,2,1,NULL,NULL),(19,'PENVINS','inconnu',0,0,NULL,NULL),(20,'PENVINS','inconnu',0,0,NULL,'FBV/MtW#'),(21,'BEG LANN',NULL,0,1,NULL,NULL),(22,'LANDREZAC',NULL,1,0,NULL,NULL),(23,'BRENEGUY','inconnu',0,1,NULL,NULL),(24,'KERVER','inconnu',0,0,NULL,NULL),(25,'KERPENHIR','inconnu',1,1,NULL,NULL),(26,'KERPENHIR',NULL,3,1,NULL,'FBB/MtJV'),(27,'BANASTERE','inconnu',0,0,NULL,NULL);
/*!40000 ALTER TABLE `nid_gci` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 13:16:42
