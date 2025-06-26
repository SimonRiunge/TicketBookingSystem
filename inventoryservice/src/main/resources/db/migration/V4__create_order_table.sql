CREATE TABLE "order" (
    id SERIAL PRIMARY KEY,
    total NUMERIC(19, 2),
    quantity BIGINT,
    placed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    customer_id BIGINT,
    event_id BIGINT
);