ALTER TABLE moneys ADD COLUMN   cashier_id   BIGINT NOT NULL REFERENCES users (id);