/**创建数据库  操作表DDL*/  
CREATE DATABASE chao;
/**切换数据库*/
USE chao;
/**创建表*/
CREATE TABLE USER(id INT PRIMARY KEY,NAME VARCHAR(20),phone VARCHAR(20));
CREATE TABLE USER(id INT ,NAME VARCHAR(20),phone VARCHAR(20),PRIMARY KEY(id));
/*添加列*/
ALTER TABLE USER ADD image BLOB;
/*删除列*/
ALTER TABLE users DROP image;
/**改变字段*/
ALTER TABLE USER CHANGE username NAMES VARCHAR(22);
/**修改表名*/
RENAME TABLE USER TO users;
/**插入表数据   操作表数据DML*/
INSERT INTO users(NAMES,phone) VALUE('chao','13594347817');
/**修改表数据*/
UPDATE users SET NAMES='chaochao',phone='18090445628' WHERE id=1;
/**删除表数据*/
DELETE FROM users WHERE id=1;
/*查看表字段*/
DESC users;
-- 插入表数据(全值)
INSERT INTO users VALUE(3,'chao','13594347817');
-- 设置指定列属性值
UPDATE users SET age=20;
UPDATE users SET age=22,phone=13594347817 WHERE NAMES='chao' AND id=2;
-- 删除表中指定记录
DELETE FROM users WHERE NAMES='chao';
-- 删除所有记录,有日志，可恢复
DELETE FROM users;
-- 删除表再生成表，清除，无日志
TRUNCATE TABLE users;
-- 操作表数据DQL  数据查询语言 不会对数据有影响，仅查询。
SELECT * FROM users;
SELECT * FROM users WHERE NAMES='chao' AND id>0;
SELECT * FROM users WHERE age=20 OR id=2;
SELECT * FROM users WHERE NAMES IN('chao','chaochao');
SELECT * FROM users WHERE NAMES NOT IN('chaochao');
SELECT * FROM users WHERE age IS NULL;
SELECT * FROM users WHERE age IS NOT NULL;
SELECT * FROM users WHERE age BETWEEN 20 AND 22;
SELECT * FROM users WHERE age != 22;
SELECT * FROM users WHERE age <> 22;
SELECT * FROM users WHERE NAMES LIKE '____';
SELECT * FROM users WHERE NAMES LIKE '%c%c%';
SELECT COUNT(*) FROM users;
SELECT COUNT(age) FROM users WHERE age>20;
-- MAX最大 MIN最小 SUM 求和 AVG平均值
SELECT MAX(age) FROM users;
SELECT COUNT(*) FROM users WHERE (age+IFNULL(age,0))>40;
SELECT COUNT(NAMES),COUNT(age) FROM users;
SELECT SUM(NAMES)+SUM(age) FROM users;
SELECT SUM(NAMES+IFNULL(age,0)) FROM users;
SELECT MAX(age),MIN(age) FROM users;
-- 分组
SELECT COUNT(*) FROM users WHERE age IS NOT NULL GROUP BY age;
SELECT age,COUNT(*) FROM users WHERE age IS NOT NULL GROUP BY age;
SELECT age,COUNT(*) FROM users WHERE age >20 GROUP BY age;
SELECT age,SUM(age) FROM users  GROUP BY age HAVING SUM(age)>40;
-- 去除重复
SELECT DISTINCT NAMES,age FROM users;
-- 起别名  as可省略
SELECT NAMES AS 姓名,age AS 年龄 FROM users;
-- 排序
SELECT * FROM users ORDER BY age ASC;
SELECT * FROM users ORDER BY age DESC;
SELECT * FROM users ORDER BY age DESC,id DESC;
-- 分页 LIMIT  开始行，查询行数
SELECT * FROM users LIMIT 1,1;
-- 关系表
CREATE TABLE USER(
  id INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR (20) NOT	NULL,
  phone VARCHAR (20)
) ;
 -- CONSTRAINT sid FOREIGN KEY(id) REFERENCES USER(id)
CREATE TABLE likes(
  id INT PRIMARY KEY,
  sex VARCHAR (20),
  likes VARCHAR (20)
) ;
DROP TABLE likes;
-- 去除重复
SELECT * FROM USER UNION  SELECT * FROM users;
SELECT * FROM USER UNION  SELECT id,NAMES,phone FROM users;
SELECT * FROM USER UNION ALL  SELECT id,NAMES,phone FROM users;
-- 多表查询
SELECT * FROM USER,users WHERE user.id=users.id;
-- 内连接查询
SELECT u.`names`,s.`names`,u.`phone`,s.`phone` FROM USER u JOIN users s ON u.id=s.id WHERE s.`age`>20;
SELECT u.`names`,s.`names`,u.`phone`,s.`phone` FROM USER u,users s WHERE u.id=s.id AND age>20;
-- 外连接查询  左外连接
SELECT u.`names`,s.`names`,u.`phone`,s.`phone` FROM USER u LEFT JOIN users s ON u.id=s.id WHERE s.`age`>20;
-- 外连接查询  右外连接
SELECT u.`names`,s.`names`,u.`phone`,s.`phone` FROM USER u RIGHT JOIN users s ON u.id=s.id WHERE s.`age`>20;
-- 自然连接 自动找几张表相同的数据
SELECT * FROM USER NATURAL JOIN users;
-- 子查询，嵌套查询
SELECT * FROM users WHERE NAMES=(SELECT NAMES FROM USER WHERE id=3);
SELECT * FROM users WHERE (NAMES,phone) IN (SELECT NAMES,phone FROM USER WHERE id=3);
-- 自连接查询  自己连接自己
SELECT s.`stuid`,s.`names`,t.`names` FROM stu s,stu t WHERE s.ldid=t.stuid AND s.`stuid`=1001;
-- mysql函数
SELECT DATE_ADD(NOW(),INTERVAL 1 YEAR);
SELECT DATEDIFF (NOW(),'2017-12-03 11:23:38');
