CREATE TABLE users
(
    id         INT AUTO_INCREMENT  NOT NULL,
    username   VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    created_on datetime            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);