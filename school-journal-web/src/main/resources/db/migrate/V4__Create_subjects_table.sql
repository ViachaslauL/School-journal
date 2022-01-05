CREATE TABLE IF NOT EXISTS `subjects`
(
    `subject_id`           int NOT NULL AUTO_INCREMENT,
    `subject_name` varchar(32) NOT NULL,
    PRIMARY KEY (`subject_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;