ALTER TABLE groups
    ADD COLUMN IF NOT EXISTS color VARCHAR (7) NOT NULL default '#FFFFFF';