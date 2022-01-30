create table users
(
    id        bigint AUTO_INCREMENT PRIMARY KEY,
    email          varchar(40)      NOT NULL,
    password  varchar(250)     NOT NULL,
    username       varchar(20)      NOT NULL,
    CONSTRAINT email
        UNIQUE (email)
);