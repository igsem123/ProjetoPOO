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
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dataEvento` date NOT NULL,
  `cerimonial` bigint DEFAULT NULL,
  `igreja` varchar(255) DEFAULT NULL,
  `cartorio` varchar(255) DEFAULT NULL,
  `pessoaNoivo1` bigint NOT NULL,
  `pessoaNoivo2` bigint NOT NULL,
  `nomeDoEvento` varchar(255) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `cerimonial` (`cerimonial`),
  KEY `pessoaNoivo1` (`pessoaNoivo1`),
  KEY `pessoaNoivo2` (`pessoaNoivo2`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`cerimonial`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`pessoaNoivo1`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `evento_ibfk_3` FOREIGN KEY (`pessoaNoivo2`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'2024-11-27',5,'Igreja Central','Cartório Central',1,2,'Casamento de Raphael Nathan Moreira e Lucas Tobias de Sousa Machado em 27/11/2024','2024-11-27 12:02:26','2024-11-27 12:02:26'),(2,'2024-12-27',6,'Igreja Nova','Cartório Nova',3,4,'Casamento de Ana Paula Ferreira e Carlos Alberto Pereira em 27/12/2024','2024-11-27 12:02:26','2024-11-27 12:02:26'),(4,'2026-07-12',5,'Igreja de São Luís','Cartório License',18,25,'Casamento de Maria Oliveira e Alfredo Luis em 12/07/2026','2024-11-28 09:23:16','2024-12-03 10:44:52');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-11 13:01:34
