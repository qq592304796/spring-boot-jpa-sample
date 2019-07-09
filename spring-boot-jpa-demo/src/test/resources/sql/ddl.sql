CREATE TABLE IF NOT EXISTS `test`
(
    `id`   bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `test_un` (`name`)
);