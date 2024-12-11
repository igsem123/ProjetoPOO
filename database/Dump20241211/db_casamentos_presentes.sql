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
-- Table structure for table `presentes`
--

DROP TABLE IF EXISTS `presentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presentes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `tipo` int NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `dataCriacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataModificacao` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentes`
--

LOCK TABLES `presentes` WRITE;
/*!40000 ALTER TABLE `presentes` DISABLE KEYS */;
INSERT INTO `presentes` VALUES (1,'Faqueiro (jogo de talheres)',1,150.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(2,'Aparelho de jantar',1,200.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(3,'Jogo de panelas',1,250.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(4,'Jogo de formas e assadeiras',1,120.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(5,'Chaleira',1,80.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(6,'Jogo de copos (dia a dia)',1,70.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(7,'Jogo de taças',1,100.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(8,'Bule e leiteira',1,90.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(9,'Fruteira',1,60.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(10,'Garrafa térmica',1,50.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(11,'Balança para cozinha',1,40.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(12,'Jogo de facas',1,150.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(13,'Tábua para queijos e frios',1,45.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(14,'Tábua para carnes',1,70.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(15,'Balde de gelo',1,80.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(16,'Aparelho de café e chá',1,110.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(17,'Bomboniere',1,35.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(18,'Jogo de sobremesa',1,90.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(19,'Conjunto para bolo (prato e espátula)',1,60.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(20,'Jarra',1,40.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(21,'Petisqueira',1,50.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(22,'Porta-condimentos/temperos',1,60.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(23,'Porta-guardapo',1,20.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(24,'Saladeira',1,80.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(25,'Apoio para copos',1,25.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(26,'Jogo de utensílios e espátulas',1,70.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(27,'Jogo de lugar americano',1,50.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(28,'Fogão',2,1500.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(29,'Coifa',2,900.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(30,'Geladeira',2,2500.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(31,'Freezer',2,2000.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(32,'Liquidificador',2,150.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(33,'Torradeira/sanduicheira',2,200.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(34,'Air fryer',2,400.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(35,'Micro-ondas',2,350.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(36,'Processador de alimentos',2,500.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(37,'Batedeira',2,300.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(38,'Espremedor de frutas',2,100.00,'2024-12-10 10:02:25','2024-12-10 10:08:41'),(39,'Aparelho de fondue e raclette',2,150.00,'2024-12-10 10:02:25','2024-12-10 10:08:40'),(40,'Cafeteira',2,200.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(41,'Grill elétrico',2,250.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(42,'Juicer',2,180.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(43,'Máquina de pão',2,450.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(44,'Panela elétrica',2,220.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(45,'Jogo de lençóis',3,200.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(46,'Edredom',3,300.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(47,'Manta',3,150.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(48,'Cobertor',3,180.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(49,'Jogo de toalhas de banho',3,120.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(50,'Toalhas de mesa',3,100.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(51,'Castiçais e velas',3,60.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(52,'Objetos de decoração',3,250.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(53,'Tapetes',3,350.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(54,'Conjunto para pia/lavabo',3,90.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(55,'Aromatizador de ambiente',3,80.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(56,'Tábua de passar',3,120.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(57,'Capacho',3,50.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(58,'Relógio',3,150.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(59,'Vasos',3,100.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(60,'Cama King Size',3,3000.00,'2024-12-10 10:02:26','2024-12-10 10:36:19'),(61,'Colchão',4,1800.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(62,'Sofá',4,2500.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(63,'TV',4,3000.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(64,'Mesa e cadeiras de jantar',4,2200.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(65,'Rack',4,700.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(66,'Luminária',4,350.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(67,'Abajour',4,200.00,'2024-12-10 10:02:26','2024-12-10 10:08:41'),(68,'Ferro de passar',4,150.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(69,'Lavadora de roupas/Lava e seca',4,2500.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(70,'Aspirador de pó',4,500.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(71,'Guarda-roupa',4,2000.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(72,'Mesa de centro',4,800.00,'2024-12-10 10:02:26','2024-12-10 10:08:40'),(73,'Poltronas',4,1200.00,'2024-12-10 10:02:26','2024-12-10 10:08:40');
/*!40000 ALTER TABLE `presentes` ENABLE KEYS */;
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
