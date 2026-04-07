CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

DROP TABLE IF EXISTS `claims`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `addresses`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `organization_name` varchar(255) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `district` varchar(45) NOT NULL,
  `full_address` varchar(255) NOT NULL,
  `users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_users1_idx` (`users_id`),
  CONSTRAINT `fk_addresses_users1`
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

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
  CONSTRAINT `fk_products_users`
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `claims` (
  `id` int NOT NULL AUTO_INCREMENT,
  `claim_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  `users_id` int NOT NULL,
  `products_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_claims_users1_idx` (`users_id`),
  KEY `fk_claims_products1_idx` (`products_id`),
  CONSTRAINT `fk_claims_users1`
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_claims_products1`
    FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;