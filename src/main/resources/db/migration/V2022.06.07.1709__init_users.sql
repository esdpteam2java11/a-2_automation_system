CREATE TABLE users
(
    id         bigserial,
    name       varchar(128) NOT NULL,
    surname    varchar(128) NOT NULL,
    patronymic varchar(128),
    phone      varchar(128) NOT NULL,
    whatsapp   varchar(128),
    telegram   varchar(128),
    login      varchar(128),
    password   varchar(128),
    birth_date DATE ,
    role       varchar(128) NOT NULL,
    is_active  boolean      NOT NULL default true,
    mother_id  bigint REFERENCES users (id),
    father_id  bigint REFERENCES users (id),
    primary key (id)
);

create UNIQUE index login_unique on users (login ASC);