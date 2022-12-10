CREATE TABLE `geo_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `entity_status` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `latitude` decimal(18,15) DEFAULT NULL,
  `longitude` decimal(18,15) DEFAULT NULL,
  `image_url` varchar(255),
  `like_numbers` int(5) DEFAULT NULL,
  `description` varchar(255),
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk2_user_id` (`user_id`),
  CONSTRAINT `fk2_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `geo_entity_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `entity_status` int(11) NOT NULL,
  `geo_entity_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_geo_entity_id` (`user_id`),
  KEY `fk3_user_id` (`user_id`),
  CONSTRAINT `fk_geo_entity_id` FOREIGN KEY (`geo_entity_id`) REFERENCES `geo_entity_id` (`id`),
  CONSTRAINT `fk3_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);