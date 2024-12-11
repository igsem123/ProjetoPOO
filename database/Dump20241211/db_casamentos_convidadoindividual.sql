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
-- Table structure for table `convidadoindividual`
--

DROP TABLE IF EXISTS `convidadoindividual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `convidadoindividual` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pessoaId` bigint NOT NULL,
  `familiaId` bigint DEFAULT NULL,
  `eventoId` bigint NOT NULL,
  `parentesco` varchar(100) DEFAULT NULL,
  `confirmacao` tinyint(1) DEFAULT '0',
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `pessoaId` (`pessoaId`),
  KEY `familiaId` (`familiaId`),
  KEY `eventoId` (`eventoId`),
  CONSTRAINT `convidadoindividual_ibfk_1` FOREIGN KEY (`pessoaId`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE,
  CONSTRAINT `convidadoindividual_ibfk_2` FOREIGN KEY (`familiaId`) REFERENCES `convidadofamilia` (`id`) ON DELETE SET NULL,
  CONSTRAINT `convidadoindividual_ibfk_3` FOREIGN KEY (`eventoId`) REFERENCES `evento` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `convidadoindividual`
--

LOCK TABLES `convidadoindividual` WRITE;
/*!40000 ALTER TABLE `convidadoindividual` DISABLE KEYS */;
INSERT INTO `convidadoindividual` VALUES (1,13,1,1,'Mãe',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(2,14,1,1,'Irmã',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(3,15,1,1,'Amigo',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(4,16,2,1,'Mãe',0,'2024-11-27 12:54:28','2024-12-10 13:37:04'),(5,20,1,1,'Filho',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(6,21,1,1,'Filha',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(7,22,1,1,'Filho',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(8,23,1,1,'Filha',1,'2024-11-27 12:54:28','2024-12-10 13:39:35'),(11,27,1,1,'Amigo',1,'2024-11-28 15:10:08','2024-12-10 13:39:35'),(12,23,6,4,'Amiga',1,'2024-11-28 15:11:04','2024-11-28 15:30:38'),(13,23,7,1,'Irmã',1,'2024-11-28 15:22:42','2024-12-03 10:42:42'),(14,16,9,1,'Mãe do Lucas',0,'2024-12-09 09:45:29','2024-12-10 12:37:51'),(15,29,9,1,'Irmã do Lucas',0,'2024-12-10 13:43:31','2024-12-10 13:43:31');
/*!40000 ALTER TABLE `convidadoindividual` ENABLE KEYS */;
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
