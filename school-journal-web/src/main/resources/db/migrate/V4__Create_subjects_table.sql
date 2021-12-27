CREATE TABLE IF NOT EXISTS `subjects`
(
    `id`           serial primary key,
    `subject_name` varchar(32) NOT NULL/*,
    PRIMARY KEY (`id`)*/
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;