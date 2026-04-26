USE mydb;

-- Shows all tables in the database.
SHOW TABLES;

-- Checks product columns.
DESCRIBE products;

-- Checks claim columns.
DESCRIBE claims;

-- Checks user columns.
DESCRIBE users;

-- Checks category columns.
DESCRIBE categories;

-- Shows all foreign key relationships.
SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'mydb'
  AND REFERENCED_TABLE_NAME IS NOT NULL;

-- Checks if old foreign key column names still exist.
SELECT 
    TABLE_NAME,
    COLUMN_NAME
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'mydb'
  AND COLUMN_NAME IN ('users_id', 'products_id', 'categories_id');