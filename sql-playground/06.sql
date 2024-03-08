/*
某工程项目管理系统的部分数据库关系模式如下:

项目:PROJECT(Ino，Jname，City，Date)，各属性分别表示项目代码、项目名称、项目所在城市和项目开始日期；

零件:PART(Pno，Pname，Color，Sno，Stock) 各属性分别表示零件代码、零件名称、零件颜色、零件所在仓库代码及库存量;

供应情况:PJC(Pno，Jno，Qty)，各属性分别表示零件代码、项目代码、使用量；

仓库:STORE(Sno，Sname，Address)，各属性分别表示仓库代码、仓库名称、仓库地址。有关上述关系模式的说明如下:

(1)下划线标出的属性是表的主键。

(2)零件表的属性 Sno 参照了仓库表的主码。一种零件只存放在一个仓库，库存量最低为0。

(3)供应情况表的属性 Pno 和Jno 分别参照了零件表和项目表的主码。

根据以上描述，回答下列问题，设计 SQL 语句。
[问题 1]

请书写创建零件表 PART 的 SQL 语句，要求定义实体完整性约束、参照完整性约束，以及其他完整性约束。


[问题 2]

创建视图 PARTUSED，给出在项目中已使用零件的代码和使用量。书写视图的创建语句。


[问题 3]

在视图 PARTUSED 的基础上，查询所有零件的信息要求输出每种零件的零件代码、零件名、零件颜色和零件总量(使用量与库存量之和)，查询结果按照零件总量降序排列。书写 SQL 语句。


[问题 4]

由于某种原因，要拆除代码为’A006’的仓库，该仓库中的零件转入’A002’仓库存放。据此更新数据库的功书写 SQL语句。

*/
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `test`;

-- 设置外键检查
SET FOREIGN_KEY_CHECKS = 0;


-- 删除表
DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS PJC;
DROP TABLE IF EXISTS PART;
DROP TABLE IF EXISTS STORE;


-- 创建项目表
CREATE TABLE IF NOT EXISTS PROJECT
(
    Ino   VARCHAR(50) PRIMARY KEY, -- 项目代码
    Jname VARCHAR(100) NOT NULL,   -- 项目名称
    City  VARCHAR(50)  NOT NULL,   -- 项目所在城市
    Date  DATE         NOT NULL    -- 项目开始日期
);

-- 创建仓库表
CREATE TABLE IF NOT EXISTS STORE
(
    Sno     VARCHAR(50) PRIMARY KEY, -- 仓库代码
    Sname   VARCHAR(100) NOT NULL,   -- 仓库名称
    Address VARCHAR(100) NOT NULL    -- 仓库地址
);

-- 创建零件表
CREATE TABLE IF NOT EXISTS PART
(
    Pno   VARCHAR(50) PRIMARY KEY,           -- 零件代码
    Pname VARCHAR(100) NOT NULL,             -- 零件名称
    Color VARCHAR(50)  NOT NULL,             -- 零件颜色
    Sno   VARCHAR(50)  NOT NULL,             -- 零件所在仓库代码
    Stock INT CHECK (Stock >= 0),            -- 库存量
    FOREIGN KEY (Sno) REFERENCES STORE (Sno) -- 外键
);
-- 创建供应情况表
CREATE TABLE IF NOT EXISTS PJC
(
    Pno VARCHAR(50) NOT NULL,                  -- 零件代码
    Jno VARCHAR(50) NOT NULL,                  -- 项目代码
    Qty INT         NOT NULL,                  -- 使用量
    PRIMARY KEY (Pno, Jno),                    -- 主键
    FOREIGN KEY (Pno) REFERENCES PART (Pno),   -- 外键
    FOREIGN KEY (Jno) REFERENCES PROJECT (Ino) -- 外键
);

-- 创建视图 PARTUSED 用于显示零件代码和使用量

DROP VIEW IF EXISTS PARTUSED;
CREATE VIEW PARTUSED AS
SELECT Pno, SUM(Qty) AS Qty
FROM PJC
GROUP BY Pno;
-- 显示视图 PARTUSED 的数据
SELECT *
FROM PARTUSED;


-- 删除外键约束
ALTER TABLE PJC
    DROP FOREIGN KEY PJC_ibfk_2;
ALTER TABLE PJC
    DROP FOREIGN KEY PJC_ibfk_1;
ALTER TABLE PART
    DROP FOREIGN KEY PART_ibfk_1;

-- 清空 PROJECT 表
TRUNCATE TABLE PROJECT;
-- 添加测试数据
INSERT INTO PROJECT (Ino, Jname, City, Date)
VALUES ('A001', '项目1', '北京', '2020-01-01'),
       ('A002', '项目2', '上海', '2020-01-02'),
       ('A003', '项目3', '广州', '2020-01-03'),
       ('A004', '项目4', '深圳', '2020-01-04'),
       ('A005', '项目5', '杭州', '2020-01-05'),
       ('A006', '项目6', '成都', '2020-01-06');
-- 清空数据
TRUNCATE TABLE PART;
TRUNCATE TABLE STORE;
-- 添加测试数据
INSERT INTO STORE (Sno, Sname, Address)
VALUES ('A001', '仓库1', '北京'),
       ('A002', '仓库2', '上海'),
       ('A003', '仓库3', '广州'),
       ('A004', '仓库4', '深圳'),
       ('A005', '仓库5', '杭州'),
       ('A006', '仓库6', '成都');
INSERT INTO PART (Pno, Pname, Color, Sno, Stock)
VALUES ('A001', '零件1', '红色', 'A001', 10),
       ('A002', '零件2', '橙色', 'A002', 20),
       ('A003', '零件3', '黄色', 'A003', 30),
       ('A004', '零件4', '绿色', 'A004', 40),
       ('A005', '零件5', '青色', 'A005', 50),
       ('A006', '零件6', '蓝色', 'A006', 60);
-- 显示测试数据
SELECT *
FROM PART;
-- 添加外键约束
ALTER TABLE PJC
    ADD CONSTRAINT PJC_ibfk_2 FOREIGN KEY (Jno) REFERENCES PROJECT (Ino);
ALTER TABLE PJC
    ADD CONSTRAINT PJC_ibfk_1 FOREIGN KEY (Pno) REFERENCES PART (Pno);
ALTER TABLE PART
    ADD CONSTRAINT PART_ibfk_1 FOREIGN KEY (Sno) REFERENCES STORE (Sno);

-- 查询所有零件的信息要求输出每种零件的零件代码、零件名、零件颜色和零件总量(使用量与库存量之和)，查询结果按照零件总量降序排列
SELECT P.Pno, P.Pname, P.Color, P.Stock + IFNULL(PU.Qty, 0) AS Total
FROM PART P
         LEFT JOIN PARTUSED PU ON P.Pno = PU.Pno
ORDER BY Total DESC;

-- 由于某种原因，要拆除代码为’A006’的仓库，该仓库中的零件转入’A002’仓库存放。据此更新数据库的功书写 SQL语句。
-- 查询修改前的数据
SELECT *
FROM PART;
-- 由于外键约束，不能直接修改，需要先删除外键约束
ALTER TABLE PART
    DROP FOREIGN KEY PART_ibfk_1;
-- 修改带外键的表
UPDATE PART
SET Sno = 'A002'
WHERE Sno = 'A006';
-- 添加外键约束
ALTER TABLE PART
    ADD CONSTRAINT PART_ibfk_1 FOREIGN KEY (Sno) REFERENCES STORE (Sno);
-- 查询修改后的数据
SELECT *
FROM PART;
