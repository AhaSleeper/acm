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

有时，需要在检索出来的行中前进或后退一行或多行。这就是使用游标的原因。游标（cursor）是一个存储在MySQL服务器上的数据库查询，它不是一条SELECT 语句，而是被该语句检索出来的结果集。在存储了游标之后，应用程序可以根据需要滚动或浏览其中的数据。游标只能用于存储过程。

**使用游标**

> - 在能够使用游标前，必须声明（定义）它。这个过程实际上没有检索数据，它只是定义要使用的SELECT 语句。
> - 一旦声明后，必须打开游标以供使用。这个过程用前面定义的SELECT 语句把数据实际检索出来。
> - 对于填有数据的游标，根据需要取出（检索）各行。
> - 在结束游标使用时，必须关闭游标。

**创建游标**

> 游标用DECLARE语句创建。DECLARE命名游标，并定义相应的SELECT语句，根据需要带WHERE和其他子句。

```mysql
CREATE PROCEDURE processorders()
BEGIN
    DECLARE ordernumbers CURSOR
    FOR 
    SELECT ordernum FROM orders;
END;
```

**打开和关闭游标**

```mysql
#用OPEN CURSOR 语句打开游标
OPEN ordernumbers;
#用CLOSE CURSOR 语句关闭游标
CLOSE ordernumbers;
#如果没有明确关闭，MySQL将在达到END语句时自动关闭游标
```

**使用游标数据**

```mysql
DROP PROCEDURE IF EXISTS processorders;
CREATE PROCEDURE processorders()
BEGIN 
    -- Declare local variables
    DECLARE done BOOLEAN DEFAULT 0;
    DECLARE o INT;
    
    -- Declare the cursor
    DECLARE ordernumbers CURSOR 
    FOR  
    SELECT order_num FROM orders;
    -- Declare continue HANDLER
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;#SQLSTATE '02000'表示一个未找到条件，当REPEAT由于没有更多的行供循环而不能继续时，出现这个条件
    -- Open the cursor;
    OPEN ordernumbers;
    -- Loop through all rows
    REPEAT   
        -- Get order number
        FETCH ordernumbers INTO o;
    -- End of LOOP
    UNTIL done END REPEAT;
    -- Close the cursor
    CLOSE ordernumbers;
END;
```

**DECLARE语句次序**

> DECLARE 语句的发布存在特定的次序。用DECLARE 语句定义的局部变量必须在定义任意游标或句柄之前定义，而句柄必须在游标之后定义。不遵守此顺序将产生错误消息。

```mysql
DROP PROCEDURE IF EXISTS processorders;
CREATE PROCEDURE processorders()
BEGIN 
    -- Declare local variables
    DECLARE done BOOLEAN DEFAULT 0;
    DECLARE o INT;
    DECLARE t DECIMAL(8,2);
    
    -- Declare the CURSOR 
    DECLARE ordernumbers CURSOR 
    FOR  
    SELECT order_num FROM orders;
    -- Declare continue handler
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;
    
    -- Create a table to store the results
    CREATE TABLE IF NOT EXISTS ordertotals
        (order_num INT, total DECIMAL(8,2));
    -- open the CURSOR
    OPEN ordernumbers;

    -- Loop through all rows
    REPEAT
      
      -- Get order number
      FETCH ordernumbers INTO o;
      -- Get the total for this ORDER BY
      CALL ordertotal(o, 1, t);
      -- insert order and total into ordertotals
      INSERT INTO ordertotals(order_num, total) VALUES(o, t);
    -- End of LOOP
    UNTIL done END REPEAT;
    -- close the cursor
    CLOSE ordernumbers;
END;
```

#### 二十三、触发器

触发器是MySQL响应DELETE/UPDATE/INSERT等语句而自动执行的一条MySQL语句（或位于BEGIN 和END 语句之间的一组语句）。

**创建触发器**

> 创建触发器时，需要给出4条信息：
>
> - 唯一的触发器名；
> - 触发器关联的表；
> - 触发器应该响应的活动（DELETE/INSERT/UPDATE)
> - 触发器何时执行（处理之前或之后）

```mysql
CREATE TRIGGER newproduct AFTER INSERT ON products FOR EACH ROW SELECT 'Product added';#插入数据后，显示product added文本
```

触发器按每个表每个事件每次地定义，每个表每个事件每次只允许一个触发器。因此，每个表最多支持6个触发器（每条INSERT 、UPDATE 和DELETE 的之前和之后）。单一触发器不能与多个事件或多个表关联，所以，如果你需要一个对INSERT 和UPDATE 操作执行的触发器，则应该定义两个触发器

**删除触发器**

```mysql
DROP TRIGGER triggerName;
```

**使用触发器**

>  INSERT触发器: 在INSERT语句执行之前或之后执行。需要知道以下几点：
>
> - 在INSERT 触发器代码内，可引用一个名为NEW的虚拟表，访问被 插入的行；
> - 在BEFORE INSERT 触发器中，NEW 中的值也可以被更新（允许更改被插入的值）；
> - 对于AUTO_INCREMENT 列，NEW 在INSERT 执行之前包含0 ，在INSERT 执行之后包含新的自动生成值。

```mysql
CREATE TRIGGER neworder AFTER INSERT ON orders
FOR EACH ROW 
	SELECT NEW.order_num INTO @orderNum; #MYSQL5以后，不允许触发器返回任何结果
```

> DELETE触发器
>
> - 在DELETE 触发器代码内，你可以引用一个名为OLD 的虚拟表，访问被删除的行；
> - OLD 中的值全都是只读的，不能更新。

```MYSQL
CREATE TRIGGER deleteorder BEFORE DELETE ON orders 
FOR EACH ROW
BEGIN
   INSERT INTO archive_orders(order_num, order_date, cust_id) 
   VALUES(OLD.order_num, OLD.order_date, OLD.cust_id);
END;
```

> UPDATE触发器
>
> - 在UPDATE 触发器代码中，你可以引用一个名为OLD 的虚拟表访问以前（UPDATE 语句前）的值，引用一个名为NEW 的虚拟表访问新更新的值；
> - 在BEFORE UPDATE 触发器中，NEW 中的值可能也被更新（允许更改将要用于UPDATE 语句中的值）；
> - OLD 中的值全都是只读的，不能更新。

```mysql
CREATE TRIGGER updatevendor BEFORE UPDATE ON vendors
FOR EACH ROW SET NEW.vend_state = UPPER(NEW.vend_state);
```

#### 二十四、管理事务处理

事务处理（transactionprocessing）可以用来维护数据库的完整性，它保证成批的MySQL操作要么完全执行，要么完全不执行

- 事务（transaction)指一组SQL语句；
- 回退（rollback)指撤销指定SQL语句的过程；
- 提交（commit)指将未存储的SQL语句结果写入数据库表；
- 保留点（savepoint)指事务处理中设置的临时占位符，你可以对它发布回退。

```mysql
START TRANSACTION; #开启事务
```

**使用ROLLBACK**

```mysql
SELECT * FROM ordertotals;
START TRANSACTION;
DELETE FROM ordertotals;
SELECT * FROM ordertotals;
ROLLBACK;
SELECT * FROM ordertotals;
```

**使用COMMIT**

一般的MySQL语句都是直接针对数据库表执行和编写的。这就是所谓的隐含提（implicitcommit），即提交（写或保存）操作是自动进行的。但是，在事务处理块中，提交不会隐含地进行。

```mysql
START TRANSACTION;
DELETE FROM orders WHERE order_num=20010;
DELETE FROM orderitems WHERE order_num=20010;
COMMIT;
```

**使用保留点**

简单的ROLLBACK 和COMMIT 语句就可以写入或撤销整个事务处理。但是，只是对简单的事务处理才能这样做，更复杂的事务处理可能需要部分提交或回退。

为了支持回退部分事务处理，必须能在事务处理块中合适的位置放置占位符。这样，如果需要回退，可以回退到某个占位符。

```mysql
SAVEPOINT delete1;#声明保留点
ROLLBACK TO delete1;#回退到保留点
RELEASE delete1;#释放保留点
```

MySQL默认自动提交所有修改，可以修改为不自动提交：

```mysql
SET autocommit=0;#autocommit 标志是针对每个连接而不是服务器的。
```

#### 二十五、全球化和本地化

#### 二十六、安全管理

**访问控制**

> MySQL服务器的安全基础是：用户应该对他们需要的数据具有适当的访问权，既不能多也不能少 。换句话说，用户不能对过多的数据具有过多的访问权。

**管理用户**

```mysql
#查看用户账号列表
use mysql;
SELECT user FROM user;
```

```mysql
-- 创建用户账号
CREATE USER ben IDENTIFIED BY 'benpwd';#IDENTIFIED BY PASSWORD
-- 重命名用户账号
RENAME USER ben TO bend;
-- 删除用户账号
DROP USER bend;
```

**设置访问权限**

```mysql
-- 查看赋予用户账号的权限
SHOW GRANTS FOR username;
```

> 使用GRANT 语句，需要给出：
>
> - 要授予的权限；
> - 被授予访问权限的数据库或表；
> - 用户名

```mysql
GRANT SELECT ON mysql_crash_course.* TO ben; #授予ben用户查询mysql_crash_course库所有表的权限
REVOKE SELECT ON crash_couse.* FROM ben; #收回查询权限
```

**GRANT和REVOKE可在几个层次上控制访问权限：**

- 整个服务器，使用GRANT ALL 和REVOKE ALL
- 整个数据库，使用ON database.* ；
- 特定的表，使用ON database.table ；
- 特定的列；

**可以授予或撤销的权限列表**

|          权限           |                             说明                             |
| :---------------------: | :----------------------------------------------------------: |
|           ALL           |                 除GRANT OPTION 外的所有权限                  |
|          ALTER          |                       使用ALTER TABLE                        |
|      ALTER ROUTINE      |             使用ALTER PROCEDURE 和DROP PROCEDURE             |
|         CREATE          |                       使用CREATE TABLE                       |
|     CREATE ROUTINE      |                     使用CREATE PROCEDURE                     |
| CREATE TEMPORARY TABLES |                  使用CREATE TEMPORARY TABLE                  |
|       CREATE USER       | 使用CREATE USER 、DROP USER 、RENAME USER 和REVOKE ALL PRIVILEGES |
|       CRAETE VIEW       |                       使用CREATE VIEW                        |
|         DELETE          |                          使用DELETE                          |
|          DROP           |                        使用DROP TABLE                        |
|         EXECUTE         |                     使用CALL 和存储过程                      |
|          FILE           |          使用SELECT INTO OUTFILE 和LOAD DATA INFILE          |
|      GRANT OPTION       |                      使用GRANT 和REVOKE                      |
|          INDEX          |                使用CREATE INDEX 和DROP INDEX                 |
|         INSERT          |                          使用INSERT                          |
|       LOCK TABLES       |                       使用LOCK TABLES                        |
|         PROCESS         |                  使用SHOW FULL PROCESSLIST                   |
|         RELOAD          |                          使用FLUSH                           |
|   REPLICATION CLIENT    |                       服务器位置的访问                       |
|    REPLICATION SLAVE    |                        由复制从属使用                        |
|         SELECT          |                          使用SELECT                          |
|     SHOW DATABASES      |                      使用SHOW DATABASES                      |
|        SHOW VIEW        |                     使用SHOW CREATE VIEW                     |
|        SHUTDOWN         |          使用mysqladmin shutdown （用来关闭MySQL）           |
|          SUPER          | 使用CHANGE MASTER 、KILL 、LOGS 、PURGE 、MASTER 和SET GLOBAL 。还允许mysqladmin 调试登录 |
|         UPDATE          |                          使用UPDATE                          |
|          USAGE          |                          无访问权限                          |

**更改口令（密码）**

```mysql
SET PASSWORD FOR ben = Password('new pwd');#更改ben用户的密码
SET PASSWORD = Password('n3w p@$$w0rd');#当不指定用户时，默认修改当前用户的密码
```



#### 二十七、数据库维护

**备份数据**

- 使用命令行实用程序mysqldump 转储所有数据库内容到某个外部文件。在进行常规备份前这个实用程序应该正常运行，以便能正确地备份转储文件。
- 可用命令行实用程序mysqlhotcopy 从一个数据库复制所有数据（并非所有数据库引擎都支持这个实用程序）。
- 可以使用MySQL的BACKUP TABLE 或SELECT INTO OUTFILE 转储所有数据到某个外部文件。这两条语句都接受将要创建的系统文件名，此系统文件必须不存在，否则会出错。数据可以用RESTORE TABLE 来复原。

备份前使用FLUSH TABLES保证数据已经写入磁盘。

**进行数据库维护**

- ANALYZE TABLE ，用来检查表键是否正确。
- CHECK TABLE 用来针对许多问题对表进行检查。在MyISAM 表上还对索引进行检查。CHECKTABLE 支持一系列的用于MyISAM 表的方式。CHANGED 检查自最后一次检查以来改动过的表。EXTENDED 执行最彻底的检查，FAST 只检查未正常关闭的表，MEDIUM 检查所有被删除的链接并进行键检验，QUICK 只进行快速扫描。
- 如果MyISAM 表访问产生不正确和不一致的结果，可能需要用REPAIR TABLE 来修复相应的表。这条语句不应该经常使用，如果需要经常使用，可能会有更大的问题要解决。
- 如果从一个表中删除大量数据，应该使用OPTIMIZE TABLE 来收回所用的空间，从而优化表的性能。

```mysql
ANALYZE TABLE orders;
CHECK TABLE orders, orderitems;
```

**诊断启动问题**

服务器启动问题通常在对MySQL配置或服务器本身进行更改时出现。MySQL在这个问题发生时报告错误，但由于多数MySQL服务器是作为系统进程或服务自动启动的，这些消息可能看不到。
在排除系统启动问题时，首先应该尽量用手动启动服务器。MySQL服务器自身通过在命令行上执行mysqld 启动。下面是几个重要的mysqld 命令行选项：

- --help 显示帮助——一个选项列表
- --safe-mode 装载减去某些最佳配置的服务器
- --verbose 显示全文本消息（为获得更详细的帮助消息与--help 联合使用）
- --version 显示版本信息然后退出

**查看日志文件**

- 错误日志。它包含启动和关闭问题以及任意关键错误的细节。此日志通常名为hostname.err ，位于data 目录中。此日志名可用--log-error 命令行选项更改。
- 查询日志。它记录所有MySQL活动，在诊断问题时非常有用。此日志文件可能会很快地变得非常大，因此不应该长期使用它。此日志通常名为hostname.log ，位于data 目录中。此名字可以用--log 命令行选项更改。
- 二进制日志。它记录更新过数据（或者可能更新过数据）的所有语句。此日志通常名为hostname-bin ，位于data 目录内。此名字可以用--log-bin 命令行选项更改。注意，这个日志文件是MySQL 5中添加的，以前的MySQL版本中使用的是更新日志。
- 缓慢查询日志。顾名思义，此日志记录执行缓慢的任何查询。这个日志在确定数据库何处需要优化很有用。此日志通常名为hostname-slow.log ，位于data 目录中。此名字可以用--log-slow-queries 命令行选项更改。

#### 二十八、改善性能

- 首先，MySQL（与所有DBMS一样）具有特定的硬件建议。在学习和研究MySQL时，使用任何旧的计算机作为服务器都可以。但对用于生产的服务器来说，应该坚持遵循这些硬件建议。
- 一般来说，关键的生产DBMS应该运行在自己的专用服务器上。
- MySQL是用一系列的默认设置预先配置的，从这些设置开始通常是很好的。但过一段时间后你可能需要调整内存分配、缓冲区大小等。（为查看当前设置，可使用SHOW VARIABLES; 和SHOW STATUS; 。）
- MySQL一个多用户多线程的DBMS，换言之，它经常同时执行多个任务。如果这些任务中的某一个执行缓慢，则所有请求都会执行缓慢。如果你遇到显著的性能不良，可使用SHOW PROCESS LIST 显示所有活动进程（以及它们的线程ID和执行时间）。你还可以用KILL 命令终结某个特定的进程（使用这个命令需要作为管理员登录）。
- 总是有不止一种方法编写同一条SELECT 语句。应该试验联结、并、子查询等，找出最佳的方法。
- 使用EXPLAIN 语句让MySQL解释它将如何执行一条SELECT 语句。
- 一般来说，存储过程执行得比一条一条地执行其中的各条MySQL语句快。
- 应该总是使用正确的数据类型。
- 决不要检索比需求还要多的数据。换言之，不要用SELECT* （除非你真正需要每个列）。
- 有的操作（包括INSERT ）支持一个可选的DELAYED 关键字，如果使用它，将把控制立即返回给调用程序，并且一旦有可能就实际执行该操作。
- 在导入数据时，应该关闭自动提交。你可能还想删除索引（包括FULLTEXT 索引），然后在导入完成后再重建它们。
- 必须索引数据库表以改善数据检索的性能。确定索引什么不是一件微不足道的任务，需要分析使用的SELECT 语句以找出重复的WHERE 和ORDER BY 子句。如果一个简单的WHERE 子句返回结果所花的时间太长，则可以断定其中使用的列（或几个列）就是需要索引的对象。
- 你的SELECT 语句中有一系列复杂的OR 条件吗？通过使用多条SELECT 语句和连接它们的UNION 语句，你能看到极大的性能改进。
- 索引改善数据检索的性能，但损害数据插入、删除和更新的性能。如果你有一些表，它们收集数据且不经常被搜索，则在有必要之前不要索引它们。（索引可根据需要添加和删除。）
- LIKE 很慢。一般来说，最好是使用FULLTEXT 而不是LIKE 。
- 数据库是不断变化的实体。一组优化良好的表一会儿后可能就面目全非了。由于表的使用和内容的更改，理想的优化和配置也会改变。
- 最重要的规则就是，每条规则在某些条件下都会被打破。