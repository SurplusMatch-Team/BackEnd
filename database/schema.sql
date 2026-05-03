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
  PRIMARY KEY (`id`),
  UNIQUE KEY `categories_name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) NOT NULL,
  `district` VARCHAR(255) NOT NULL,
  `full_address` VARCHAR(255) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_user_idx` (`user_id`),
  CONSTRAINT `fk_addresses_user`
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `quantity` INT NOT NULL,
  `expiry_date` DATETIME NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` VARCHAR(255) NOT NULL,
  `unit` VARCHAR(32) NOT NULL DEFAULT 'UNIT',
  `user_id` BIGINT NOT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_products_users_idx` (`user_id`),
  KEY `fk_products_categories_idx` (`category_id`),
  CONSTRAINT `fk_products_users`
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_products_categories`
    FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `claims` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `claim_date` DATETIME NOT NULL,
  `requested_quantity` INT NOT NULL,
  `status` VARCHAR(255) NOT NULL,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `claimant_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_claims_users_idx` (`claimant_id`),
  KEY `fk_claims_products_idx` (`product_id`),
  CONSTRAINT `fk_claims_users`
    FOREIGN KEY (`claimant_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_claims_products`
    FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
