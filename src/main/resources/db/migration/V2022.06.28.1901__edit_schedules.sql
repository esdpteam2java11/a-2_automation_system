ALTER TABLE schedules
DROP COLUMN IF EXISTS start_date;

ALTER TABLE schedules
DROP COLUMN IF EXISTS end_date;

ALTER TABLE schedules
    ADD COLUMN IF NOT EXISTS event_date TIMESTAMP NOT NULL;

ALTER TABLE schedules
    ADD COLUMN IF NOT EXISTS start_time TIME NOT NULL;

ALTER TABLE schedules
    ADD COLUMN IF NOT EXISTS end_time TIME NOT NULL;