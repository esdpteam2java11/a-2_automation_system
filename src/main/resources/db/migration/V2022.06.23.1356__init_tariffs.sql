CREATE TABLE tariffs
(
    id         BIGSERIAL,
    group_id   BIGINT NOT NULL REFERENCES groups (id),
    amount        DOUBLE PRECISION NOT NULL,
    start_date       TIMESTAMP   NOT NULL,
    PRIMARY KEY (id)
);