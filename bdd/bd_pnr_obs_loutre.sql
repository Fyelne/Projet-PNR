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
-- Table structure for table `obs_loutre`
--

DROP TABLE IF EXISTS `obs_loutre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obs_loutre` (
  `ObsL` int NOT NULL,
  `commune` varchar(50) DEFAULT NULL,
  `lieuDit` varchar(50) DEFAULT NULL,
  `indice` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ObsL`),
  CONSTRAINT `fk_ObsLoutre_Observation` FOREIGN KEY (`ObsL`) REFERENCES `observation` (`idObs`),
  CONSTRAINT `dom_indice` CHECK ((`indice` in (_utf8mb4'Positif',_utf8mb4'Negatif',_utf8mb4'Non prospection')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obs_loutre`
--

LOCK TABLES `obs_loutre` WRITE;
/*!40000 ALTER TABLE `obs_loutre` DISABLE KEYS */;
INSERT INTO `obs_loutre` VALUES (681,'PLOUGOUMELEN','Pont Sal','negatif'),(682,'PLOUGOUMELEN','Pont Sal','negatif'),(683,'PLOUGOUMELEN','Pont-er-Len','negatif'),(684,'PLOUGOUMELEN','Bequerel','negatif'),(685,'PLUNERET','Lescrezan','positif'),(686,'PLUNERET','Leran','positif'),(687,'PLUNERET','Moulin de Kervilio','positif'),(688,'PLUNERET','Marvillo','positif'),(689,'PLESCOP','Beaumarchais','positif'),(690,'PLESCOP','Pont de Menessal','negatif'),(691,NULL,'Le moustoir des fleurs','negatif'),(692,'PLESCOP','Trezelo','negatif'),(693,'PLESCOP','Moulin de Bot Lann','positif'),(694,'SAINT AVE','La briqueterie','positif'),(695,'SAINT AVE','le moulin de calric','positif'),(696,'SAINT AVE','Burguin','non prospection'),(697,'SAINT AVE','Lezellec','positif'),(698,'SAINT AVE','Trehont','positif'),(699,'PLOEREN','Le Garo','negatif'),(700,'PLOEREN','Kenneurier','non prospection'),(701,'PLESCOP','Le moustoire','negatif'),(702,'PLESCOP','Trehuinec','positif'),(703,'VANNES','Bodelhannec','negatif'),(704,'VANNES','Le tenenio','negatif'),(705,'VANNES','Kermesquel','negatif'),(706,'NOYALO','Le pont de noyalo','positif'),(707,'NOYALO','Pont grandic','positif'),(708,'THEIX','Pont roz','positif'),(709,'THEIX','Le moustoir','positif'),(710,'THEIX','kercroix','positif'),(711,'THEIX','Pont Malgoin','positif'),(712,'THEIX','calzac moulin','negatif'),(713,'THEIX','ker antoine','positif'),(714,'THEIX','kergoual','positif'),(715,'THEIX','le pontu','negatif'),(716,'THEIX','le net','positif'),(717,'SURZUR','kerbossen','negatif'),(718,'SURZUR','lambre','negatif'),(719,'SURZUR','kerbiren','positif'),(720,'SARZEAU','quintin','positif'),(721,'AMBON','pont de sule','negatif'),(722,'AMBON','tremeret','negatif'),(723,'AMBON','pont treube','negatif'),(724,'AMBON','le prieure','negatif'),(725,'AMBON','billion','negatif'),(726,'AMBON','la lande du scloff','negatif'),(727,'LAUZACH','pont de puil','negatif'),(728,'LAUZACH','kerlonien','positif'),(729,'BERRIC','le flahec','negatif'),(730,'SULNIAC','le pont guil','positif'),(731,'ELVEN','kerhelene','negatif'),(732,'ELVEN','le goulet','negatif'),(733,'ELVEN','le pont guilmet','positif'),(734,'ELVEN','le moulin du helfau','positif'),(735,'ELVEN','le lermont','positif'),(736,'ELVEN','logodec','negatif'),(737,'ELVEN','castel de tremegan','positif'),(738,'ELVEN','largouet','positif'),(739,'ELVEN','kerfuntel','negatif'),(740,'ELVEN','lederin','positif'),(741,'ELVEN','kerdure','positif'),(742,'ELVEN','le clestro','positif'),(743,'ELVEN','la cour','negatif'),(744,'ELVEN','le moulin d elven','negatif'),(745,'ELVEN','pont er guel','positif'),(746,'MONTERBLANC','le bourg','negatif'),(747,'MONTERBLANC','le procureur','positif'),(748,'MONTERBLANC','kerdaneguy','positif'),(749,'MONTERBLANC','kerveu','positif'),(750,'ELVEN','le resto','negatif'),(751,'ELVEN','moulin de clidan','positif'),(752,'SAINT AVE','le porlair','positif'),(753,'SAINT AVE','kerozer','negatif'),(754,'SAINT AVE','feten hont','positif'),(755,'SAINT AVE','moulin de lesneue','positif'),(756,'SAINT NOLFF','le moulin du val','non prospection'),(757,'SAINT NOLFF','le foulon','positif'),(758,'SAINT NOLFF','le bourg','negatif'),(759,'SAINT NOLFF','le vieux moulin','positif'),(760,'SAINT NOLFF','gornay','negatif'),(761,'SAINT NOLFF','lascouit','positif'),(762,'TREFFLEAN','bizole','negatif'),(763,'THEIX','corn er houet','positif'),(764,'THEIX','le saindo','negatif'),(765,'THEIX','saint leonard','positif'),(766,'THEIX','kerpayen','positif'),(767,'THEIX','bonnervo','non prospection'),(768,'SENE','cantizac','negatif'),(769,'VANNES','le prat','negatif'),(770,'VANNES','poignan','positif'),(771,'VANNES','etang au duc','positif'),(772,'VANNES','liziec','positif'),(773,'VANNES','poignan','negatif'),(774,'PLUNERET','station pompage','positif'),(775,'PLUNERET','moulin d estaing','positif'),(776,'PLUNERET','moulin d estaing','positif'),(777,'PLUNERET','treauray','negatif'),(778,'PLUNERET','moulin d estaing','positif'),(779,'PLUNERET','Treveiven','negatif'),(780,'PLESCOP','Luzern','positif'),(781,'SULNIAC','Pebeyec','negatif'),(782,'SURZUR','Cosqueric','negatif'),(783,'PLUNERET','Kergoho','negatif'),(784,'DAMGAN','station de lagunage','negatif'),(785,'LAUZACH','Le Cosuero','negatif'),(786,'MONTERBLANC','Mongolerian','negatif'),(787,'THEIX','kerpayen','positif'),(788,'CRACH','Pontroual','negatif'),(789,'CRACH','Toul er Hah','negatif'),(790,'CRACH','Pont Pesked','negatif'),(791,'CRACH','Pont Varec','negatif'),(792,'CRACH','Bequerel','negatif'),(793,'CRACH','Les quatre chemins','negatif'),(794,'THEIX','Pont Bugat','negatif'),(795,'LE HEZO','Etang de lezuis','positif'),(796,'SAINT AVE','La briqueterie','positif'),(797,'THEIX','le saindo','negatif'),(798,'SULNIAC','La Hallaye','negatif'),(799,'SULNIAC','Ste Margueritte','negatif'),(800,'TREFFLEAN','Lanvoz','negatif'),(801,'SULNIAC','Trino','positif'),(802,'SULNIAC','Moulin de Treguere','negatif'),(803,'SULNIAC','Tregal','positif'),(804,'DAMGAN','Loch','negatif'),(805,'MEUCON','Le Guerneve','negatif'),(806,'PLESCOP','Leslegot','negatif'),(807,'PLOUGOUMELEN','Le Moulin de Kervilio','non prospection'),(808,'PLOUGOUMELEN','Tremodec','positif'),(809,'NOYALO','Kernicole','positif'),(810,'NOYALO','Per Prad Bily','positif'),(811,'PLOUGOUMELEN','Treverno','positif');
/*!40000 ALTER TABLE `obs_loutre` ENABLE KEYS */;
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
