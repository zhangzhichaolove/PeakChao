CREATE DATABASE chao;  /*创建数据库*/
SHOW DATABASES;		/*显示数据库*/
CREATE DATABASE chao2 CHARACTER SET gbk;
SHOW CREATE DATABASE chao;  	/*显示创建数据库*/
SELECT * FROM `chao`.`user`;   -- 显示表数据
DROP DATABASE chao2;	/*删除数据库*/
SELECT DATABASE();	/*查询使用的数据库*/
USE chao;		/*切换数据库*/
INSERT INTO USER(NAME,phone,result) VALUE('MrYang',18090445628,'签名');  -- 添加数据
UPDATE USER SET result='这里显示个性签名...' WHERE id=2;    -- 更改内容
DELETE FROM chao WHERE id=2;    -- 删除表中id=2的数据
SELECT NAME,phone FROM USER;    -- 查询指定列
SELECT * FROM USER WHERE phone='13594347817' AND NAME='MrZhang';  
SELECT * FROM USER WHERE phone='13594347817' OR NAME='MrYang';  
SELECT * FROM USER WHERE phone='13594347817' OR phone='18090445628';
SELECT * FROM USER WHERE phone IN('13594347817','18090445628');
SELECT * FROM USER WHERE result IS NULL;
SELECT * FROM USER WHERE result IS NOT NULL;
SELECT * FROM USER WHERE NAME LIKE '_______';		-- 查询name长度为任意7个字母
SELECT * FROM USER WHERE NAME LIKE 'M%';
SELECT * FROM USER WHERE NAME LIKE '%n%';		-- 包含n
SELECT DISTINCT phone FROM USER;		-- 去掉重复
SELECT IFNULL(phone,0) FROM USER;		-- 滤空
SELECT IFNULL(phone,0) AS NAME FROM USER;		-- 滤空
SELECT NAME AS 姓名,phone 电话 FROM USER; 		-- as
SELECT * FROM USER ORDER BY phone ASC;
SELECT * FROM USER ORDER BY phone DESC;
SELECT * FROM USER ORDER BY phone DESC,id DESC;		-- 排序，如果有相同，然后使用id二次排序
SELECT COUNT(NAME) FROM USER; 				-- 非空所有行总数  MAX()  MIN() SUM()总和 AVG()平均值
SELECT MIN(phone),MAX(phone) FROM USER;
SELECT NAME,COUNT(*) FROM USER GROUP BY phone;		-- 以phone分组
SELECT * FROM USER LIMIT 0,1;			`user`	-- 从第0行开始查询1行
SELECT u.name,u.phone,u.result,t.name FROM USER u,test t WHERE u.userid=t.userid;




