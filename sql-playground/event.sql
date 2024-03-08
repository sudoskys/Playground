# 创建数据库
DROP DATABASE shop;
CREATE DATABASE shop;
# 选择数据库
USE shop;
DROP TABLE sh_goods_category;
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

#添加测试数据
INSERT INTO sh_goods_category (id, parent_id, NAME)
VALUES (1, 0, '办公'),
       (2, 1, '耗材'),
       (3, 2, '文具'),
       (4, 0, '电子产品'),
       (5, 4, '通讯'),
       (6, 5, '手机'),
       (7, 4, '影音'),
       (8, 7, '音箱'),
       (9, 7, '耳机'),
       (10, 4, '计算机'),
       (11, 10, '台式计算机'),
       (12, 10, '笔记本计算机'),
       (13, 0, '服装'),
       (14, 13, '女装'),
       (15, 14, '风衣'),
       (16, 14, '毛衣');
#创建商品表
CREATE TABLE sh_goods
(
    id               INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '商品id',
    category_id      INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '分类id',
    spu_id           INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT 'SPU id',
    sn               VARCHAR(20)      NOT NULL DEFAULT '' COMMENT '编号',
    NAME             VARCHAR(120)     NOT NULL DEFAULT '' COMMENT '商品名称',
    keyword          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '关键词',
    picture          VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '图片',
    DESCRIPTION      VARCHAR(255)     NOT NULL DEFAULT '' COMMENT '描述',
    content          TEXT             NOT NULL COMMENT '详情',
    score            DECIMAL(3, 2)    NOT NULL DEFAULT 0 COMMENT '评分',
    price            DECIMAL(10, 2)   NOT NULL DEFAULT 0 COMMENT '价格',
    stock            INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '库存',
    is_on_sale       TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否上架',
    is_del           TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
    is_free_shipping TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否包邮',
    sell_count       INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '销量计数',
    comment_count    INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '评论计数',
    on_sale_time     DATETIME                  DEFAULT NULL COMMENT '上架时间',
    create_time      DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time      DATETIME                  DEFAULT NULL COMMENT '更新时间'
) COMMENT '商品表';
DESC sh_goods;
#添加测试数据
INSERT INTO sh_goods (id, category_id, NAME, keyword, content, price, stock, score, comment_count)
VALUES (1, 3, '2H铅笔S30804', '办公', '考试专用', 0.5, 500, 4.9, 40000),
       (2, 3, '钢笔T1616', '办公', '练字必不可少', 15, 300, 3.9, 500),
       (3, 3, '碳素笔GP1008', '办公', '平时使用', 1, 500, 5, 98000),
       (4, 12, '超薄笔记本Pro12', '电子产品', '轻小便携', 5999, 0, 2.5, 200),
       (5, 6, '华为P50智能手机', '电子产品', '人人必备', 1999, 0, 5, 98000),
       (6, 8, '桌面音箱BMS10', '电子产品', '扩音装备', 69, 750, 4.5, 1000),
       (7, 9, '头戴耳机Star Y360', '电子产品', '独享个人世界', 109, 0, 3.9, 500),
       (8, 11, '办公计算机 天逸510Pro', '电子产品', '适合办公', 2000, 0, 4.8, 6000),
       (9, 15, '收腰风衣中长款', '服装', '春节潮流单品', 299, 0, 4.9, 40000),
       (10, 16, '薄毛衣联名款', '服装', '居家旅行必备', 48, 0, 4.8, 98000);
SELECT *
FROM sh_goods;

# 创建视图
CREATE VIEW goods_with_categories AS
SELECT g.id,
       g.NAME,
       g.price,
       g.stock,
       g.comment_count,
       g.score,
       g.on_sale_time,
       gc.NAME AS category_name
FROM sh_goods g
         LEFT JOIN sh_goods_category gc ON g.category_id = gc.id;

# 删除视图
DROP VIEW goods_with_categories;

# 查看视图
SHOW CREATE VIEW goods_with_categories;

# 使用视图
INSERT INTO goods_with_categories (id, NAME, price, stock, comment_count, score, on_sale_time, category_name)
VALUES (11, '办公计算机 天逸510Pro', 2000, 0, 6000, 4.8, NULL, '台式计算机'),
       (20, '办公计算机 天逸510Pro', 2000, 0, 6000, 4.8, NULL, '台式计算机');

SELECT *
FROM goods_with_categories;


# 修改视图
    ALTER VIEW goods_with_categories AS
    SELECT id
    FROM sh_goods;

SELECT *
FROM goods_with_categories;