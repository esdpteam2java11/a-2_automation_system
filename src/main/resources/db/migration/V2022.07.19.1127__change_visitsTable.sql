alter table visits drop  column date;
alter table visits drop  column group_id;
ALTER TABLE visits     ADD COLUMN  IF NOT EXISTS schedule_id   BIGINT NOT NULL REFERENCES schedules (id);
