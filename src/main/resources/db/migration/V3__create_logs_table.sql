CREATE TABLE logs
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_on datetime           NOT NULL,
    user_id    INT                NOT NULL,
    type       VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_logs PRIMARY KEY (id)
);