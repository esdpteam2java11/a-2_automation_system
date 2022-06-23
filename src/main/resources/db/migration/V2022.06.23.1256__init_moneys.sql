CREATE TABLE moneys
(
    id            BIGSERIAL,
    date DATE        NOT NULL,
    counterparty_id   BIGINT NOT NULL REFERENCES users (id),
    purpose       VARCHAR(128),
    type_of_finance      VARCHAR(128) NOT NULL,
    amount        DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
);