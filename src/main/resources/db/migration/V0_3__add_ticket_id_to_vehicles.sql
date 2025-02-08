-- add ticket id to vehicles
ALTER TABLE vehicles ADD COLUMN ticket_id VARCHAR(36) NOT NULL;

-- create fk constraint on ticket_id
ALTER TABLE vehicles ADD CONSTRAINT vehicles_key_2 FOREIGN KEY (ticket_id) REFERENCES tickets(id) ON DELETE CASCADE;

-- create unique constraint on ticket_id
ALTER TABLE vehicles ADD CONSTRAINT vehicles_key_3 UNIQUE (ticket_id);