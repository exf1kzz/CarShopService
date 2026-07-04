CREATE TABLE users (
    id UUID PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL
);

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    client_id UUID,
    manager_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_orders_client
        FOREIGN KEY (client_id) REFERENCES users(id),
    CONSTRAINT fk_orders_manager
        FOREIGN KEY (manager_id) REFERENCES users(id)
);

CREATE TABLE stock_orders (
    id UUID PRIMARY KEY,
    car_id UUID,
    status TEXT,

    CONSTRAINT fk_stock_orders_orders
        FOREIGN KEY (id) REFERENCES orders(id)
);

CREATE TABLE custom_orders (
    id UUID PRIMARY KEY,
    configuration_id UUID,
    status TEXT,

    CONSTRAINT fk_custom_orders_orders
        FOREIGN KEY (id) REFERENCES orders(id)
);

CREATE TABLE test_drive_requests (
    id UUID PRIMARY KEY,
    client_id UUID,
    car_id UUID,
    date TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_test_drive_requests_client
        FOREIGN KEY (client_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS outbox_events (
    id UUID PRIMARY KEY,
    aggregate_id UUID NOT NULL,
    event_type TEXT NOT NULL,
    payload TEXT NOT NULL,
    status TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    sent_at TIMESTAMP NULL,
    retry_count INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS processed_messages (
    event_id UUID PRIMARY KEY,
    processed_at TIMESTAMP NOT NULL,
    consumer_name TEXT NOT NULL
);
