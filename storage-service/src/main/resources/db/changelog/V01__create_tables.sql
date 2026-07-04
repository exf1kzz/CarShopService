CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE component_options (
    id UUID PRIMARY KEY,
    name TEXT,
    additional_price NUMERIC(19),
    component_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL
);

CREATE TABLE wheels (
    id UUID PRIMARY KEY,
    base_option_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_wheels_base_option
        FOREIGN KEY (base_option_id) REFERENCES component_options(id)
);

CREATE TABLE transmissions (
    id UUID PRIMARY KEY,
    base_option_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_transmissions_base_option
        FOREIGN KEY (base_option_id) REFERENCES component_options(id)
);

CREATE TABLE steering_wheels (
    id UUID PRIMARY KEY,
    base_option_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_steering_wheels_base_option
        FOREIGN KEY (base_option_id) REFERENCES component_options(id)
);

CREATE TABLE interiors (
    id UUID PRIMARY KEY,
    base_option_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_interiors_base_option
        FOREIGN KEY (base_option_id) REFERENCES component_options(id)
);

CREATE TABLE car_models (
    id UUID PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price NUMERIC(19),
    wheels_id UUID,
    transmission_id UUID,
    steering_wheel_id UUID,
    interior_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_car_models_wheels
        FOREIGN KEY (wheels_id) REFERENCES wheels(id),
    CONSTRAINT fk_car_models_transmission
        FOREIGN KEY (transmission_id) REFERENCES transmissions(id),
    CONSTRAINT fk_car_models_steering_wheel
        FOREIGN KEY (steering_wheel_id) REFERENCES steering_wheels(id),
    CONSTRAINT fk_car_models_interior
        FOREIGN KEY (interior_id) REFERENCES interiors(id)
);

CREATE TABLE component_option_models (
    option_id UUID,
    model_id UUID,

    CONSTRAINT fk_component_option_models_option
        FOREIGN KEY (option_id) REFERENCES component_options(id),
    CONSTRAINT fk_component_option_models_model
        FOREIGN KEY (model_id) REFERENCES car_models(id)
);

CREATE TABLE cars (
    id UUID PRIMARY KEY,
    model_id UUID,
    body_type TEXT,
    color TEXT,
    fuel_type TEXT,
    transmission_type TEXT,
    drive_type TEXT,
    engine_power INTEGER,
    engine_volume INTEGER,
    price NUMERIC(19),
    status TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_cars_model
        FOREIGN KEY (model_id) REFERENCES car_models(id)
);

CREATE TABLE car_configurations (
    id UUID PRIMARY KEY,
    car_model_id UUID,
    total_price NUMERIC(19),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_car_configurations_model
        FOREIGN KEY (car_model_id) REFERENCES car_models(id)
);

CREATE TABLE car_configuration_options (
    id UUID PRIMARY KEY,
    configuration_id UUID,
    component_id UUID,
    option_id UUID,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL,

    CONSTRAINT fk_car_configuration_options_configuration
        FOREIGN KEY (configuration_id) REFERENCES car_configurations(id),
    CONSTRAINT fk_car_configuration_options_option
        FOREIGN KEY (option_id) REFERENCES component_options(id)
);

CREATE TABLE assembly_orders (
    id UUID PRIMARY KEY,
    source_order_id UUID NOT NULL,
    status TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS processed_messages (
    event_id UUID PRIMARY KEY,
    processed_at TIMESTAMP NOT NULL,
    consumer_name TEXT NOT NULL
);

ALTER TABLE assembly_orders
    ADD COLUMN IF NOT EXISTS source_order_type TEXT;

ALTER TABLE assembly_orders
    ADD COLUMN IF NOT EXISTS trace_id TEXT;


