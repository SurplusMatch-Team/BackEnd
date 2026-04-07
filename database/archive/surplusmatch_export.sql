-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `district` varchar(45) NOT NULL,
  `full_address` varchar(255) NOT NULL,
  `users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_users1_idx` (`users_id`),
  CONSTRAINT `fk_addresses_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Kayseri','Talas','Yenidoğan Mah. No:1',1),(2,'Kayseri','Melikgazi','Cumhuriyet Meydanı No:5',3),(3,'Kayseri','Melikgazi','İldem Cumhuriyet Mah. No:34',5),(4,'Kayseri','Kocasinan','Zümrüt Mah. Emniyet Cad.',6),(5,'Kayseri','Melikgazi','Alpaslan Mah. Kızılırmak Cad.',7),(6,'Kayseri','Kocasinan','Fevzi Çakmak Mah. No:12',8);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Fırın Ürünleri'),(2,'Manav'),(3,'Süt ve Şarküteri'),(4,'Konserve');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claims`
--

DROP TABLE IF EXISTS `claims`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claims` (
  `id` int NOT NULL AUTO_INCREMENT,
  `claim_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  `users_id` int NOT NULL,
  `products_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_claims_users1_idx` (`users_id`),
  KEY `fk_claims_products1_idx` (`products_id`),
  CONSTRAINT `fk_claims_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
  CONSTRAINT `fk_claims_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claims`
--

LOCK TABLES `claims` WRITE;
/*!40000 ALTER TABLE `claims` DISABLE KEYS */;
/*!40000 ALTER TABLE `claims` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `quantity` int NOT NULL,
  `expiry_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  `users_id` int NOT NULL,
  `categories_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_products_users_idx` (`users_id`),
  KEY `fk_products_categories1_idx` (`categories_id`),
  CONSTRAINT `fk_products_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_products_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Tam Buğday Ekmeği',15,'2026-04-10 18:00:00','ACTIVE',1,1),(2,'Salkım Domates',10,'2026-04-08 12:00:00','ACTIVE',1,2),(3,'Susamlı Simit',40,'2026-04-06 10:00:00','ACTIVE',1,1),(4,'Roll Ekmek',100,'2026-04-07 20:00:00','ACTIVE',1,1),(5,'Yerli Muz',25,'2026-04-08 18:00:00','ACTIVE',5,2),(6,'Granny Smith Elma',50,'2026-04-12 15:00:00','ACTIVE',5,2),(7,'Yarım Yağlı Süt 1L',12,'2026-04-09 23:59:59','ACTIVE',6,3),(8,'Süzme Yoğurt 900g',8,'2026-04-07 12:00:00','ACTIVE',6,3),(9,'Bezelye Konservesi',30,'2026-05-20 00:00:00','ACTIVE',7,4),(10,'Haşlanmış Nohut',20,'2026-06-15 00:00:00','ACTIVE',7,4),(11,'Çavdar Ekmeği',10,'2026-04-06 19:00:00','ACTIVE',2,1),(12,'Köy Tipi Tereyağı',5,'2026-04-10 09:00:00','ACTIVE',2,3);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `organization_name` varchar(255) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'migros_talas@mail.com','123456','Migros Talas','MARKET'),(2,'a101_anayurt@mail.com','123456','A101 Anayurt','MARKET'),(3,'kizilay_kayseri@mail.com','123456','Kızılay Kayseri','NGO'),(4,'ahbap_kayseri@mail.com','123456','Ahbap Kayseri','NGO'),(5,'bim_ildem@mail.com','123456','BİM İldem Şubesi','MARKET'),(6,'sok_emniyet@mail.com','123456','Şok Emniyet Müdürlüğü Yanı','MARKET'),(7,'carrefour_alpaslan@mail.com','123456','CarrefourSA Alpaslan','MARKET'),(8,'kayseri_asevi@mail.com','123456','Kayseri Büyükşehir Aşevi','NGO'),(9,'hayrat_kayseri@mail.com','123456','Hayrat Yardım Kayseri','NGO'),(10,'iyilikder_kayseri@mail.com','123456','İyilikder Kayseri Temsilciliği','NGO');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-07 13:24:07
