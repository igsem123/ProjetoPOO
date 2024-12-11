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
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pessoaId` bigint NOT NULL,
  `fornecedorId` bigint NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  `parcela` int NOT NULL DEFAULT '1',
  `agendado` tinyint(1) NOT NULL DEFAULT '0',
  `dataPagamento` date NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `pessoaId` (`pessoaId`),
  KEY `fornecedorId` (`fornecedorId`),
  CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`pessoaId`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE,
  CONSTRAINT `pagamento_ibfk_2` FOREIGN KEY (`fornecedorId`) REFERENCES `fornecedor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (1,2,2,'Pagamento a empresa XZY das lonas da festa, primeira parcela.',1250.00,1,1,'2024-06-21','2024-12-04 10:00:23','2024-12-10 13:26:55'),(2,2,2,'Pagamento a empresa XZY das lonas da festa, segunda parcela.',1250.00,1,1,'2024-07-01','2024-12-04 10:00:23','2024-12-10 13:26:55'),(3,2,2,'Pagamento a empresa XZY das lonas da festa, terceira parcela.',1250.00,1,1,'2024-07-15','2024-12-04 10:00:23','2024-12-10 13:26:55'),(4,2,2,'Pagamento a empresa XZY das lonas da festa, quarta parcela.',1250.00,1,1,'2024-08-01','2024-12-04 10:00:23','2024-12-10 13:26:55'),(5,2,2,'Pagamento a empresa XZY das lonas da festa, quinta parcela.',1250.00,1,1,'2024-08-15','2024-12-04 10:00:23','2024-12-10 13:26:55'),(6,2,2,'Pagamento a empresa XZY das lonas da festa, sexta parcela.',1250.00,1,1,'2024-09-01','2024-12-04 10:00:23','2024-12-10 13:26:55'),(7,2,2,'Pagamento a empresa XZY das lonas da festa, sétima parcela.',1250.00,1,1,'2024-09-15','2024-12-04 10:00:23','2024-12-10 13:26:55'),(8,2,2,'Pagamento a empresa XZY das lonas da festa, oitava e última parcela.',1250.00,1,1,'2024-10-01','2024-12-04 10:00:23','2024-12-10 13:26:55'),(9,3,3,'Pagamento a empresa ABC das bebidas da festa, primeira parcela.',1500.00,1,1,'2024-10-22','2024-12-04 10:00:23','2024-12-10 13:26:55'),(10,3,3,'Pagamento a empresa ABC das bebidas da festa, segunda e terceira parcela.',3000.00,2,1,'2024-11-02','2024-12-04 10:00:23','2024-12-10 13:26:55'),(11,1,4,'Pagamento de Buffetzinho',5000.00,2,1,'2024-12-04','2024-12-04 10:22:31','2024-12-10 13:26:55'),(12,1,4,'Buffet',1666.67,1,1,'2024-08-04','2024-12-04 11:12:18','2024-12-10 13:26:55');
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
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
