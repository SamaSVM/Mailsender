CREATE TABLE crons
(
    id         INT AUTO_INCREMENT NOT NULL,
    created_on datetime           NOT NULL,
    expression VARCHAR(255)       NOT NULL,
    CONSTRAINT pk_crons PRIMARY KEY (id)
);