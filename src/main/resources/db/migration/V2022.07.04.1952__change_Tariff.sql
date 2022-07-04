
ALTER TABLE tariffs add user_id   BIGINT NOT NULL REFERENCES users (id);
ALTER TABLE tariffs RENAME COLUMN start_date to date;
ALTER TABLE tariffs DROP COLUMN  group_id;
ALTER table tariffs RENAME to sportsman_payments;
ALTER TABLE sportsman_payments add operation_type     VARCHAR(128) NOT NULL;

