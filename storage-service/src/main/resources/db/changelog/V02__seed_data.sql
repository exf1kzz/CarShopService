INSERT INTO component_options (id, name, additional_price, component_id, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(), '17'''' Standard', 0, NULL, now(), now(), FALSE),
    (gen_random_uuid(), '19'''' M-Sport', 95000, NULL, now(), now(), FALSE),
    (gen_random_uuid(), '18'''' Aero', 45000, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Automatic 8AT', 0, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Manual 6MT', -30000, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Sport Leather Steering Wheel (Standard)', 0, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Heated M-Sport Steering Wheel', 25000, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Graphite Fabric Interior', 0, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Dakota Leather Interior', 110000, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'Performance Sport Interior', 160000, NULL, now(), now(), FALSE);

INSERT INTO wheels (id, base_option_id, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    id,
    now(),
    now(),
    FALSE
FROM component_options
WHERE name = '17'''' Standard';

INSERT INTO transmissions (id, base_option_id, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    id,
    now(),
    now(),
    FALSE
FROM component_options
WHERE name = 'Automatic 8AT';

INSERT INTO steering_wheels (id, base_option_id, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    id,
    now(),
    now(),
    FALSE
FROM component_options
WHERE name = 'Sport Leather Steering Wheel (Standard)';

INSERT INTO interiors (id, base_option_id, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    id,
    now(),
    now(),
    FALSE
FROM component_options
WHERE name = 'Graphite Fabric Interior';

INSERT INTO car_models (id, brand, model, price, wheels_id, transmission_id, steering_wheel_id, interior_id, created_at, updated_at, removed)
VALUES
    (
        gen_random_uuid(),
        'BMW',
        '320i',
        5999999,
        (SELECT id FROM wheels WHERE base_option_id = (SELECT id FROM component_options WHERE name = '17'''' Standard')),
        (SELECT id FROM transmissions WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Automatic 8AT')),
        (SELECT id FROM steering_wheels WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Sport Leather Steering Wheel (Standard)')),
        (SELECT id FROM interiors WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Graphite Fabric Interior')),
        now(),
        now(),
        FALSE
    ),
    (gen_random_uuid(), 'BMW', '330i', 6799999, NULL, NULL, NULL, NULL, now(), now(), FALSE),
    (gen_random_uuid(), 'BMW', 'M340i', 7999999, NULL, NULL, NULL, NULL, now(), now(), FALSE);

UPDATE component_options
SET component_id = (SELECT id FROM wheels WHERE base_option_id = (SELECT id FROM component_options WHERE name = '17'''' Standard'))
WHERE name IN ('17'''' Standard', '19'''' M-Sport', '18'''' Aero');

UPDATE component_options
SET component_id = (SELECT id FROM transmissions WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Automatic 8AT'))
WHERE name IN ('Automatic 8AT', 'Manual 6MT');

UPDATE component_options
SET component_id = (SELECT id FROM steering_wheels WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Sport Leather Steering Wheel (Standard)'))
WHERE name IN ('Sport Leather Steering Wheel (Standard)', 'Heated M-Sport Steering Wheel');

UPDATE component_options
SET component_id = (SELECT id FROM interiors WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Graphite Fabric Interior'))
WHERE name IN ('Graphite Fabric Interior', 'Dakota Leather Interior', 'Performance Sport Interior');

INSERT INTO component_option_models (option_id, model_id)
SELECT option_id, model_id
FROM (
    VALUES
        ('17'''' Standard', '320i'),
        ('Automatic 8AT', '320i'),
        ('Automatic 8AT', '330i'),
        ('Sport Leather Steering Wheel (Standard)', '320i'),
        ('Sport Leather Steering Wheel (Standard)', '330i'),
        ('Graphite Fabric Interior', '320i'),
        ('19'''' M-Sport', '320i'),
        ('19'''' M-Sport', '330i'),
        ('19'''' M-Sport', 'M340i'),
        ('18'''' Aero', '320i'),
        ('18'''' Aero', '330i'),
        ('Manual 6MT', '320i'),
        ('Manual 6MT', '330i'),
        ('Heated M-Sport Steering Wheel', '320i'),
        ('Heated M-Sport Steering Wheel', '330i'),
        ('Heated M-Sport Steering Wheel', 'M340i'),
        ('Dakota Leather Interior', '320i'),
        ('Dakota Leather Interior', '330i'),
        ('Performance Sport Interior', '330i'),
        ('Performance Sport Interior', 'M340i')
) AS compatibility(option_name, model_name)
CROSS JOIN LATERAL (
    SELECT id AS option_id
    FROM component_options
    WHERE name = compatibility.option_name
) option_ref
CROSS JOIN LATERAL (
    SELECT id AS model_id
    FROM car_models
    WHERE brand = 'BMW' AND model = compatibility.model_name
) model_ref;

INSERT INTO cars (id, model_id, body_type, color, fuel_type, transmission_type, drive_type, engine_power, engine_volume, price, status, created_at, updated_at, removed)
VALUES
    (
        gen_random_uuid(),
        (SELECT id FROM car_models WHERE brand = 'BMW' AND model = '320i'),
        'SEDAN',
        'BLACK',
        'PETROL',
        'AUTOMATIC',
        'REAR',
        184,
        2,
        5999999,
        'AVAILABLE',
        now(),
        now(),
        FALSE
    ),
    (
        gen_random_uuid(),
        (SELECT id FROM car_models WHERE brand = 'BMW' AND model = '320i'),
        'SEDAN',
        'WHITE',
        'PETROL',
        'AUTOMATIC',
        'REAR',
        184,
        2,
        6119999,
        'TEST_DRIVE_AVAILABLE',
        now(),
        now(),
        FALSE
    );

INSERT INTO car_configurations (id, car_model_id, total_price, created_at, updated_at, removed)
VALUES
    (
        gen_random_uuid(),
        (SELECT id FROM car_models WHERE brand = 'BMW' AND model = '320i'),
        6229999,
        now(),
        now(),
        FALSE
    );

INSERT INTO car_configuration_options (id, configuration_id, component_id, option_id, created_at, updated_at, removed)
VALUES
    (
        gen_random_uuid(),
        (SELECT id FROM car_configurations LIMIT 1),
        (SELECT id FROM wheels WHERE base_option_id = (SELECT id FROM component_options WHERE name = '17'''' Standard')),
        (SELECT id FROM component_options WHERE name = '19'''' M-Sport'),
        now(),
        now(),
        FALSE
    ),
    (
        gen_random_uuid(),
        (SELECT id FROM car_configurations LIMIT 1),
        (SELECT id FROM transmissions WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Automatic 8AT')),
        (SELECT id FROM component_options WHERE name = 'Automatic 8AT'),
        now(),
        now(),
        FALSE
    ),
    (
        gen_random_uuid(),
        (SELECT id FROM car_configurations LIMIT 1),
        (SELECT id FROM steering_wheels WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Sport Leather Steering Wheel (Standard)')),
        (SELECT id FROM component_options WHERE name = 'Heated M-Sport Steering Wheel'),
        now(),
        now(),
        FALSE
    ),
    (
        gen_random_uuid(),
        (SELECT id FROM car_configurations LIMIT 1),
        (SELECT id FROM interiors WHERE base_option_id = (SELECT id FROM component_options WHERE name = 'Graphite Fabric Interior')),
        (SELECT id FROM component_options WHERE name = 'Dakota Leather Interior'),
        now(),
        now(),
        FALSE
    );
