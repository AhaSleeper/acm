#### 一、show语句

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

#### 二、检索数据

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

#### 三、排序

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

#### 四、过滤数据

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

#### 五、数据过滤

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

#### 六、使用通配符进行过滤

```mysql
#1.like操作符，使用%通配符
select * from tablename where c1 like 'test%';#匹配以test开头的所有行
select * from tablename where c1 like 'se%e';#匹配以se开头和e结尾的所有行
select * from tablename where c1 like '%ee';#匹配以ee结尾的所有行
select * from tablename where c1 like '%ee%';#匹配包含ee的所有行
#2.下划线（_）通配符，匹配单个字符
select c1, c2 from products where c1 like '_ hello';
```

#### 七、用正则表达式进行搜索

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

#### 八、创建计算字段

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

#### 九、使用数据处理函数

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

#### 十、汇总数据

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

#### 十一、分组数据

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

#### 十二、使用子查询

1. 利用子查询进行过滤

   ```mysql
   select cust_id from orders where orders_num in (select order_num from orderitems where prod_id='TNT2');
   ```

   在select语句中，子查询总是从内向外处理。

2. 作为计算字段使用子查询

   ```mysql
   SELECT cust_name, cust_state, (SELECT COUNT(*) FROM orders where orders.cust_id = customers.cust_id) AS orders FROM customers ORDER BY cust_name;
   ```

   涉及外部查询的子查询称为相关子查询。

#### 十三、联结表

创建联结

```mysql
SELECT vend_name, prod_name, prod_price FROM vendors, products WHERE vendors.vend_id = products.vend_id ORDER BY vend_name, prod_name;#查询供应商及其产品信息
```

内部联结

```mysql
SELECT vend_name, prod_name, prod_price FROM vendors INNER JOIN products ON vendors.vend_id = products.vend_id;
```

联结多个表

```mysql
SELECT prod_name, vend_name, prod_price, quantity FROM orderitems, products, vendors 
WHERE products.vend_id = vendors.vend_id AND orderitems.prod_id = products.prod_id
AND order_num = 20005;#显示标号20005订单的物品
SELECT cust_name, cust_contact FROM customers, orders, orderitems WHERE customers.cust_id = orders.cust_id
AND orderitems.order_num = orders.order_num AND prod_id = 'TNT2';#查询购买了TNT2商品的客户信息
```

#### 十四、创建高级联结

**使用表别名：**

- 缩短SQL语句
- 允许在单条select语句中多次使用相同的表

```mysql
SELECT cust_name, cust_contact FROM customers AS c, orders AS o, orderitems AS oi
WHERE c.cust_id = o.cust_id AND oi.order_num = o.order_num AND prod_id = 'TNT2';
```

**使用不同类型的联结：**

**自联结**

```mysql
#某id为DTNTR的物品存在问题，要查询生产该物品的供应商生产的其他物品是否也存在问题
SELECT prod_id, prod_name FROM products WHERE vend_id = (SELECT vend_id FROM products WHERE prod_id = 'DTNTR');#子查询方式
SELECT p.prod_id, p.prod_name FROM products AS p, products AS pt WHERE p.vend_id = pt.vend_id AND pt.prod_id = 'DTNTR';
SELECT p.prod_id, p.prod_name FROM products p inner join products pt on p.vend_id = pt.vend_id WHERE pt.prod_id = 'DTNTR';#自联结方式
```

**自然联结**

> 自然联结排除列的多次出现，使每个列都只返回一次。

**外部联结**

> 将一个表中的行与另一个表中的行相关联，有时候会需要包含没有关联行的那些行。比如：
>
> - 对每个客户下了多少订单进行计数，包括那些至今未下单的客户；
> - 列出所有产品以及订购数量，包括没有人订购的产品；
> - 计算平均销售规模，包括那些至今尚未相当的客户；

即联结包含那些在相关表中没有关联行的行，这种类型的联结称为外部联结。

```mysql
SELECT customers.cust_id, orders.order_num FROM customers INNER JOIN orders ON
customers.cust_id = orders.cust_id;#检索客户及其订单
SELECT customers.cust_id, orders.order_num FROM customers LEFT OUTER JOIN orders ON
customers.cust_id = orders.cust_id;#检索客户及其订单，包括那些没有订单的客户

```

> 在使用关键字OUTER JOIN来指定联结的类型时，必须使用RIGHT或LEFT关键字指定包括其所有行的表（RIGHT 指出是OUTER JOIN 右边的表，而LEFT指出的是OUTER JOIN左边的表）

> **外部联结的类型** 存在两种基本的外部联结形式：左外部联结和右外部联结。它们之间的唯一差别是所关联的表的顺序不同。换句话说，左外部联结可通过颠倒FROM 或WHERE 子句中表的顺序转换为右外部联结。因此，两种类型的外部联结可互换使用，而究竟使用哪一种纯粹是根据方便而定。

**使用带聚集函数的联结**

```mysql
SELECT customers.cust_name, customers.cust_id, COUNT(orders.order_num) AS num_ord
FROM customers INNER JOIN orders ON customers.cust_id = orders.cust_id GROUP BY customers.cust_id;#检索所有客户及每个客户所下的订单数
SELECT customers.cust_name, customers.cust_id, COUNT(orders.order_num) AS num_ord
FROM customers LEFT OUTER JOIN orders ON customers.cust_id = orders.cust_id GROUP BY customers.cust_id;#检索所有客户及每个客户所下的订单数（包括没有下单的客户）
```

**使用联结和联结条件**

> - 注意所使用的链接类型。一般我们使用内部联结，但使用外部联结也是有效的。
> - 保证使用正确的联结条件，否则将返回不正确的数据
> - 应该总是提供联结条件，否则会得出笛卡尔积
> - 在一个联结中可以包含多个表，甚至于对于每个联结可以采用不同的联结类型。虽然这样做是合法的，一般也很有用，但应该在一起测试他们前，分别测试每个联结。这将使故障排除更为简单

#### 十五、组合查询

> 有两种基本情况，其中需要使用组合查询：
>
> - 在单个查询中从不同的表返回类似结构的数据；
> - 对单个表执行多个查询，按单个查询返回数据。

**创建组合查询**

使用UNION

```mysql
SELECT vend_id, prod_id, prod_price FROM products WHERE prod_price <= 5
UNION
SELECT vend_id, prod_id, prod_price FROM products WHERE vend_id IN (1001, 1002);#查询价格小于5的物品，供应商1001和1002生产的所有物品
```

union规则

>- union必须由两条或两条以上的select语句组成，语句之间用关键字union分隔
>- union中的每个查询必须包含相同的列，表达式或聚集函数（列的次序可以不同）
>- 列数据类型必须兼容：类型不必完全相同，但必须是DBMS可以隐含地转换的类型

包含或取消重复的行

> union从查询结果集中自动去除了重复的行。如果需要所有的行，可以是使用union all。

```mysql
SELECT vend_id, prod_id, prod_price FROM products WHERE prod_price <= 5
UNION ALL 
SELECT vend_id, prod_id, prod_price FROM products WHERE vend_id IN (1001, 1002);#查询价格小于5的物品，供应商1001和1002生产的所有物品（包含所有行）
```

**对组合查询结果排序**

> SELECT语句的输出用ORDER BY 子句排序。在用UNION组合查询时，只能使用一条ORDER BY子句，它必须出现在最后一条SELECT语句之后。对于结果集，不存在用一种方式排序一部分，而又用另一种方式排序另一部分的情况，因此不允许使用多条ORDER BY 子句。虽然ORDER BY 子句似乎只是最后一条SELECT语句的组成部分，但实际上MySQL将用它来排序所有SELECT语句返回的所有结果。

```mysql
SELECT vend_id, prod_id, prod_price FROM products WHERE prod_price <= 5
UNION
SELECT vend_id, prod_id, prod_price FROM products WHERE vend_id IN (1001, 1002)
ORDER BY vend_id, prod_price;
```

#### 十六、全文本搜索

> 并非所有引擎都支持全文本搜索：并非所有的引擎都支持本书所描述的全文本搜索。两个最常使用的引擎为MyISAM 和InnoDB ，前者支持全文本搜索，而后者不支持。
>
> 启用全文本搜索支持：创建表时使用FULLTEXT子句，它给出被索引列的一个逗号分隔的列表

```mysql
CREATE TABLE productnotes
(
  note_id    int           NOT NULL AUTO_INCREMENT,
  prod_id    char(10)      NOT NULL,
  note_date datetime       NOT NULL,
  note_text  text          NULL ,
  PRIMARY KEY(note_id),
  FULLTEXT(note_text) #全文本索引指定
) ENGINE=MyISAM;
```

**进行全文本搜索**

> 建立全文本索引之后，使用两个函数Match()和Against()执行全文本搜索，其中Match()指定被搜索的列，Against()指定要使用的搜索表达式。
>
> 传递给Match()的值必须与FULLTEXT()定义中的相同。如果指定多个列，则必须列出它们（而且次序正确）

```mysql
SELECT note_text FROM productnotes where MATCH(note_text) AGAINST('rabbit');#指定rabbit作为搜索文本
```

**使用查询扩展**

```mysql
SELECT note_text FROM productnotes where MATCH(note_text) AGAINST('rabbit' WITH QUERY EXPANSION);
```

**布尔文本搜索**

> 以布尔方式，可以提供关于如下内容细节：
>
> - 要匹配的词；
> - 要排斥的词；
> - 排列提示（指定某些词比其他词更重要，更重要的词等级更高）
> - 表达式分组；
> - 另外一些内容

```mysql
SELECT note_text FROM productnotes where MATCH(note_text) AGAINST('heavy' IN BOOLEAN MODE);#没有指定布尔操作符，结果与没有指定布尔方式的结果相同
SELECT note_text FROM productnotes where MATCH(note_text) AGAINST('heavy -rope*' IN BOOLEAN MODE);# - rope*指示排除包含rope*
SELECT note_text FROM productnotes where 
MATCH(note_text) AGAINST('+rabbit +bait' IN BOOLEAN MODE);#匹配包含词rabbit和bait的行
SELECT note_text FROM productnotes where 
MATCH(note_text) AGAINST('"rabbit bait"' IN BOOLEAN MODE);#匹配短语rabbit bait
SELECT note_text FROM productnotes where 
MATCH(note_text) AGAINST('>rabbit <carrot' IN BOOLEAN MODE);#匹配rabbit和carrot，增加前者的等级，降低后者的等级
```

**全文本搜索的使用说明**

> - 在索引全文本数据时，短词被忽略且从索引中排除。短词定义为那些具有3个或3个以下字符的词（如果需要，这个数目可以更改）
> - MySQL带有一个内建的非用词（stopword）列表，这些词在索引全文本数据时总是被忽略。如果需要，可以覆盖这个列表
> - 许多词出现的频率很高，搜索它们没有用处（返回太多的结果）。因此，MySQL规定了一条50%规则，如果一个词出现在50%以上的行中，则将它作为一个非用词忽略。50%规则不用于IN BOOLEAN MODE 。
> - 如果表中的行数少于3行，则全文本搜索不返回结果（因为每个词或者不出现，或者至少出现在50%的行中）
> - 忽略词中的单引号。例如，don't 索引为dont 。
> - 不具有词分隔符（包括日语和汉语）的语言不能恰当地返回全文本搜索结果
> - 仅在MyISAM 数据库引擎中支持全文本搜索。

#### 十七、插入数据

INSERT的几种使用方式

> - 插入完整的行；
> - 插入行的一部分；
> - 插入多行，可以使用多条insert语句，分号结束，可以一次提交；也可以使用单条insert语句，语句有多组值，每组值用一对圆括号括起来，用逗号分隔；
> - 插入某些查询的结果（INSERT SELECT)

```mysql
insert into tablename values(val1, val2....);#插入所有列，必须给每个列提供值
insert into tablename(col1, col2, col3) values(val1, val2, val3);#指定列名
insert into tablename(col1, clo2, col3) values(val1, val2, val3),
(val1, val2, val3), (val1, val2, val3);#单条insert，多组值
insert into tablename(col1, col2, col3) select col1, col2, col3 from tablename2;#使用insert select，insert的每个列位置要和select的每个列位置对应起来。
```

#### 十八、更新和删除数据

**UPDATE语句**

> 使用方式：
>
> - 更新表中特定行；
> - 更新表中所有行；
>
> 语句的组成：
>
> - 要更新的表；
> - 列名和它们的新值，更新多个列需要以列名=值的形式用逗号分隔；
> - 确定要更新行的过滤条件。
>
> 在UPDATE语句中使用子查询,使得能用SELECT语句检索出的数据更新列数据。
>
> IGNORE关键字：使用IGNORE关键字可以使得更新过程中即使发生错误，也能继续进行更新；

```mysql
UPDATE customers SET cust_email = 'elmer@fudd.com' WHERE cust_id = 10005;#更新单个列
UPDATE customers SET cust_name = 'The Fudds', cust_email = 'elmer@fudd.com' WHERE cust_id = 10005;#更新多个列
```

**DELETE语句**

> 使用方式：
>
> - 从表中删除特定的行。
> - 从表中删除所有的行。
> - 不要省略WHERE子句，否则会删除表中所有的行。
> - 要想删除表中所有的行，不要使用DELETE， 可以使用TRUNCATE TABLE 语句，它的速度更快。

```mysql
DELETE FROM customers WHERE cust_id = 10006;#删除单行数据
```

**更新和删除的指导原则**

> - 除非确实打算更新和删除每一行，否则绝对不要使用不带WHERE 子句的UPDATE 或DELETE 语句。
> - 保证每个表都有主键，尽可能像WHERE 子句那样使用它（可以指定各主键、多个值或值的范围）
> - 在对UPDATE 或DELETE 语句使用WHERE 子句前，应该先用SELECT 进行测试，保证它过滤的是正确的记录，以防编写的WHERE 子句不正确
> - 使用强制实施引用完整性的数据库，这样MySQL将不允许删除具有与其他表相关联的数据的行

#### 十九、创建和操纵表

**创建表(CREATE TABLE语句）**

> 建表时，必须给出下列信息：
>
> - 新表的名字，在关键字CREATE TABLE 之后给出；
> - 表列的名字和定义，用逗号分隔。
>
> 处理现有的表：创建新表时，指定的表名必须不存在，否则将出错。如果你仅想在一个表不存在时创建它，应该在表名后给出IF NOT EXISTS。

```mysql
CREATE TABLE customers
(
  cust_id      int       NOT NULL AUTO_INCREMENT,
  cust_name    char(50)  NOT NULL ,
  cust_address char(50)  NULL ,
  cust_city    char(50)  NULL ,
  cust_state   char(5)   NULL ,
  cust_zip     char(10)  NULL ,
  cust_country char(50)  NULL ,
  cust_contact char(50)  NULL ,
  cust_email   char(255) NULL ,
  PRIMARY KEY (cust_id)
) ENGINE=InnoDB;
```

**使用NULL值**

> 每个表列或者是NULL列，或者是NOT NULL 列，这种状态在创建时由表的定义规定。

**主键**

> 使用PRIMARY KEY(column_name)定义主键，由多个列构成的主键，使用逗号分隔列名。

**AUTO_INCREMENT**

> 自动增量，可以使用SELECT last_insert_id()获得

**指定默认值**

> 如果在插入行时没有给出值，MySQL运行指定此时使用的默认值。
>
> 默认值用CREATE TABLE语句的列定义中的DEFAULT关键字指定。

```mysql
CREATE TABLE orderitems
(
  order_num  int          NOT NULL ,
  order_item int          NOT NULL ,
  prod_id    char(10)     NOT NULL ,
  quantity   int          NOT NULL  DEFAULT 1,
  item_price decimal(8,2) NOT NULL ,
  PRIMARY KEY (order_num, order_item)
) ENGINE=InnoDB;
```

**引擎类型（ENGIN=引擎名称）**

> MySQL有一个具体管理和处理数据的内部引擎。在你使用CREATE TABLE 语句时，该引擎具体创建表，而在你使用SELECT 语句或进行其他数据库处理时，该引擎在内部处理你的请求。
>
> **外健不能跨引擎**：混用引擎的情况下，外健不能跨引擎。

------

**更新表（ALTER TABLE)**

> 使用ALTER TABLE更改表结构时，需要的信息：
>
> - 在ALTER TABLE 之后给出要更改的表名（该表必须存在）
> - 所做更改的列表。
>
> 复杂的表结构更改一般需要手动删除过程，它涉及以下步骤：
>
> - 使用INSERT SELECT 语句从旧表复制数据到新表。如果有必要，可使用转换函数和计算字段；
> - 检验包含所需数据的新表
> - 重命名旧表（如果确定，可以删除它）；
> - 用旧表原来的名字重命名新表；
> - 根据需要，重新创建触发器、存储过程、索引和外键。

```mysql
ALTER TABLE vendors ADD vend_phone CHAR(20);#添加一个列
ALTER TABLE vendors DROP COLUMN vend_phone;#删除一个列
ALTER TABLE orderitems ADD CONSTRAINT fk_orderitems_orders FOREIGN KEY(order_num) REFERENCES orders(order_num);#修改表，增加外健约束
```

**删除表**

> DROP TABLE tablename;

**重命名表**

> RENAME TABLE table1 TO table2;

#### 二十、使用视图

**为什么使用视图？**

> - 重用SQL语句
> - 简化复杂的SQL操作。在编写查询后，可以方便地重用它而不必知道它的基本查询细节。
> - 使用表的组成部分而不是整个表。
> - 保护数据。可以给用户授予表的特定部分的访问权限而不是整个表的访问权限。
> - 更改数据格式和表示。视图可返回与底层表的表示和格式不同的数据。
>
> 在视图创建之后，可以用与表基本相同的方式利用它们。可以对视图执行SELECT 操作，过滤和排序数据，将视图联结到其他视图或表，甚至能添加和更新数据。
>
> 重要的是知道视图仅仅是用来查看存储在别处的数据的一种设施。视图本身不包含数据，因此它们返回的数据是从其他表中检索出来的。在添加或更改这些表中的数据时，视图将返回改变过的数据。

**视图的规则和限制**

> - 与表一样，视图必须唯一命名（不能给视图取与别的视图或表相同的名字）
> - 对于可以创建的视图数目没有限制
> - 为了创建视图，必须具有足够的访问权限。这些限制通常由数据库管理人员授予
> - 视图可以嵌套，即可以利用从其他视图中检索数据的查询来构造一个视图
> - ORDER BY 可以用在视图中，但如果从该视图检索数据SELECT 中也含有ORDER BY ，那么该视图中的ORDER BY 将被覆盖
> - 视图不能索引，也不能有关联的触发器或默认值
> - 视图可以和表一起使用。例如，编写一条联结表和视图的SELECT 语句

**使用视图**

> 创建视图
>
> - 视图用CREATE VIEW 语句来创建。
> - 使用SHOW CREATE VIEW viewname; 来查看创建视图的语句。
> - 用DROP 删除视图，其语法为DROP VIEW viewname; 。
> - 更新视图时，可以先用DROP 再用CREATE ，也可以直接用CREATE OR REPLACE VIEW 。如果要更新的视图不存在，则第1条更新语句会创建一个视图；如果要更新的视图存在，则第2条更新语句会替换原有视图。

**利用视图简化复杂的联结**

```mysql
CREATE VIEW productcustomers AS
SELECT cust_name, cust_contact, prod_id FROM customers, orders, orderitems
WHERE customers.cust_id = orders.cust_id
AND orderitems.order_num = orders.order_num;#创建一个名为productcustomers的视图，它联结三个表，以返回已订购了任意产品的所有客户的列表。
SELECT cust_name, cust_contact FROM productcustomers WHERE prod_id='TNT2';#检索订购了产品TNT2 的客户
```

**用视图重新格式化检索出的数据**

```mysql
CREATE VIEW vendorlocations AS
SELECT CONCAT(RTrim(vend_name),' (', RTrim(vend_country), ')') AS vend_title
FROM vendors
ORDER BY vend_name;
SELECT * from vendorlocations;
```

**用视图过滤不想要的数据**

```mysql
CREATE VIEW customeremaillist AS
SELECT cust_id, cust_name, cust_email  
FROM customers
WHERE cust_email IS NOT NULL;#过滤没有电子邮件地址的客户
SELECT * FROM customeremaillist;
```

**使用视图与计算字段**

```mysql
CREATE VIEW orderitemsexpanded AS
SELECT order_num, prod_id, quantity, item_price, quantity*item_price AS expanded_price
FROM orderitems;#检索某个特定订单中的物品，计算每种物品的总价格；
SELECT * FROM orderitemsexpanded WHERE order_num=20005;
```

**更新视图**

能否更新视图，需要视情况而定。如果视图定义中有以下操作，则不能进行视图的更新：

- 分组（使用GROUP BY 和HAVING)；
- 联结；
- 子查询；
- 并；
- 聚集函数
- DISTINCT;
- 导出（计算）列

#### 二十一、存储过程

**执行存储过程**

```mysql
CALL procedurename(para1, para2....);
CALL productpricing(@pricelow, @pricehigh, @priceaverage);
```

**创建存储过程**

```mysql
CREATE PROCEDURE productpricing()
BEGIN
		SELECT AVG(prod_price) AS priceaverage
    FROM products;
END
```

**删除存储过程**

```mysql
DROP PROCEDURE productpricing;
DROP PROCEDURE IF EXISTS productpricing;#仅当存在时删除
```

**使用参数**

```mysql
CREATE PROCEDURE productpricing(
    OUT pl DECIMAL(8,2),#关键字OUT 指出相应的参数用来从存储过程传出一个值（返回给调用者）
    OUT ph DECIMAL(8,2),
    OUT pa DECIMAL(8,2)
)
BEGIN
    SELECT MIN(prod_price)
    INTO pl
    FROM products;
    SELECT MAX(prod_price)
    INTO ph 
    FROM products;
    SELECT AVG(prod_price)
    FROM products;
END;
#MySQL支持IN （传递给存储过程）、OUT （从存储过程传出，如这里所用）和INOUT （对存储过程传入和传出）类型的参数。
select @pricelow, @pricehigh, @priceaverage;#获得值

CREATE PROCEDURE ordertotal(
    IN onumber INT,
    OUT ototal DECIMAL(8,2)
)
BEGIN 
    SELECT SUM(item_price*quantity)
    FROM orderitems
    where order_num = onumber
    INTO ototal;
END;
CALL ordertotal(20005, @total);
select @total;
```

**建立智能存储过程**

```mysql
-- Name: ordertotal
-- Parameters: onumber = order number
-- 						 taxable = 0 if not taxable, 1 if taxable
-- 						 ototal = order total VARIABLES
CREATE PROCEDURE ordertotal(
		IN onumber INT,
    IN taxable BOOLEAN,
    OUT ototal DECIMAL(8,2)
) COMMENT 'Obtain order total, optionally adding tax'
BEGIN
   -- Declare variable for total
   DECLARE total DECIMAL(8,2);
   -- Declare tax percentage
   DECLARE taxrate INT DEFAULT 6;
   -- get the order total
   SELECT SUM(item_price*quantity)
   FROM orderitems
   where order_num=onumber
   INTO total;
   -- is this taxable?
   IF taxable THEN
      -- Yes, so add taxrate to the total
      SELECT total+(total/100*taxrate) INTO total;
   END IF;
   -- AND finally, save to out VARIABLES 
   SELECT total INTO ototal;
END;
```

**检查存储过程**

```mysql
SHOW CREATE PROCEDURE ordertotal;
SHOW PROCEDURE status;#查看存储过程列表的详细信息
```

#### 二十二、游标