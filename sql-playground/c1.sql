select *
from shop.sh_goods;

CREATE DATABASE test_sh_goods_2;
USE test_sh_goods_2;
CREATE TABLE sh_goods_attr
(
    id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '属性id',

    parent_id   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级属性id',

    category_id INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品分类id',

    NAME        VARCHAR(80)  NOT NULL DEFAULT '' COMMENT '属性名称',

    sort        INT          NOT NULL DEFAULT 0 COMMENT '排序值'

) COMMENT '商品属性表';

SELECT *
FROM sh_goods_attr;

CREATE TABLE sh_goods_attr_value
(

    id         INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '属性值id',

    goods_id   INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',

    attr_id    INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性id',

    attr_value VARCHAR(80)  NOT NULL DEFAULT '' COMMENT '属性值'

) COMMENT '商品属性值表';

SELECT *
FROM sh_goods_attr_value;
DESC sh_goods_attr_value;

INSERT INTO sh_goods_attr
VALUES (1, 0, 6, '基本信息', 0),
       (2, 1, 6, '机身颜色', 0),
       (3, 1, 6, '输入方式', 1),
       (4, 1, 6, '操作系统', 2),
       (5, 0, 6, '屏幕', 1),
       (6, 5, 6, '屏幕尺寸', 0),
       (7, 5, 6, '屏幕材质', 1),
       (8, 5, 6, '分辨率', 2),
       (9, 0, 6, '摄像头', 2),
       (10, 9, 6, '前置摄像头', 0),
       (11, 9, 6, '后置摄像头', 1),
       (12, 0, 6, '电池信息', 3),
       (13, 12, 6, '电池容量', 0),
       (14, 12, 6, '是否可拆卸', 1);

INSERT INTO sh_goods_attr_value
VALUES (1, 5, 2, '黑色'),
       (2, 5, 3, '触摸屏'),
       (3, 5, 4, 'Android'),
       (4, 5, 6, '5.5 寸'),
       (5, 5, 7, 'IPS'),
       (6, 5, 8, '1920*1080'),
       (7, 5, 10, '1600 万'),
       (8, 5, 11, '800 万'),
       (9, 5, 13, '3500mAh'),
       (10, 5, 14, '否');

SELECT *
FROM sh_goods_attr_value;
SELECT *
FROM sh_goods_attr;


# 1）查询sh_goods_attr表中category_id为6所对应的商品的属性信息，将属性信息按照层级升序排列。

SELECT *
FROM sh_goods_attr
WHERE category_id = 6
ORDER BY sort;

# 2）查询sh_goods_attr_value表中goods_id为5的商品所具有的属性信息，显示属性名称和属性值。
SELECT a.NAME,
       b.attr_value
FROM sh_goods_attr a
         JOIN sh_goods_attr_value b ON a.id = b.attr_id
WHERE b.goods_id = 5;

# 3）查询sh_goods_attr表中parent_id为1的属性包含的所有子属性值。

SELECT *
FROM sh_goods_attr
WHERE parent_id = 1;

# 4）查询拥有属性值个数大于1的商品的id和name。
SELECT a.id,
       a.NAME
FROM sh_goods_attr a
         JOIN sh_goods_attr_value b ON a.id = b.goods_id
GROUP BY a.id
HAVING COUNT(b.id) > 1;

# （1）创建shop用户，该用户可以通过IP地址为“192.168.1.%”范围内的客户端登录MySQL服务器，用户的初始密码为123456，并且将密码设置为登录后立即过期。

CREATE USER 'shop'@'192.168.1.%' IDENTIFIED BY '123456';
ALTER USER 'shop'@'192.168.1.%' PASSWORD EXPIRE;

# （2）将shop用户的密码重置为2c5-q8h。

ALTER USER 'shop'@'192.168.1.%' IDENTIFIED BY '2c5-q8h';

# （3）给shop用户授予查看sh_goods数据表的权限。

GRANT SELECT ON shop.sh_goods TO 'shop'@'192.168.1.%';

# （4）回收shop用户对sh_goods数据表的查询权限。

REVOKE SELECT ON shop.sh_goods FROM 'shop'@'192.168.1.%';
