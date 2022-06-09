CREATE TABLE user_params
(
    id            BIGSERIAL,
    creation_date TIMESTAMP        NOT NULL,
    user_id       BIGINT           NOT NULL,
    weight        DOUBLE PRECISION NOT NULL,
    height        DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    PRIMARY KEY (id)
);