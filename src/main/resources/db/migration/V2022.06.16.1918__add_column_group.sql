ALTER TABLE users
    ADD COLUMN group_id BIGINT REFERENCES users(id);