/*
[说明]

某订单管理系统的部分数据库关系模式如下:

客户: CUSTOMERS (Cno，Cname,Cage,Csex)，各属性分别表示客户编号、客户姓名、年龄和性别；

商品:GOODS(Gno，Gname，Gprice，Gorigin)，各属性分别表示商品编号、商品名称、单价和产地;

订单:ORDERS(Ono,Cno,Gno,Oprice,Onumbcr)，各属性分别表示订单编号、客户编号、商品编号、顾客购买商品的单价和数量。

有关关系模式的说明如下:

(1)下划线标出的属性是表的主键

(2)商品表中的Gprice是商品的当前价格，可能会发生变动；订单表中的Oprice 是订单成交时的商品单价。

(3)一个订单只包含一位顾客购买的一种商品；其商品数量至少1件，最多99件。


根据以上描述，回答下列问题，并完成SQL语句设计。

[问题1]

请将书写创建订单表的 SQL 语句，要求定义实体完整性约束、参照完整性约束，以及其他完整性约束。


[问题2]

查询所有订单的详细情况，要求输出订单号(Ono)、客户姓名(Cname)、商品名称(Gname)、单价(Oprice)、数量(Onumber)和金额(Oamount)，查询结果按照金额从大到小排列。书写 SQL语句。


[问题3]

创建已售商品信息视图，给出已售商品的编号(Gno), 名称(Gname)、订单个数(Onum)及平均每单的商品数量(GAnum)。书写创建视图SQL语句。


[问题4]

查询未售出商品的编号和名称。书写SQL语句。
*/

CREATE DATABASE IF NOT EXISTS `test01` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `test01`;

-- 如果存在则删除外键约束
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------

-- 创建客户表
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`
(
    `Cno`   int(11)     NOT NULL AUTO_INCREMENT COMMENT '客户编号',
    `Cname` varchar(20) NOT NULL COMMENT '客户姓名',
    `Cage`  int(11)     NOT NULL COMMENT '年龄',
    `Csex`  varchar(10) NOT NULL COMMENT '性别',
    PRIMARY KEY (`Cno`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- 创建商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `Gno`     int(11)        NOT NULL AUTO_INCREMENT COMMENT '商品编号',
    `Gname`   varchar(20)    NOT NULL COMMENT '商品名称',
    `Gprice`  decimal(10, 2) NOT NULL COMMENT '单价',
    `Gorigin` varchar(20)    NOT NULL COMMENT '产地',
    PRIMARY KEY (`Gno`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- 创建订单表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `Ono`     int(11)        NOT NULL AUTO_INCREMENT COMMENT '订单编号',
    `Cno`     int(11)        NOT NULL COMMENT '客户编号',
    `Gno`     int(11)        NOT NULL COMMENT '商品编号',
    `Oprice`  decimal(10, 2) NOT NULL COMMENT '单价',
    `Onumber` int(11)        NOT NULL COMMENT '数量',
    PRIMARY KEY (`Ono`),
    KEY `Cno` (`Cno`),
    KEY `Gno` (`Gno`),
    CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Cno`) REFERENCES `customers` (`Cno`),
    CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`Gno`) REFERENCES `goods` (`Gno`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- 插入数据
INSERT INTO `customers`
VALUES ('1', '张三', '20', '男');
INSERT INTO `customers`
VALUES ('2', '李四', '21', '男');


INSERT INTO `goods`
VALUES ('1', '苹果', '5.00', '山东');
INSERT INTO `goods`
VALUES ('2', '香蕉', '3.00', '海南');
INSERT INTO `goods`
VALUES ('3', '橘子', '4.00', '江西');

-- 问题2
SELECT o.Ono,
       c.Cname,
       g.Gname,
       o.Oprice,
       o.Onumber,
       o.Oprice * o.Onumber AS Oamount
FROM orders o
         INNER JOIN
     customers c ON o.Cno = c.Cno
         INNER JOIN
     goods g ON o.Gno = g.Gno
ORDER BY Oamount DESC;

-- 问题3: 创建已售商品信息视图
CREATE VIEW `sold_goods` AS
SELECT o.Gno,
       g.Gname,
       COUNT(o.Ono)   AS Onum,
       AVG(o.Onumber) AS GAnum
FROM orders o
         INNER JOIN
     goods g ON o.Gno = g.Gno
GROUP BY o.Gno;


-- 问题4: 查询未售出商品的编号和名称
SELECT g.Gno,
       g.Gname
FROM goods g
         LEFT JOIN
     orders o ON g.Gno = o.Gno
WHERE o.Gno IS NULL;