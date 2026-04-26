INSERT INTO categories (name)
SELECT 'Bakery'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Bakery'
);

INSERT INTO categories (name)
SELECT 'Fruits'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Fruits'
);

INSERT INTO categories (name)
SELECT 'Vegetables'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Vegetables'
);

INSERT INTO categories (name)
SELECT 'Dairy'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Dairy'
);

INSERT INTO categories (name)
SELECT 'Dry Food'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Dry Food'
);

INSERT INTO categories (name)
SELECT 'Cooked Meal'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Cooked Meal'
);

INSERT INTO categories (name)
SELECT 'Beverages'
WHERE NOT EXISTS (
    SELECT 1 FROM categories WHERE name = 'Beverages'
);
