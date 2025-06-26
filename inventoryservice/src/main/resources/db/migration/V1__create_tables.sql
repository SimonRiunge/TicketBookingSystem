CREATE TABLE venue (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    total_capacity BIGINT
);

CREATE TABLE event (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    total_capacity BIGINT,
    left_capacity BIGINT,
    venue_id BIGINT,
    CONSTRAINT fk_venue
        FOREIGN KEY (venue_id)
        REFERENCES venue(id)
        ON DELETE SET NULL
);