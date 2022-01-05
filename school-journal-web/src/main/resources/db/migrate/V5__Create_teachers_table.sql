CREATE TABLE IF NOT EXISTS `teachers`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `last_name`  varchar(32) NOT NULL,
    `first_name` varchar(32) NOT NULL,
    `patronymic` varchar(32) NOT NULL,
    `subject_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `subject_id` (`subject_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE `teachers`
    ADD CONSTRAINT `teachers_subjects_id_fk`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects` (subject_id)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

