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
-- Table structure for table `obs_batracien`
--

DROP TABLE IF EXISTS `obs_batracien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obs_batracien` (
  `obsB` int NOT NULL,
  `espece` varchar(50) NOT NULL,
  `nombreAdultes` int DEFAULT NULL,
  `nombreAmplexus` int DEFAULT NULL,
  `nombrePonte` int DEFAULT NULL,
  `nombreTetard` int DEFAULT NULL,
  `temperature` decimal(10,0) DEFAULT NULL,
  `meteo_ciel` varchar(50) DEFAULT NULL,
  `meteo_temp` varchar(50) DEFAULT NULL,
  `meteo_vent` varchar(50) DEFAULT NULL,
  `meteo_pluie` varchar(50) DEFAULT NULL,
  `concerne_ZH` int DEFAULT NULL,
  `concernes_vege` int DEFAULT NULL,
  PRIMARY KEY (`obsB`,`espece`),
  KEY `fk_ObsBatracien_ZoneHumide` (`concerne_ZH`),
  KEY `fk_ObsBatracien_Vegetation` (`concernes_vege`),
  CONSTRAINT `fk_ObsBatracien_Observation` FOREIGN KEY (`obsB`) REFERENCES `observation` (`idObs`),
  CONSTRAINT `fk_ObsBatracien_Vegetation` FOREIGN KEY (`concernes_vege`) REFERENCES `Lieu_Vegetation` (`idVegeLieu`),
  CONSTRAINT `fk_ObsBatracien_ZoneHumide` FOREIGN KEY (`concerne_ZH`) REFERENCES `zonehumide` (`zh_id`),
  CONSTRAINT `dom_especeBatracien` CHECK ((`espece` in (_utf8mb4'calamite',_utf8mb4'pelodyte'))),
  CONSTRAINT `dom_meteoCiel` CHECK ((`meteo_ciel` in (_utf8mb4'dégagé',_utf8mb4'semi-dégagé',_utf8mb4'nuageux'))),
  CONSTRAINT `dom_meteoPluie` CHECK ((`meteo_pluie` in (_utf8mb4'non',_utf8mb4'légère',_utf8mb4'moyenne',_utf8mb4'forte'))),
  CONSTRAINT `dom_meteoTemp` CHECK ((`meteo_temp` in (_utf8mb4'froid',_utf8mb4'moyen',_utf8mb4'chaud'))),
  CONSTRAINT `dom_meteoVent` CHECK ((`meteo_vent` in (_utf8mb4'non',_utf8mb4'léger',_utf8mb4'moyen',_utf8mb4'fort')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obs_batracien`
--

LOCK TABLES `obs_batracien` WRITE;
/*!40000 ALTER TABLE `obs_batracien` DISABLE KEYS */;
INSERT INTO `obs_batracien` VALUES (1,'calamite',0,0,0,0,7,'dégagé',NULL,'léger','non',1,1),(1,'pelodyte',0,0,0,0,7,'dégagé',NULL,'léger','non',1,1),(2,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'non',2,2),(2,'pelodyte',0,0,0,0,8,'nuageux',NULL,NULL,'non',2,2),(3,'calamite',3,0,0,0,8,'dégagé',NULL,NULL,'non',3,3),(3,'pelodyte',1,0,0,0,8,'dégagé',NULL,NULL,'non',3,3),(4,'calamite',0,0,0,0,2,NULL,'froid','moyen','non',4,4),(4,'pelodyte',0,0,0,0,2,NULL,'froid','moyen','non',4,4),(5,'calamite',0,0,0,0,6,'nuageux',NULL,'moyen','non',5,5),(5,'pelodyte',10,0,0,0,6,'nuageux',NULL,'moyen','non',5,5),(6,'calamite',0,0,0,0,6,'nuageux',NULL,NULL,'non',6,6),(6,'pelodyte',0,0,0,0,6,'nuageux',NULL,NULL,'non',6,6),(7,'calamite',0,0,0,0,4,'nuageux',NULL,NULL,'non',7,7),(7,'pelodyte',0,0,0,0,4,'nuageux',NULL,NULL,'non',7,7),(8,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'non',8,8),(8,'pelodyte',1,0,0,0,8,'nuageux',NULL,NULL,'non',8,8),(9,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',9,9),(9,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',9,9),(10,'calamite',1,0,0,0,7,'nuageux',NULL,NULL,'non',10,10),(10,'pelodyte',0,0,0,0,7,'nuageux',NULL,NULL,'non',10,10),(11,'calamite',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',11,11),(11,'pelodyte',2,0,0,0,NULL,'dégagé',NULL,NULL,'non',11,11),(12,'calamite',0,0,0,0,5,'semi-dégagé',NULL,NULL,'non',12,12),(12,'pelodyte',0,0,0,0,5,'semi-dégagé',NULL,NULL,'non',12,12),(13,'calamite',0,0,0,0,6,'nuageux',NULL,'moyen','non',13,13),(13,'pelodyte',0,0,0,0,6,'nuageux',NULL,'moyen','non',13,13),(14,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',14,14),(14,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',14,14),(15,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'non',15,15),(15,'pelodyte',5,0,0,0,8,'nuageux',NULL,NULL,'non',15,15),(16,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',16,16),(16,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',16,16),(17,'calamite',0,0,0,0,7,'dégagé',NULL,NULL,'non',17,17),(17,'pelodyte',0,0,0,0,7,'dégagé',NULL,NULL,'non',17,17),(18,'calamite',0,0,0,0,6,'dégagé',NULL,'moyen','non',18,18),(18,'pelodyte',0,0,0,0,6,'dégagé',NULL,'moyen','non',18,18),(19,'calamite',1,0,0,0,8,'dégagé',NULL,NULL,'non',19,19),(19,'pelodyte',1,0,0,0,8,'dégagé',NULL,NULL,'non',19,19),(20,'calamite',0,0,0,0,4,'nuageux',NULL,NULL,'non',20,20),(20,'pelodyte',0,0,0,0,4,'nuageux',NULL,NULL,'non',20,20),(21,'calamite',0,0,0,0,8,'nuageux',NULL,'moyen','non',21,21),(21,'pelodyte',0,0,0,0,8,'nuageux',NULL,'moyen','non',21,21),(22,'calamite',0,0,0,0,6,'dégagé',NULL,NULL,'non',22,22),(22,'pelodyte',0,0,0,0,6,'dégagé',NULL,NULL,'non',22,22),(23,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',23,23),(23,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',23,23),(24,'calamite',1,0,0,0,8,'semi-dégagé',NULL,NULL,'non',24,24),(24,'pelodyte',0,0,0,0,8,'semi-dégagé',NULL,NULL,'non',24,24),(25,'calamite',0,0,0,0,7,'dégagé',NULL,NULL,'non',25,25),(25,'pelodyte',0,0,0,0,7,'dégagé',NULL,NULL,'non',25,25),(26,'calamite',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',26,26),(26,'pelodyte',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',26,26),(27,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'non',27,27),(27,'pelodyte',0,0,0,0,8,'nuageux',NULL,NULL,'non',27,27),(28,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'moyenne',28,28),(28,'pelodyte',2,0,0,0,8,'nuageux',NULL,NULL,'moyenne',28,28),(29,'calamite',0,0,0,0,8,'dégagé',NULL,NULL,'non',29,29),(29,'pelodyte',0,0,0,0,8,'dégagé',NULL,NULL,'non',29,29),(30,'calamite',0,0,0,0,6,'dégagé',NULL,NULL,'non',30,30),(30,'pelodyte',0,0,0,0,6,'dégagé',NULL,NULL,'non',30,30),(31,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',31,31),(31,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',31,31),(32,'calamite',0,0,0,0,6,'nuageux',NULL,NULL,'non',32,32),(32,'pelodyte',0,0,0,0,6,'nuageux',NULL,NULL,'non',32,32),(33,'calamite',0,0,0,0,8,'nuageux',NULL,'moyen','non',33,33),(33,'pelodyte',0,0,0,0,8,'nuageux',NULL,'moyen','non',33,33),(34,'calamite',NULL,NULL,NULL,NULL,4,'nuageux',NULL,NULL,'non',34,34),(34,'pelodyte',NULL,NULL,NULL,NULL,4,'nuageux',NULL,NULL,'non',34,34),(35,'calamite',0,0,0,0,10,'nuageux',NULL,NULL,'non',35,35),(35,'pelodyte',4,0,0,0,10,'nuageux',NULL,NULL,'non',35,35),(36,'calamite',0,0,0,0,10,'dégagé',NULL,NULL,'non',36,36),(36,'pelodyte',0,0,0,0,10,'dégagé',NULL,NULL,'non',36,36),(37,'calamite',0,0,0,0,6,'dégagé',NULL,'moyen','non',37,37),(37,'pelodyte',1,0,0,0,6,'dégagé',NULL,'moyen','non',37,37),(38,'calamite',0,0,0,0,12,'dégagé',NULL,NULL,'non',38,38),(38,'pelodyte',0,0,0,0,12,'dégagé',NULL,NULL,'non',38,38),(39,'calamite',0,0,0,0,9,'nuageux',NULL,NULL,'légère',39,39),(39,'pelodyte',0,0,0,0,9,'nuageux',NULL,NULL,'légère',39,39),(40,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',40,40),(40,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',40,40),(41,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'non',41,41),(41,'pelodyte',0,0,0,0,8,'nuageux',NULL,NULL,'non',41,41),(42,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',42,42),(42,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',42,42),(43,'calamite',0,0,0,0,10,'nuageux',NULL,'non','non',43,43),(43,'pelodyte',0,0,0,0,10,'nuageux',NULL,'non','non',43,43),(44,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',44,44),(44,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',44,44),(45,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',45,45),(45,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',45,45),(46,'calamite',0,0,0,0,5,'dégagé',NULL,'moyen','non',46,46),(46,'pelodyte',0,0,0,0,5,'dégagé',NULL,'moyen','non',46,46),(47,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',47,47),(47,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',47,47),(48,'calamite',0,0,0,0,7,'semi-dégagé',NULL,NULL,'non',48,48),(48,'pelodyte',0,0,0,0,7,'semi-dégagé',NULL,NULL,'non',48,48),(49,'calamite',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',49,49),(49,'pelodyte',50,0,0,0,NULL,'dégagé',NULL,NULL,'non',49,49),(50,'calamite',0,0,0,0,10,'dégagé',NULL,NULL,'non',50,50),(50,'pelodyte',0,0,0,0,10,'dégagé',NULL,NULL,'non',50,50),(51,'calamite',0,0,0,0,12,'dégagé',NULL,'moyen','non',51,51),(51,'pelodyte',0,0,0,0,12,'dégagé',NULL,'moyen','non',51,51),(52,'calamite',0,0,0,0,4,'nuageux',NULL,NULL,'non',52,52),(52,'pelodyte',0,0,0,0,4,'nuageux',NULL,NULL,'non',52,52),(53,'calamite',0,0,0,0,7,'nuageux',NULL,NULL,'non',53,53),(53,'pelodyte',0,0,0,0,7,'nuageux',NULL,NULL,'non',53,53),(54,'calamite',0,0,0,0,6,'nuageux',NULL,'moyen','non',54,54),(54,'pelodyte',0,0,0,0,6,'nuageux',NULL,'moyen','non',54,54),(55,'calamite',0,0,0,0,8,'nuageux',NULL,'moyen','non',55,55),(55,'pelodyte',0,0,0,0,8,'nuageux',NULL,'moyen','non',55,55),(56,'calamite',0,0,0,0,4,'nuageux',NULL,NULL,'non',56,56),(56,'pelodyte',0,0,0,0,4,'nuageux',NULL,NULL,'non',56,56),(57,'calamite',0,0,0,0,11,'dégagé',NULL,NULL,'non',57,57),(57,'pelodyte',0,0,0,0,11,'dégagé',NULL,NULL,'non',57,57),(58,'calamite',NULL,NULL,NULL,NULL,8,'dégagé',NULL,NULL,'non',58,58),(58,'pelodyte',NULL,NULL,NULL,NULL,8,'dégagé',NULL,NULL,'non',58,58),(59,'calamite',0,0,0,0,10,'nuageux',NULL,'non','non',59,59),(59,'pelodyte',0,0,0,0,10,'nuageux',NULL,'non','non',59,59),(60,'calamite',0,0,0,0,8,'nuageux',NULL,NULL,'non',60,60),(60,'pelodyte',0,0,0,0,8,'nuageux',NULL,NULL,'non',60,60),(61,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',61,61),(61,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',61,61),(62,'calamite',0,0,0,0,2,NULL,'froid','moyen','non',62,62),(62,'pelodyte',1,0,0,0,2,NULL,'froid','moyen','non',62,62),(63,'calamite',0,0,0,0,5,'nuageux',NULL,NULL,'non',63,63),(63,'pelodyte',0,0,0,0,5,'nuageux',NULL,NULL,'non',63,63),(64,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',64,64),(64,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',64,64),(65,'calamite',0,0,0,0,11,'nuageux',NULL,NULL,'légère',65,65),(65,'pelodyte',0,0,0,0,11,'nuageux',NULL,NULL,'légère',65,65),(66,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',66,66),(66,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',66,66),(67,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',67,67),(67,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',67,67),(68,'calamite',99,20,0,0,6,'nuageux',NULL,NULL,'légère',68,68),(68,'pelodyte',2,0,0,0,6,'nuageux',NULL,NULL,'légère',68,68),(69,'calamite',0,0,0,0,12,'dégagé',NULL,'moyen','non',69,69),(69,'pelodyte',0,0,0,0,12,'dégagé',NULL,'moyen','non',69,69),(70,'calamite',0,0,0,0,6,'dégagé',NULL,'moyen','non',70,70),(70,'pelodyte',0,0,0,0,6,'dégagé',NULL,'moyen','non',70,70),(71,'calamite',NULL,NULL,NULL,NULL,7,'dégagé',NULL,'moyen','non',71,71),(71,'pelodyte',2,0,0,0,7,'dégagé',NULL,'moyen','non',71,71),(72,'calamite',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',72,72),(72,'pelodyte',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',72,72),(73,'calamite',0,0,0,0,6,'dégagé',NULL,'léger','non',73,73),(73,'pelodyte',0,0,0,0,6,'dégagé',NULL,'léger','non',73,73),(74,'calamite',NULL,NULL,NULL,NULL,7,'dégagé',NULL,'moyen','non',74,74),(74,'pelodyte',2,0,0,0,7,'dégagé',NULL,'moyen','non',74,74),(75,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',75,75),(75,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',75,75),(76,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',76,76),(76,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',76,76),(77,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',77,77),(77,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',77,77),(78,'calamite',0,0,0,0,7,'dégagé',NULL,'moyen','non',78,78),(78,'pelodyte',0,0,0,0,7,'dégagé',NULL,'moyen','non',78,78),(79,'calamite',0,0,0,0,NULL,'dégagé',NULL,NULL,'non',79,79),(79,'pelodyte',1,NULL,NULL,NULL,NULL,'dégagé',NULL,NULL,'non',79,79),(80,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',80,80),(80,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',80,80),(81,'calamite',0,0,0,0,12,'nuageux',NULL,NULL,'légère',81,81),(81,'pelodyte',0,0,0,0,12,'nuageux',NULL,NULL,'légère',81,81),(82,'calamite',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',82,82),(82,'pelodyte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'non',82,82);
/*!40000 ALTER TABLE `obs_batracien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 13:16:39
