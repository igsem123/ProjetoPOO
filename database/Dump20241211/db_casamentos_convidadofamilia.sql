-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: db_casamentos
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
-- Table structure for table `convidadofamilia`
--

DROP TABLE IF EXISTS `convidadofamilia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `convidadofamilia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nomeFamilia` varchar(255) NOT NULL,
  `acesso` varchar(100) NOT NULL,
  `eventoId` bigint NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `acesso` (`acesso`),
  KEY `eventoId` (`eventoId`),
  CONSTRAINT `convidadofamilia_ibfk_1` FOREIGN KEY (`eventoId`) REFERENCES `evento` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convidadofamilia`
--

LOCK TABLES `convidadofamilia` WRITE;
/*!40000 ALTER TABLE `convidadofamilia` DISABLE KEYS */;
INSERT INTO `convidadofamilia` VALUES (1,'Reis','LucasTobiasdeSousaMachadoAnaPaulaFerreira27/11/2024YHIP',1,'2024-11-27 12:43:16','2024-11-27 12:43:16'),(2,'Ferreira','CarlosAlbertoPereiraFernandaSilva27/12/2024SDQC',2,'2024-11-27 12:43:16','2024-11-27 12:43:16'),(3,'Pereira','CarlosAlbertoPereiraFernandaSilva27/12/2024QZWU',2,'2024-11-27 12:43:17','2024-11-27 12:43:17'),(6,'Silva','RaphaelNathanMoreiraLucasTobiasdeSousaMachado27/11/2024GDKX',1,'2024-11-28 15:11:00','2024-11-28 15:11:00'),(7,'Morais','AnaPaulaFerreiraCarlosAlbertoPereira27/12/2024AUNL',2,'2024-11-28 15:22:34','2024-11-28 15:22:34'),(9,'Machado','RaphaelNathanMoreiraLucasTobiasdeSousaMachado27/11/2024LVTT',1,'2024-12-09 09:45:24','2024-12-09 09:45:24');
/*!40000 ALTER TABLE `convidadofamilia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-11 13:01:33
