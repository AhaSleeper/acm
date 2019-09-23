1. Mybatis的优点

   > - 基于SQL语句编程，相当灵活，不会对应用程序或者数据库的现有设计造成任何影响，SQL写在XML里，解除sql与程序代码的耦合，便于统一管理；提供xml标签，支持编写动态SQL语句，并可重用；
   > - 与JDBC相比，减少了50%以上的代码量，消除了JDBC大量冗余的代码，不需要手动开关连接；
   > - 很好的与各种数据库兼容（因为Mybatis使用jdbc来连接数据库，所以只要jdbc支持的数据库Mybatis都支持；
   > - 能够与Spring很好的集成；
   > - 提供映射标签，支持对象与数据库的ORM字段关系映射；提供对象关系映射标签，支持对象关系组件维护。

2. Mybatis的缺点

   > - SQL语句的编写工作量较大，尤其当字段多、关联表多时，对开发人员编写SQL语句的功底有一定要求；
   > - SQL语句依赖于数据库，导致数据库移植性差，不能随意更换数据库

3. MyBatis使用场合

   > - Mybatis专注于SQL本身，是一个足够灵活的DAO层解决方案
   > - 对性能的要求很高，或者需求变化较多的项目，比如互联网项目

4. \#{}和${}的区别是什么？

   > - \#{}解析传递进来的参数数据
   > - ${}对传递进来的参数原样拼接在SQL中
   > - \#{}是预编译处理，${}是字符串替换
   > - 使用#{}可以有效防止SQL注入，提高系统安全性

5. 当实体类中的属性名和表中的字段名不一样，怎么办？

   > 1）通过在查询的sql语句中定义字段名的别名，让字段名的别名和实体类的属性名一致
   >
   > ```xml
   > <select id=”selectorder” parametertype=”int” resultetype=”me.gacl.domain.order”> 
   >        select order_id id, order_no orderno ,order_price price form orders where order_id=#{id}; 
   >     </select>
   > ```
   >
   > 2)通过<resultMap>来映射字段名和实体类属性名的一一对应关系
   >
   > ```xml
   >  <select id="getOrder" parameterType="int" resultMap="orderresultmap">
   >         select * from orders where order_id=#{id}
   >     </select>
   >    <resultMap type=”me.gacl.domain.order” id=”orderresultmap”> 
   >         <!–用id属性来映射主键字段–> 
   >         <id property=”id” column=”order_id”> 
   >         <!–用result属性来映射非主键字段，property为实体类属性名，column为数据表中的属性–> 
   >         <result property = “orderno” column =”order_no”/> 
   >         <result property=”price” column=”order_price” /> 
   >     </reslutMap>
   > ```

6. 如何获取自动生成的（主）键值？

   > 1）通过LAST_INSERT_ID()获取刚插入记录的自增主键值，在insert语句执行后，执行select last_insert_id()就可以获取自增主键
   >
   > mysql:
   >
   > ```xml
   >     <insert id="insertUser" parameterType="cn.itcast.mybatis.po.User">
   >         <selectKey keyProperty="id" order="AFTER" resultType="int">
   >             select LAST_INSERT_ID()
   >         </selectKey>
   >         INSERT INTO USER(username,birthday,sex,address) VALUES(#{username},#{birthday},#{sex},#{address})
   >     </insert>
   > ```
   >
   > oracle:先查询序列得到主键，将主键设置到user对象中，将user对象插入数据库
   >
   > ```xml
   >     <!-- oracle
   >     在执行insert之前执行select 序列.nextval() from dual取出序列最大值，将值设置到user对象 的id属性
   >      -->
   >     <insert id="insertUser" parameterType="cn.itcast.mybatis.po.User">
   >         <selectKey keyProperty="id" order="BEFORE" resultType="int">
   >             select 序列.nextval() from dual
   >         </selectKey>
   >         
   >         INSERT INTO USER(id,username,birthday,sex,address) VALUES( 序列.nextval(),#{username},#{birthday},#{sex},#{address})
   >     </insert> 
   > ```

7. 在mapper中如何传递多个参数？

   > 第一种：使用占位符的思想
   >
   > - 在映射文件中使用#{0}，#{1}代表传递进来的第几个参数（jdk8可能会有bug）
   > - 使用@param注解来命名参数
   >
   > \#{0},#{1}方式
   >
   > ```xml
   > //对应的xml,#{0}代表接收的是dao层中的第一个参数，#{1}代表dao层中第二参数，更多参数一致往后加即可。
   > 
   > <select id="selectUser"resultMap="BaseResultMap">  
   >     select *  fromuser_user_t   whereuser_name = #{0} anduser_area=#{1}  
   > </select>  
   > ```
   >
   > ​       @Param注解方式
   >
   > ```java
   >  public interface usermapper { 
   >          user selectuser(@param(“username”) string username, 
   >          @param(“hashedpassword”) string hashedpassword); 
   >         }
   > ```
   >
   > ```xml
   >  <select id=”selectuser” resulttype=”user”> 
   >          select id, username, hashedpassword 
   >          from some_table 
   >          where username = #{username} 
   >          and hashedpassword = #{hashedpassword} 
   >     </select>
   > 
   > ```
   >
   > 第二种：使用Map集合作为参数来装载
   >
   > ```java
   >      try{
   >             //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
   > 
   > 
   >             /**
   >              * 由于我们的参数超过了两个，而方法中只有一个Object参数收集
   >              * 因此我们使用Map集合来装载我们的参数
   >              */
   >             Map<String, Object> map = new HashMap();
   >             map.put("start", start);
   >             map.put("end", end);
   >             return sqlSession.selectList("StudentID.pagination", map);
   >         }catch(Exception e){
   >             e.printStackTrace();
   >             sqlSession.rollback();
   >             throw e;
   >         }finally{
   >             MybatisUtil.closeSqlSession();
   >         }
   > ```
   >
   > ```xml
   >     <!--分页查询-->
   >     <select id="pagination" parameterType="map" resultMap="studentMap">
   > 
   >         /*根据key自动找到对应Map集合的value*/
   >         select * from students limit #{start},#{end};
   > 
   >     </select>
   > 
   > ```
   >
   > 

8. 模糊查询like语句该怎么写？

9. Mybatis动态sql是做什么的？都有哪些动态sql？能简述一下动态sql的执行原理吗？

10. Mybatis的xml映射文件中，不同的xml映射文件，id是否可以重复？

11. 为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？

12. 一对一、一对多的关联查询？

13. Mybatis实现一对一有几种方式？具体怎么操作的？

14. 实现一对多有几种方式，怎么操作的？

15. Mybatis的一级、二级缓存？

16. 通常一个xml映射文件，都会写一个Dao接口与之对应，请问，这个dao接口的工作原理是什么？dao接口里的方法，参数不同时，方法能重载吗？

17. Mybatis比IBatis比较大的几个改进是什么？

18. 接口绑定有几种实现方式，分别是怎么实现的？

19. 使用Mybatis的mapper接口调用时有哪些要求？

20. Mybatis如何进行分页？分页插件的原理是什么？

21. Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？

22. 如何执行批量插入？

23. 简述Mybatis的插件运行原理，以及如何编写一个插件

24. Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

25. Mybatis都有哪些Executor执行器？它们之间的区别是什么？

26. Mybatis与hibernate有哪些不同？

    > - Mybatis不完全是一个ORM框架，因为它需要程序员自己编写sql语句，不过Mybatis可以通过xml或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果在映射生成java对象
    > - Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，比如互联网软件，企业运营类软件等，因为这类软件需求变化频繁，一旦需求变化要求成果输出迅速。但是灵活的前提是Mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。
    > - hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是hibernate的缺点是学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎么用好hibernate需要具有很强的经验和能力才行。
    > - 总之，按照用户的需求在有限的资源环境下只要能做出维护性、扩展性良好的软件架构都是好架构，所以框架只有适合才是最好。

27. 