-- create tickets
CREATE TABLE tickets (
    id VARCHAR(36) NOT NULL,
    serial_number VARCHAR(15) NOT NULL,
    amount DOUBLE NOT NULL,
    passenger INTEGER NOT NULL,
    payment_mode ENUM ('upi', 'cash', 'card', 'cash_upi') NOT NULL,
    gstn VARCHAR(45) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted TINYINT(1) NOT NULL DEFAULT 0,
    deleted_on TIMESTAMP,

    -- constraints
    CONSTRAINT tickets_key_1 PRIMARY KEY (id),
    CONSTRAINT tickets_key_2 UNIQUE (serial_number)
);

-- create vehicles
CREATE TABLE vehicles (
    id VARCHAR(36) NOT NULL,
    number_suffix VARCHAR(4) NOT NULL,
    name VARCHAR(25) NOT NULL,
    type ENUM ('Cab', 'Mini_Bus', 'Bus', 'Two_Wheeler', 'Car'),
    commissioned TINYINT(1) NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- constraints
    CONSTRAINT vehicles_key_1 PRIMARY KEY (id)
);

-- create commissions
CREATE TABLE commissions (
    id VARCHAR(36) NOT NULL,
    ticket_id VARCHAR(36) NOT NULL,
    vehicle_id VARCHAR(36) NOT NULL,
    amount DOUBLE NOT NULL,
    status ENUM ('Pending', 'Processed', 'Paid', 'Failed', 'Refunded') NOT NULL DEFAULT 'Pending',
    contact_name VARCHAR(50) NOT NULL,
    contact_number VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- constraints
    CONSTRAINT commissions_key_1 PRIMARY KEY (id),
    CONSTRAINT commissions_key_2 FOREIGN KEY (ticket_id) REFERENCES tickets (id) ON DELETE CASCADE,
    CONSTRAINT commissions_key_3 FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);