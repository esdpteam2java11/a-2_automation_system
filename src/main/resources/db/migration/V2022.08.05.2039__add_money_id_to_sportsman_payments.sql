ALTER TABLE sportsman_payments
    ADD COLUMN money_id BIGINT REFERENCES moneys(id);