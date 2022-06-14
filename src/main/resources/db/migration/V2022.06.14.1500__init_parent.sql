CREATE TABLE parent
(
    id         bigserial    NOT NULL,
    name       varchar(255) NOT NULL,
    surname    varchar(255) NOT NULL,
    patronymic varchar(255),
    phone      varchar(255) NOT NULL,
    kinship       varchar(255) NOT NULL,
    student_id  bigint REFERENCES users (id),
    primary key (id)
);