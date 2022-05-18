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
-- Table structure for table `vegetation`
--

DROP TABLE IF EXISTS `vegetation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vegetation` (
  `idVege` int NOT NULL,
  `natureVege` varchar(20) DEFAULT NULL,
  `vegetation` varchar(70) DEFAULT NULL,
  `decrit_LieuVege` int DEFAULT NULL,
  PRIMARY KEY (`idVege`),
  KEY `fk_Vegetation_LieuVegetation` (`decrit_LieuVege`),
  CONSTRAINT `fk_Vegetation_LieuVegetation` FOREIGN KEY (`decrit_LieuVege`) REFERENCES `lieu_vegetation` (`idVegeLieu`),
  CONSTRAINT `dom_natureVege` CHECK ((`natureVege` in (_utf8mb4'environnement',_utf8mb4'bordure',_utf8mb4'ripisyle')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vegetation`
--

LOCK TABLES `vegetation` WRITE;
/*!40000 ALTER TABLE `vegetation` DISABLE KEYS */;
INSERT INTO `vegetation` VALUES (1,'environnement','Prairie,Fourrés',1),(2,'environnement','Prairie',2),(3,'environnement','Prairie,Culture,Fourrés',3),(4,'environnement','Culture,Fourrés',4),(5,'environnement','Fourrés',5),(6,'environnement','Autres',6),(7,'environnement','Prairie',7),(8,'environnement','Autres ',8),(9,'environnement','Fourrés',9),(10,'environnement','Prairie,Culture',10),(11,'environnement','Culture,Fourrés',11),(12,'environnement','Culture,Fourrés,Autres ',12),(13,'environnement','Prairie',13),(14,'environnement','Fourrés',14),(15,'environnement','Prairie,Fourrés',15),(16,'environnement','Fourrés',16),(17,'environnement','Autres',17),(18,'environnement','Prairie,Culture',18),(19,'environnement','Prairie,Culture,Fourrés',19),(20,'environnement','Prairie',20),(21,'environnement','Autres',21),(22,'environnement','Culture',22),(23,'environnement','Culture,Fourrés',23),(24,'environnement','Prairie,Fourrés',24),(25,'environnement','Fourrés',25),(26,'environnement','Prairie,Fourrés,Autres',26),(27,'environnement','Prairie',27),(28,'environnement','Culture,Fourrés',28),(29,'environnement','Prairie,Fourrés',29),(30,'environnement','Prairie',30),(31,'environnement','Prairie,Culture,Fourrés',31),(32,'environnement','Prairie',32),(33,'environnement','Autres',33),(34,'environnement',NULL,34),(35,'environnement','Prairie,Fourrés',35),(36,'environnement','Fourrés',36),(37,'environnement','Prairie,Culture,Fourrés',37),(38,'environnement','Fourrés',38),(39,'environnement','Prairie',39),(40,'environnement','Prairie',40),(41,'environnement','Prairie,Autres',41),(42,'environnement','Autres',42),(43,'environnement','Prairie,Fourrés',43),(44,'environnement','Prairie,Fourrés',44),(45,'environnement','Prairie',45),(46,'environnement','Fourrés',46),(47,'environnement',NULL,47),(48,'environnement','Culture,Fourrés',48),(49,'environnement','Culture,Autres',49),(50,'environnement','Fourrés',50),(51,'environnement',NULL,51),(52,'environnement','Prairie',52),(53,'environnement','Fourrés,Autres ',53),(54,'environnement','Prairie,Fourrés',54),(55,'environnement',NULL,55),(56,'environnement','Prairie',56),(57,'environnement','Prairie,Culture,Fourrés',57),(58,'environnement',NULL,58),(59,'environnement','Prairie',59),(60,'environnement','Prairie,Fourrés',60),(61,'environnement','Prairie,Culture,Fourrés',61),(62,'environnement','Prairie',62),(63,'environnement','Autres',63),(64,'environnement','Prairie',64),(65,'environnement','Fourrés,Autres ',65),(66,'environnement','Prairie,Fourrés',66),(67,'environnement','Fourrés',67),(68,'environnement','Autres ',68),(69,'environnement','Fourrés,Autres ',69),(70,'environnement','Prairie,Fourrés,Autres',70),(71,'environnement','Culture,Autres ',71),(72,'environnement','Prairie,Autres ',72),(73,'environnement','Prairie,Fourrés',73),(74,'environnement','Prairie,Culture,Fourrés',74),(75,'environnement','Prairie,Fourrés',75),(76,'environnement','Prairie',76),(77,'environnement','Culture',77),(78,'environnement','Prairie,Culture,Fourrés',78),(79,'environnement','Autres',79),(80,'environnement','Culture,Autres ',80),(81,'environnement','Prairie,Fourrés',81),(82,'environnement','Autres',82),(83,'bordure','Fourré,Prairie',1),(84,'bordure','Fourré',2),(85,'bordure','Fourré,Prairie,Cultures',3),(86,'bordure','Prairie',4),(87,'bordure','Fourré',5),(88,'bordure','Autres ',6),(89,'bordure','Autres ',7),(90,'bordure','Autres ',8),(91,'bordure','Fourré',9),(92,'bordure','Fourré,Prairie,Cultures',10),(93,'bordure','Fourré',11),(94,'bordure','Fourré,Autres ',12),(95,'bordure','Roselière,Prairie',13),(96,'bordure','Fourré',14),(97,'bordure','Roselière,Fourré',15),(98,'bordure','Fourré',16),(99,'bordure','Fourré',17),(100,'bordure','Fourré',18),(101,'bordure','Fourré,Prairie',19),(102,'bordure','Fourré',20),(103,'bordure','Autres ',21),(104,'bordure','Fourré,Autres ',22),(105,'bordure','Fourré',23),(106,'bordure','Roselière,Autres',24),(107,'bordure','Fourré',25),(108,'bordure','Prairie',26),(109,'bordure','Fourré',27),(110,'bordure','Fourré,Cultures',28),(111,'bordure','Fourré,Prairie',29),(112,'bordure','Autres ',30),(113,'bordure','Fourré,Prairie,Cultures',31),(114,'bordure','Fourré',32),(115,'bordure','Fourré',33),(116,'bordure',NULL,34),(117,'bordure','Prairie',35),(118,'bordure','Roselière,Fourré,Autres',36),(119,'bordure','Fourré,Prairie,Cultures',37),(120,'bordure','Fourré',38),(121,'bordure','Prairie',39),(122,'bordure','Prairie',40),(123,'bordure','Autres ',41),(124,'bordure','Autres ',42),(125,'bordure','Roselière',43),(126,'bordure','Fourré',44),(127,'bordure','Prairie',45),(128,'bordure','Prairie',46),(129,'bordure',NULL,47),(130,'bordure','Cultures',48),(131,'bordure','Autres ',49),(132,'bordure','Fourré',50),(133,'bordure','Fourré,Autres ',51),(134,'bordure','Prairie',52),(135,'bordure','Fourré,Autres ',53),(136,'bordure','Fourré',54),(137,'bordure','Fourré',55),(138,'bordure','Prairie',56),(139,'bordure','Prairie,Cultures,Autres',57),(140,'bordure',NULL,58),(141,'bordure','Fourré,Prairie',59),(142,'bordure','Roselière,Fourré',60),(143,'bordure','Fourré,Prairie,Cultures',61),(144,'bordure','Fourré,Prairie',62),(145,'bordure','Roselière,Fourré',63),(146,'bordure','Prairie,Autres ',64),(147,'bordure','Fourré',65),(148,'bordure','Prairie',66),(149,'bordure','Roselière,Fourré',67),(150,'bordure','Autres',68),(151,'bordure','Fourré',69),(152,'bordure','Fourré,Prairie',70),(153,'bordure','Cultures',71),(154,'bordure','Prairie',72),(155,'bordure','Fourré,Prairie',73),(156,'bordure','Fourré',74),(157,'bordure','Fourré,Prairie',75),(158,'bordure','Fourré,Prairie',76),(159,'bordure','Fourré',77),(160,'bordure','Fourré',78),(161,'bordure','Autres',79),(162,'bordure','Fourré,Prairie',80),(163,'bordure','Fourré,Prairie',81),(164,'bordure','Roselière,Autres ',82),(165,'ripisyle','Herbacée,Arbustive',1),(166,'ripisyle','Arbustive',2),(167,'ripisyle','Herbacée',3),(168,'ripisyle','Herbacée,Arbustive',4),(169,'ripisyle','Arbustive',5),(170,'ripisyle',NULL,6),(171,'ripisyle','Herbacée',7),(172,'ripisyle','Herbacée',8),(173,'ripisyle','Arbustive,Arborhée',9),(174,'ripisyle','Herbacée,Arbustive',10),(175,'ripisyle','Herbacée',11),(176,'ripisyle','Herbacée',12),(177,'ripisyle','Herbacée',13),(178,'ripisyle','Arbustive,Arborhée',14),(179,'ripisyle','Herbacée,Arbustive',15),(180,'ripisyle','Arbustive',16),(181,'ripisyle','Herbacée,Arborhée',17),(182,'ripisyle',NULL,18),(183,'ripisyle','Herbacée,Arbustive,Arborhée',19),(184,'ripisyle','Herbacée',20),(185,'ripisyle','Herbacée',21),(186,'ripisyle','Arbustive,Arborhée',22),(187,'ripisyle',NULL,23),(188,'ripisyle','Herbacée',24),(189,'ripisyle','Arborhée',25),(190,'ripisyle','Herbacée',26),(191,'ripisyle',NULL,27),(192,'ripisyle',NULL,28),(193,'ripisyle','Arbustive,Arborhée',29),(194,'ripisyle','Arborhée',30),(195,'ripisyle','Herbacée,Arbustive',31),(196,'ripisyle','Herbacée',32),(197,'ripisyle','Herbacée',33),(198,'ripisyle',NULL,34),(199,'ripisyle','Herbacée',35),(200,'ripisyle','Herbacée,Arbustive',36),(201,'ripisyle','Herbacée,Arborhée',37),(202,'ripisyle','Arborhée',38),(203,'ripisyle','Arbustive,Arborhée',39),(204,'ripisyle','Herbacée,Arbustive',40),(205,'ripisyle','Arbustive,Arborhée',41),(206,'ripisyle','Arborhée',42),(207,'ripisyle','Herbacée,Arbustive',43),(208,'ripisyle','Herbacée,Arbustive,Arborhée',44),(209,'ripisyle','Herbacée,Arbustive',45),(210,'ripisyle','Herbacée,Arbustive',46),(211,'ripisyle',NULL,47),(212,'ripisyle','Herbacée',48),(213,'ripisyle','Arborhée',49),(214,'ripisyle','Arbustive,Arborhée',50),(215,'ripisyle','Herbacée',51),(216,'ripisyle','Herbacée',52),(217,'ripisyle','Arbustive,Arborhée',53),(218,'ripisyle','Herbacée,Arbustive,Arborhée',54),(219,'ripisyle','Arbustive',55),(220,'ripisyle','Herbacée',56),(221,'ripisyle','Herbacée,Arbustive',57),(222,'ripisyle',NULL,58),(223,'ripisyle','Herbacée,Arbustive',59),(224,'ripisyle','Herbacée',60),(225,'ripisyle','Herbacée',61),(226,'ripisyle','Herbacée,Arbustive',62),(227,'ripisyle','Herbacée',63),(228,'ripisyle','Arbustive',64),(229,'ripisyle','Arbustive',65),(230,'ripisyle','Herbacée',66),(231,'ripisyle','Arbustive,Arborhée',67),(232,'ripisyle','Herbacée',68),(233,'ripisyle','Arbustive',69),(234,'ripisyle',NULL,70),(235,'ripisyle',NULL,71),(236,'ripisyle','Herbacée',72),(237,'ripisyle','Arbustive,Arborhée',73),(238,'ripisyle',NULL,74),(239,'ripisyle','Arbustive,Arborhée',75),(240,'ripisyle','Herbacée',76),(241,'ripisyle','Arbustive',77),(242,'ripisyle',NULL,78),(243,'ripisyle','Arborhée',79),(244,'ripisyle','Arbustive',80),(245,'ripisyle','Herbacée,Arbustive',81),(246,'ripisyle','Arborhée',82);
/*!40000 ALTER TABLE `vegetation` ENABLE KEYS */;
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
