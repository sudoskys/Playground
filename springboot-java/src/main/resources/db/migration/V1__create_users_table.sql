-- src/main/resources/db/migration/V1__create_users_table.sql
-- postgresql
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20)  NOT NULL DEFAULT 'USER'
);

-- Create a check constraint to limit the values of role
ALTER TABLE users
    ADD CONSTRAINT check_role CHECK (role IN ('USER', 'ADMIN'));