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
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `sexo` enum('Homem','Mulher','Outro') DEFAULT 'Outro',
  `dataNascimento` date DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipoUsuario` int NOT NULL,
  `cpf` char(11) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`email`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Raphael Nathan Moreira','Homem','1999-05-04','+55 34 9 9222-8686','rnathan@gmail.com','12345678',1,'00078666602','2024-11-22 17:26:49','2024-11-22 17:26:49'),(2,'Lucas Tobias de Sousa Machado','Homem','1994-03-28','+55 34 9 9227-8858','lucastobias@gmail.com','lucas123',1,'78514754201','2024-11-22 17:26:49','2024-11-22 17:26:49'),(3,'Ana Paula Ferreira','Mulher','1993-12-15','+55 34 9 9988-7766','ana.paula@gmail.com','ana123',1,'32456778911','2024-11-22 17:26:49','2024-11-22 17:26:49'),(4,'Carlos Alberto Pereira','Homem','1990-09-23','+55 34 9 9876-5432','carlos.pereira@gmail.com','carlos456',1,'21345678912','2024-11-22 17:26:49','2024-11-22 17:26:49'),(5,'Fernanda Silva','Mulher','1985-07-18','+55 34 9 7788-6655','fernanda.silva@gmail.com','cerimonial123',2,'55566677788','2024-11-22 17:26:49','2024-11-22 17:26:49'),(6,'Gabriel Antunes','Homem','1989-05-05','+55 34 9 2233-4455','gabriel.antunes@gmail.com','gabriel456',2,'22233344455','2024-11-22 17:26:49','2024-11-22 17:26:49'),(7,'José Roberto','Homem','1980-02-02','+55 34 9 5555-9999','jose.admin@gmail.com','admin456',3,'44455566677','2024-11-22 17:26:49','2024-11-22 17:26:49'),(8,'Jujuba Liliane','Mulher','2001-01-01','+55 034 9 9555-8525','juliana.liliane@gmail.com','juliana123',1,'12365478920','2024-11-27 13:14:12','2024-11-27 13:53:01'),(9,'Admin','Outro','2024-08-14','+55 34 9 2222-5555','admin','admin',3,'12345678910','2024-11-22 17:28:39','2024-11-22 17:28:39'),(10,'Sofia Mendes','Mulher','2000-03-27','+55 34 9 2234-5678','sofia.mendes@gmail.com','sofia123',4,'98712345678','2024-11-22 17:28:39','2024-11-22 17:28:39'),(11,'Ricardo Barbosa','Homem','1987-04-14','+55 34 9 3344-5566','ricardo.barbosa@gmail.com','ricardo123',4,'65432198700','2024-11-22 17:28:39','2024-11-22 17:28:39'),(12,'Camila Rocha','Mulher','1995-10-10','+55 34 9 4455-6677','camila.rocha@gmail.com','camila456',4,'12332145698','2024-11-22 17:28:39','2024-11-22 17:28:39'),(13,'Adivâina dos Reis','Mulher','1972-03-17','+55 34 9 3333-5555','adivaina@gmail.com','adivaina123',4,'85577766632','2024-11-22 17:28:39','2024-11-22 17:28:39'),(14,'Jessica Moreira','Mulher','1992-06-30','+55 34 9 4444-7777','jessica@gmail.com','jessica123',4,'78945612300','2024-11-22 17:28:39','2024-11-22 17:28:39'),(15,'Anderson Alvarenga','Homem','1998-05-01','+55 34 9 1111-8888','anderson@gmail.com','anderson123',4,'11122233344','2024-11-22 17:28:39','2024-11-22 17:28:39'),(16,'Adriana Tobias Machado','Mulher','1976-11-10','+55 34 9 7777-9999','adriana@gmail.com','adriana123',4,'14785201298','2024-11-22 17:28:39','2024-11-22 17:28:39'),(17,'João das Neves','Homem','1990-01-01','+55 34 9978-8852','joao@email.com','joao123',1,'12345678900','2024-11-22 17:28:39','2024-11-22 17:28:39'),(18,'Maria Oliveira','Mulher','1995-02-12','+55 34 9125-4550','maria@email.com','maria123',1,'98765432100','2024-11-22 17:28:39','2024-11-22 17:28:39'),(19,'José Silva','Homem','1985-03-23','+55 34 9874-1234','jose@email.com','jose123',4,'45678912300','2024-11-22 17:28:39','2024-11-22 17:28:39'),(20,'Pedro Silva','Homem','2016-08-15','+55 34 9 1234-5678','pedro.silva@gmail.com','pedro123',4,'11122233301','2024-11-22 17:28:39','2024-11-22 17:28:39'),(21,'Maria Souza','Mulher','2015-11-20','+55 34 9 8765-4321','maria.souza@gmail.com','maria123',4,'22233344402','2024-11-22 17:28:39','2024-11-22 17:28:39'),(22,'João Pereira','Homem','2014-05-05','+55 34 9 1122-3344','joao.pereira@gmail.com','joao123',4,'33344455503','2024-11-22 17:28:39','2024-11-22 17:28:39'),(23,'Ana Costa','Mulher','2010-09-12','+55 34 9 5566-7788','ana.costa@gmail.com','ana123',4,'44455566604','2024-11-22 17:28:39','2024-11-22 17:28:39'),(25,'Alfredo Luis','Homem','1999-12-04','+55 034 9 7777-5555','alfredoluis@email.com','alfredo123',1,'44455577700','2024-11-27 13:19:10','2024-11-27 13:19:10'),(27,'Jonas','Homem','2003-03-13','+55 9 9999-7575','jonasbrothers@email.com','jonas123',4,'12345678912','2024-11-27 13:27:52','2024-11-27 13:27:52'),(28,'Ulisse Joaquin da Silva','Homem','1982-08-04','+55 034 8852-5252','ulisess@email.com','ulisses123',2,'17234255500','2024-11-27 13:31:17','2024-11-27 14:19:27'),(29,'Rafaela Tobias Machado de Sousa','Mulher','2003-02-01','+55 034 9 9922-4545','rafaelat@gmail.com','rafaela123',4,'99911122200','2024-12-10 13:42:44','2024-12-10 13:42:44');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
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
