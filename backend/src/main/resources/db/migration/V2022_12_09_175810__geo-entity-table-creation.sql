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
  PRIMARY KEY (`id`)
);