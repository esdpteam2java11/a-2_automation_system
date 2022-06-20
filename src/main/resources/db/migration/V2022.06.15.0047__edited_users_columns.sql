ALTER TABLE users
    DROP COLUMN mother_id,
    DROP COLUMN father_id,
    ADD COLUMN address VARCHAR(255) NOT NULL,
    ADD COLUMN school VARCHAR (255);
