CREATE TABLE visits
(
    id         BIGSERIAL,
    student_id BIGINT NOT NULL REFERENCES users (id),
    group_id   BIGINT NOT NULL REFERENCES groups (id),
    date       DATE   NOT NULL,
    PRIMARY KEY (id)
);