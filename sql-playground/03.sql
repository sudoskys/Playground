/*
[说明]

某竞赛管理系统的部分数据库关系模式如下:

选手:PLAYER ( Pno ,Pname,Sex,Region,Tel),各属性分别表示参赛选手的编号、姓名、性别、地区和联系电话;

竞赛项目:CONTEST(Cno,Cname,Type,Date),各属性分别表示竞赛项目的编号、名称、类别和举办日期;

选手参赛: Pc(Pno,Cno,City,Rank, Point),各属性分别表示选手编号、竞赛项目编号、竞赛所在城市、选手取得的名次和积分。

有关关系模式的说明如下:

(1)下划线标出的属性是表的主码。

(2)选手参赛表的属性 Pno 和 Cno 分别参照了选手表和竞赛项目表的主码。

(3)一个选手参加一项竞赛有一个名次和一个积分，名次有 4 个取值(“一”，“二”， “三”,无”)。另外，竞赛所在城市不能为空。

根据以上描述，回答下列问题，写SQL语句。

[问题 1]

书写创建选手参赛表 PC 的 SQL 语句，要求定义实体完整性约束、参照完整性约束，以及其他完整性约束。

[问题 2]

查询所有未参加"AI类别竞赛的选手，要求输出选手的编号(Pno)，查询结果按照选手编号的升序排列。

[问题 3]

由于某种原因，编号为TE06 的竞赛项目在正式举办前被取消了。而此前系统中已经记录了这些选手的报名参赛情况，因此需要在系统中删除 E06 的竞赛项目记录，以及该竞赛的所有报名参赛纪录。根据问题 1在选手参赛表 PC 上定义的参照完整性约束。
*/
CREATE DATABASE IF NOT EXISTS `test01` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `test01`;

-- 如果存在则删除外键约束
SET FOREIGN_KEY_CHECKS = 0;

-- 竞赛项目表
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest`
(
    `Cno`   varchar(10) NOT NULL COMMENT '竞赛项目编号',
    `Cname` varchar(20) NOT NULL COMMENT '竞赛项目名称',
    `Type`  varchar(10) NOT NULL COMMENT '竞赛项目类别',
    `Date`  date        NOT NULL COMMENT '竞赛项目举办日期',
    PRIMARY KEY (`Cno`)
) COMMENT '竞赛项目表';

-- 选手表
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player`
(
    `Pno`    varchar(10) NOT NULL COMMENT '选手编号',
    `Pname`  varchar(20) NOT NULL COMMENT '选手姓名',
    `Sex`    varchar(2)  NOT NULL COMMENT '选手性别',
    `Region` varchar(20) NOT NULL COMMENT '选手地区',
    `Tel`    varchar(20) NOT NULL COMMENT '选手联系电话',
    PRIMARY KEY (`Pno`)
) COMMENT '选手表';

-- 选手参赛表
DROP TABLE IF EXISTS `pc`;
CREATE TABLE `pc`
(
    `Pno`   varchar(10) NOT NULL COMMENT '选手编号',
    `Cno`   varchar(10) NOT NULL COMMENT '竞赛项目编号',
    `City`  varchar(20) NOT NULL COMMENT '竞赛所在城市',
    `Rank`  varchar(2)  NOT NULL COMMENT '选手取得的名次',
    `Point` int(11)     NOT NULL COMMENT '选手取得的积分',
    PRIMARY KEY (`Pno`, `Cno`),
    CONSTRAINT `FK_pc_1` FOREIGN KEY (`Pno`) REFERENCES `player` (`Pno`),
    CONSTRAINT `FK_pc_2` FOREIGN KEY (`Cno`) REFERENCES `contest` (`Cno`)
) COMMENT '选手参赛表';

-- 问题1解决展示
SHOW CREATE TABLE `pc`;

-- 清空数据
TRUNCATE TABLE `contest`;
TRUNCATE TABLE `player`;
TRUNCATE TABLE `pc`;

-- 插入测试数据
INSERT INTO `contest` (`Cno`, `Cname`, `Type`, `Date`)
VALUES ('E01', 'C语言程序设计竞赛', 'C', '2019-01-01'),
       ('E02', 'Java程序设计竞赛', 'Java', '2019-01-02'),
       ('E03', 'Python程序设计竞赛', 'Python', '2019-01-03'),
       ('E04', 'C++程序设计竞赛', 'C++', '2019-01-04'),
       ('E05', 'PHP程序设计竞赛', 'PHP', '2019-01-05'),
       ('E06', 'AI程序设计竞赛', 'AI', '2019-01-06');

INSERT INTO `player` (`Pno`, `Pname`, `Sex`, `Region`, `Tel`)
VALUES ('P01', '张三', '男', '北京', '13800138000'),
       ('P02', '李四', '男', '上海', '13800138001'),
       ('P03', '王五', '男', '广州', '13800138002'),
       ('P04', '赵六', '男', '深圳', '13800138003'),
       ('P05', '钱七', '男', '杭州', '13800138004'),
       ('P06', '孙八', '男', '南京', '13800138005'),
       ('P07', '周九', '男', '武汉', '13800138006'),
       ('P08', '吴十', '男', '成都', '13800138007'),
       ('P09', '郑十一', '男', '重庆', '13800138008'),
       ('P10', '王十二', '男', '西安', '13800138009');

INSERT INTO `pc` (`Pno`, `Cno`, `City`, `Rank`, `Point`)
VALUES ('P01', 'E01', '北京', '一', 100),
       ('P02', 'E01', '上海', '二', 90),
       ('P03', 'E06', '广州', '三', 80),
       ('P04', 'E01', '深圳', '无', 0),
       ('P05', 'E01', '杭州', '无', 0),
       ('P06', 'E06', '南京', '无', 0),
       ('P07', 'E06', '武汉', '无', 0),
       ('P08', 'E01', '成都', '无', 0),
       ('P08', 'E06', '成都', '无', 0);

-- 问题2: 查询所有未参加"AI类别竞赛的选手，要求输出选手的编号(Pno)，查询结果按照选手编号的升序排列。
SELECT `Pno`
FROM `player`
WHERE `Pno` NOT IN
      (SELECT `Pno`
       FROM `pc`
       WHERE `Cno` IN
             (SELECT `Cno`
              FROM `contest`
              WHERE `Type` = 'AI'))
ORDER BY `Pno` ASC;

-- 展示竞赛项目 E06 的数据
SELECT *
FROM `contest`;
-- 问题3: 删除竞赛项目编号为E06的竞赛项目记录，以及该竞赛的所有报名参赛纪录。
DELETE
FROM `contest`
WHERE `Cno` = 'E06';
DELETE
FROM `pc`
WHERE `Cno` = 'E06';
-- 展示删除后的数据
SELECT *
FROM `contest`;