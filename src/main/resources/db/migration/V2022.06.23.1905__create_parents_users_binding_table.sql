CREATE TABLE IF NOT EXISTS parents_users
(
    id        BIGSERIAL,
    user_id   BIGINT REFERENCES users (id),
    parent_id BIGINT REFERENCES parents (id),
    primary key (id)
);