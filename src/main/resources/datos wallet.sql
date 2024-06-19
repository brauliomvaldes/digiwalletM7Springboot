-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: digiwalletm7
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('3af52b87-fae8-4e76-a024-d5f95b99d3cc',100000.00,'2024-06-18 20:51:02.941000','00-177-050550-1',_binary '',1,2,1,'1753260d-07fb-4c97-bc89-c04085cb1824'),('82bc2787-9ccf-455e-9b25-25bbca88c6e2',100000.00,'2024-06-18 20:48:59.997000','000-500-500',_binary '',1,1,1,'c6f181a6-3452-42ab-80fc-ad5bf826b68b'),('a9e7577f-de1c-41ac-9bcd-b3acc0fbdaa8',50000.00,'2024-06-18 20:49:08.607000','00-155-55000-0',_binary '',1,2,1,'c6f181a6-3452-42ab-80fc-ad5bf826b68b'),('d736c857-ac30-4af7-bdd2-b84ee92787d7',500000.00,'2024-06-18 20:50:40.243000','122-00-22220-0',_binary '',1,1,1,'1753260d-07fb-4c97-bc89-c04085cb1824');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `banks`
--

LOCK TABLES `banks` WRITE;
/*!40000 ALTER TABLE `banks` DISABLE KEYS */;
INSERT INTO `banks` VALUES (1,'Banco de Chile',_binary ''),(2,'Banco Estado',_binary ''),(3,'Banco B.C.I.',_binary ''),(4,'Banco Santander',_binary ''),(5,'Banco Scotiabank',_binary '');
/*!40000 ALTER TABLE `banks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (1,'braulio','Banco de Chile','CLP','000-500-500',_binary '','1753260d-07fb-4c97-bc89-c04085cb1824'),(2,'braulio cuenta 2','Banco de Chile','USD','00-155-55000-0',_binary '','1753260d-07fb-4c97-bc89-c04085cb1824'),(3,'michel','Banco de Chile','USD','00-177-050550-1',_binary '','c6f181a6-3452-42ab-80fc-ad5bf826b68b'),(4,'michel 2','Banco de Chile','CLP','122-00-22220-0',_binary '','c6f181a6-3452-42ab-80fc-ad5bf826b68b');
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `currencyy`
--

LOCK TABLES `currencyy` WRITE;
/*!40000 ALTER TABLE `currencyy` DISABLE KEYS */;
INSERT INTO `currencyy` VALUES (1,'Pesos Chilenos',_binary '','CLP'),(2,'Dolar Americano',_binary '','USD'),(3,'Euros',_binary '','EUR');
/*!40000 ALTER TABLE `currencyy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_permissions`
--

LOCK TABLES `role_permissions` WRITE;
/*!40000 ALTER TABLE `role_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES ('6b76d7d6-94c8-48d6-9ada-996d999a0595',100000.00,0.00,'2024-06-18 20:49:21.031000','abono directo o dinero recibido','222987',_binary '','82bc2787-9ccf-455e-9b25-25bbca88c6e2','82bc2787-9ccf-455e-9b25-25bbca88c6e2'),('706242db-9d85-4cfa-b326-9789a8103ea4',100000.00,0.00,'2024-06-18 20:51:18.913000','abono directo o dinero recibido','384549',_binary '','3af52b87-fae8-4e76-a024-d5f95b99d3cc','3af52b87-fae8-4e76-a024-d5f95b99d3cc'),('b121b752-a4c9-4a09-9788-43dd126aedb9',500000.00,0.00,'2024-06-18 20:51:30.081000','abono directo o dinero recibido','294999',_binary '','d736c857-ac30-4af7-bdd2-b84ee92787d7','d736c857-ac30-4af7-bdd2-b84ee92787d7'),('e8b73c99-8d41-4ba5-9a75-8247d33d6de6',50000.00,0.00,'2024-06-18 20:49:31.907000','abono directo o dinero recibido','45846',_binary '','a9e7577f-de1c-41ac-9bcd-b3acc0fbdaa8','a9e7577f-de1c-41ac-9bcd-b3acc0fbdaa8');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `type_of_account`
--

LOCK TABLES `type_of_account` WRITE;
/*!40000 ALTER TABLE `type_of_account` DISABLE KEYS */;
INSERT INTO `type_of_account` VALUES (1,'Cuenta Corriente',_binary ''),(2,'Cuenta RUT',_binary ''),(3,'Chiquera Electónica',_binary '');
/*!40000 ALTER TABLE `type_of_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1753260d-07fb-4c97-bc89-c04085cb1824',_binary '',_binary '','2024-06-18 20:48:31.758000',_binary '','michel@gmail.com','michel','29696919-2',_binary '','contreras','$2a$10$omefpHHUt9.w/DhhGxJo..9YsIYk3Asxy3dOea1cQKyr.tnYOYFrW','ROLE_USER',_binary '','michel@gmail.com'),('c6f181a6-3452-42ab-80fc-ad5bf826b68b',_binary '',_binary '','2024-06-18 20:48:00.626000',_binary '','brauliomariano@gmail.com','braulio','29280539-k',_binary '','valdés','$2a$10$52n4FhqLwM7FTZELL40xgeuCJcbPDcruSOplLMdrzSzcHP5ewENGO','ROLE_USER',_binary '','brauliomariano@gmail.com');
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

-- Dump completed on 2024-06-18 21:09:05
