**一、show语句**

```mysql
#1.查看数据库
show databases;
#2.查看表
show tables;
#3.查看表的列
show columns from tablename;
describe tablename;
#4.显示服务器状态
show status;
#5.查看建库语句
show create database databasename;
#6.查看建表语句
show create table tablename;
#7.显示授予用户的安全权限
show grants;
#8.显示错误/警告信息
show errors;
show warnings;
```

**二、检索数据**

```mysql
#1.检索单个列
select column_name from tablename;
#2.检索多个列
select column1, column2, ... from tablename;
#3.检索所有列
select * from tablename;
#4.过滤相同的行
select distinct column_name from tablename;
#注意：select distinct column1, column2 这种形式的检索，除非column1和column2都不同，否则所有的行都会被检索出来。
#5.限制结果:limit n
select column_name from tablename limit 5;
#指定开始行和行数 limit n,m
select column_name from tablename limit 5,5;
#6.使用完全限定的表名
select tablename.column_name from tablename;
```

**三、排序**

```mysql
#1.按单个列排序，排序的列可以是表中的任意一个列
select column_name from tablename order by column_name;
#2.按多个列排序,按order by后面的列依次排序，即如果column1如果都是唯一的，column2就不起作用
select column1, column2, column3 from tablename order by column1,column2;
#3.按指定方向排序
#降序
select column1,column2 from tablename order by column1 desc;
#升序
select column1 from tablename order by column1 asc;
#在多个列上进行排序，必须对每个列指定排序关键字
select column1, column2 from tablename order by column1 desc, column2 asc;
```

**四、过滤数据**

```mysql
#1. where子句
select c1, c2 from tablename where ....
#2. where子句操作符
# 等于(=),不等于(<>,!=),小于(<),小于等于(<=),大于(>),大于等于(>=),在指定的两个值之间(between)
#3.不匹配检查
select c1, c2 from tablename where c1 <> value;
#4.范围检查
select c1, c2 from tablename where c1 between value1 and value2;
#5.空值检查
select * from tablename where c1 is null;
```

**五、数据过滤**

```mysql
#组合where子句
#1.and操作符
select c1, c2 from tablename where c1 = 1000 and c2<=500;
#2.or操作符
select c1, c2 from tablename where c1 = 1000 or c1 = 1001;
#3.计算次序：and 的次序高于 or次序
select c1, c2 from tablename where c1=1000 or c1=1001 and c2>10;
#以上语句可能范围的结果会不符合预期，因为and的次序高于or，所以先匹配c1=1001 and c2>10，再匹配c1=1000
#解决此问题可以通过圆括号：where (c1=1000 or c1=1001) and c2>10;

#4.in操作符(与or操作符具有相同功能)
select c1, c2 from tablename where id in(1,2,3);
#5.not操作符，否定它之后的任何条件
select c1,c2 from tablename where id not in (1,2);
```

**六、使用通配符进行过滤**

```mysql
#1.like操作符，使用%通配符
select * from tablename where c1 like 'test%';#匹配以test开头的所有行
select * from tablename where c1 like 'se%e';#匹配以se开头和e结尾的所有行
select * from tablename where c1 like '%ee';#匹配以ee结尾的所有行
select * from tablename where c1 like '%ee%';#匹配包含ee的所有行
#2.下划线（_）通配符，匹配单个字符
select c1, c2 from products where c1 like '_ hello';
```

**七、用正则表达式进行搜索**

```mysql
#1.基本的字符匹配
select prod_name from products where prod_name REGEXP '1000' order by prod_name; #检索列prod_name包含文本1000的所有行
select prod_name from products where prod_name REGEXP '.000' order by prod_name; 
#2.进行or匹配：使用 |
select prod_name from products where prod_name REGEXP '1000|2000' order by prod_name; 
#3.匹配几个字符之一：使用[]
SELECT prod_name FROM products WHERE prod_name REGEXP '[123] Ton' ORDER BY prod_name;
#4.匹配范围
SELECT prod_name FROM products WHERE prod_name REGEXP '[1-5] Ton' ORDER BY prod_name;
#5.匹配特殊字符：要匹配.、[]、|、-等符号，需要在前面加双反斜杠符号
select vend_name from vendors where vend_name REGEXP '\\.' order by vend_name;
#6.匹配字符类：如[:alnum:]，任意字母和数字（同[a-zA-Z0-9]）
#7.匹配多个实例（重复元字符）
SELECT prod_name FROM products WHERE prod_name REGEXP '\\([0-9] sticks?\\)' ORDER BY prod_name;
SELECT prod_name FROM products WHERE prod_name REGEXP '[[:digit:]]{4}' ORDER BY prod_name;
#8.定位符：^表示文本的开始，$表示文本的结尾，[[:表示词的开始，[[:>:]]表示词的结尾
SELECT prod_name FROM products WHERE prod_name REGEXP '^[0-9\\.]'
```

**八、创建计算字段**

```mysql
#1.拼接字段：concat()函数
SELECT CONCAT(vend_name,' (',vend_country, ')') FROM vendors ORDER BY vend_name;
#2.删除右侧多余空格：RTrim()函数
SELECT CONCAT(RTrim(vend_name),' (',RTrim(vend_country), ')') FROM vendors ORDER BY vend_name;
#3.使用别名
SELECT CONCAT(RTrim(vend_name),' (',RTrim(vend_country), ')') AS vend_title FROM vendors ORDER BY vend_name;
#4.执行计算（+，-，*，/）
SELECT prod_id, quantity, item_price, quantity*item_price AS expanded_price FROM orderitems WHERE order_num=20005;
```

**九、使用数据处理函数**

```mysql
#1.文本处理函数
SELECT vend_name, UPPER(vend_name) AS vend_name_upcase FROM vendors ORDER BY vend_name;#UPPER()函数将文本转为大写
```

日期函数：

|     函数      |              说明              |
| :-----------: | :----------------------------: |
|   AddDate()   |    增加一个日期（天、周等）    |
|   AddTime()   |    增加一个时间（时、分等）    |
|   CurDate()   |          返回当前日期          |
|   CurTime()   |          返回当前时间          |
|    Date()     |     返回日期时间的日期部分     |
|  DateDiff()   |        计算两个日期之差        |
|  Date_Add()   |     高度灵活的日期运算函数     |
| Date_Format() |  返回一个格式化的日期或时间串  |
|     Day()     |     返回一个日期的天数部分     |
|  DayOfWeek()  | 对于一个日期，返回对应的星期几 |
|    Hour()     |     返回一个时间的小时部分     |
|   Minute()    |     返回一个时间的分钟部分     |
|    Month()    |     返回一个日期的月份部分     |
|     Now()     |       返回当前日期和时间       |
|   Second()    |      返回一个时间的秒部分      |
|    Time()     |   返回一个日期时间的时间部分   |
|    Year()     |     返回一个日期的年份部分     |

数值处理函数：

|  函数  |        说明        |
| :----: | :----------------: |
| Abs()  | 返回一个数的绝对值 |
| Cos()  | 返回一个角度的余弦 |
| Exp()  | 返回一个数的指数值 |
| Mod()  | 返回除操作数的余数 |
|  Pi()  |     返回圆周率     |
| Rand() |   返回一个随机数   |
| Sin()  | 返回一个角度的正弦 |
| Sqrt() | 返回一个数的平方根 |
| Tan()  | 返回一个角度的正切 |

**十、汇总数据**

聚集函数

|  函数   |                             说明                             |
| :-----: | :----------------------------------------------------------: |
|  AVG()  |                       返回某列的平均值                       |
| COUNT() | 返回某列的行数；count(*)返回所有行（null值也返回），count(column)对特定列中具有值的行进行计数 |
|  MAX()  |                       返回某列的最大值                       |
|  MIN()  |                       返回某列的最小值                       |
|  SUM()  |                        返回某列值之和                        |

```mysql
SELECT AVG(prod_price) AS avg_price FROM products;#求平均价格
SELECT AVG(prod_price) AS avg_price FROM products where vend_id=1003;#求某个供应商的产品平均价格
SELECT count(*) AS num_cust FROM customers;#查询所有客户数
SELECT count(cust_email) AS num_cust from customers;#查询具有电子邮件地址的客户总数
SELECT MAX(prod_price) AS max_price from products;#查询最贵商品的价格
SELECT MIN(prod_price) AS max_price from products;#查询最便宜商品的价格
SELECT SUM(quantity) AS items_ordered FROM orderitems WHERE order_num = 20005;#订购商品的总数
SELECT SUM(item_price*quantity) AS total_price FROM orderitems WHERE order_num = 20005;#计算总的订单金额
```

**十一、分组数据**

1. 创建分组(group by)

   分组是在select语句的group by子句中建立的。

   ```mysql
   SELECT vend_id, COUNT(*) AS num_prods FROM products GROUP BY vend_id;
   ```

   - GROUP BY 子句可以包含任意数目的列。这使得能对分组进行嵌套，为数据分组提供更细致的控制。
   - 如果在GROUP BY 子句中嵌套了分组，数据将在最后规定的分组上进行汇总。换句话说，在建立分组时，指定的所有列都一起计算（所以不能从个别的列取回数据）
   - GROUP BY 子句中列出的每个列都必须是检索列或有效的表达式（但不能是聚集函数）。如果在SELECT 中使用表达式，则必须在GROUP BY 子句中指定相同的表达式。不能使用别名
   - 除聚集计算语句外，SELECT 语句中的每个列都必须在GROUP BY 子句中给出
   - 如果分组列中具有NULL 值，则NULL 将作为一个分组返回。如果列中有多行NULL 值，它们将分为一组
   - GROUP BY 子句必须出现在WHERE 子句之后，ORDER BY 子句之前。

   使用ROLLUP 使用WITH ROLLUP 关键字，可以得到每个分组以及每个分组汇总级别（针对每个分组）的值

   ```mysql
   SELECT vend_id, COUNT(*) AS num_prods FROM products GROUP BY vend_id WITH ROLLUP;
   ```

2. 过滤分组（having）

   having子句和where子句很相似，where子句用于过滤行，而having子句用于过滤分组。where在数据分组前进行过滤，having在数据分组后进行过滤。

   ```mysql
   SELECT cust_id, COUNT(*) AS orders FROM orders GROUP BY cust_id HAVING COUNT(*) >=2;#使用haing子句过滤分组数据
   SELECT vend_id, COUNT(*) AS num_prods FROM products where prod_price >= 10 GROUP BY vend_id HAVING COUNT(*) >= 2;#同时使用where子句与having子句
   ```

3. 分组和排序

   ```mysql
   SELECT
   	order_num,
   	SUM(quantity * item_price) AS ordertotal
   FROM
   	orderitems
   GROUP BY
   	order_num
   HAVING
   	SUM(quantity * item_price) >= 50
   ORDER BY ordertotal;
   ```

4. SELECT子句顺序

   | 子句     | 说明               | 是否必须使用           |
   | -------- | ------------------ | ---------------------- |
   | SELECT   | 要返回的列或表达式 | 是                     |
   | FROM     | 从中检索数据的表   | 仅在从表选择数据时使用 |
   | WHERE    | 行级过滤           | 否                     |
   | GROUP BY | 分组说明           | 仅在按组计算聚集时使用 |
   | HAVING   | 组级过滤           | 否                     |
   | ORDER BY | 输出排序顺序       | 否                     |
   | LIMIT    | 要检索的行数       | 否                     |

**十二、使用子查询**

