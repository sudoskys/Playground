/*
[说明]

某公司要对其投放的自动售货机建立商品管理系统，其数据库的部分关系模式如下:

售货机:VEM (VEMno， Location) ，各属性分别表示售货机编号、部署地点;

商品:GOODS(Gno， Brand， Price) ，各属性分别表示商品编号、品牌名和价格;

销售单:SALES(Sno， VEMno，Gno，SDate，STime)，各属性分别表示销售号、售货机编号、商品编号、日期和时间。

缺货单:OOS(VEMno，Gno，SDate，STime )，各属性分别表示售货机编号、商品编号、 日期和时间。

相关关系模式的属性及说明如下: (1)售货机摆放固定种类的商品，售货机内每种商品最多可以储存 10 件。管理员在每天结束的时候将售货机中所有售出商品补全

(2)每售出一件商品，就自动向销售单中添加一条销售记录。如果一天内某个售货机上某种商品的销售记录达到 10 条，则表明该售货机上该商品已售完，需要通知系统立即补货，通过自动向缺货单中添加一条缺货记录来实现。

根据以上描述，回答下列问题，设计SQL语句。

[问题1]

书写创建销售单表的 SQL 语句，要求指定关系的主码和外码约束。


[问题2]

创建销售记录详单视图 SALES_Detail ，要求按日期统计每个售货机上各种商品的销售数量, 属性有 VEMno、Location 、Gno、Brand 、Price 、amount 和 SDate。为方便实现，首先建立一个视图 SALES_Total ，然后利用 SALES_Total 完成视图 SALES_Detail 的定义。


[问题3]

每售出一件商品，就自动向销售单中添加一条销售记录。如果一天内某个售货机上某种商品的销售记录达到 10 条，则自动向缺货单中添加一条缺货记录。需要用触发器来实现缺货单的自动维护。程序中的 GetTime ()获取当前时间。


[问题4]

查询当天销售最多的商品编号、品牌和数量。程序中的 GetDate()获取当天日期SELECT GOODS.Gno ，Brand，(k)FROM


[问题5]

查询一件都没有售出的所有商品编号和品牌。
*/
CREATE
    DATABASE IF NOT EXISTS `test05` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE
    `test05`;

-- 如果存在则删除外键约束
SET
    FOREIGN_KEY_CHECKS = 0;

-- 如果存在则删除表
DROP TABLE IF EXISTS `VEM`;
CREATE TABLE `VEM`
(
    `VEMno`    int(11) NOT NULL COMMENT '售货机编号',
    `Location` varchar(255) DEFAULT NULL COMMENT '部署地点',
    PRIMARY KEY (`VEMno`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='售货机';

DROP TABLE IF EXISTS `GOODS`;
CREATE TABLE `GOODS`
(
    `Gno`   int(11) NOT NULL COMMENT '商品编号',
    `Brand` varchar(255) DEFAULT NULL COMMENT '品牌名',
    `Price` int(11)      DEFAULT NULL COMMENT '价格',
    PRIMARY KEY (`Gno`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='商品';

-- 问题1 ：书写创建销售单表的 SQL 语句，要求指定关系的主码和外码约束。
DROP TABLE IF EXISTS `SALES`;
CREATE TABLE `SALES`
(
    `Sno`   int(11) NOT NULL COMMENT '销售号',
    `VEMno` int(11) DEFAULT NULL COMMENT '售货机编号',
    `Gno`   int(11) DEFAULT NULL COMMENT '商品编号',
    `SDate` date    DEFAULT NULL COMMENT '日期',
    `STime` time    DEFAULT NULL COMMENT '时间',
    PRIMARY KEY (`Sno`),                                                     -- 主键约束
    KEY `VEMno` (`VEMno`),                                                   -- 索引
    KEY `Gno` (`Gno`),                                                       -- 索引
    CONSTRAINT `SALES_ibfk_1` FOREIGN KEY (`VEMno`) REFERENCES `VEM` (`VEMno`),
    CONSTRAINT `SALES_ibfk_2` FOREIGN KEY (`Gno`) REFERENCES `GOODS` (`Gno`) -- 外键约束
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='销售单';
-- 问题1：展示表结构
SHOW
    COLUMNS FROM `SALES`;


DROP TABLE IF EXISTS `OOS`;
CREATE TABLE `OOS`
(
    `VEMno` int(11) NOT NULL COMMENT '售货机编号',
    `Gno`   int(11) COMMENT '商品编号',
    `SDate` date COMMENT '日期',
    `STime` time COMMENT '时间',
    PRIMARY KEY (`VEMno`, `Gno`, `SDate`), -- 主键约束
    KEY `Gno` (`Gno`),                     -- 用于查询
    CONSTRAINT `OOS_ibfk_1` FOREIGN KEY (`VEMno`) REFERENCES `VEM` (`VEMno`),
    CONSTRAINT `OOS_ibfk_2` FOREIGN KEY (`Gno`) REFERENCES `GOODS` (`Gno`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='缺货单';


-- 添加测试数据
INSERT INTO `VEM` (`VEMno`, `Location`)
VALUES (1, '北京'),
       (2, '上海'),
       (3, '广州'),
       (4, '深圳'),
       (5, '杭州'),
       (6, '南京');

INSERT INTO `GOODS` (`Gno`, `Brand`, `Price`)
VALUES (1, '可乐', 3),
       (2, '雪碧', 3),
       (3, '芬达', 3),
       (4, '红牛', 5),
       (5, '怡宝', 2),
       (6, '农夫山泉', 2);

-- 添加 10 条销售记录
INSERT INTO `SALES` (`Sno`, `VEMno`, `Gno`, `SDate`, `STime`)
VALUES (1, 1, 1, '2020-01-01', '12:00:00'),
       (2, 1, 1, '2020-01-01', '12:00:00'),
       (3, 1, 1, '2020-01-01', '12:00:00'),
       (4, 1, 1, '2020-01-01', '12:00:00'),
       (5, 1, 1, '2023-12-17', '12:00:00'),
       (6, 1, 1, '2020-01-01', '12:00:00');

-- 添加 10 条缺货记录
INSERT INTO `OOS` (`VEMno`, `Gno`, `SDate`, `STime`)
VALUES (1, 1, '2020-01-01', '12:00:00'),
       (2, 1, '2020-01-01', '12:00:00'),
       (3, 1, '2020-01-01', '12:00:00'),
       (4, 1, '2020-01-01', '12:00:00'),
       (5, 1, '2020-01-01', '12:00:00'),
       (6, 1, '2020-01-01', '12:00:00');


-- 问题2 ：创建销售记录详单视图 SALES_Detail ，要求按日期统计每个售货机上各种商品的销售数量, 属性有 VEMno、Location 、Gno、Brand 、Price 、amount 和 SDate。为方便实现，首先建立一个视图 SALES_Total ，然后利用 SALES_Total 完成视图 SALES_Detail 的定义。
DROP VIEW IF EXISTS `SALES_Detail`;
DROP VIEW IF EXISTS `SALES_Total`;

CREATE VIEW `SALES_Total` AS
SELECT `VEMno`, `Gno`, `SDate`, COUNT(*) AS `amount`
FROM `SALES`
GROUP BY `VEMno`, `Gno`, `SDate`;

CREATE VIEW `SALES_Detail` AS
SELECT `SALES_Total`.`VEMno`,
       `VEM`.`Location`,
       `SALES_Total`.`Gno`,
       `GOODS`.`Brand`,
       `GOODS`.`Price`,
       `SALES_Total`.`amount`,
       `SALES_Total`.`SDate`
FROM `SALES_Total`
         LEFT JOIN `VEM` ON `SALES_Total`.`VEMno` = `VEM`.`VEMno`
         LEFT JOIN `GOODS` ON `SALES_Total`.`Gno` = `GOODS`.`Gno`;

-- 问题2展示视图结构
SHOW
    COLUMNS FROM `SALES_Detail`;

-- 问题3 ：每售出一件商品，就自动向销售单中添加一条销售记录。如果一天内某个售货机上某种商品的销售记录达到 10 条，则自动向缺货单中添加一条缺货记录。需要用触发器来实现缺货单的自动维护。程序中的 GetTime ()获取当前时间。
-- GetTime
DROP FUNCTION IF EXISTS `GetTime`;
CREATE FUNCTION `GetTime`()
    RETURNS time
    LANGUAGE SQL
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
    RETURN CURTIME();
END;

-- GetDate

DROP FUNCTION IF EXISTS `GetDate`;
CREATE FUNCTION `GetDate`()
    RETURNS date
    LANGUAGE SQL
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
    RETURN CURDATE();
END;

-- Trigger

DROP TRIGGER IF EXISTS `SALES_INSERT`;
DROP TRIGGER IF EXISTS `SALES_UPDATE`;

DELIMITER
$$
CREATE TRIGGER `SALES_INSERT`
    AFTER INSERT
    ON `SALES`
    FOR EACH ROW

BEGIN
    DECLARE count INT;
    SELECT COUNT(*)
    INTO count
    FROM `SALES`
    WHERE `VEMno` = NEW.`VEMno`
      AND `Gno` = NEW.`Gno`
      AND `SDate` = NEW.`SDate`;
    IF count = 10 THEN
        INSERT INTO `OOS` (`VEMno`, `Gno`, `SDate`, `STime`)
        VALUES (NEW.`VEMno`, NEW.`Gno`, NEW.`SDate`, GetTime());
    END IF;
END$$
DELIMITER ;

DELIMITER
$$
CREATE TRIGGER `SALES_UPDATE`
    AFTER UPDATE
    ON `SALES`
    FOR EACH ROW
BEGIN
    DECLARE count INT;
    SELECT COUNT(*)
    INTO count
    FROM `SALES`
    WHERE `VEMno` = NEW.`VEMno`
      AND `Gno` = NEW.`Gno`
      AND `SDate` = NEW.`SDate`;
    IF count = 10 THEN
        INSERT INTO `OOS` (`VEMno`, `Gno`, `SDate`, `STime`)
        VALUES (NEW.`VEMno`, NEW.`Gno`, NEW.`SDate`, GetTime());
    END IF;
END$$

DELIMITER ;

-- 问题4 ：查询当天销售最多的商品编号、品牌和数量。程序中的 GetDate()获取当天日期SELECT GOODS.Gno ，Brand，(k)FROM
SELECT `GOODS`.`Gno`, `GOODS`.`Brand`, `SALES_Total`.`amount`
FROM `SALES_Total`
         LEFT JOIN `GOODS` ON `SALES_Total`.`Gno` = `GOODS`.`Gno`
WHERE `SALES_Total`.`SDate` = GetDate()
ORDER BY `SALES_Total`.`amount` DESC
LIMIT 1;

-- 问题5 ：查询一件都没有售出的所有商品编号和品牌。
SELECT `GOODS`.`Gno`, `GOODS`.`Brand`
FROM `GOODS`
WHERE `GOODS`.`Gno` NOT IN (SELECT `Gno` FROM `SALES`);

