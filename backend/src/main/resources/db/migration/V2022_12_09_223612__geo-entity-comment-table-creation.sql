CREATE TABLE `geo_entity_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `entity_status` int(11) NOT NULL,
  `geo_entity_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `comment_text` varchar(255),
  PRIMARY KEY (`id`),
  KEY `fk2_geo_entity_id` (`geo_entity_id`),
  KEY `fk4_user_id` (`user_id`),
  CONSTRAINT `fk2_geo_entity_id` FOREIGN KEY (`geo_entity_id`) REFERENCES `geo_entity` (`id`),
  CONSTRAINT `fk4_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `geo_entity_comment_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `entity_status` int(11) NOT NULL,
  `geo_entity_comment_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_geo_entity_comment_id` (`geo_entity_comment_id`),
  KEY `fk5_user_id` (`user_id`),
  CONSTRAINT `fk_geo_entity_comment_id` FOREIGN KEY (`geo_entity_comment_id`) REFERENCES `geo_entity_comment` (`id`),
  CONSTRAINT `fk5_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);