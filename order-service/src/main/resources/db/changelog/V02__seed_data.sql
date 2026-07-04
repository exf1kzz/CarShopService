INSERT INTO users (id, username, created_at, updated_at, removed)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'user', now(), now(), FALSE),
    ('22222222-2222-2222-2222-222222222222', 'manager', now(), now(), FALSE),
    ('33333333-3333-3333-3333-333333333333', 'warehouse', now(), now(), FALSE),
    ('44444444-4444-4444-4444-444444444444', 'admin2', now(), now(), FALSE);

INSERT INTO orders (id, client_id, manager_id, created_at, updated_at, removed)
VALUES
    (
        gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'user'),
        (SELECT id FROM users WHERE username = 'manager'),
        now(),
        now(),
        FALSE
    ),
    (
        gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'user'),
        (SELECT id FROM users WHERE username = 'manager'),
        now(),
        now(),
        FALSE
    );

INSERT INTO stock_orders (id, car_id, status)
VALUES
    (
        (SELECT id FROM orders ORDER BY created_at ASC LIMIT 1),
        gen_random_uuid(),
        'CREATED'
    );

INSERT INTO custom_orders (id, configuration_id, status)
VALUES
    (
        (SELECT id FROM orders ORDER BY created_at DESC LIMIT 1),
        gen_random_uuid(),
        'WAITING_PAYMENT'
    );

INSERT INTO test_drive_requests (id, client_id, car_id, date, created_at, updated_at, removed)
VALUES
    (
        gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'user'),
        gen_random_uuid(),
        now() + interval '7 days',
        now(),
        now(),
        FALSE
    );
