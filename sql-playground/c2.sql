# 创建数据库
START TRANSACTION;
DROP DATABASE shop;
CREATE DATABASE shop;
COMMIT;

# 选择数据库
USE shop;

# 创建用户表
START TRANSACTION;
START TRANSACTION WITH CONSISTENT SNAPSHOT;
DROP TABLE sh_goods_category;
# 保存事务点
SAVEPOINT savepoint1;
#创建商品分类表
CREATE TABLE sh_goods_category
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '分类id',
    parent_id   INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '上级分类id',
    NAME        VARCHAR(100)              DEFAULT '' COMMENT '分类名称',
    sort        INT              NOT NULL DEFAULT 0 COMMENT '排序值',
    is_show     TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否显示',
    create_time DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME                  DEFAULT NULL COMMENT '更新时间'
) COMMENT '商品分类表';
COMMIT;

START TRANSACTION;
#添加测试数据
INSERT INTO sh_goods_category (id, parent_id, NAME)
VALUES (1, 0, '办公'),
       (2, 1, '耗材');
SAVEPOINT savepoint2;
INSERT INTO sh_goods_category (id, parent_id, NAME)
VALUES (3, 2, '墨盒'),
       ('ERROR', 2, '墨盒');
ROLLBACK TO SAVEPOINT savepoint2;
ROLLBACK;

SELECT *
FROM sh_goods_category;

START TRANSACTION;
#添加测试数据
INSERT INTO sh_goods_category (id, parent_id, NAME)
VALUES (6, 2, '糖葫芦'),
       (7, 2, '星巴克');
ROLLBACK;
# 此时的数据被回滚了，所以没有数据
COMMIT; # 即使提交了事务，也没有数据
SELECT *
FROM sh_goods_category;

SET AUTOCOMMIT = 0;
# 关闭自动提交
START TRANSACTION;
START TRANSACTION;
INSERT INTO sh_goods_category (id, parent_id, NAME)
VALUES (9, 2, '糖葫芦'),
       (10, 2, '星巴克');
ROLLBACK;


# 事务隔离级别
SELECT @@transaction_isolation;

# 全局事务隔离级别
SELECT @@global.transaction_isolation;

# 设置全局事务隔离级别
SET GLOBAL transaction_isolation = 'READ-COMMITTED';
# 设置当前会话事务隔离级别
SET SESSION transaction_isolation = 'READ-COMMITTED';
# 查看当前事务的访问模式
SHOW VARIABLES LIKE 'transaction_read_only';


#####
# 测试脏读

CREATE TABLE sh_goods
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '商品id',
    category_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '分类id',
    NAME        VARCHAR(100)          DEFAULT '' COMMENT '商品名称',
    stock       INT          NOT NULL DEFAULT 0 COMMENT '库存',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME              DEFAULT NULL COMMENT '更新时间'
) COMMENT '商品表';

INSERT INTO sh_goods (id, category_id, NAME, stock)
VALUES (1, 1, '墨盒', 100);

SELECT *
FROM sh_goods;

-- 事务A
START TRANSACTION;
UPDATE sh_goods
SET stock = 200
WHERE id = 1;
-- 此时不提交事务

-- 事务B
START TRANSACTION;
SELECT stock
FROM sh_goods
WHERE id = 1;
COMMIT;
-- 事务B读取到的库存数量是200，这是脏数据
-- 事务A
ROLLBACK;
-- 事务A回滚，库存数量恢复为100
SELECT *
FROM sh_goods;


####
# 幻读

CREATE TABLE sh_orders
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '订单id',
    status      VARCHAR(20) NOT NULL DEFAULT '' COMMENT '订单状态',
    create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME             DEFAULT NULL COMMENT '更新时间'
) COMMENT '订单表';

-- 事务A
SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
START TRANSACTION;
SELECT COUNT(*)
FROM sh_orders
WHERE status = '已支付';
-- 此时不提交事务

-- 事务B
START TRANSACTION;
INSERT INTO sh_orders (status)
VALUES ('已支付');
COMMIT;
-- 事务B插入了一个新的已支付订单

-- 事务A
SELECT COUNT(*)
FROM sh_orders
WHERE status = '已支付';
COMMIT;
-- 事务A再次统计，发现数量增加了，这是幻读

-- 避免脏读
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- 避免幻读
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;