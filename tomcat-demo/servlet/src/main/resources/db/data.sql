-- src/main/resources/db/data.sql
CREATE TABLE "web_user"
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email    VARCHAR(50) NOT NULL,
    role     VARCHAR(20) NOT NULL
);

INSERT INTO "web_user" (id, username, password, email, role)
VALUES (1, 'admin', 'admin', 'admin@localhost', 'ADMIN');

