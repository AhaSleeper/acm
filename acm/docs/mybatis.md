### 一、Mybatis

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
   >     select order_id id, order_no orderno ,order_price price form orders where order_id=#{id}; 
   >  </select>
   > ```
   >
   > 2)通过<resultMap>来映射字段名和实体类属性名的一一对应关系
   >
   > ```xml
   > <select id="getOrder" parameterType="int" resultMap="orderresultmap">
   >      select * from orders where order_id=#{id}
   >  </select>
   > <resultMap type=”me.gacl.domain.order” id=”orderresultmap”> 
   >      <!–用id属性来映射主键字段–> 
   >      <id property=”id” column=”order_id”> 
   >      <!–用result属性来映射非主键字段，property为实体类属性名，column为数据表中的属性–> 
   >      <result property = “orderno” column =”order_no”/> 
   >      <result property=”price” column=”order_price” /> 
   >  </reslutMap>
   > ```

6. 如何获取自动生成的（主）键值？

   > 1）通过LAST_INSERT_ID()获取刚插入记录的自增主键值，在insert语句执行后，执行select last_insert_id()就可以获取自增主键
   >
   > mysql:
   >
   > ```xml
   >  <insert id="insertUser" parameterType="cn.itcast.mybatis.po.User">
   >      <selectKey keyProperty="id" order="AFTER" resultType="int">
   >          select LAST_INSERT_ID()
   >      </selectKey>
   >      INSERT INTO USER(username,birthday,sex,address) VALUES(#{username},#{birthday},#{sex},#{address})
   >  </insert>
   > ```
   >
   > oracle:先查询序列得到主键，将主键设置到user对象中，将user对象插入数据库
   >
   > ```xml
   >  <!-- oracle
   >  在执行insert之前执行select 序列.nextval() from dual取出序列最大值，将值设置到user对象 的id属性
   >   -->
   >  <insert id="insertUser" parameterType="cn.itcast.mybatis.po.User">
   >      <selectKey keyProperty="id" order="BEFORE" resultType="int">
   >          select 序列.nextval() from dual
   >      </selectKey>
   >      
   >      INSERT INTO USER(id,username,birthday,sex,address) VALUES( 序列.nextval(),#{username},#{birthday},#{sex},#{address})
   >  </insert> 
   > ```

7. 在mapper中如何传递多个参数？

   > 第一种：使用占位符的思想
   >
   > - 在映射文件中使用#{0}，#{1}代表传递进来的第几个参数（jdk8可能会有bug）
   > - 使用@param注解来命名参数
   >
   > \#{0},\#{1}方式
   >
   > ```xml
   > //对应的xml,#{0}代表接收的是dao层中的第一个参数，#{1}代表dao层中第二参数，更多参数一致往后加即可。
   > 
   > <select id="selectUser"resultMap="BaseResultMap">  
   >  select *  fromuser_user_t   whereuser_name = #{0} anduser_area=#{1}  
   > </select>  
   > ```
   >
   > ​       @Param注解方式
   >
   > ```java
   > public interface usermapper { 
   >       user selectuser(@param(“username”) string username, 
   >       @param(“hashedpassword”) string hashedpassword); 
   >      }
   > ```
   >
   > ```xml
   > <select id=”selectuser” resulttype=”user”> 
   >       select id, username, hashedpassword 
   >       from some_table 
   >       where username = #{username} 
   >       and hashedpassword = #{hashedpassword} 
   >  </select>
   > 
   > ```
   >
   > 第二种：使用Map集合作为参数来装载
   >
   > ```java
   >   try{
   >          //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
   > 
   > 
   >          /**
   >              * 由于我们的参数超过了两个，而方法中只有一个Object参数收集
   >              * 因此我们使用Map集合来装载我们的参数
   >           */
   >          Map<String, Object> map = new HashMap();
   >          map.put("start", start);
   >          map.put("end", end);
   >          return sqlSession.selectList("StudentID.pagination", map);
   >      }catch(Exception e){
   >          e.printStackTrace();
   >          sqlSession.rollback();
   >          throw e;
   >      }finally{
   >          MybatisUtil.closeSqlSession();
   >      }
   > ```
   >
   > ```xml
   >  <!--分页查询-->
   >  <select id="pagination" parameterType="map" resultMap="studentMap">
   > 
   >      /*根据key自动找到对应Map集合的value*/
   >      select * from students limit #{start},#{end};
   > 
   >  </select>
   > 
   > ```
   >
   > 

8. 模糊查询like语句该怎么写？

   > 1）在java代码中添加sql通配符，
   >
   > 2）sql语句中拼接通配符，但这种做法会引起sql注入。

9. Mybatis动态sql是做什么的？都有哪些动态sql？能简述一下动态sql的执行原理吗？

   > - 动态sql可以让我们在xml映射文件内，以标签的形式编写动态sql，完成逻辑判断和动态拼接sql的功能；
   > - mybatis提供了9种动态sql标签：trim|where|set|foreach|if|choose|when|otherwise|bind
   > - 其执行原理为：使用OGNL从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能

10. Mybatis的xml映射文件中，不同的xml映射文件，id是否可以重复？

    > 如果配置了namespace，那么可以重复，因为我们的statement实际上就是namespace+id。如果没有配置namespace的话，那么相同的id就会导致覆盖。

11. 为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？

    > - hibernate属于全自动ORM映射工具，使用hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
    > - Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以，称为半自动ORM映射工具

12. 一对一、一对多的关联查询？

    一对一：使用association标签（指定id）

    一对多：使用collection标签（指定id)

    ```xml
    <mapper namespace="com.lcb.mapping.userMapper">  
        <!--association  一对一关联查询 -->  
        <select id="getClass" parameterType="int" resultMap="ClassesResultMap">  
            select * from class c,teacher t where c.teacher_id=t.t_id and c.c_id=#{id}  
        </select>  
        <resultMap type="com.lcb.user.Classes" id="ClassesResultMap">  
            <!-- 实体类的字段名和数据表的字段名映射 -->  
            <id property="id" column="c_id"/>  
            <result property="name" column="c_name"/>  
            <association property="teacher" javaType="com.lcb.user.Teacher">  
                <id property="id" column="t_id"/>  
                <result property="name" column="t_name"/>  
            </association>  
        </resultMap>  
    
        <!--collection  一对多关联查询 -->  
        <select id="getClass2" parameterType="int" resultMap="ClassesResultMap2">  
            select * from class c,teacher t,student s where c.teacher_id=t.t_id and c.c_id=s.class_id and c.c_id=#{id}  
        </select>  
        <resultMap type="com.lcb.user.Classes" id="ClassesResultMap2">  
            <id property="id" column="c_id"/>  
            <result property="name" column="c_name"/>  
            <association property="teacher" javaType="com.lcb.user.Teacher">  
                <id property="id" column="t_id"/>  
                <result property="name" column="t_name"/>  
            </association>  
            <collection property="student" ofType="com.lcb.user.Student">  
                <id property="id" column="s_id"/>  
                <result property="name" column="s_name"/>  
            </collection>  
        </resultMap>  
    
    </mapper>  
    ```

13. Mybatis实现一对一有几种方式？具体怎么操作的？

    > 有联合查询和嵌套查询：
    >
    > - 联合查询是几个表联合查询，只查询一次，通过在resultMap里面配置association节点配置一对一的类就可以完成；
    > - 嵌套查询是先查一个表，根据这个表里面的结果的外键id，去在另外一个表里面查询数据，也是通过association配置，但另外一个表的查询通过select属性配置

14. 实现一对多有几种方式，怎么操作的？

    > 有联合查询和嵌套查询：
    >
    > - 联合查询是几个表联合查询，只查询以此，通过在resultMap里面的collection节点配置一对多的类就可以完成；
    > - 嵌套查询是先查一个表，根据这个表里面的结果的外键id，去另外一个表里面查询数据，也是通过配置collection，但另外一个表的查询通过select节点配置。

15. Mybatis的一级、二级缓存？

    > 1） 一级缓存：基于PerpetualCache的HashMap本地缓存，其存储作用域为session，当session flush或close之后，该 session中的所有cache就清空，默认打开一级缓存。
    >
    > 2） 二级缓存与一级缓存其机制相同，默认也是采用perpetualCache, hashMap存储，不同在于其作用域为mapper(namespace)，并且可以自定义存储源，如Ehcache。默认不打开二级缓存，要开启二级缓存，使用二级缓存属性类需要实现serializable序列化接口（可用来保持对象的状态），可在它的映射文件中配置<cache />;
    >
    > 3）对于缓存数据更新机制，当某一个作用域（一级缓存session/二级缓存Namespaces)的进行了C/U/D操作后，默认该作用域下所有select中的缓存将被clear

16. 通常一个xml映射文件，都会写一个Dao接口与之对应，请问，这个dao接口的工作原理是什么？dao接口里的方法，参数不同时，方法能重载吗？

    > - Dao接口，就是人们常说的Mapper接口，接口的全限名，就是映射文件中的namespace的值，接口的方法名，就是映射文件中MappedStatement的id值，接口方法内的参数，就是传递给sql的参数；
    > - Mapper接口没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为key值，可唯一定位一个MappedStatement
    >
    > 举例：
    >
    > ```xml
    > com.mybatis3.mappers.StudentDao.findStudentById，
    > 
    > 可以唯一找到namespace为com.mybatis3.mappers.StudentDao下面id = findStudentById的MappedStatement。在Mybatis中，每一个<select>、<insert>、<update>、<delete>标签，都会被解析为一个MappedStatement对象。
    > 
    > ```
    >
    > Dao接口里面的方法是不能重载的，因为是全限名+方法名的保存和寻找策略。
    >
    > Dao接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Dao接口生成代理proxy对象，代理对象proxy会拦截接口方法，转而执行MappedStatement所代表的sql，然后将sql执行结果返回。

17. Mybatis比IBatis比较大的几个改进是什么？

    > - 有接口绑定，包括注解绑定sql和xml绑定sql；
    > - 动态sql由原来的节点配置编程OGNL表达式；
    > - 在一对一，一对多的时候引进了association，在一对多的时候引入了collection节点，不过都是在resultMap里面配置

18. 接口绑定有几种实现方式，分别是怎么实现的？

    > - 一种是通过注解绑定，就是在接口的方法上面加上@Select @Update等注解里面包含了sql语句来绑定；
    > - 另外一种就是通过xml里面写SQL来绑定，在这种情况下，要指定xml映射文件里面的namespace必须为接口的全路径名

19. 使用Mybatis的mapper接口调用时有哪些要求？

    > - Mapper接口方法名和mapper.xml中定义的每个sql的id相同
    > - Mapper接口中输入的参数类型和mapper.xml中定义的每个sql的ParameterType相同
    > - Mapper接口中输出的参数类型和mapper.xml中定义的每个sql的resultType相同
    > - Mapper.xml文件中的namespace即是接口的类路径

20. Mybatis如何进行分页？分页插件的原理是什么？

    > - 使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页
    > - 可以在sql内直接书写带有物理分页的参数来完成物理分页功能
    > - 还可以使用分页插件来完成物理分页
    >
    > 分页插件的原理是使用Mybatis提供的插件接口，实现自定义插件，在插件的拦截方法内拦截待执行的sql，然后重写sql，根据dialect方言，添加对应的物理分页语句和物理分页参数。
    >
    > 例：
    >
    > select * from student，拦截sql后重写为：select t.* from （select * from student）t limit 0，10

21. Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？

    > - 第一种是使用<resultMap>标签，逐一定义数据库列名和对象属性名之间的映射关系
    > - 第二种是使用sql列的别名功能，将列的别名书写为对象属性名
    >
    > 有了列名与属性名的映射关系后，Mybatis通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。

22. 如何执行批量插入？

    > 首先，创建一个简单的insert语句：
    >
    > ```XML
    >     <insert id=”insertname”> 
    >      insert into names (name) values (#{value}) 
    >     </insert>
    > ```
    >
    > 然后在Java代码中执行批处理插入：
    >
    > ```java
    >     list<string> names = new arraylist(); 
    >     names.add(“fred”); 
    >     names.add(“barney”); 
    >     names.add(“betty”); 
    >     names.add(“wilma”); 
    > 
    >     // 注意这里 executortype.batch 
    >     sqlsession sqlsession = sqlsessionfactory.opensession(executortype.batch); 
    >     try { 
    >      namemapper mapper = sqlsession.getmapper(namemapper.class); 
    >      for (string name : names) { 
    >      mapper.insertname(name); 
    >      } 
    >      sqlsession.commit(); 
    >     } finally { 
    >      sqlsession.close(); 
    >     }
    > ```

23. 简述Mybatis的插件运行原理，以及如何编写一个插件

    > Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4中接口的插件，Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4中接口对象的方法时，就会进入拦截方法，具体就是invocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。
    >
    > 实现Mybatis的Interceptor接口并重写intercept()方法，然后再给插件编写注解，指定要拦截哪一个接口的哪些方法即可，另外，还要在配置文件中配置你编写的插件。

24. Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

    > Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的是一对一，collection指的就是一对多查询。在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。
    >
    > **它的原理是使用CGLIB创建目标对象的代理对象**，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName(),拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b),于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。这就是延迟加载的基本原理。

25. Mybatis都有哪些Executor执行器？它们之间的区别是什么？

    > Mybatis有三种基本的Executor执行器：SimpleExecutor，ReuseExecutor，BatchExecutor.
    >
    > - SimpleExecutor:每执行一次update或select，就开启一个Statement对象，用完立刻关闭statement对象
    > - ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map<String, Statement>内，供下一次使用。简言之，就是重复使用Statement对象。
    > - BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()),等待统一执行（executeBatch())，它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同
    >
    > 作用范围：Executo的这些特点，都严格限制在SqlSession生命周期范围内。

26. Mybatis与hibernate有哪些不同？

    > - Mybatis不完全是一个ORM框架，因为它需要程序员自己编写sql语句，不过Mybatis可以通过xml或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果在映射生成java对象
    > - Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，比如互联网软件，企业运营类软件等，因为这类软件需求变化频繁，一旦需求变化要求成果输出迅速。但是灵活的前提是Mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。
    > - hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是hibernate的缺点是学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎么用好hibernate需要具有很强的经验和能力才行。
    > - 总之，按照用户的需求在有限的资源环境下只要能做出维护性、扩展性良好的软件架构都是好架构，所以框架只有适合才是最好。

