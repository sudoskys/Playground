/*
部署 PostgreSQL 数据库

docker run --name myPostgresDb -p 5432:5432 -e POSTGRES_USER=postgresUser -e POSTGRES_PASSWORD=postgresPW -e POSTGRES_DB=postgresDB -d postgres

*/
/*
内部配置参考

jdbcUrl=jdbc:postgresql://localhost:5432/postgres
username=postgresUser
password=postgresPW
*/

-- 创建数据库
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100)       NOT NULL,
    score    INT DEFAULT 0
);

-- 插入一些测试数据
INSERT INTO users (username, password, score)
VALUES ('张三', 'zhangsan', 100);
INSERT INTO users (username, password, score)
VALUES ('李四', 'lisi', 200);
INSERT INTO users (username, password, score)
VALUES ('王五', 'wangwu', 300);
INSERT INTO users (username, password, score)
VALUES ('赵六', 'zhaoliu', 400);

-- 查询所有用户
SELECT *
FROM users;
