CREATE TABLE users
(
    id         BIGSERIAL,
    name       VARCHAR(128) NOT NULL,
    surname    VARCHAR(128) NOT NULL,
    patronymic VARCHAR(128),
    phone      VARCHAR(128) NOT NULL,
    whatsapp   VARCHAR(128),
    telegram   VARCHAR(128),
    login      VARCHAR(128),
    channels VARCHAR(128),
    password   VARCHAR(128),
    birth_date TIMESTAMP,
    role       VARCHAR(128) NOT NULL,
    is_active  BOOLEAN      NOT NULL DEFAULT true,
    mother_id  BIGINT REFERENCES users (id),
    father_id  BIGINT REFERENCES users (id),
    primary key (id)
);

CREATE UNIQUE index login_unique on users (login ASC);