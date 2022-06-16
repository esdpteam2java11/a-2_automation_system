CREATE TABLE groups
(
    id         BIGSERIAL,
    name       VARCHAR(128) NOT NULL,
    trainer_id BIGINT  NOT NULL,
    FOREIGN KEY (trainer_id)
        REFERENCES users (id),
    PRIMARY KEY (id)
);