CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

DROP TABLE IF EXISTS `claims`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `addresses`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `organization_name` VARCHAR(255) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) NOT NULL,
  `district` VARCHAR(255) NOT NULL,
  `full_address` VARCHAR(255) NOT NULL,
  `users_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_users1_idx` (`users_id`),
  CONSTRAINT `fk_addresses_users1`
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `quantity` INT NOT NULL,
  `expiry_date` DATETIME NOT NULL,
  `status` VARCHAR(255) NOT NULL,
  `users_id` BIGINT NOT NULL,
  `categories_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_products_users_idx` (`users_id`),
  KEY `fk_products_categories1_idx` (`categories_id`),
  CONSTRAINT `fk_products_users`
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `claims` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `claim_date` DATETIME NOT NULL,
  `status` VARCHAR(255) NOT NULL,
  `users_id` BIGINT NOT NULL,
  `products_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_claims_users1_idx` (`users_id`),
  KEY `fk_claims_products1_idx` (`products_id`),
  CONSTRAINT `fk_claims_users1`
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_claims_products1`
    FOREIGN KEY (`products_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;