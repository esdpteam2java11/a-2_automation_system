CREATE TABLE sportsman_events
(
    id         BIGSERIAL,
    sportsman_id   BIGINT NOT NULL REFERENCES users (id),
    event_date   DATE  NOT NULL,
    training_program TEXT,
    food TEXT,
    PRIMARY KEY (id)
);
