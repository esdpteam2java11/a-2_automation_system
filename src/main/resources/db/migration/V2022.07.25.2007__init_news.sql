CREATE TABLE news
(
    id          BIGSERIAL,
    title       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    image       VARCHAR(255) NOT NULL,
    date        DATE         NOT NULL,
    PRIMARY KEY (id)
);