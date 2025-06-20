-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: studiomusik
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `alat_musik`
--

DROP TABLE IF EXISTS `alat_musik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alat_musik` (
  `alat_id` int NOT NULL AUTO_INCREMENT,
  `nama_alat` varchar(100) DEFAULT NULL,
  `studio_id` int DEFAULT NULL,
  PRIMARY KEY (`alat_id`),
  KEY `fk_alat_studio` (`studio_id`),
  CONSTRAINT `fk_alat_studio` FOREIGN KEY (`studio_id`) REFERENCES `studio_musik` (`studio_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alat_musik`
--

LOCK TABLES `alat_musik` WRITE;
/*!40000 ALTER TABLE `alat_musik` DISABLE KEYS */;
INSERT INTO `alat_musik` VALUES (23,'Drum',2),(24,'Gitar',1),(25,'Keyboard',2),(26,'Bass',1),(27,'Bass',2),(28,'Mic',1),(29,'Mic',2),(30,'Ketipung',1);
/*!40000 ALTER TABLE `alat_musik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `tanggal_booking` date DEFAULT NULL,
  `status_pembayaran` enum('belum','lunas') DEFAULT 'belum',
  `total_biaya` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,'2025-06-15','lunas',300000.00),(2,1,'2025-06-10','lunas',300000.00),(3,1,'2025-06-10','lunas',100000.00),(4,1,'2025-06-10','lunas',100000.00),(5,1,'2025-06-10','lunas',100000.00),(6,1,'2025-06-10','lunas',120000.00),(7,1,'2025-06-10','lunas',100000.00),(8,1,'2025-06-16','lunas',100000.00),(10,4,'2025-06-16','lunas',50000.00);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_booking`
--

DROP TABLE IF EXISTS `detail_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_booking` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `booking_id` int DEFAULT NULL,
  `studio_id` int DEFAULT NULL,
  `jam_mulai` int DEFAULT NULL,
  `jam_selesai` int DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `studio_id` (`studio_id`),
  KEY `fk_detail_booking_booking` (`booking_id`),
  CONSTRAINT `detail_booking_ibfk_2` FOREIGN KEY (`studio_id`) REFERENCES `studio_musik` (`studio_id`),
  CONSTRAINT `fk_detail_booking_booking` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_booking`
--

LOCK TABLES `detail_booking` WRITE;
/*!40000 ALTER TABLE `detail_booking` DISABLE KEYS */;
INSERT INTO `detail_booking` VALUES (1,2,1,10,12),(2,3,1,14,15),(3,4,2,10,12),(4,5,1,10,12),(5,5,1,12,13),(6,6,1,13,14),(7,7,1,9,10),(8,8,1,9,11),(9,10,1,13,14);
/*!40000 ALTER TABLE `detail_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jadwal_booking`
--

DROP TABLE IF EXISTS `jadwal_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jadwal_booking` (
  `jadwal_id` int NOT NULL AUTO_INCREMENT,
  `studio_id` int DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `jam` int DEFAULT NULL,
  `status` enum('available','booked') DEFAULT 'available',
  PRIMARY KEY (`jadwal_id`),
  UNIQUE KEY `uc_studio_tanggal_jam` (`studio_id`,`tanggal`,`jam`),
  CONSTRAINT `fk_jadwal_studio` FOREIGN KEY (`studio_id`) REFERENCES `studio_musik` (`studio_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jadwal_booking`
--

LOCK TABLES `jadwal_booking` WRITE;
/*!40000 ALTER TABLE `jadwal_booking` DISABLE KEYS */;
INSERT INTO `jadwal_booking` VALUES (65,1,'2025-06-16',9,'booked'),(66,1,'2025-06-16',10,'booked'),(67,1,'2025-06-16',11,'available'),(68,1,'2025-06-16',12,'available'),(69,1,'2025-06-16',13,'booked'),(70,1,'2025-06-16',14,'available'),(71,1,'2025-06-16',15,'available'),(72,1,'2025-06-16',16,'available'),(73,2,'2025-06-16',9,'available'),(74,2,'2025-06-16',10,'available'),(75,2,'2025-06-16',11,'available'),(76,2,'2025-06-16',12,'available'),(77,2,'2025-06-16',13,'available'),(78,2,'2025-06-16',14,'available'),(79,2,'2025-06-16',15,'available'),(80,2,'2025-06-16',16,'available');
/*!40000 ALTER TABLE `jadwal_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studio_musik`
--

DROP TABLE IF EXISTS `studio_musik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studio_musik` (
  `studio_id` int NOT NULL AUTO_INCREMENT,
  `nama_studio` varchar(100) DEFAULT NULL,
  `lokasi` varchar(100) DEFAULT NULL,
  `kapasitas` int DEFAULT NULL,
  PRIMARY KEY (`studio_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studio_musik`
--

LOCK TABLES `studio_musik` WRITE;
/*!40000 ALTER TABLE `studio_musik` DISABLE KEYS */;
INSERT INTO `studio_musik` VALUES (1,'Studio Yasmine','Pilang Kenceng',5),(2,'Studio B','Ketintang',3);
/*!40000 ALTER TABLE `studio_musik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` enum('admin','musisi') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Andi','andi@mail.com','musisi'),(2,'Budi','budi@mail.com','admin'),(4,'daffa','daffa@mail.com','musisi'),(5,'radit','tidar@mail.com','musisi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-16 19:54:25
