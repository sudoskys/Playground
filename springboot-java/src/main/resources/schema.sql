-- postgresql
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

-- 创建一个检查约束来限制 role 的值
ALTER TABLE users ADD CONSTRAINT check_role CHECK (role IN ('USER', 'ADMIN'));
