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
-- Table structure for table `obs_gci`
--

DROP TABLE IF EXISTS `obs_gci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obs_gci` (
  `obsG` int NOT NULL,
  `nature` varchar(50) DEFAULT NULL,
  `nombre` int DEFAULT NULL,
  `presentMaisNonObs` int DEFAULT NULL,
  `leNid` int DEFAULT NULL,
  PRIMARY KEY (`obsG`),
  KEY `fk_obsGCI_NidGCI` (`leNid`),
  CONSTRAINT `fk_obsGCI_NidGCI` FOREIGN KEY (`leNid`) REFERENCES `nid_gci` (`idNid`),
  CONSTRAINT `fk_ObsGCI_Observation` FOREIGN KEY (`obsG`) REFERENCES `observation` (`idObs`),
  CONSTRAINT `dom_nature` CHECK ((`nature` in (_utf8mb4'Oeuf',_utf8mb4'Poussin',_utf8mb4'Nid')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obs_gci`
--

LOCK TABLES `obs_gci` WRITE;
/*!40000 ALTER TABLE `obs_gci` DISABLE KEYS */;
INSERT INTO `obs_gci` VALUES (812,'oeuf',3,NULL,1),(813,'nid',0,NULL,1),(814,'oeuf',2,NULL,2),(815,'nid',0,NULL,2),(816,'oeuf',3,NULL,3),(817,'poussin',2,NULL,3),(818,'oeuf',1,NULL,4),(819,'nid',0,NULL,4),(820,'nid',1,NULL,5),(821,'poussin',2,NULL,5),(822,'poussin',2,NULL,5),(823,'poussin',2,NULL,5),(824,'oeuf',3,NULL,6),(825,'nid',0,NULL,6),(826,'oeuf',3,NULL,7),(827,'poussin',2,NULL,7),(828,'poussin',2,NULL,7),(829,'nid',1,NULL,8),(830,'oeuf',2,NULL,8),(831,'nid',0,NULL,8),(832,'oeuf',3,NULL,9),(833,'poussin',3,NULL,9),(834,'poussin',3,NULL,9),(835,'oeuf',2,NULL,10),(836,'oeuf',3,NULL,10),(837,'nid',0,NULL,10),(838,'poussin',2,NULL,11),(839,'poussin',3,NULL,11),(840,'poussin',2,NULL,11),(841,'oeuf',2,NULL,12),(842,'nid',0,NULL,12),(844,'poussin',2,NULL,13),(845,'oeuf',2,NULL,14),(846,'oeuf',3,NULL,14),(847,'oeuf',3,NULL,14),(848,'oeuf',3,NULL,14),(849,'oeuf',3,NULL,14),(850,'oeuf',3,NULL,14),(851,'oeuf',3,NULL,14),(852,'oeuf',3,NULL,14),(853,'oeuf',3,NULL,14),(854,'nid',0,NULL,14),(855,'poussin',3,NULL,15),(856,'poussin',3,NULL,15),(857,'nid',1,NULL,16),(858,'nid',0,NULL,16),(859,'nid',1,NULL,17),(860,'nid',0,NULL,17),(861,'oeuf',1,NULL,18),(862,'oeuf',2,NULL,18),(863,'oeuf',3,NULL,18),(864,'oeuf',2,NULL,18),(865,'poussin',2,NULL,18),(866,'poussin',2,NULL,18),(867,'poussin',2,NULL,18),(868,'oeuf',1,NULL,19),(869,'nid',0,NULL,19),(870,'oeuf',1,NULL,20),(871,'nid',0,NULL,20),(872,'oeuf',3,NULL,21),(873,'nid',0,NULL,21),(874,'poussin',1,NULL,22),(875,'poussin',1,NULL,22),(876,'poussin',1,NULL,22),(877,'nid',1,NULL,23),(878,'nid',0,NULL,23),(879,'oeuf',2,NULL,24),(880,'nid',0,NULL,24),(881,'oeuf',3,NULL,25),(882,'poussin',2,NULL,25),(883,'poussin',1,NULL,25),(884,'poussin',3,NULL,26),(885,'poussin',3,NULL,26),(886,'poussin',3,NULL,26),(887,'oeuf',2,NULL,27),(888,'oeuf',1,NULL,27),(889,'nid',0,NULL,27);
/*!40000 ALTER TABLE `obs_gci` ENABLE KEYS */;
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
