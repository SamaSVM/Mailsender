CREATE TABLE users
(
    id         INT AUTO_INCREMENT  NOT NULL,
    username   VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    created_on datetime            NOT NULL,
    count_id   INT                 NOT NULL,
    first      datetime            NULL,
    last       datetime            NULL,
    FOREIGN KEY (count_id) REFERENCES counts (id),
    CONSTRAINT pk_users PRIMARY KEY (id)
);