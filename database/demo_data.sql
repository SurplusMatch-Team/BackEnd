USE mydb;

-- Demo users are intentionally not inserted here.
-- Create them through /api/auth/register so passwords are hashed by backend logic.

INSERT INTO products (name, description, quantity, expiry_date, created_at, updated_at, status, user_id, category_id)
SELECT
    'Bread',
    'Fresh bakery bread',
    30,
    DATE_ADD(NOW(), INTERVAL 1 DAY),
    NOW(),
    NOW(),
    'AVAILABLE',
    u.id,
    c.id
FROM users u
JOIN categories c ON c.name = 'Bakery'
WHERE u.email = 'market1@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM products p
      WHERE p.name = 'Bread' AND p.user_id = u.id
  );

INSERT INTO products (name, description, quantity, expiry_date, created_at, updated_at, status, user_id, category_id)
SELECT
    'Tomatoes',
    'Fresh ripe tomatoes',
    15,
    DATE_ADD(NOW(), INTERVAL 2 DAY),
    NOW(),
    NOW(),
    'AVAILABLE',
    u.id,
    c.id
FROM users u
JOIN categories c ON c.name = 'Vegetables'
WHERE u.email = 'market1@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM products p
      WHERE p.name = 'Tomatoes' AND p.user_id = u.id
  );

INSERT INTO products (name, description, quantity, expiry_date, created_at, updated_at, status, user_id, category_id)
SELECT
    'Milk',
    'Dairy milk',
    10,
    DATE_ADD(NOW(), INTERVAL 1 DAY),
    NOW(),
    NOW(),
    'AVAILABLE',
    u.id,
    c.id
FROM users u
JOIN categories c ON c.name = 'Dairy'
WHERE u.email = 'market2@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM products p
      WHERE p.name = 'Milk' AND p.user_id = u.id
  );

INSERT INTO products (name, description, quantity, expiry_date, created_at, updated_at, status, user_id, category_id)
SELECT
    'Apples',
    'Fresh apples',
    20,
    DATE_ADD(NOW(), INTERVAL 5 DAY),
    NOW(),
    NOW(),
    'AVAILABLE',
    u.id,
    c.id
FROM users u
JOIN categories c ON c.name = 'Fruits'
WHERE u.email = 'market2@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM products p
      WHERE p.name = 'Apples' AND p.user_id = u.id
  );

INSERT INTO products (name, description, quantity, expiry_date, created_at, updated_at, status, user_id, category_id)
SELECT
    'Rice',
    'Dry food rice',
    50,
    DATE_ADD(NOW(), INTERVAL 30 DAY),
    NOW(),
    NOW(),
    'AVAILABLE',
    u.id,
    c.id
FROM users u
JOIN categories c ON c.name = 'Dry Food'
WHERE u.email = 'market1@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM products p
      WHERE p.name = 'Rice' AND p.user_id = u.id
  );

INSERT INTO claims (claim_date, requested_quantity, status, updated_at, claimant_id, product_id)
SELECT
    NOW(),
    5,
    'PENDING',
    NOW(),
    claimant.id,
    product_row.id
FROM users claimant
JOIN users owner ON owner.email = 'market1@example.com'
JOIN products product_row ON product_row.name = 'Bread' AND product_row.user_id = owner.id
WHERE claimant.email = 'ngo1@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM claims c
      WHERE c.claimant_id = claimant.id
        AND c.product_id = product_row.id
        AND c.requested_quantity = 5
        AND c.status = 'PENDING'
  );

INSERT INTO claims (claim_date, requested_quantity, status, updated_at, claimant_id, product_id)
SELECT
    NOW(),
    3,
    'APPROVED',
    NOW(),
    claimant.id,
    product_row.id
FROM users claimant
JOIN users owner ON owner.email = 'market1@example.com'
JOIN products product_row ON product_row.name = 'Tomatoes' AND product_row.user_id = owner.id
WHERE claimant.email = 'ngo2@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM claims c
      WHERE c.claimant_id = claimant.id
        AND c.product_id = product_row.id
        AND c.requested_quantity = 3
        AND c.status = 'APPROVED'
  );

INSERT INTO claims (claim_date, requested_quantity, status, updated_at, claimant_id, product_id)
SELECT
    NOW(),
    2,
    'REJECTED',
    NOW(),
    claimant.id,
    product_row.id
FROM users claimant
JOIN users owner ON owner.email = 'market2@example.com'
JOIN products product_row ON product_row.name = 'Milk' AND product_row.user_id = owner.id
WHERE claimant.email = 'ngo1@example.com'
  AND NOT EXISTS (
      SELECT 1
      FROM claims c
      WHERE c.claimant_id = claimant.id
        AND c.product_id = product_row.id
        AND c.requested_quantity = 2
        AND c.status = 'REJECTED'
  );

UPDATE products p
JOIN users u ON u.id = p.user_id
SET p.quantity = p.quantity - 3,
    p.updated_at = NOW()
WHERE p.name = 'Tomatoes'
  AND u.email = 'market1@example.com'
  AND EXISTS (
      SELECT 1
      FROM claims c
      JOIN users claimant ON claimant.id = c.claimant_id
      WHERE c.product_id = p.id
        AND claimant.email = 'ngo2@example.com'
        AND c.requested_quantity = 3
        AND c.status = 'APPROVED'
  );
