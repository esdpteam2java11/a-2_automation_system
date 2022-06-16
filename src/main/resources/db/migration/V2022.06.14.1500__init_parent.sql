CREATE TABLE parents
(
    id         BIGSERIAL,
    name       VARCHAR(255) NOT NULL,
    surname    VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255),
    phone      VARCHAR(255) NOT NULL,
    kinship    VARCHAR(255) NOT NULL,
    student_id BIGINT REFERENCES users (id),
    PRIMARY KEY (id)
);