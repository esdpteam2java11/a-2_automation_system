ALTER TABLE users
    DROP COLUMN mother_id,
    DROP COLUMN father_id,
    ADD COLUMN address varchar (255) NOT NULL,
    ADD COLUMN school varchar (255);
