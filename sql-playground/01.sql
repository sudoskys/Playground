-- 创建选手表 'PLAYER'
CREATE TABLE PLAYER (
    Pno VARCHAR(255) NOT NULL PRIMARY KEY,
    Pname VARCHAR(255),
    Sex CHAR(1),
    Region VARCHAR(255),
    Tel VARCHAR(20)
);

-- 创建竞赛表 'CONTEST'
CREATE TABLE CONTEST (
    Cno VARCHAR(255) NOT NULL PRIMARY KEY,
    Cname VARCHAR(255),
    Type VARCHAR(255),
    Date DATE
);

-- 创建选手参赛表 'Pc'
CREATE TABLE Pc
(
    Pno VARCHAR(255) NOT NULL,
    Cno VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    Rank ENUM('一', '二', '三', '无') NOT NULL,
    Point INT,
    PRIMARY KEY (Pno, Cno),
    FOREIGN KEY (Pno) REFERENCES PLAYER(Pno),
    FOREIGN KEY (Cno) REFERENCES CONTEST(Cno)
);

-- 查询未参加"AI类别竞赛"的选手
SELECT P.Pno
FROM PLAYER P
WHERE P.Pno NOT IN
(
    SELECT Pc.Pno
    FROM Pc
    JOIN CONTEST C ON Pc.Cno = C.Cno
    WHERE C.Type = 'AI类别竞赛'
)
ORDER BY P.Pno ASC;

-- 删除编号为 'TE06' 的竞赛项目以及相关的选手参赛记录
DELETE FROM CONTEST WHERE Cno = 'TE06';
