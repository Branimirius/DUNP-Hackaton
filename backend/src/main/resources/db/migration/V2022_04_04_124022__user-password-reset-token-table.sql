CREATE TABLE `user_password_reset_token` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `created_by` varchar(50) NOT NULL,
    `created_date` datetime NOT NULL,
    `last_modified_by` varchar(50) DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    `version` bigint(20) NOT NULL,
    `entity_status` int(1) NOT NULL,
    `token` varchar(36) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `used`  bit(1) DEFAULT 0,
    PRIMARY KEY (`id`),
    KEY `fk_user_id` (`user_id`),
    CONSTRAINT `fk_user_id2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);