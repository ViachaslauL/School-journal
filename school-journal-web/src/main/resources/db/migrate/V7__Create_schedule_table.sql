create table schedules
(
    schedule_id bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_id    bigint       NOT NULL,
    subject_id  bigint       NOT NULL,
    task        varchar(255) NULL,
    date        datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8mb4;