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
-- Table structure for table `muralrecados`
--

DROP TABLE IF EXISTS `muralrecados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muralrecados` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pessoaId` bigint DEFAULT NULL,
  `nomePessoa` varchar(255) NOT NULL,
  `eventoId` bigint NOT NULL,
  `comentario` text NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `pessoaId` (`pessoaId`),
  KEY `eventoId` (`eventoId`),
  CONSTRAINT `muralrecados_ibfk_1` FOREIGN KEY (`pessoaId`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE,
  CONSTRAINT `muralrecados_ibfk_2` FOREIGN KEY (`eventoId`) REFERENCES `evento` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muralrecados`
--

LOCK TABLES `muralrecados` WRITE;
/*!40000 ALTER TABLE `muralrecados` DISABLE KEYS */;
INSERT INTO `muralrecados` VALUES (1,NULL,'Gabriel',1,'Felicidadessss aos dois, sempre!','2024-12-10 08:15:33','2024-12-10 08:15:33'),(2,NULL,'Joana',2,'Que o matrimônio de vocês dois seja abençoado!!!','2024-12-10 08:16:12','2024-12-10 08:16:12'),(3,NULL,'Joao',1,'Que emoção participar desse momento tão especial! Desejo toda felicidade do mundo para vocês!','2024-12-10 10:02:26','2024-12-10 10:02:26'),(4,NULL,'Paulo',2,'Ana Paula e Carlos, desejo a vocês um casamento repleto de momentos lindos e inesquecíveis!','2024-12-10 10:02:26','2024-12-10 10:02:26'),(5,16,'Adriana Tobias Machado',1,'Que Deus abençoe o casamento de vocês!','2024-12-10 12:44:02','2024-12-10 12:44:02'),(6,13,'Adivâina dos Reis',1,'Que o amor de vocês seja infinito!','2024-12-10 12:55:07','2024-12-10 13:19:25'),(7,NULL,'Mariazinha',1,'Tô extremamente feliz por vocêsss!! Parabéns...','2024-12-10 13:28:56','2024-12-10 13:28:56');
/*!40000 ALTER TABLE `muralrecados` ENABLE KEYS */;
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
