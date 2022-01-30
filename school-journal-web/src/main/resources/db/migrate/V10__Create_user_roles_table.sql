create table user_roles
(
    user_id bigint NULL,
    role_id bigint NULL,
    CONSTRAINT role_fk
        FOREIGN KEY (role_id) REFERENCES roles (id)
            ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT user_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON UPDATE CASCADE ON DELETE CASCADE
);