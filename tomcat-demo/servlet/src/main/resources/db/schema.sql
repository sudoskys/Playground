-- src/main/resources/db/schema.sql
CREATE TABLE "web_user"
(
    id       BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL
);