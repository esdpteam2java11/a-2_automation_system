CREATE TABLE schedules
(
    id         BIGSERIAL,
    group_id   BIGINT NOT NULL REFERENCES groups (id),
    end_date       TIMESTAMP   NOT NULL,
    start_date       TIMESTAMP   NOT NULL,
    PRIMARY KEY (id)
);