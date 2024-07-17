CREATE DATABASE securityspring;

\c securityspring;

CREATE TABLE IF NOT EXISTS tb_roles (
    id INT PRIMARY KEY,
    name VARCHAR(50)
);

WITH insert_data AS (
    SELECT 1 AS id, 'ADMIN' AS name
)
INSERT INTO tb_roles (id, name)
SELECT id, name
FROM insert_data
WHERE NOT EXISTS (SELECT 1 FROM tb_roles WHERE name = 'ADMIN');

WITH insert_data AS (
    SELECT 2 AS id, 'BASIC' AS name
)
INSERT INTO tb_roles (id, name)
SELECT id, name
FROM insert_data
WHERE NOT EXISTS (SELECT 2 FROM tb_roles WHERE name = 'BASIC');